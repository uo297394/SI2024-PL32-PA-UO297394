package registrarCursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

public class ControllerH2 {
    private ModelH2 model;
    private ViewH2 view;

    public ControllerH2(ModelH2 model, ViewH2 view) {
        this.model = model;
        this.view = view;
    }

    public void initController() {
        view.getBtnRegistrar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarCurso();
            }
        });
        view.setVisible(true);
    }

    private void registrarCurso() {
        String titulo = view.getTxtTitulo().getText();
        String descripcion = view.getTxtDescripcion().getText();
        String fechaInicioStr = view.getTxtFechaInicio().getText().trim();
        String fechaFinStr = view.getTxtFechaFin().getText().trim();

        // Validación de fechas: formato y lógica
        LocalDate fechaInicio;
        LocalDate fechaFin;
        try {
            fechaInicio = LocalDate.parse(fechaInicioStr); // Se espera formato AAAA-MM-DD
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(view, "El campo 'Fecha de Inicio' debe tener el formato AAAA-MM-DD.", 
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            fechaFin = LocalDate.parse(fechaFinStr);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(view, "El campo 'Fecha de Fin' debe tener el formato AAAA-MM-DD.", 
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validar que la fecha de fin no sea anterior a la fecha de inicio
        if (fechaFin.isBefore(fechaInicio)) {
            JOptionPane.showMessageDialog(view, "La 'Fecha de Fin' no puede ser anterior a la 'Fecha de Inicio'.", 
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validar que la fecha de fin no sea anterior a la fecha mínima (2025-03-07)
        LocalDate fechaMinima = LocalDate.of(2025, 3, 7);
        if (fechaFin.isBefore(fechaMinima)) {
            JOptionPane.showMessageDialog(view, "La 'Fecha de Fin' no puede ser anterior a 2025-03-07.", 
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación de campos numéricos
        int duracion;
        try {
            duracion = Integer.parseInt(view.getTxtDuracion().getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "El campo 'Duración' debe ser un número entero.", 
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int maxPlazas;
        try {
            maxPlazas = Integer.parseInt(view.getTxtMaxPlazas().getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "El campo 'Máximo de Plazas' debe ser un número entero.", 
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double cuota;
        try {
            cuota = Double.parseDouble(view.getTxtCuota().getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "El campo 'Cuota' debe ser un número válido.", 
                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String colectivo = (String) view.getComboColectivos().getSelectedItem();

        // Si todas las validaciones se cumplen, se procede a registrar el curso
        model.registrarCurso(titulo, descripcion, fechaInicioStr, fechaFinStr, duracion, maxPlazas, cuota, colectivo);

        // Mostrar mensaje de confirmación con los datos del curso
        String mensaje = "Curso registrado correctamente:\n" +
                         "Título: " + titulo + "\n" +
                         "Descripción: " + descripcion + "\n" +
                         "Fecha Inicio: " + fechaInicioStr + "\n" +
                         "Fecha Fin: " + fechaFinStr + "\n" +
                         "Duración: " + duracion + " horas\n" +
                         "Máximo de Plazas: " + maxPlazas + "\n" +
                         "Cuota: " + cuota + "\n" +
                         "Colectivo: " + colectivo + "\n" +
                         "Estado: planificado";
        JOptionPane.showMessageDialog(view, mensaje, "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        view.dispose();
    }
}