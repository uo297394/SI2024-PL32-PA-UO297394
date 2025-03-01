package mostrar_historico_cursos_inscritos;

import java.util.List;

import registrarCursos.CursoDisplayDTO;
import util.Database;

public class CursosInscritosModelo {
	private Database db=new Database();
	 public List<CursosInscritosDTO> getListaTodosCursos(int numeroColegiado) { 
	        String sql = "SELECT titulo_curso, fecha_inicio_curso, fecha_fin_curso, duracion FROM Cursos c"
	        		+ "JOIN Inscripciones i ON i.idColegiado=?";
	        return db.executeQueryPojo(CursosInscritosDTO.class, sql, numeroColegiado);
	    }
	    
}