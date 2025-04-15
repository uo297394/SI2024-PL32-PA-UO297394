package aperturaInscripciones;


/**
 * Clase utilizada para transformar una fila de la base de datos de la tabla Cursos en un Objeto, siendo las columnas los atributos.
 * No se incluyen las fechas de inicio y fin de apertura debido a que siempre serán nulas para las filas que se pretenden mostrar.
 */
public class AperturaInscripcionesDisplayDTO {

	private String id;
	private String tituloCurso;
	private String descripcion;
	private String fechaInicioCurso; 
	private String fechaFinCurso; 
	private String duracion; 
	private String maxPlazas; 
	private String cuotas; 
	private String colectivos;
	private String fechaInicioInscripcion; 
	private String fechaFinInscripcion; 
	private boolean cancelable;
	private boolean cancelado;
	public AperturaInscripcionesDisplayDTO() {}
	public AperturaInscripcionesDisplayDTO(String id, String tituloCurso, String descripcion,
			String fechaInicioCurso, String fechaFinCurso, String duracion, String maxPlazas, String cuotas,
			String colectivos, String fechaInicioInscripcion, String fechaFinInscripcion, boolean cancelable, boolean cancelado) {
		this.id = id;
		this.tituloCurso = tituloCurso;
		this.descripcion = descripcion;
		this.fechaInicioCurso = fechaInicioCurso;
		this.fechaFinCurso = fechaFinCurso;
		this.duracion = duracion;
		this.maxPlazas = maxPlazas;
		this.cuotas = cuotas;
		this.colectivos = colectivos;
		this.fechaInicioInscripcion = fechaInicioInscripcion; 
		this.fechaFinInscripcion = fechaFinInscripcion;
		this.cancelable = cancelable;
		this.cancelado = cancelado;
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
	public String getCuotas() {
		return cuotas;
	}
	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
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
	@Override
	public String toString() {
		return "AperturaInscripcionesDisplayDTO [id=" + id + ", tituloCurso=" + tituloCurso + ", descripcion="
				+ descripcion + ", fechaInicioCurso=" + fechaInicioCurso + ", fechaFinCurso=" + fechaFinCurso
				+ ", duracion=" + duracion + ", maxPlazas=" + maxPlazas + ", cuotas=" + cuotas + ", colectivos="
				+ colectivos + ", fechaInicioInscripcion=" + fechaInicioInscripcion + ", fechaFinInscripcion="
				+ fechaFinInscripcion + "]";
	}
	public boolean isCancelable() {
		return cancelable;
	}
	public void setCancelable(boolean cancelable) {
		this.cancelable = cancelable;
	}
	public boolean isCancelado() {
		return cancelado;
	}
	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}
	
		
	
}
