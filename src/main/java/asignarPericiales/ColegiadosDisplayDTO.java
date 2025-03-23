package asignarPericiales;

public class ColegiadosDisplayDTO {
	private int id;
	private String nombre;
	private String apellido;
	private String correo;
	private String telefono;
	private int orden_TAP;

	public ColegiadosDisplayDTO() {

	}

	public ColegiadosDisplayDTO(int id, String nombre, String apellido, String correo, String telefono, int orden_TAP) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
		this.orden_TAP = orden_TAP;
	}

	// Getters
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public int getOrden_TAP() {
		return orden_TAP;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setOrden_TAP(int orden_TAP) {
		this.orden_TAP = orden_TAP;
	}
}
