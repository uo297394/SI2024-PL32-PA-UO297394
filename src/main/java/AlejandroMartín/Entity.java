package AlejandroMartín;

public class Entity {
	private String tituloCurso;
    private String fechaInicioCurso;
    private String fechaFinCurso;
    private String estado;
    private int maxPlazas;
    private String colectivos;
    private double cuota;
    
    
    public String getTituloCurso() {
        return tituloCurso;
    }

    public void setTituloCurso(String tituloCurso) {
        this.tituloCurso = tituloCurso;
    }

    public String getFechaInicioCurso() {
        return fechaInicioCurso;
    }

    public void setFechaInicioCurso(String fechaInicioCurso) {
        this.fechaInicioCurso = fechaInicioCurso;
    }

    public String getFechaFinCurso() {
        return fechaFinCurso;
    }

    public void setFechaFinCurso(String fechaFinCurso) {
        this.fechaFinCurso = fechaFinCurso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getMaxPlazas() {
        return maxPlazas;
    }

    public void setMaxPlazas(int maxPlazas) {
        this.maxPlazas = maxPlazas;
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

}
