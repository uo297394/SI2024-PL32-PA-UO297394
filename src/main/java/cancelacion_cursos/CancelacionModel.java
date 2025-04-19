package cancelacion_cursos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import util.Database;
import util.ApplicationException; // Si la usas

public class CancelacionModel {

    private Database db = new Database();

    public void marcarComoCanceladoYAjustarDeuda(int cursoId) throws SQLException, ApplicationException {
        Connection conn = null;
        PreparedStatement pstMarcarCurso = null;
        PreparedStatement pstAjustarDeuda = null;

        String sqlMarcarCurso = "UPDATE Cursos SET cancelado = true, fecha_cancelacion = ?, cancelable = false WHERE id = ?";
        String sqlAjustarDeuda = "UPDATE Inscripciones " +
                                 "SET deuda = COALESCE(deuda, 0) + (" +
                                 "    SELECT c.cuota " +
                                 "    FROM Cuotas c " +
                                 "    WHERE c.idCurso = Inscripciones.idCurso " +
                                 "      AND c.colectivo = Inscripciones.colectivo" +
                                 ") " +
                                 "WHERE idCurso = ? " +
                                 "  AND EXISTS (" +
                                 "    SELECT 1 " +
                                 "    FROM Cuotas c " +
                                 "    WHERE c.idCurso = Inscripciones.idCurso " +
                                 "      AND c.colectivo = Inscripciones.colectivo" +
                                 ")";

        try {
            conn = db.getConnection();
            conn.setAutoCommit(false); // Mantener el control de la transacción

            // 1. Marcar el curso
            pstMarcarCurso = conn.prepareStatement(sqlMarcarCurso);
            pstMarcarCurso.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            pstMarcarCurso.setInt(2, cursoId);
            int affectedRowsCurso = pstMarcarCurso.executeUpdate();

            if (affectedRowsCurso == 0) {
                conn.rollback(); // Deshacer
                throw new ApplicationException("No se encontró el curso con ID " + cursoId + " para marcar como cancelado.");
            }

            // 2. Ajustar deuda de inscripciones
            pstAjustarDeuda = conn.prepareStatement(sqlAjustarDeuda);
            pstAjustarDeuda.setInt(1, cursoId);
            pstAjustarDeuda.executeUpdate(); // Ejecutar aunque no haya inscritos (no dará error)

            // 3. COMMIT
            conn.commit();

        } catch (SQLException e) {
            // ROLLBACK en caso de error SQL
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                // Loggear este error crítico si tienes un logger, si no, printStackTrace es mejor que nada
                ex.printStackTrace();
            }
            // Imprimir el stack trace del error original es útil
            e.printStackTrace();
            throw e; // Relanza la excepción original
        } catch (ApplicationException ae) {
            // ROLLBACK en caso de error lógico (curso no encontrado)
             try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
             throw ae; // Relanzar
        } finally {
            // Cerrar recursos y restaurar autoCommit
            try { if (pstMarcarCurso != null) pstMarcarCurso.close(); } catch (SQLException e) { /* Ignorar o Log */ }
            try { if (pstAjustarDeuda != null) pstAjustarDeuda.close(); } catch (SQLException e) { /* Ignorar o Log */ }
            try {
                if (conn != null) {
                     try { conn.setAutoCommit(true); } catch(SQLException e) { /* Ignorar o Log */ }
                     conn.close();
                }
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}