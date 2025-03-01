package mostrar_historico_cursos_inscritos;

public class CursosInscritosDTO {
private String titulo, fechaInicio,fechaFin, estado;
private int horas;
public CursosInscritosDTO() {}
public CursosInscritosDTO(String titulo, String fechaInicio, String fechaFin, String estado, int horas) {
	this.titulo=titulo;
	this.fechaInicio=fechaInicio;
	this.fechaFin=fechaFin;
	this.estado=estado;	
	this.horas=horas;
}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getFechaInicio() {
	return fechaInicio;
}
public void setFechaInicio(String fechaInicio) {
	this.fechaInicio = fechaInicio;
}
public String getFechaFin() {
	return fechaFin;
}
public void setFechaFin(String fechaFin) {
	this.fechaFin = fechaFin;
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public int getHoras() {
	return horas;
}
public void setHoras(int horas) {
	this.horas = horas;
}

}
