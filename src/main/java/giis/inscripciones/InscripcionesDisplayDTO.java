package giis.inscripciones;
/**
 * Cada una de las filas que muestran al usuario las carreras y su estado
 * IMPORTANTE: Cuando se usan los componentes de Apache Commons DbUtils debe
 * mantenerse de forma estricta el convenio de capitalización de Java:
 *  - Capitalizar todas las palabras que forman un identificador 
 *    excepto la primera letra de nombres de métodos y variables.
 *  - No utilizar subrayados
 * Seguir tambien estos mismos criterios en los nombres de tablas y campos de la BD
 */
public class InscripcionesDisplayDTO {
	private String id;
	private String nombreColegiado;
	private String apellidoColegiado;
	private String tituloCurso;
	private String fechaInscripcion;
	public InscripcionesDisplayDTO() {}
	public InscripcionesDisplayDTO(String id, String nombreColegiado, String apellidoColegiado, String tituloCurso,
			String fechaInscripcion) {
		this.id = id;
		this.nombreColegiado = nombreColegiado;
		this.apellidoColegiado = apellidoColegiado;
		this.tituloCurso = tituloCurso;
		this.fechaInscripcion = fechaInscripcion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombreColegiado() {
		return nombreColegiado;
	}
	public void setNombreColegiado(String nombreColegiado) {
		this.nombreColegiado = nombreColegiado;
	}
	public String getApellidoColegiado() {
		return apellidoColegiado;
	}
	public void setApellidoColegiado(String apellidoColegiado) {
		this.apellidoColegiado = apellidoColegiado;
	}
	public String getTituloCurso() {
		return tituloCurso;
	}
	public void setTituloCurso(String tituloCurso) {
		this.tituloCurso = tituloCurso;
	}
	public String getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	
	
	
	
	//NOTA: se pueden generar getters y setters de forma automatica usando lombok:  
	//https://www.sitepoint.com/declutter-pojos-with-lombok-tutorial/
	//http://www.baeldung.com/intro-to-project-lombok
}
