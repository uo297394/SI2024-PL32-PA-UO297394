package AlejandroMartín;

import java.util.*;
import util.Util;
import util.Database;
import util.ApplicationException;


public class ModelH3 {
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
