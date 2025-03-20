package inscribirColegiado;

import java.util.*;
import util.Util;
import util.ApplicationException;
import util.Database;

public class InscribirColegiadoModel {
	enum states{ACEPTADO,PENDIENTE, RECHAZADO}
	private static final String MSG_COLEG_INSCR = "El colegiado ya está inscrito, operación de pago cancelada";
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
	 * Comprueba si el colegiado está inscrito al curso
	 * @param idColegiado
	 * @param idCurso
	 * @return
	 */
	private boolean estaInscrito(String idColegiado, String idCurso) {
		String sql="SELECT COUNT(id) FROM Inscripciones WHERE idColegiado = ? AND idCurso = ?";
		Object[] inscr =db.executeQueryArray(sql,idColegiado,idCurso).get(0);
		int estaInscrito = (int)inscr[0];
		return estaInscrito>0;
	}
	private boolean hayPlazas(String idCurso) {
		String sql="SELECT COUNT(id) FROM Inscripciones WHERE idCurso = ?";
		String sql2="SELECT max_plazas FROM Cursos WHERE id = ?";
		Object[] inscr =db.executeQueryArray(sql,idCurso).get(0);
		Object[] maxPlazas = db.executeQueryArray(sql2,idCurso).get(0);
		int nInsc = (int)inscr[0];
		int nPlazas = (int)maxPlazas[0];
		return nPlazas>nInsc;
		
	}
	/**
	 * Inserta la inscripcion del colegiado al curso si no está inscrito
	 */
	public void insertInscColegiado(String idColeg, String idCurso, int estado) {
		states estadoEnum = states.RECHAZADO;
		switch(estado) {
		case 0:
			estadoEnum = states.ACEPTADO;
			break;
		case 1:
			estadoEnum = states.PENDIENTE;
			break;
			
		}
		if(!estaInscrito(idColeg,idCurso) && hayPlazas(idCurso)) {
		String sql="INSERT INTO Inscripciones (id, idColegiado, idCurso, fechaInscripcion, estado)"+
					" VALUES"+
					" (?, ?, ?, ?, ?);";
		int id=lastID();
		db.executeUpdate(sql, id ,idColeg, idCurso, Util.getTodayISO(), estadoEnum);
		}else if(hayPlazas(idCurso))throw new ApplicationException(MSG_COLEG_INSCR);
		else throw new ApplicationException("No hay plazas disponibles para este curso");
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
