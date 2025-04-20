package mostrar_historico_cursos_inscritos;

import java.util.List;

import javax.swing.JOptionPane;
import util.ApplicationException;
import util.Database;

public class CursosInscritosModelo {
	private Database db=new Database();
	 public List<CursosInscritosDTO> getListaTodosCursos(String DNI) { 
		 String sql = "SELECT COALESCE(o.DNI,cl.DNI), c.titulo_curso, c.fecha_inicio_curso, c.fecha_fin_curso, c.duracion, "
		 		+ "CASE "
		 		+ "WHEN c.fecha_inicio_curso IS NOT NULL AND c.fecha_fin_curso IS NOT NULL "
		 		+ "THEN 'Abierto' "
		 		+ "ELSE 'Cerrado' "
		 		+ "END AS estado, i.id as idInsc, c.fecha_cancelacion as fechaMaximaCancelacion, "
		 		+ "c.porcentaje_cuota_devuelta as porcentaje,ct.cuota "
		 		+ "FROM Inscripciones as i LEFT JOIN Cursos c ON i.idCurso = c.id "
		 		+ "LEFT JOIN Cuotas ct ON c.id = ct.idCurso "
		 		+ "LEFT JOIN Colegiados cl ON cl.id = i.idColegiado "
		 		+ "LEFT JOIN Otros o ON o.id = i.idOtros "
		 		+ "WHERE (o.DNI = ? OR cl.DNI = ?) AND i.colectivo = ct.colectivo AND i.estado = 0";
	        List<CursosInscritosDTO> listaCursos=db.executeQueryPojo(CursosInscritosDTO.class, sql, DNI,DNI);
	        return listaCursos;
	   
	    }
public int getTotalCursos(String DNI) {
	String sql="SELECT COUNT(c.id) FROM Cursos c "
			+ "LEFT JOIN Inscripciones i ON i.idCurso=c.id "
			+ "LEFT JOIN Colegiados cl ON cl.id = i.idColegiado "
			+ "LEFT JOIN Otros o ON o.id = i.idOtros "
			+ "WHERE (o.DNI = ? OR cl.DNI = ?) AND i.estado = 0";
	int total=(int)db.executeQueryArray(sql, DNI,DNI).get(0)[0];
	return total;
}
public int getTotalHoras(String DNI) {
	String sql="SELECT SUM(c.duracion) FROM Cursos c "
			+ "LEFT JOIN Inscripciones i ON i.idCurso=c.id "
			+ "LEFT JOIN Colegiados cl ON cl.id = i.idColegiado "
			+ "LEFT JOIN Otros o ON o.id = i.idOtros "
			+ "WHERE (o.DNI = ? OR cl.DNI = ?) AND i.estado = 0";
	try {
	int total=(int)db.executeQueryArray(sql, DNI,DNI).get(0)[0];
	return total;
	}catch(NullPointerException e) {}
	return 0;
}
public void hayDatos(String DNI) {
	String sql="SELECT COUNT(*) FROM Inscripciones i "
			+ "LEFT JOIN Colegiados cl ON cl.id = i.idColegiado "
			+ "LEFT JOIN Otros o ON o.id = i.idOtros "
			+ "WHERE o.DNI = ? OR cl.DNI = ?";	
	Object[] ocurrencias=db.executeQueryArray(sql,DNI,DNI).get(0);
	int occ=(int) ocurrencias[0];
	if(occ==0) {
		JOptionPane.showMessageDialog(null, "Esta persona no cuenta con ningún curso", "Error", JOptionPane.ERROR_MESSAGE);
		throw new ApplicationException("Esta persona no cuenta con ningún curso:");
		}
	}
public void cancelaInscripcion(String idInsc, String porcentaje,String cuota) {
	String deuda = db.executeQueryArray("SELECT deuda FROM Inscripciones WHERE id = ?", idInsc).get(0)[0].toString();
	Float d = Float.parseFloat(deuda) + (Float.parseFloat(cuota)*Float.parseFloat(porcentaje));
	db.executeUpdate("UPDATE Inscripciones SET estado=5, deuda=? WHERE id = ?", d.toString(),idInsc);
	
}
}