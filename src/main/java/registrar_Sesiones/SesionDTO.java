
package registrar_Sesiones;

public class SesionDTO {
    private String nombre;
    private String fecha;
    private String horaInicio;
    private int duracion; // duración en horas

    // Constructor por defecto
    public SesionDTO() {}

    // Constructor con todos los campos
    public SesionDTO(String nombre, String fecha, String horaInicio, int duracion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.duracion = duracion;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}