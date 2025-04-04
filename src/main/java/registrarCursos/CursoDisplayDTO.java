package registrarCursos;

public class CursoDisplayDTO {
	private int id;
    private String titulo_curso;
    private String fecha_inicio_curso;
    private String fecha_fin_curso;
    private String estado;
    private int max_plazas;
    private String colectivos;
    private double cuota;
    private int duracion;
    
    private boolean listaDeEspera;
    private boolean cancelable;
    private String fechaCancelacion;
    private Double porcentajeCuotaDevuelta;

    // Constructor por defecto
    public CursoDisplayDTO() {}

    // Constructor con todos los campos
    public CursoDisplayDTO(int id, String titulo_curso, String fecha_inicio_curso, String fecha_fin_curso,
                             String estado, int max_plazas, String colectivos, double cuota, int duracion) {
    	this.id = id;
        this.titulo_curso = titulo_curso;
        this.fecha_inicio_curso = fecha_inicio_curso;
        this.fecha_fin_curso = fecha_fin_curso;
        this.estado = estado;
        this.max_plazas = max_plazas;
        this.colectivos = colectivos;
        this.cuota = cuota;
        this.duracion= duracion;
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

public String getEstado() {
return estado;
}

public void setEstado(String estado) {
this.estado = estado;
}

public int getMax_plazas() {
return max_plazas;
}

public void setMax_plazas(int max_plazas) {
this.max_plazas = max_plazas;
}

public String getColectivos() {
return colectivos;
}

public void setColectivos(String colectivos) {
this.colectivos = colectivos;
}

public double getCuota() {
return cuota;
}

public void setCuota(double cuota) {
this.cuota = cuota;
}

public int getDuracion() {
	return duracion;
}

public void setDuracion(int duracion) {
	this.duracion = duracion;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public boolean isListaDeEspera() {
	return listaDeEspera;
}

public void setListaDeEspera(boolean listaDeEspera) {
	this.listaDeEspera = listaDeEspera;
}

public boolean isCancelable() {
	return cancelable;
}

public void setCancelable(boolean cancelable) {
	this.cancelable = cancelable;
}

public String getFechaCancelacion() {
	return fechaCancelacion;
}

public void setFechaCancelacion(String fechaCancelacion) {
	this.fechaCancelacion = fechaCancelacion;
}

public Double getPorcentajeCuotaDevuelta() {
	return porcentajeCuotaDevuelta;
}

public void setPorcentajeCuotaDevuelta(Double porcentajeCuotaDevuelta) {
	this.porcentajeCuotaDevuelta = porcentajeCuotaDevuelta;
}
 
    
}