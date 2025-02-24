package giis.aperturaInscripciones;

import java.time.LocalDate;
import java.util.*;
import giis.demo.util.Util;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

public class AperturaInscripcionesModel {
	private static final String MSG_FECHAS_NO_VALIDAS = "La fecha de inicio debe ser menor a la fecha de fin";
	private static final String MSG_FECHAS_NULAS = "La fecha de inscripcion no puede ser nula";
	private static final String MSG_FECHA_FIN_POSTERIOR = "La fecha de hoy no puede ser posterior a la de fin";
	private static final String MSG_FECHA_INICIO_POSTERIOR = "La fecha de hoy no puede ser posterior a la de inicio";

	private Database db=new Database();
	
	//SQL para obtener la lista de titulos de los cursos que no han sido planificados
	public static final String SQL_LISTA_CURSOS=
			"SELECT titulo_curso"
			+" from Cursos  where fecha_inicio_inscripcion IS NULL AND fecha_fin_inscripcion IS NULL";
	/**
	 * Obtiene la lista de titulos de curso que o han sido planificados
	 * Implementacion usando la utilidad que obtiene una lista de arrays de objetos 
	 * resultado de la ejecucion de una query sql
	 */
	public List<Object[]> getListaCursosArray() {
		
		return db.executeQueryArray(SQL_LISTA_CURSOS);
	}

	/**
	 * Actualiza las fechas de apertura de un curso
	 */
	public void updateAperturaCurso(String titulo, String inicio, String fin) {
		validateFechasApertura(Util.isoStringToDate(inicio), Util.isoStringToDate(fin));
		String sql="UPDATE Cursos SET fecha_inicio_inscripcion=?, fecha_fin_inscripcion=? WHERE titulo_curso=?";
		db.executeUpdate(sql, inicio, fin, titulo);
	}
	
	private void validateFechasApertura(Date inicio, Date fin) {
		String[] fechaHoy = LocalDate.now().atStartOfDay().toString().split("T");
		validateNotNull(inicio,MSG_FECHAS_NULAS);
		validateNotNull(fin,MSG_FECHAS_NULAS);
		validateCondition(inicio.compareTo(Util.isoStringToDate(fechaHoy[0]))<0, MSG_FECHA_INICIO_POSTERIOR);
		validateCondition(fin.compareTo(Util.isoStringToDate(fechaHoy[0]))>0, MSG_FECHA_FIN_POSTERIOR);
		validateCondition(inicio.compareTo(fin)<=0, MSG_FECHAS_NO_VALIDAS);
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
