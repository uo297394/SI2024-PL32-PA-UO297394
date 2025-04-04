package actualizarInscritos;

import java.util.List;
import inscritos_cursos_formacion.InscripcionDisplayDTO;
import util.Database;

/**
 * Esta clase define las consultas y operaciones logicas necesarias para la HU ActualizarInscritos
 */
public class ActualizarInscritosModel {

	private Database db = new Database();
	
	/**
	 * Genera una lista de inscripciones donde se visualizan los datos de la persona inscrita junto a su cuota a pagar y el titulo del curso al que se inscribe
	 * @return la lista de inscripciones
	 */
	public List<InscripcionDisplayDTO> getInscripcionesPorCurso() {
		String sql = "SELECT DISTINCT COALESCE(c.id,o.id) as id, COALESCE(c.nombre,o.nombre) as nombre, COALESCE(c.apellido,o.apellido) as apellido,"
				+ " COALESCE(c.DNI,o.DNI) as DNI, COALESCE(c.telefono,o.telefono) as telefono, COALESCE(c.correo,o.correo) as correo,"
				+ " i.estado,i.fechaInscripcion, ct.cuota,cr.titulo_curso as tituloCurso, i.id as idInsc " +
                "FROM Inscripciones i " +
                "LEFT JOIN Colegiados c ON c.id = i.idColegiado "
                +"LEFT JOIN Otros o ON o.id = i.idOtros " +
                "LEFT JOIN Cuotas ct ON ct.idCurso = i.idCurso "+
                "LEFT JOIN Cursos cr ON cr.id = i.idCurso "+
                "where i.estado = 1 and i.colectivo = ct.colectivo";
		return db.executeQueryPojo(InscripcionDisplayDTO.class, sql);
	}
	/**
	 * Actualiza el estado de una inscripcion
	 * @param aprobado booleano que indica si la inscripcion está aprobada o no
	 * @param DNI DNI de la persona que figura en la inscripcion
	 */
	public void actualizaInscripcion(boolean aprobado, String DNI, String curso) {
		int estado = aprobado? 0 : 2;
		String sql="UPDATE Inscripciones as i SET estado=? WHERE (COALESCE((SELECT DNI FROM Colegiados c WHERE i.idColegiado = c.id),(SELECT DNI FROM Otros o WHERE i.idOtros = o.id)) = ?"
				+ " AND (SELECT id FROM Cursos WHERE titulo_curso = ?) = i.idCurso)";
		db.executeUpdate(sql, estado, DNI,curso);
	}
	
	public List<InscripcionDisplayDTO> getInscripcionesActualizadas(){
		String sql = "SELECT DISTINCT COALESCE(c.id,o.id) as id, COALESCE(c.nombre,o.nombre) as nombre, COALESCE(c.apellido,o.apellido) as apellido,"
				+ " COALESCE(c.DNI,o.DNI) as DNI, COALESCE(c.telefono,o.telefono) as telefono, COALESCE(c.correo,o.correo) as correo,"
				+ " i.estado,i.fechaInscripcion, ct.cuota,cr.titulo_curso as tituloCurso, i.deuda,i.id as idInsc " +
                "FROM Inscripciones i " +
                "LEFT JOIN Colegiados c ON c.id = i.idColegiado "
                +"LEFT JOIN Otros o ON o.id = i.idOtros " +
                "LEFT JOIN Cuotas ct ON ct.idCurso = i.idCurso "+
                "LEFT JOIN Cursos cr ON cr.id = i.idCurso "+
                "where i.colectivo = ct.colectivo";
		return db.executeQueryPojo(InscripcionDisplayDTO.class, sql);
	}
	//TODO MOSTRAR BIEN DEUDAS
	public void actualizaDeuda(String DNI, String concepto, String deuda) {
		db.executeUpdate("UPDATE Inscripciones\r\n"
				+ "SET deuda = ?\r\n"
				+ "WHERE idColegiado IN (SELECT id FROM Colegiados WHERE DNI = ?)\r\n"
				+ "   OR idOtros IN (SELECT id FROM Otros WHERE DNI = ?)\r\n"
				+ "   AND idCurso IN (SELECT id FROM Cursos WHERE titulo_curso = ?)",deuda,DNI,DNI,concepto);
		
	}
	
}
