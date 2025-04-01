package cobrarRecibos;

import java.util.List;

import enviarSolicitud.ColegiadoDTO;
import util.Database;

public class CobrarRecibosModelo {
	private Database db=new Database();
	public List<RecibosDTO> ColegiadosRecibos(int año) {
		String sql="SELECT c.id as idColegiado, c.nombre,r.id as idRecibo, r.estado, r.cuota FROM Colegiados c LEFT JOIN Recibos r ON(c.id=r.idColegiado AND r.año_emitido=?) ";
		List<RecibosDTO> recibos=db.executeQueryPojo(RecibosDTO.class,sql);
		return recibos;
	}
	public void insertarRecibo(int id, int idColegiado) {
		String sql="INSERT INTO Recibos(id , cuota , estado , año_emitido, idColegiado) VALUES (? ,120,'emitido',2025,?)";
		db.executeUpdate(sql, id, idColegiado);
	}
	private int lastID() {
		String ide = "SELECT COUNT(id) FROM Recibos";
	    Object[] numerorecibos=db.executeQueryArray(ide).get(0);
	    int numrec=(int) numerorecibos[0];
	    return numrec+1;
	    }
}
