package cobrarRecibos;

public class RecibosDTO {
private int idRecibo, idColegiado;
private String estado;
private double cuota;
private String nombre;
public int getIdRecibo() {
	return idRecibo;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public RecibosDTO(int id, int idColegiado, String estado, double cuota, String nombre) {
	super();
	this.idRecibo = id;
	this.idColegiado = idColegiado;
	this.estado = estado;
	this.cuota = cuota;
	this.nombre=nombre;
}
public RecibosDTO() {}
public void setIdRecibo(int id) {
	this.idRecibo = id;
}
public int getIdColegiado() {
	return idColegiado;
}
public void setIdColegiado(int idColegiado) {
	this.idColegiado = idColegiado;
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public double getCuota() {
	return cuota;
}
public void setCuota(double cuota) {
	this.cuota = cuota;
}
}
