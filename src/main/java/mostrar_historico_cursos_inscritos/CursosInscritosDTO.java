package mostrar_historico_cursos_inscritos;

public class CursosInscritosDTO {
private String titulo_curso, fecha_inicio_curso,fecha_fin_curso;
private int duracion;
public CursosInscritosDTO() {}
public CursosInscritosDTO(String titulo, String fechaInicio, String fechaFin,  int horas) {
	this.titulo_curso=titulo;
	this.fecha_inicio_curso=fechaInicio;
	this.fecha_fin_curso=fechaFin;
	//this.estado=estado;	
	this.duracion=horas;
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
