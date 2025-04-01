package cobrarRecibos;

import util.Database;

public class CobrarRecibosModelo {
	private Database db=new Database();
	public void ColegiadosRecibos(int año) {
		String sql="SELECT r.id, r.idColegiado, r.estado, r.cuota FROM Recibos WHERE año = ?";
		
	}
}
