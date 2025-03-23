package asignarPericiales;



public class SolicitudesDisplayDTO {
    private int id;
    private String estado;
    private int idSolicitante;
    private String caracter;
    private String descripcion;
    
    public SolicitudesDisplayDTO() {
    	
    	
    }

    public SolicitudesDisplayDTO(int id, String estado, int idSolicitante, String caracter, String descripcion) {
        this.id = id;
        this.estado = estado;
        this.idSolicitante = idSolicitante;
        this.caracter = caracter;
        this.descripcion = descripcion;
    }

    // Getters
    public int getId() { return id; }
    public String getEstado() { return estado; }
    public int getIdSolicitante() { return idSolicitante; }
    public String getCaracter() { return caracter; }
    public String getDescripcion() { return descripcion; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setIdSolicitante(int idSolicitante) { this.idSolicitante = idSolicitante; }
    public void setCaracter(String caracter) { this.caracter = caracter; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}

