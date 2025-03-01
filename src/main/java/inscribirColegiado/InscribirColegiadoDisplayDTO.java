package inscribirColegiado;


/**
 * Clase utilizada para transformar una fila de la base de datos de la tabla Cursos en un Objeto, siendo las columnas los atributos.
 */
public class InscribirColegiadoDisplayDTO {

	private String id;
	private String tituloCurso;
	private String descripcion;
	private String fechaInicioCurso; 
	private String fechaFinCurso; 
	private String duracion; 
	private String maxPlazas; 
	private String cuota; 
	private String colectivos;
	private String fechaInicioInscripcion;
	private String fechaFinInscripcion;
	public InscribirColegiadoDisplayDTO() {}
	public InscribirColegiadoDisplayDTO(String id, String tituloCurso, String descripcion, String fechaInicioCurso,
			String fechaFinCurso, String duracion, String maxPlazas, String cuota, String colectivos,
			String fechaInicioInscripcion, String fechaFinInscripcion) {
		this.id = id;
		this.tituloCurso = tituloCurso;
		this.descripcion = descripcion;
		this.fechaInicioCurso = fechaInicioCurso;
		this.fechaFinCurso = fechaFinCurso;
		this.duracion = duracion;
		this.maxPlazas = maxPlazas;
		this.cuota = cuota;
		this.colectivos = colectivos;
		this.fechaInicioInscripcion = fechaInicioInscripcion;
		this.fechaFinInscripcion = fechaFinInscripcion;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public String getMaxPlazas() {
		return maxPlazas;
	}
	public void setMaxPlazas(String max_plazas) {
		this.maxPlazas = max_plazas;
	}
	public String getCuota() {
		return cuota;
	}
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
	public String getColectivos() {
		return colectivos;
	}
	public void setColectivos(String colectivos) {
		this.colectivos = colectivos;
	}
	public String getTituloCurso() {
		return tituloCurso;
	}
	public void setTituloCurso(String tituloCurso) {
		this.tituloCurso = tituloCurso;
	}
	public String getFechaInicioCurso() {
		return fechaInicioCurso;
	}
	public void setFechaInicioCurso(String fechaInicioCurso) {
		this.fechaInicioCurso = fechaInicioCurso;
	}
	public String getFechaFinCurso() {
		return fechaFinCurso;
	}
	public void setFechaFinCurso(String fechaFinCurso) {
		this.fechaFinCurso = fechaFinCurso;
	}
	public String getFechaInicioInscripcion() {
		return fechaInicioInscripcion;
	}
	public void setFechaInicioInscripcion(String fechaInicioInscripcion) {
		this.fechaInicioInscripcion = fechaInicioInscripcion;
	}
	public String getFechaFinInscripcion() {
		return fechaFinInscripcion;
	}
	public void setFechaFinInscripcion(String fechaFinInscripcion) {
		this.fechaFinInscripcion = fechaFinInscripcion;
	}
	
		
	
}
