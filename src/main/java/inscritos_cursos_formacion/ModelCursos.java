package inscritos_cursos_formacion;

import java.util.List;
import util.ApplicationException;
import util.Database;

public class ModelCursos {
    //instancia Database
    private Database db = new Database();

    // Método para obtener todos los cursos 
    public List<CursoDisplayDTO2> getListaTodosCursos() { 
        String sql = "SELECT * FROM Cursos";
        return db.executeQueryPojo(CursoDisplayDTO2.class, sql);
    }
    
    public int ultimoID() {
    		String ide = "SELECT COUNT(id) FROM Cursos";
    		Object[] numerocurso=db.executeQueryArray(ide).get(0);
    		int totalcurso=(int) numerocurso[0];
    		return totalcurso+1;
    }
}
