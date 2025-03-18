package enviarSolicitud;

import java.util.List;

import util.Database;

public class EnviarSolicitudModelo {
	private Database db=new Database();
	 public List<ColegiadoDTO> getListaColegiados() { 
		 //la consulta toma el dni, nombre y apellidos de los colegiados con una solicitud pendiente
		 String sql = "SELECT c.DNI, c.nombre, c.apellido FROM Colegiados c WHERE c.estado_solicitud = 'pendiente' ";
	        List<ColegiadoDTO> listaCursos=db.executeQueryPojo(ColegiadoDTO.class,sql);
	        return listaCursos;

	    }



}
