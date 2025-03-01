package inscribirColegiado;

import java.util.*;
import util.Util;
import util.ApplicationException;
import util.Database;

public class InscribirColegiadoModel {
	private static final String MSG_FECHAS_NO_VALIDAS = "La fecha de inicio debe ser menor a la fecha de fin";
	private static final String MSG_FECHAS_NULAS = "La fecha de inscripcion no puede ser nula";
	private static final String MSG_FECHA_FIN_POSTERIOR = "La fecha de hoy no puede ser posterior a la de fin";
	private static final String MSG_FECHA_INICIO_POSTERIOR = "La fecha de hoy no puede ser posterior a la de inicio";

	private Database db=new Database();
	
	//SQL para obtener la lista de titulos de los cursos que no han sido planificados
	public static final String SQL_LISTA_CURSOS=
			"SELECT id,titulo_curso as tituloCurso,descripcion,fecha_inicio_curso as fechaInicioCurso,fecha_fin_curso as fechaFinCurso,"
			+ "duracion,max_plazas as maxPlazas,cuota,colectivos,fecha_inicio_inscripcion as fechaInicioInscripcion, fecha_fin_inscripcion as fechaFinInscripcion"
					+" from Cursos AS c  where NOT(fechaInicioInscripcion IS NULL) AND NOT(fechaFinInscripcion IS NULL) AND NOT(fechaInicioInscripcion > ?) AND NOT(fechaFinInscripcion < ?)"
					+ " AND (SELECT COUNT(id) FROM Inscripciones AS i WHERE i.idCurso = c.id) < c.max_plazas";
	/**
	 * Obtiene la lista de curso que han sido planificados y que se encuentran abiertos
	 * Implementacion usando la utilidad que obtiene una lista de arrays de objetos 
	 * resultado de la ejecucion de una query sql
	 */
	public List<InscribirColegiadoDisplayDTO> getListaCursos() {
		return db.executeQueryPojo(InscribirColegiadoDisplayDTO.class, SQL_LISTA_CURSOS, Util.getTodayISO(),Util.getTodayISO());
	}

	/**
	 * Actualiza las fechas de apertura de un curso
	 */
	public void insertInscColegiado(String idColeg, String idCurso) {
		String sql="INSERT INTO Inscripciones (id, idColegiado, idCurso, fechaInscripcion)"+
					" VALUES"+
					" (?, ?, ?, ?);";
		int id=lastID();
		db.executeUpdate(sql, id ,idColeg, idCurso, Util.getTodayISO());
	}
	private int lastID() {
		String ide = "SELECT COUNT(id) FROM Inscripciones";
	    Object[] numerocolegiados=db.executeQueryArray(ide).get(0);
	    int numerocoleg=(int) numerocolegiados[0];
	    return numerocoleg+1;
	    }

	/* De uso general para validacion de objetos */
	private void validateNotNull(Object obj, String message) {
		if (obj==null)
			throw new ApplicationException(message);
	}
	private void validateCondition(boolean condition, String message) {
		if (!condition)
			throw new ApplicationException(message);
	}
	
	
}
