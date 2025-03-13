package registrar_Sesiones;

import util.ApplicationException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ControllerSesiones {
    private ModelSesiones model;
    private SesionManagerView view;

    public ControllerSesiones(ModelSesiones model, SesionManagerView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        view.setVisible(true);
        // Listener para guardar la sesión de forma inmediata
        view.setGuardarSesionListener(e -> guardarSesion());
        // Cada vez que se selecciona un curso se recargan sus sesiones
        view.setCursoSelectedListener(() -> cargarSesiones());
    }
    
    private void guardarSesion() {
        int cursoId = view.getSelectedCursoId();
        if (cursoId == -1) {
            view.showError("Seleccione un curso primero.");
            return;
        }
        
        // Recuperar datos de la sesión desde el formulario
        SesionDTO sesion = view.getSesionFromForm();
        if (sesion == null) return; // La vista se encarga de mostrar errores de validación
        
        // Validar la fecha de la sesión
        try {
            LocalDate sessionDate = LocalDate.parse(sesion.getFecha());
            LocalDate minDate = LocalDate.of(2025, 3, 7);
            if (sessionDate.isBefore(minDate)) {
                view.showError("La fecha de la sesión no puede ser anterior a 2025/03/07.");
                return;
            }
            LocalDate courseStartDate = view.getSelectedCursoInicio();
            if (courseStartDate != null && sessionDate.isBefore(courseStartDate)) {
                view.showError("La fecha de la sesión no puede ser anterior al inicio del curso (" + courseStartDate + ").");
                return;
            }
            LocalDate courseEndDate = view.getSelectedCursoFin();
            if (courseEndDate != null && sessionDate.isAfter(courseEndDate)) {
                view.showError("La fecha de la sesión no puede ser posterior a la fecha de fin del curso (" + courseEndDate + ").");
                return;
            }
        } catch (DateTimeParseException e) {
            view.showError("Formato de fecha inválido.");
            return;
        }
         
        // Validar que la suma de las duraciones no exceda la duración total del curso
        List<SesionDTO> sesionesRegistradas = model.getSesionesByCurso(cursoId);
        int sumaRegistrada = sesionesRegistradas.stream().mapToInt(SesionDTO::getDuracion).sum();
        int cursoDuracion = view.getSelectedCursoDuracion();
        if (sumaRegistrada + sesion.getDuracion() > cursoDuracion) {
            view.showError("La suma de las sesiones excede la duración total del curso (" + cursoDuracion + " horas).");
            return;
        }
        
        try {
            // Registrar la nueva sesión en la base de datos
            model.registrarSesion(cursoId, sesion);
            view.showMessage("Sesión guardada correctamente.");
            // Refrescar la tabla de sesiones con los datos actualizados
            List<SesionDTO> sesionesActualizadas = model.getSesionesByCurso(cursoId);
            view.refreshSesionesTable(sesionesActualizadas);
            // Limpiar el formulario
            view.clearSesionForm();
        } catch (ApplicationException ex) {
            view.showError("Error al guardar la sesión: " + ex.getMessage());
        }
    }
    
    // Cada vez que se selecciona un curso se recargan las sesiones asociadas
    private void cargarSesiones() {
        int cursoId = view.getSelectedCursoId();
        if (cursoId != -1) {
            List<SesionDTO> sesiones = model.getSesionesByCurso(cursoId);
            view.refreshSesionesTable(sesiones);
        }
    }
}