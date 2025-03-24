package inscritos_cursos_formacion;

import java.util.List;
import util.Database;

public class ModelInscripciones {
    private Database db = new Database();

    /**
     * Obtiene la lista de personas inscritas en un curso dado.
     * Se unen Colegiados e Inscripciones para obtener el nombre, apellido, DNI y estado_solicitud.
     */
    public List<InscripcionDisplayDTO> getInscripcionesPorCurso(int idCurso) {
        String sql = "SELECT c.nombre, c.apellido, c.DNI, c.telefono, c.correo " +
                     "FROM Colegiados c " +
                     "JOIN Inscripciones i ON c.id = i.idColegiado "+
                     "WHERE i.idCurso = ? AND i.estado = 0";
        return db.executeQueryPojo(InscripcionDisplayDTO.class, sql, idCurso);
    }
}