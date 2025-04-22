package pericialesAnuales;


import java.util.Date;

public class PericialInformeDTO {
    private int id;                // ID de la pericial (o de la solicitud, según el diseño)
    private Date fecha_pericial; // Fecha de realización de la pericial
    private String estado;         // Estado: "Pendiente", "Asignada", "Anulado"
    private Integer idPerito;      // ID del perito asignado, puede ser null si no asignada
    private int idSolicitud;       // ID de la solicitud de pericial
    private int idSolicitante;     // ID del solicitante
    private String descripcion;	   // Breve descripción de la solicitud

    // Constructor vacío
    public PericialInformeDTO() { }

    // Constructor con parámetros (opcional)
    public PericialInformeDTO(int id, Date fecha_pericial, String estado, Integer idPerito, int idSolicitud, int idSolicitante, String descripcion) {
        this.id = id;
        this.fecha_pericial = fecha_pericial;
        this.estado = estado;
        this.idPerito = idPerito;
        this.idSolicitud = idSolicitud;
        this.idSolicitante = idSolicitante;
        this.descripcion = descripcion;
    }


	// Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getFechaRealizacion() { return fecha_pericial; }
    public void setFechaRealizacion(Date fechaRealizacion) { this.fecha_pericial = fechaRealizacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getIdPerito() { return idPerito; }
    public void setIdPerito(Integer idPerito) { this.idPerito = idPerito; }

    public int getIdSolicitud() { return idSolicitud; }
    public void setIdSolicitud(int idSolicitud) { this.idSolicitud = idSolicitud; }

    public int getIdSolicitante() { return idSolicitante; }
    public void setIdSolicitante(int idSolicitante) { this.idSolicitante = idSolicitante; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    
  	
}
