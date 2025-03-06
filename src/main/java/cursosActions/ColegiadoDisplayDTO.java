package cursosActions;

public class ColegiadoDisplayDTO {

	private String id;
	private String nombre;
	private String apellido;
	private String DNI;
	private String direccion;
	private String correo;
	private String telefono;
	private String fechaNacimiento;
	private String numeroCuenta;
	private String banco;
	private String precolegiados;
	private String estadoSolicitud;
	private String fechaSolicitud;
	private String titulacion;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
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
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getPrecolegiados() {
		return precolegiados;
	}
	public void setPrecolegiados(String precolegiados) {
		this.precolegiados = precolegiados;
	}
	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}
	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public String getTitulacion() {
		return titulacion;
	}
	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}
	public ColegiadoDisplayDTO() {}
	public ColegiadoDisplayDTO(String id, String nombre, String apellido, String dNI, String direccion, String correo,
			String telefono, String fechaNacimiento, String numeroCuenta, String banco, String precolegiados,
			String estadoSolicitud, String fechaSolicitud, String titulacion) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.numeroCuenta = numeroCuenta;
		this.banco = banco;
		this.precolegiados = precolegiados;
		this.estadoSolicitud = estadoSolicitud;
		this.fechaSolicitud = fechaSolicitud;
		this.titulacion = titulacion;
	}
	@Override
	/**
	 * toString necesario para la HU inscribirColegiado
	 */
	public String toString() {
		return "Nombre: "+nombre+"\nApellidos: "+apellido+"\nNumero de colegiado/precolegiado: "+id;
	}
	
}
