package mostrar_historico_cursos_inscritos;

import java.util.List;

import javax.swing.JOptionPane;
import util.ApplicationException;
import util.Database;

public class CursosInscritosModelo {
	private Database db=new Database();
	 public List<CursosInscritosDTO> getListaTodosCursos(int numeroColegiado) { 
		 String sql = "SELECT i.idColegiado, c.titulo_curso, c.fecha_inicio_curso, c.fecha_fin_curso, c.duracion, CASE WHEN c.fecha_inicio_curso IS NOT NULL AND c.fecha_fin_curso IS NOT NULL THEN 'Abierto' ELSE 'Cerrado' END AS estado FROM Inscripciones i JOIN Cursos c ON i.idCurso = c.id WHERE i.idColegiado = ? ";
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
		JOptionPane.showMessageDialog(null, "Número de colegiado incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
		throw new ApplicationException("Número de colegiado incorrecto:");
	}}
public void TieneCursos(int id) {
	String sql="SELECT COUNT(*) from Cursos c INNER JOIN Inscripciones i ON i.idCurso=c.id WHERE i.idColegiado=?";
	int numeroCursos=(int)db.executeQueryArray(sql,id).get(0)[0];
	if(numeroCursos==0) {
		JOptionPane.showMessageDialog(null, "No está inscrito a ningún cursos", "Error", JOptionPane.ERROR_MESSAGE);
		throw new ApplicationException("Número de colegiado incorrecto:");
	}
}

}