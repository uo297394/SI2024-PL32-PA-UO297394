package inscribirse_peritos;

public class ColegiadoDisplayDTO {
	private String nombre;
	private String apellido;
	private String direccion;
	private String correo;
	private String telefono;
	private  String dni;
	private String fecha_nacimiento;
	
	
	public ColegiadoDisplayDTO() {
		
		
	}
	public ColegiadoDisplayDTO(String nombre, String apellido, String direccion,String correo,
			String telefono,String dni,String fecha_nacimiento) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.direccion=direccion;
		this.correo=correo;
		this.telefono=telefono;
		this.dni=dni;
		this.fecha_nacimiento=fecha_nacimiento;
	}

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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	

	

}
