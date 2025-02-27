package PabloSuarezVega;

import java.util.List;
import util.ApplicationException;
import util.Database;

public class ModelH2 {
    //instancia Database
    private Database db = new Database();

    // Método para obtener todos los cursos 
    public List<CursoDisplayDTO> getListaTodosCursos() { 
        String sql = "SELECT * FROM Cursos";
        return db.executeQueryPojo(CursoDisplayDTO.class, sql);
    }
    
    public int ultimoID() {
    		String ide = "SELECT COUNT(id) FROM Cursos";
    		Object[] numerocurso=db.executeQueryArray(ide).get(0);
    		int totalcurso=(int) numerocurso[0];
    		return totalcurso+1;
    }
    
    //Nuevo Curso
    public void registrarCurso(String titulo, String descripcion, String fechaInicio, String fechaFin,
                               int duracion, int maxPlazas, double cuota, String colectivo) throws ApplicationException {
        // Como nos dice la historia, hemos de establecer 'planificado' automáticamente al crearlo.
        String sql = "INSERT INTO Cursos (id, titulo_curso, descripcion, fecha_inicio_curso, fecha_fin_curso, " +
                     "duracion, max_plazas, cuota, colectivos, fecha_inicio_inscripcion, fecha_fin_inscripcion) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //La propia database se encarga de actualizar data !!!
        db.executeUpdate(sql, ultimoID(), titulo, descripcion, fechaInicio, fechaFin, duracion, maxPlazas, cuota, colectivo, null, null);
    }
}
