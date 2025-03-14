package registrar_Sesiones;

import util.ApplicationException;
import util.Database;
import java.util.List;

//Esta clase se encarga de menejar las sesiones en general, tanto registrarlas, obtenerlas...
public class ModelSesiones {
    private Database db = new Database();

    //* Obtener las sesiones registradas para un curso (por su id)*/
    public List<SesionDTO> getSesionesByCurso(int cursoId) {
        String sql = "SELECT nombre_sesion AS nombre, fecha_sesion AS fecha, hora_inicio AS horaInicio, duracion " +
                     "FROM Sesiones WHERE idCurso = ?";
        List<SesionDTO> sesiones = db.executeQueryPojo(SesionDTO.class, sql, cursoId);
        return sesiones;
    }
    
  //* Registrar una sesión para un curso*/
    public void registrarSesion(int idCurso, SesionDTO sesion) throws ApplicationException { 
        String sql = "INSERT INTO Sesiones (id, idCurso, nombre_sesion, fecha_sesion, hora_inicio, duracion) " +
                     "VALUES (?, ?, ?, ?, ?, ?)" ;
        int nextId = getNextSesionId();
        db.executeUpdate(sql, nextId, idCurso, sesion.getNombre(), sesion.getFecha(), sesion.getHoraInicio(), sesion.getDuracion());
    }
    
    //* Obtener el siguiente ID para la tabla Sesiones de forma segura*/
    public int getNextSesionId() {
        String sql = "SELECT COALESCE(MAX(id), 0) FROM Sesiones";
        Object[] result = db.executeQueryArray(sql).get(0);
        return ((int) result[0]) + 1;
    }
}