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
        String sql = "SELECT \r\n"
        		+ "    i.id AS id_inscripcion,\r\n"
        		+ "    COALESCE(cl.nombre, o.nombre) AS nombre,\r\n"
        		+ "    COALESCE(cl.apellido, o.apellido) AS apellido,\r\n"
        		+ "    COALESCE(cl.DNI, o.DNI) AS DNI,\r\n"
        		+ "    COALESCE(cl.direccion, o.direccion) AS direccion,\r\n"
        		+ "    COALESCE(cl.correo, o.correo) AS correo,\r\n"
        		+ "    COALESCE(cl.telefono, o.telefono) AS telefono,\r\n"
        		+ "    i.estado AS estado_inscripcion\r\n"
        		+ "FROM Inscripciones i\r\n"
        		+ "LEFT JOIN Colegiados cl ON cl.id = i.idColegiado\r\n"
        		+ "LEFT JOIN Otros o ON o.id = i.idOtros WHERE i.idCurso = ?";
        return db.executeQueryPojo(InscripcionDisplayDTO.class, sql, idCurso);
    }
}