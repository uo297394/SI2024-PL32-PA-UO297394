package bajaColegiado;

import java.util.List;

import cobrarRecibos.RecibosDTO;
import util.Database;

public class BajaColegiadoModelo {
	private Database db=new Database();
	public void cambiarCancelado(int id, String motivos) {
		String sql="UPDATE Colegiados SET estado_solicitud = 'cancelado', motivosCancelacion=?, es_perito=FALSE WHERE id = ?";
		db.executeUpdate(sql,motivos, id);
	}
	public String getDNI(int id) {
		String sql="SELECT DNI FROM Colegiados WHERE id=?";
		String DNI=(String)db.executeQueryArray(sql, id).get(0)[0];
		return DNI;
	}
	public boolean EstaCancelado(int id) {
		String sql="SELECT COUNT(id) FROM Colegiados WHERE id=? AND estado_solicitud = 'cancelado'";
		int cancelados=(int)db.executeQueryArray(sql, id).get(0)[0];
		return cancelados>0;

	}
	public List<BajaColegiadoDTO> Colegiados(int id,int año) {
		String sql="SELECT c.id as idColegiado, c.nombre,c.estado_solicitud, c.es_perito, c.DNI, r.id as idRecibo, r.estado FROM Colegiados c LEFT JOIN Recibos r ON(c.id=r.idColegiado AND r.año_emitido=?) WHERE c.id=? ";
		List<BajaColegiadoDTO> colegiado=db.executeQueryPojo(BajaColegiadoDTO.class,sql,año,id);
		return colegiado;
	}
	public boolean Emitido(int id, int año) {
		String sql="SELECT COUNT(r.id) from Recibos r JOIN Colegiados c ON (c.id=r.idColegiado AND r.año_emitido=?) WHERE r.id=? AND r.estado = 'emitido'";
		int estado=(int) db.executeQueryArray(sql, año,id).get(0)[0];
		return estado>0;
	}
	public boolean EstaColegiado(int id) {
		String sql="SELECT COUNT(id) FROM Colegiados WHERE id=?";
		int colegiado=(int)db.executeQueryArray(sql, id).get(0)[0];
		return colegiado>0;
	}
	public boolean Recibo(int id, int año) {
		String sql="SELECT COUNT(r.id) from Recibos r JOIN Colegiados c ON (c.id=r.idColegiado AND r.año_emitido=?) WHERE c.id=?";
		int estado=(int) db.executeQueryArray(sql, año,id).get(0)[0];
		return estado>0;
	}
}
