package enviarSolicitud;

public class ColegiadoDTO {
private String DNI, nombre, apellido,estado_solicitud;
public ColegiadoDTO() {}
public ColegiadoDTO(String DNI, String nombre, String apellido, String estado_solicitud) {
	this.DNI=DNI;
	this.nombre=nombre;
	this.apellido=apellido;
	this.estado_solicitud=estado_solicitud;
}
public String getEstado_solicitud() {
	return estado_solicitud;
}
public void setEstado_solicitud(String estado_solicitud) {
	this.estado_solicitud = estado_solicitud;
}
public String getDNI() {
	return DNI;
}
public void setDNI(String dNI) {
	DNI =  dNI;
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
