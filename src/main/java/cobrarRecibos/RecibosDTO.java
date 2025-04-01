package cobrarRecibos;

public class RecibosDTO {
private int id, idColegiado;
private String estado;
private double cuota;
public int getId() {
	return id;
}
public RecibosDTO(int id, int idColegiado, String estado, double cuota) {
	super();
	this.id = id;
	this.idColegiado = idColegiado;
	this.estado = estado;
	this.cuota = cuota;
}
public RecibosDTO() {}
public void setId(int id) {
	this.id = id;
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
