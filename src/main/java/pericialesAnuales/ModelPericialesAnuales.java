package pericialesAnuales;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import util.Database;

public class ModelPericialesAnuales {
	
	private Database db;

	public ModelPericialesAnuales() {
		this.db = new Database();
	}
	public List<PericialInformeDTO> obtenerPericialesPorAnioYEstado(Date año, String estadoFiltro) {
        // Formatear el año para obtener sólo los 4 dígitos, por ejemplo: "2025"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String anioStr = sdf.format(año);
        
        // Consulta usando strftime para extraer el año en SQLite
        String sql = "SELECT id AS idSolicitud, idSolicitante, estado, idColegiado AS idPerito, descripcion " +
                     "FROM Periciales " +
                     "WHERE strftime('%Y', fecha_pericial) = ?";
        if (!estadoFiltro.equalsIgnoreCase("todas")) {
            sql += " AND estado = ?";
            return db.executeQueryPojo(PericialInformeDTO.class, sql, anioStr, estadoFiltro);
        } else {
            return db.executeQueryPojo(PericialInformeDTO.class, sql, anioStr);
        }
    }
}
