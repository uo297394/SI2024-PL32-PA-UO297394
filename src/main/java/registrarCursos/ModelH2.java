package registrarCursos;

import java.util.List;
import util.ApplicationException;
import util.Database;

public class ModelH2 {
    // Instancia de Database
    private Database db = new Database();

    // Método para obtener todos los cursos 
    public List<CursoDisplayDTO> getListaTodosCursos() { 
        String sql = "SELECT * FROM Cursos";
        return db.executeQueryPojo(CursoDisplayDTO.class, sql);
    }
    
    public List<CursoDisplayDTO> getAllCursosSesiones() {
        String sql = "SELECT id, titulo_curso, fecha_inicio_curso, fecha_fin_curso, duracion FROM Cursos";
        return db.executeQueryPojo(CursoDisplayDTO.class, sql);
    }
    
    public int ultimoIDCursos() {
        String ide = "SELECT COUNT(id) FROM Cursos";
        Object[] numerocurso = db.executeQueryArray(ide).get(0);
        int totalcurso = (int) numerocurso[0];
        return totalcurso + 1;
    }
    
    public int ultimoIDSesiones() {
        String ide = "SELECT COUNT(id) FROM Sesiones";
        Object[] numerosesion = db.executeQueryArray(ide).get(0);
        int totalsesion = (int) numerosesion[0];
        return totalsesion + 1;
    }
    
    public List<CuotaDTO> getAllCuotas() {
        // Se asume que las cuotas predeterminadas tienen idCurso = 0
        String sql = "SELECT id AS idCuota, cuota, colectivo FROM Cuotas";
        return db.executeQueryPojo(CuotaDTO.class, sql);
    }
    
    public int generarNuevoIDCuotas() {
        String sql = "SELECT COALESCE(MAX(id), 0) FROM Cuotas";
        Object[] result = db.executeQueryArray(sql).get(0);
        return ((int) result[0]) + 1;
    }

    public void registrarCuota(int idCurso, String colectivo, double cuota) throws ApplicationException {
        int newId = generarNuevoIDCuotas();
        String sql = "INSERT INTO Cuotas (id, idCurso, cuota, colectivo) VALUES (?, ?, ?, ?)";
        db.executeUpdate(sql, newId, idCurso, cuota, colectivo);
    }
    
    public int getUltimoIdCursoRegistrado() {
        String sql = "SELECT COALESCE(MAX(id), 0) FROM Cursos";
        Object[] resultado = db.executeQueryArray(sql).get(0);
        return ((int) resultado[0]);
    }
    
    // Nuevo método para registrar un curso sin insertar cuota ni colectivo en la tabla Cursos
    public void registrarCurso(String titulo, String descripcion, String fechaInicio, String fechaFin,
            int duracion, int maxPlazas) throws ApplicationException {
    	String sql = "INSERT INTO Cursos (id, titulo_curso, descripcion, fecha_inicio_curso, fecha_fin_curso, " +
			"duracion, max_plazas, fecha_inicio_inscripcion, fecha_fin_inscripcion) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	db.executeUpdate(sql, ultimoIDCursos(), titulo, descripcion, fechaInicio, fechaFin,
    			duracion, maxPlazas, null, null);
    }
}