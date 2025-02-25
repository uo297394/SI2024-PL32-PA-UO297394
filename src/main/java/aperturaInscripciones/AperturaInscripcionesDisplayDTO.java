package aperturaInscripciones;

public class AperturaInscripcionesDisplayDTO {

	private String id;
	private String titulo_curso;
	private String descripcion;
	private String fecha_inicio_curso; 
	private String fecha_fin_curso; 
	private String duracion; 
	private String max_plazas; 
	private String cuota; 
	private String colectivos;
	public AperturaInscripcionesDisplayDTO() {}
	public AperturaInscripcionesDisplayDTO(String id, String titulo_curso, String descripcion,
			String fecha_inicio_curso, String fecha_fin_curso, String duracion, String max_plazas, String cuota,
			String colectivos) {
		this.id = id;
		this.titulo_curso = titulo_curso;
		this.descripcion = descripcion;
		this.fecha_inicio_curso = fecha_inicio_curso;
		this.fecha_fin_curso = fecha_fin_curso;
		this.duracion = duracion;
		this.max_plazas = max_plazas;
		this.cuota = cuota;
		this.colectivos = colectivos;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo_curso() {
		return titulo_curso;
	}
	public void setTitulo_curso(String titulo_curso) {
		this.titulo_curso = titulo_curso;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFecha_inicio_curso() {
		return fecha_inicio_curso;
	}
	public void setFecha_inicio_curso(String fecha_inicio_curso) {
		this.fecha_inicio_curso = fecha_inicio_curso;
	}
	public String getFecha_fin_curso() {
		return fecha_fin_curso;
	}
	public void setFecha_fin_curso(String fecha_fin_curso) {
		this.fecha_fin_curso = fecha_fin_curso;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public String getMax_plazas() {
		return max_plazas;
	}
	public void setMax_plazas(String max_plazas) {
		this.max_plazas = max_plazas;
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
	
		
	
}
