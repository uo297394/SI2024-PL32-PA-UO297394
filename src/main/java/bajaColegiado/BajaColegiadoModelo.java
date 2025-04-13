package bajaColegiado;

import util.Database;

public class BajaColegiadoModelo {
	private Database db=new Database();
	public void cambiarCancelado(int id, String motivos) {
		String sql="UPDATE Colegiados SET estado_solicitud = 'cancelado', motivosCancelacion=? WHERE id = ?";
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
}
