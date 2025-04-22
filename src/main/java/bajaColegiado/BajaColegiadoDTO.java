package bajaColegiado;

public class BajaColegiadoDTO {
	public String getEstado_solicitud() {
		return estado_solicitud;
	}

	public void setEstado_solicitud(String estado_solicitud) {
		this.estado_solicitud = estado_solicitud;
	}

	public BajaColegiadoDTO(int idColegiado, int idRecibo, String nombre, String estado_solicitud, String dNI,
			boolean es_perito, String estado) {
		this.idColegiado = idColegiado;
		this.idRecibo = idRecibo;
		this.nombre = nombre;
		this.estado_solicitud = estado_solicitud;
		DNI = dNI;
		this.es_perito = es_perito;
		this.estado = estado;
	}	

public BajaColegiadoDTO() {}
public int getIdColegiado() {
		return idColegiado;
	}
	public void setIdColegiado(int idColegiado) {
		this.idColegiado = idColegiado;
	}
	public int getIdRecibo() {
		return idRecibo;
	}
	public void setIdRecibo(int idRecibo) {
		this.idRecibo = idRecibo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public boolean isEs_perito() {
		return es_perito;
	}
	public void setEs_perito(boolean es_perito) {
		this.es_perito = es_perito;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
private int idColegiado, idRecibo;
private String nombre, estado_solicitud,DNI;
private boolean es_perito;
private String estado;

}
