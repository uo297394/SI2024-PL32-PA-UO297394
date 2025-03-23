package enviarSolicitud;

import java.util.List;

import util.Database;

public class EnviarSolicitudModelo {
	private Database db=new Database();
	 public List<ColegiadoDTO> getListaColegiados() { 
		 //la consulta toma el dni, nombre y apellidos de los colegiados con una solicitud pendiente
		 String sql = "SELECT c.DNI, c.nombre, c.apellido, c.estado_solicitud FROM Colegiados c WHERE c.estado_solicitud = 'pendiente' ";
	        List<ColegiadoDTO> listaCursos=db.executeQueryPojo(ColegiadoDTO.class,sql);
	        return listaCursos;
	    }
	 public List<ColegiadoDTO> getListaColegiadosAceptadosRechazados() { 
		 //la consulta toma el dni, nombre y apellidos de los colegiados con una solicitud pendiente
		 String sql = "SELECT c.DNI, c.nombre, c.apellido, c.estado_solicitud FROM Colegiados c ";
	        List<ColegiadoDTO> listaCursos=db.executeQueryPojo(ColegiadoDTO.class,sql);
	        return listaCursos;
	    }
	 
	 
	 public void cambiarEnviado(String dni) {
			String sql="UPDATE colegiados SET estado_solicitud = 'enviado' WHERE dni = ?";
			db.executeUpdate(sql, dni);
			
		}
//Los siguientes métodos son relativos a la historia Cambiar estado de la solicitud
	 //comprobamos si los dnis de la lista pertenecen a un colegiado
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
		//Se cambia el estado de la solicitud del colegiado a aprobado
		public void cambiarAprobado(String dni) {
			String sql="UPDATE colegiados SET estado_solicitud = 'aprobado' WHERE dni = ?";
			db.executeUpdate(sql, dni);
			
		}
		//Se cambia el estado de la solicitud del colegiado a denegado
		public void cambiarDenegado(String dni) {
			String sql="UPDATE colegiados SET estado_solicitud = 'rechazado' WHERE dni = ?";
			db.executeUpdate(sql, dni);
			
		}
		//Se comprueba que la solicitud esté en estado "enviado"
		public boolean comprobarEnviado(String dni) {
			String sql="SELECT COUNT(*) from Colegiados WHERE DNI = ? AND estado_solicitud='enviado'";
			int estadoenviado=(int)db.executeQueryArray(sql,dni).get(0)[0];
			return estadoenviado>0;
		}
}
