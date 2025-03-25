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
		String sql = "SELECT DISTINCT c.id, c.nombre, c.apellido, c.DNI, c.telefono, c.correo, i.estado,i.fechaInscripcion, ct.cuota,cr.titulo_curso as tituloCurso " +
                "FROM Colegiados c " +
                "JOIN Inscripciones i ON c.id = i.idColegiado " +
                "JOIN Cuotas ct ON ct.idCurso = i.idCurso "+
                "JOIN Cursos cr ON cr.id = i.idCurso "+
                "where i.estado = 1 and i.colectivo = ct.colectivo";
		return db.executeQueryPojo(InscripcionDisplayDTO.class, sql);
	}
	/**
	 * Actualiza el estado de una inscripcion
	 * @param aprobado booleano que indica si la inscripcion está aprobada o no
	 * @param DNI DNI de la persona que figura en la inscripcion
	 */
	public void actualizaInscripcion(boolean aprobado, String DNI) {
		int estado = aprobado? 0 : 2;
		String sql="UPDATE Inscripciones as i SET estado=? WHERE (SELECT DNI FROM Colegiados c WHERE i.idColegiado = c.id) = ?";
		db.executeUpdate(sql, estado, DNI);
	}
	public List<InscripcionDisplayDTO> getInscripcionesActualizadas(){
		String sql = "SELECT DISTINCT c.id, c.nombre, c.apellido, c.DNI, c.telefono, c.correo, i.estado,i.fechaInscripcion, ct.cuota,cr.titulo_curso as tituloCurso " +
                "FROM Colegiados c " +
                "JOIN Inscripciones i ON c.id = i.idColegiado " +
                "JOIN Cuotas ct ON ct.idCurso = i.idCurso "+
                "JOIN Cursos cr ON cr.id = i.idCurso "+
                "and i.colectivo = ct.colectivo";
		return db.executeQueryPojo(InscripcionDisplayDTO.class, sql);
	}
	public void actualizaDeuda(String DNI, String concepto, String deuda) {
		db.executeUpdate("UPDATE Inscripciones SET deuda = ? WHERE (SELECT titulo_curso FROM Cursos cr "
				+ "JOIN Inscripciones i ON cr.id = i.idCurso\r\n"
				+ "JOIN Colegiados cl ON cl.id = i.idColegiado\r\n"
				+ "LEFT JOIN Otros o ON o.id = i.idOtros\r\n"
				+ "WHERE (cl.DNI = ? OR o.DNI = ?) \r\n"
				+ "AND cr.titulo_curso = ?) = ?", deuda,DNI,DNI,concepto,concepto);
		
	}
	
}
