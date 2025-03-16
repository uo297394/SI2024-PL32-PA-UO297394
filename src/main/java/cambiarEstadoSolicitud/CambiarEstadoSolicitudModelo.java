package cambiarEstadoSolicitud;

import javax.swing.JOptionPane;

import util.ApplicationException;
import util.Database;

public class CambiarEstadoSolicitudModelo {
	private Database db=new Database();
	public boolean EstaColegiado(String dni) {
		String sql="SELECT COUNT(*) from Colegiados WHERE DNI = ?";	
		Object[] numerocolegiados=db.executeQueryArray(sql,dni).get(0);
		int numerocoleg=(int) numerocolegiados[0];
	return numerocoleg>0;	
	}
	public boolean estaPendiente(String dni) {
		String sql="SELECT estado_solicitud from Colegiados WHERE DNI = ?";
		String estado=db.executeQueryArray(sql, dni).get(0)[0].toString();
		return estado=="Pendiente";
		
	}
}
