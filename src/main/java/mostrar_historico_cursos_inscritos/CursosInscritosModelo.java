package mostrar_historico_cursos_inscritos;

import java.util.List;

import javax.swing.JOptionPane;

import registrarCursos.CursoDisplayDTO;
import util.ApplicationException;
import util.Database;

public class CursosInscritosModelo {
	private Database db=new Database();
	 public List<CursosInscritosDTO> getListaTodosCursos(int numeroColegiado) { 
		 String sql = "SELECT i.idColegiado, c.titulo_curso, c.fecha_inicio_curso, c.fecha_fin_curso, c.duracion\r\n"
	        		+ "FROM Inscripciones i\r\n"
	        		+ "JOIN Cursos c ON i.idCurso = c.id WHERE i.idColegiado=?"; 
		 //String sql = "SELECT titulo_curso, fecha_inicio_curso, fecha_fin_curso, duracion FROM Cursos c JOIN Inscripciones i ON i.idColegiado=?";
	        List<CursosInscritosDTO> listaCursos=db.executeQueryPojo(CursosInscritosDTO.class, sql, numeroColegiado);
	        return listaCursos;
	   
	    }
public int getTotalCursos(int numeroColegiado) {
	String sql="SELECT COUNT(c.id) FROM Cursos c INNER JOIN Inscripciones i ON i.idCurso=c.id WHERE i.idColegiado=?";
	int total=(int)db.executeQueryArray(sql, numeroColegiado).get(0)[0];
	return total;
}
public int getTotalHoras(int numeroColegiado) {
	String sql="SELECT SUM(c.duracion) FROM Cursos c INNER JOIN Inscripciones i ON i.idCurso=c.id WHERE i.idColegiado=?";
	int total=(int)db.executeQueryArray(sql, numeroColegiado).get(0)[0];
	return total;
}
public void EstaColegiado(int id) {
	String sql="SELECT COUNT(*) from Colegiados WHERE id = ?";	
	Object[] numerocolegiados=db.executeQueryArray(sql,id).get(0);
	int numerocoleg=(int) numerocolegiados[0];
	if(numerocoleg==0) {
		throw new ApplicationException("Número de colegiado incorrecto:");
	}}
}