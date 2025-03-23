package registrarCursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;

import registrar_Sesiones.ModelSesiones;
import util.ApplicationException;

public class ControllerH2 {
    private ModelH2 model;
    private ViewH2 view;
    
    public ControllerH2(ModelH2 model, ViewH2 view) {
        this.model = model;
        this.view = view;
        initController();
    }
    
    public void initController() {   
        // Agregar listener para el botón de registrar curso.
        view.getBtnRegistrar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarCurso();
            }
        });
        
        view.getBtnVerCursos().addActionListener(e -> {
            // Se instancia la nueva vista y su controlador
            registrarCuotas.ViewCuota viewCuota = new registrarCuotas.ViewCuota();
            new registrarCuotas.ControllerCuota(new ModelH2(), viewCuota);
        });
        
        view.getBtnRegistrarSesiones().addActionListener(e -> {
            // Se instancia la nueva vista y su controlador
            registrar_Sesiones.SesionManagerView viewSesiones = new registrar_Sesiones.SesionManagerView();
            new registrar_Sesiones.ControllerSesiones(new ModelSesiones(), viewSesiones);
        });
        
        view.setVisible(true);
    }
    
    
    private void registrarCurso() {
        // Recoger datos del curso desde la vista.
        String titulo = view.getTxtTitulo().getText();
        String descripcion = view.getTxtDescripcion().getText();
        String fechaInicioStr = view.getTxtFechaInicio().getText().trim();
        String fechaFinStr = view.getTxtFechaFin().getText().trim();
        
        // Validar formato de fechas.
        LocalDate fechaInicio;
        LocalDate fechaFin;
        try {
            fechaInicio = LocalDate.parse(fechaInicioStr);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(view, "La 'Fecha de Inicio' debe tener el formato AAAA-MM-DD.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            fechaFin = LocalDate.parse(fechaFinStr);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(view, "La 'Fecha de Fin' debe tener el formato AAAA-MM-DD.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (fechaFin.isBefore(fechaInicio)) {
            JOptionPane.showMessageDialog(view, "La 'Fecha de Fin' no puede ser anterior a la 'Fecha de Inicio'.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validar campos numéricos.
        int duracion;
        try {
            duracion = Integer.parseInt(view.getTxtDuracion().getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "La 'Duración' debe ser un número entero.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int maxPlazas;
        try {
            maxPlazas = Integer.parseInt(view.getTxtMaxPlazas().getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "El 'Máximo de Plazas' debe ser un número entero.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
           
        // Registrar el curso usando el idCuota seleccionado.
        try {
            model.registrarCurso(titulo, descripcion, fechaInicioStr, fechaFinStr, 
                                 duracion, maxPlazas);
        } catch (ApplicationException ex) {
            JOptionPane.showMessageDialog(view, "Error al registrar el curso: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Mostrar mensaje de confirmación con la cuota y el colectivo (en lugar del idCuota)
        String mensaje = "Curso registrado correctamente:\n" +
                         "Título: " + titulo + "\n" +
                         "Descripción: " + descripcion + "\n" +
                         "Fecha Inicio: " + fechaInicioStr + "\n" +
                         "Fecha Fin: " + fechaFinStr + "\n" +
                         "Duración: " + duracion + " horas\n" +
                         "Máximo de Plazas: " + maxPlazas;
        JOptionPane.showMessageDialog(view, mensaje, "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        
        view.dispose();
    }
}