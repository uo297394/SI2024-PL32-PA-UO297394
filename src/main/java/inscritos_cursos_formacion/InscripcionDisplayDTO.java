package inscritos_cursos_formacion;

public class InscripcionDisplayDTO {
    private String nombre;
    private String apellido;
    private String DNI;
    private String estado;
    private String fechaInscripcion;
    private String telefono;
    private String correo;
    private String cuota;

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
	public InscripcionDisplayDTO(String nombre, String apellido, String dNI, String estado, String telefono,
			String correo, String cuota, String fechaInscripcion) {
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.estado = estado;
		this.telefono = telefono;
		this.correo = correo;
		this.cuota = cuota;
		this.fechaInscripcion = fechaInscripcion;
	}
	public InscripcionDisplayDTO() {
		
	}
	
	
}