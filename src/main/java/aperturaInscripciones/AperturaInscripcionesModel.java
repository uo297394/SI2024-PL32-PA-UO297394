package aperturaInscripciones;

import java.util.*;

import cursosActions.ColegiadoDisplayDTO;
import util.Util;
import util.ApplicationException;
import util.Database;

public class AperturaInscripcionesModel {
	private static final String MSG_FECHAS_NO_VALIDAS = "La fecha de inicio debe ser menor a la fecha de fin";
	private static final String MSG_FECHAS_NULAS = "La fecha de inscripcion no puede ser nula";
	private static final String MSG_FECHA_FIN_POSTERIOR = "La fecha de hoy no puede ser posterior a la de fin";

	private Database db=new Database();
	
	//SQL para obtener la lista de titulos de los cursos que no han sido planificados
	public static final String SQL_LISTA_CURSOS=
			"SELECT cr.id,cr.titulo_curso as tituloCurso,cr.descripcion,cr.fecha_inicio_curso as fechaInicioCurso,cr.fecha_fin_curso as fechaFinCurso,cr.duracion,cr.max_plazas as maxPlazas,GROUP_CONCAT(ct.cuota) as cuotas,GROUP_CONCAT(ct.colectivo) as colectivos,cr.fecha_inicio_inscripcion as fechaInicioInscripcion, cr.fecha_fin_inscripcion as fechaFinInscripcion"
					+" from Cursos cr "
					+"LEFT JOIN Cuotas ct ON cr.id = ct.idCurso GROUP BY cr.id";
	/**
	 * Obtiene la lista de curso que o han sido planificados
	 * Implementacion usando la utilidad que obtiene una lista de arrays de objetos 
	 * resultado de la ejecucion de una query sql
	 */
	public List<AperturaInscripcionesDisplayDTO> getListaCursos() {
		return db.executeQueryPojo(AperturaInscripcionesDisplayDTO.class, SQL_LISTA_CURSOS);
	}
	public ColegiadoDisplayDTO getColegiado(String idColeg) {
		ColegiadoDisplayDTO col = db.executeQueryPojo(ColegiadoDisplayDTO.class, "SELECT id, nombre, apellido, DNI, direccion, correo, telefono, fecha_nacimiento as fechaNacimiento, numero_cuenta as numeroCuenta, banco, precolegiados, estado_solicitud as estadoSolicitud, fecha_solicitud as fechaSolicitud, titulacion FROM Colegiados WHERE id = ?", idColeg).get(0);
		return col;
		
	}
	/**
	 * Actualiza las fechas de apertura de un curso
	 */
	public void updateAperturaCurso(String titulo, String inicio, String fin) {
		validateFechasApertura(Util.isoStringToDate(inicio), Util.isoStringToDate(fin));
		String sql="UPDATE Cursos SET fecha_inicio_inscripcion=?, fecha_fin_inscripcion=? WHERE titulo_curso=?";
		db.executeUpdate(sql, inicio, fin, titulo);
	}
	/**
	 * Valida las fechas de inicio y fin de inscripcion a un curso
	 * @param inicio fecha de inicio
	 * @param fin fecha de fin
	 */
	private void validateFechasApertura(Date inicio, Date fin) {
		validateNotNull(inicio,MSG_FECHAS_NULAS);
		validateNotNull(fin,MSG_FECHAS_NULAS);
		validateCondition(fin.compareTo(Util.isoStringToDate(Util.getTodayISO()))>=0, MSG_FECHA_FIN_POSTERIOR);
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
