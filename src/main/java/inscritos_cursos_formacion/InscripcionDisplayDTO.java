package inscritos_cursos_formacion;

public class InscripcionDisplayDTO {
	private String id;
    private String nombre;
    private String apellido;
    private String DNI;
    private String estado;
    private String fechaInscripcion;
    private String telefono;
    private String correo;
    private String cuota;
    private String tituloCurso;
    private String deuda;

    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getDNI() {
        return DNI;
    }
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCuota() {
		return cuota;
	}
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
	public String getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public String getTituloCurso() {
		return tituloCurso;
	}
	public void setTituloCurso(String tituloCurso) {
		this.tituloCurso = tituloCurso;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDeuda() {
		return deuda;
	}
	public void setDeuda(String deuda) {
		this.deuda = deuda;
	}
	public InscripcionDisplayDTO() {
		
	}
	public InscripcionDisplayDTO(String id, String nombre, String apellido, String dNI, String estado,
			String fechaInscripcion, String telefono, String correo, String cuota, String tituloCurso, String deuda) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.estado = estado;
		this.fechaInscripcion = fechaInscripcion;
		this.telefono = telefono;
		this.correo = correo;
		this.cuota = cuota;
		this.tituloCurso = tituloCurso;
		this.deuda = deuda;
	}
	@Override
	public String toString() {
		return "InscripcionDisplayDTO [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", DNI=" + DNI
				+ ", estado=" + estado + ", fechaInscripcion=" + fechaInscripcion + ", telefono=" + telefono
				+ ", correo=" + correo + ", cuota=" + cuota + ", tituloCurso=" + tituloCurso + ", deuda=" + deuda + "]";
	}
	
	

	
	
	
	
}