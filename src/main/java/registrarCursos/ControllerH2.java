package registrarCursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            String fechaInicio = view.getTxtFechaInicio().getText();
            String fechaFin = view.getTxtFechaFin().getText();
            int duracion;
            
            try {
                duracion = Integer.parseInt(view.getTxtDuracion().getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "El campo 'Duración' debe ser un número entero.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int maxPlazas;
            
            try {
                maxPlazas = Integer.parseInt(view.getTxtMaxPlazas().getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "El campo 'Máximo de Plazas' debe ser un número entero.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double cuota = Double.parseDouble(view.getTxtCuota().getText());
            String colectivo = (String) view.getComboColectivos().getSelectedItem();

            // Registrar el curso en la base de datos
            model.registrarCurso(titulo, descripcion, fechaInicio, fechaFin, duracion, maxPlazas, cuota, colectivo);

            // Mostrar mensaje de confirmación con los datos del curso
            String mensaje = "Curso registrado correctamente:\n" +
                             "Título: " + titulo + "\n" +
                             "Descripción: " + descripcion + "\n" +
                             "Fecha Inicio: " + fechaInicio + "\n" +
                             "Fecha Fin: " + fechaFin + "\n" +
                             "Duración: " + duracion + " horas\n" +
                             "Máximo de Plazas: " + maxPlazas + "\n" +
                             "Cuota: " + cuota + "\n" +
                             "Colectivo: " + colectivo + "\n" +
                             "Estado: planificado";
            JOptionPane.showMessageDialog(view, mensaje, "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();
    }
}
