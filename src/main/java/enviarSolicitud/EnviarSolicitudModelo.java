package enviarSolicitud;

import java.util.List;

import util.Database;

public class EnviarSolicitudModelo {
	private Database db=new Database();
	 public List<ColegiadoDTO> getListaColegiados() { 
		 String sql = "SELECT c.DNI, c.nombre, c.apellido FROM Colegiados c WHERE c.estado_solicitud = 'pendiente' ";
	        List<ColegiadoDTO> listaCursos=db.executeQueryPojo(ColegiadoDTO.class,sql);
	        return listaCursos;

	    }



}
