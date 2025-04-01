package cobrarRecibos;

import java.util.List;

import enviarSolicitud.ColegiadoDTO;
import util.Database;

public class CobrarRecibosModelo {
	private Database db=new Database();
	public List<RecibosDTO> ColegiadosRecibos(int año) {
		String sql="SELECT c.id as idColegiado, c.nombre,r.id, r.estado, r.cuota FROM Colegiados c LEFT JOIN Recibos r ON(c.id=r.idColegiado AND r.año_emitido=?) ";
		List<RecibosDTO> recibos=db.executeQueryPojo(RecibosDTO.class,sql);
		return recibos;
	}
}
