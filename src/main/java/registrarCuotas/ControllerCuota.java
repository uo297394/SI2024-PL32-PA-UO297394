package registrarCuotas;

import registrarCursos.CursoDisplayDTO;
import registrarCursos.ModelH2;
import util.ApplicationException;

import javax.swing.*;
import java.util.List;

public class ControllerCuota {
    private ModelH2 model;
    private ViewCuota view;
    
    public ControllerCuota(ModelH2 model, ViewCuota view) {
        this.model = model;
        this.view = view;
        initController();
    }
    
    private void initController() {
        // Cargar cursos en la tabla de la vista
        List<CursoDisplayDTO> cursos = model.getAllCursosSesiones();
        view.cargarCursos(cursos);
        
        view.getBtnRegistrarCuota().addActionListener(e -> registrarCuota());
        
        view.setVisible(true);
    }
    
    private void registrarCuota() {
        // Obtener el curso seleccionado
        CursoDisplayDTO curso = view.getCursoSeleccionado();
        if (curso == null) {
            JOptionPane.showMessageDialog(view, "Debe seleccionar un curso.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validar datos de cuota y colectivo
        String colectivo = view.getColectivo();
        String cuotaStr = view.getCuota();
        if (colectivo.isEmpty() || cuotaStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Debe completar los campos de 'Colectivo' y 'Cuota'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double cuota;
        try {
            cuota = Double.parseDouble(cuotaStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "El valor de 'Cuota' debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Registrar la cuota en la base de datos
        try {
            model.registrarCuota(curso.getId(), colectivo, cuota);
            JOptionPane.showMessageDialog(view, "Cuota registrada correctamente para el curso: " + curso.getTitulo_curso(),
                    "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();
        } catch (ApplicationException ex) {
            JOptionPane.showMessageDialog(view, "Error al registrar la cuota: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}