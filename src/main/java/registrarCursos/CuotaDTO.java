package registrarCursos;

public class CuotaDTO {
	private int idCuota;
    private String colectivo;
    private double cuota;
    
    public CuotaDTO() {}

    public CuotaDTO(String colectivo, double cuota) {
        this.colectivo = colectivo;
        this.cuota = cuota;
    }

    public String getColectivo() {
        return colectivo;
    }

    public void setColectivo(String colectivo) {
        this.colectivo = colectivo;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }
    
    @Override
    public String toString() {
        return colectivo + " (" + cuota + ")";
    }

	public int getIdCuota() {
		return idCuota;
	}

	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
	}
}