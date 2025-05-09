package inscribirColegiado;

import java.util.*;

import cursosActions.ColegiadoDisplayDTO;
import util.Util;
import util.ApplicationException;
import util.Database;
import util.SwingUtil;


public class InscribirColegiadoModel {
	private static final String MSG_COLEG_INSCR = "El usuario ya está inscrito";
	private Database db=new Database();
	
	//SQL para obtener la lista de titulos de los cursos que no han sido planificados
	public static final String SQL_LISTA_CURSOS=
			"SELECT id,titulo_curso as tituloCurso,descripcion,fecha_inicio_curso as fechaInicioCurso,fecha_fin_curso as fechaFinCurso,"
			+ "duracion,max_plazas as maxPlazas,fecha_inicio_inscripcion as fechaInicioInscripcion, fecha_fin_inscripcion as fechaFinInscripcion"
					+" from Cursos AS c";
	/**
	 * Obtiene la lista de curso que han sido planificados y que se encuentran abiertos
	 * Implementacion usando la utilidad que obtiene una lista de arrays de objetos 
	 * resultado de la ejecucion de una query sql
	 */
	public List<InscribirColegiadoDisplayDTO> getListaCursos() {
		return db.executeQueryPojo(InscribirColegiadoDisplayDTO.class, SQL_LISTA_CURSOS);
	}
	/**
	 * Comprueba si el colegiado está inscrito al curso
	 * @param idColegiado
	 * @param idCurso
	 * @return
	 */
	public boolean estaInscrito(String idColegiado, String idCurso) {
		String sql="SELECT COUNT(id) FROM Inscripciones WHERE idColegiado = ? AND idCurso = ?";
		Object[] inscr =db.executeQueryArray(sql,idColegiado,idCurso).get(0);
		int estaInscrito = (int)inscr[0];
		if(estaInscrito>0)throw new ApplicationException(MSG_COLEG_INSCR);
		return estaInscrito>0;
	}
	public boolean hayPlazas(String idCurso) {
		String sql="SELECT COUNT(id) FROM Inscripciones WHERE idCurso = ? AND (estado = 1 OR estado = 0)";
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
	public void insertInscColegiado(String idColeg, String idCurso, int estado, String colectivo) {
		if(hayPlazas(idCurso) || estado == 4) {
			String sql;
			if(!idColeg.matches(".*[a-zA-Z]"))
				sql="INSERT INTO Inscripciones (id, idColegiado, idCurso, fechaInscripcion, estado, colectivo)"+
							" VALUES"+
							" (?, ?, ?, ?, ?, ?);";
			else sql="INSERT INTO Inscripciones (id, idOtros, idCurso, fechaInscripcion, estado, colectivo)"+
					" VALUES"+
					" (?, (SELECT id FROM Otros WHERE DNI = ?), ?, ?, ?, ?);";
			int id=lastID();
			db.executeUpdate(sql, id ,idColeg, idCurso, Util.getTodayISO(), estado, colectivo);
		}
		else throw new ApplicationException("No hay plazas disponibles para este curso");
	}
	public boolean listaEspera(String idCurso) {
		String sql="SELECT lista_espera FROM Cursos WHERE id = ?";
		String espera =db.executeQueryArray(sql,idCurso).get(0)[0].toString();
		return espera.equals("1");
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
	public ColegiadoDisplayDTO buscaPersona(String dNI) {
		String ide = "SELECT nombre, apellido,direccion, correo, telefono, fecha_nacimiento as fechaNacimiento FROM Otros WHERE DNI = ?";
		List<ColegiadoDisplayDTO> persona=db.executeQueryPojo(ColegiadoDisplayDTO.class,ide,dNI);
		if(persona.size() == 0)return null;
	    return persona.get(0);
	}
	public ColegiadoDisplayDTO buscaColegiado(String numCol) {
		String ide = "SELECT nombre, apellido,direccion, correo, telefono, fecha_nacimiento as fechaNacimiento FROM Colegiados WHERE id = ?";
		List<ColegiadoDisplayDTO> persona=db.executeQueryPojo(ColegiadoDisplayDTO.class,ide,numCol);
		if(persona.size() == 0)return null;
	    return persona.get(0);
	}
	public boolean estaInscritoOtro(String dni, String id) {
		String sql="SELECT COUNT(i.id) FROM Inscripciones i JOIN Otros o ON i.idOtros = o.id WHERE o.DNI = ? AND idCurso = ?";
		Object[] inscr =db.executeQueryArray(sql,dni,id).get(0);
		int estaInscrito = (int)inscr[0];
		if(estaInscrito>0)throw new ApplicationException(MSG_COLEG_INSCR);
		return estaInscrito>0;
	}
	public void guardaDatosOtro(List<String> l) {
		String sql="INSERT INTO Otros (id, nombre, apellido, direccion, correo, telefono, fecha_nacimiento, DNI)"+
				" VALUES"+
				" (?, ?, ?, ?, ?, ?, ?, ?);";
		int id=lastIDOtros();
		db.executeUpdate(sql,id, l.get(0),l.get(1),l.get(2),l.get(3),l.get(4),l.get(5),l.get(6));
		
	}
	private int lastIDOtros() {
		String ide = "SELECT COUNT(id) FROM Otros";
	    Object[] numerocolegiados=db.executeQueryArray(ide).get(0);
	    int numerocoleg=(int) numerocolegiados[0];
	    return numerocoleg+1;
	    }
	
	
}
