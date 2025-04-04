package cobrarRecibos;

import java.util.List;

import enviarSolicitud.ColegiadoDTO;
import util.Database;

public class CobrarRecibosModelo {
	private Database db=new Database();
	public List<RecibosDTO> ColegiadosRecibos(int año) {
		String sql="SELECT c.id as idColegiado, c.nombre,r.id as idRecibo, r.estado, r.cuota FROM Colegiados c LEFT JOIN Recibos r ON(c.id=r.idColegiado AND r.año_emitido=?) ";
		List<RecibosDTO> recibos=db.executeQueryPojo(RecibosDTO.class,sql,año);
		return recibos;
	}
	public void insertarRecibo(int id, int idColegiado) {
		String sql="INSERT INTO Recibos(id , cuota , estado , año_emitido, idColegiado) VALUES (? ,120,'emitido',2025,?)";
		db.executeUpdate(sql, id, idColegiado);
	}
	public int lastID() {
		String ide = "SELECT COUNT(id) FROM Recibos";
	    Object[] numerorecibos=db.executeQueryArray(ide).get(0);
	    int numrec=(int) numerorecibos[0];
	    return numrec+1;
	    }
	public boolean Recibo(int id, int año) {
		String sql="SELECT COUNT(r.id) from Recibos r JOIN Colegiados c ON (c.id=r.idColegiado AND r.año_emitido=?) WHERE c.id=?";
		int estado=(int) db.executeQueryArray(sql, año,id).get(0)[0];
		return estado>0;
	}
	public boolean Emitido(int id, int año) {
		String sql="SELECT COUNT(r.id) from Recibos r JOIN Colegiados c ON (c.id=r.idColegiado AND r.año_emitido=?) WHERE c.id=? AND r.estado = 'emitido'";
		int estado=(int) db.executeQueryArray(sql, año,id).get(0)[0];
		return estado>0;
	}
	
	/*private List<Object[]> datosColegiado(){
		String sql="Select ";
	}**/
}
