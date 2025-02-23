package AlejandroMartín;

import java.util.*;
import giis.demo.util.Util;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

public class Model {
	private Database db= new Database();
	
	public List<CursoDisplayDTO> getListaCursos(String colec) { 
	    
	    String sql = "SELECT * FROM Cursos WHERE colectivos = ? AND fecha_inicio_curso IS NOT NULL";
	    
	    return db.executeQueryPojo(CursoDisplayDTO.class, sql,colec);
	}
	
public List<CursoDisplayDTO> getListaTodosCursos() { 
	    
	    String sql = "SELECT * FROM Cursos";
	    
	    return db.executeQueryPojo(CursoDisplayDTO.class, sql);
	}
}
