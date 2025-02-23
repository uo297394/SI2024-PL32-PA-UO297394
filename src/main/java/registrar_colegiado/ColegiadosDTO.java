package registrar_colegiado;

public class ColegiadosDTO {
private String nombre, apellidos, DNI, dirección, cuenta_bancaria, titulación;

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getApellidos() {
	return apellidos;
}

public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}

public String getDNI() {
	return DNI;
}

public void setDNI(String dNI) {
	DNI = dNI;
}

public String getDirección() {
	return dirección;
}

public void setDirección(String dirección) {
	this.dirección = dirección;
}

public String getCuenta_bancaria() {
	return cuenta_bancaria;
}

public void setCuenta_bancaria(String cuenta_bancaria) {
	this.cuenta_bancaria = cuenta_bancaria;
}

public String getTitulación() {
	return titulación;
}

public void setTitulación(String titulación) {
	this.titulación = titulación;
}
public ColegiadosDTO() {
	
}
public ColegiadosDTO(String nombre, String apellidos, String DNI, String dirección, String cuenta_bancaria, String titulación) {
	this.nombre=nombre;
	this.apellidos=apellidos;
	this.DNI=DNI;
	this.dirección=dirección;
	this.cuenta_bancaria=cuenta_bancaria;
	this.titulación=titulación;
}
}

