package actualizarInscritos;

import java.util.List;
import inscritos_cursos_formacion.InscripcionDisplayDTO;
import util.Database;

public class ActualizarInscritosModel {

	private Database db = new Database();
	
	public List<InscripcionDisplayDTO> getInscripcionesPorCurso() {
		String sql = "SELECT c.nombre, c.apellido, c.DNI, c.telefono, c.correo, i.estado,i.fechaInscripcion, ct.cuota " +
                "FROM Colegiados c " +
                "JOIN Inscripciones i ON c.id = i.idColegiado " +
                "JOIN Cuotas ct ON ct.idCurso = i.idCurso where i.estado = 1";
		return db.executeQueryPojo(InscripcionDisplayDTO.class, sql);
	}
	public void actualizaInscripcion(boolean aprobado, String DNI) {
		int estado = aprobado? 0 : 2;
		String sql="UPDATE Inscripciones SET estado=? WHERE DNI = ?";
		db.executeUpdate(sql, estado, DNI);
	}
	
}
