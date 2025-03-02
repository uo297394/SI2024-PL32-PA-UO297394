package mostrar_historico_cursos_inscritos;

import java.util.List;

import registrarCursos.CursoDisplayDTO;
import util.Database;

public class CursosInscritosModelo {
	private Database db=new Database();
	 public List<CursosInscritosDTO> getListaTodosCursos(int numeroColegiado) { 
		 String sql = "SELECT i.idColegiado, c.titulo_curso, c.fecha_inicio_curso, c.fecha_fin_curso, c.duracion\r\n"
	        		+ "FROM Inscripciones i\r\n"
	        		+ "JOIN Cursos c ON i.idCurso = c.id WHERE i.idColegiado=?"; 
		 //String sql = "SELECT titulo_curso, fecha_inicio_curso, fecha_fin_curso, duracion FROM Cursos c JOIN Inscripciones i ON i.idColegiado=?";
	        List<CursosInscritosDTO> listaCursos=db.executeQueryPojo(CursosInscritosDTO.class, sql, numeroColegiado);
	        
	        System.out.println("Número de cursos encontrados: " + listaCursos.size());
	        for (CursosInscritosDTO curso : listaCursos) {
	            System.out.println("Curso: " + curso.getTitulo_curso() + ", Inicio: " + curso.getFecha_inicio_curso() + 
	                               ", Fin: " + curso.getFecha_fin_curso() + ", Horas: " + curso.getDuracion());
	        }
	        return listaCursos;
	   
	    }

	    
}