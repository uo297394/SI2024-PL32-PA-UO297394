package inscribirse_peritos;
import java.util.List;

import util.Database;

public class Model_inscribirse_peritos {
	private Database db = new Database();
	
	public List<ColegiadoDisplayDTO> getDatosPersonales(String id) { 
	    String sql = "SELECT nombre, apellido, direccion, correo, telefono, DNI as dni, fecha_nacimiento " +
	                 "FROM Colegiados WHERE id = ?";
	    return db.executeQueryPojo(ColegiadoDisplayDTO.class, sql, id);
	}
	
	public boolean registrarSolicitudPerito(String id) {
		String sqlCheck = "SELECT COUNT(*) FROM Colegiados WHERE id = ? AND (solicitud_perito = false OR solicitud_perito IS NULL)"
				+ "  AND (es_perito = false OR es_perito IS NULL)";
	    List<Object[]> resultados = db.executeQueryArray(sqlCheck, id);
	    int count = 0;
	    if (!resultados.isEmpty() && resultados.get(0).length > 0 && resultados.get(0)[0] != null) {
	        // Convertimos el valor obtenido a número
	        count = ((Number)resultados.get(0)[0]).intValue();
	    }
	    
	    
	    // Si no es elegible (count == 0), se retorna false para que el controlador muestre un mensaje de error.
	    if (count == 0) {
	        return false;
	    }
	    
	    // Si es elegible, actualizamos la base de datos marcando la solicitud como enviada.
	    String sqlUpdate = "UPDATE Colegiados SET solicitud_perito = true WHERE id = ?";
	    db.executeUpdate(sqlUpdate, id);
	    
	    return true;
	}
	
}
