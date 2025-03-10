package enviarSolicitud;

public class ColegiadoDTO {
private String DNI, nombre, apellido;
public ColegiadoDTO() {}
public ColegiadoDTO(String DNI, String nombre, String apellido) {
	this.DNI=DNI;
	this.nombre=nombre;
	this.apellido=apellido;
}
public String getDNI() {
	return DNI;
}
public void setDNI(String dNI) {
	DNI = dNI;
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
}
