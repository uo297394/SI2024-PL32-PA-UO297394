package mostrar_historico_cursos_inscritos;

public class CursosInscritosDTO {
private String titulo_curso, fecha_inicio_curso,fecha_fin_curso;
private int duracion;
private String estado;
private String idInsc;
private String fechaMaximaCancelacion;
private String porcentaje;
private String cuota;
public CursosInscritosDTO() {}

public CursosInscritosDTO(String titulo_curso, String fecha_inicio_curso, String fecha_fin_curso, int duracion,
		String estado, String idInsc, String fechaMaximaCancelacion,String porcentaje, String cuota) {
	this.titulo_curso = titulo_curso;
	this.fecha_inicio_curso = fecha_inicio_curso;
	this.fecha_fin_curso = fecha_fin_curso;
	this.duracion = duracion;
	this.estado = estado;
	this.idInsc = idInsc;
	this.fechaMaximaCancelacion = fechaMaximaCancelacion;
	this.porcentaje = porcentaje;
	this.cuota = cuota;
}

public String getCuota() {
	return cuota;
}

public void setCuota(String cuota) {
	this.cuota = cuota;
}

public String getPorcentaje() {
	return porcentaje;
}

public void setPorcentaje(String porcentaje) {
	this.porcentaje = porcentaje;
}

public String getIdInsc() {
	return idInsc;
}

public void setIdInsc(String idInsc) {
	this.idInsc = idInsc;
}

public String getFechaMaximaCancelacion() {
	return fechaMaximaCancelacion;
}

public void setFechaMaximaCancelacion(String fechaMaximaCancelacion) {
	this.fechaMaximaCancelacion = fechaMaximaCancelacion;
}

public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public String getTitulo_curso() {
	return titulo_curso;
}
public void setTitulo_curso(String titulo_curso) {
	this.titulo_curso = titulo_curso;
}
public String getFecha_inicio_curso() {
	return fecha_inicio_curso;
}
public void setFecha_inicio_curso(String fecha_inicio_curso) {
	this.fecha_inicio_curso = fecha_inicio_curso;
}
public String getFecha_fin_curso() {
	return fecha_fin_curso;
}
public void setFecha_fin_curso(String fecha_fin_curso) {
	this.fecha_fin_curso = fecha_fin_curso;
}
public int getDuracion() {
	return duracion;
}
public void setDuracion(int duracion) {
	this.duracion = duracion;
}

}
