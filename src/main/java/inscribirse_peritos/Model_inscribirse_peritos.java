package inscribirse_peritos;
import java.util.List;

import util.Database;
import visualizar_cursos.CursoDisplayDTO;

public class Model_inscribirse_peritos {
	private Database db = new Database();
	
	public List<ColegiadoDisplayDTO> getDatosPersonales(String id) { 
	    String sql = "SELECT nombre, apellido, direccion, correo, telefono, DNI as dni, fecha_nacimiento " +
	                 "FROM Colegiados WHERE id = ?";
	    return db.executeQueryPojo(ColegiadoDisplayDTO.class, sql, id);
	}
	
}
