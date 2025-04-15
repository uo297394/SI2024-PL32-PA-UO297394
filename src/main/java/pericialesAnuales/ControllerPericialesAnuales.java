package pericialesAnuales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ControllerPericialesAnuales {
	private ModelPericialesAnuales model;
    private ViewPericialesAnuales view;

    public ControllerPericialesAnuales(ModelPericialesAnuales model, ViewPericialesAnuales view) {
        this.model = model;
        this.view = view;
    }

    public void initController() {
    	iniciar();
        view.getBtnGenerarInforme().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarInforme();
            }
        });
    }

    private void generarInforme() {
        // Obtener el año y el estado seleccionado
        Date año = (Date)view.getSpinnerYear().getValue();
        String estadoFiltro = view.getComboEstado().getSelectedItem().toString();

        // Obtener la lista de periciales (informe) desde el modelo
        List<PericialInformeDTO> lista = model.obtenerPericialesPorAnioYEstado(año, estadoFiltro);
        // Convertir la lista en un arreglo Object[][] para la tabla
        Object[][] data = new Object[lista.size()][5];
        for (int i = 0; i < lista.size(); i++) {
            PericialInformeDTO dto = lista.get(i);
            data[i][0] = dto.getIdSolicitud();
            data[i][1] = dto.getIdSolicitante();
            data[i][2] = dto.getEstado();
            data[i][3] = dto.getIdPerito() == null ? "Sin asignar" : dto.getIdPerito();
            data[i][4] = dto.getDescripcion();
        }

        // Actualizar el modelo de la tabla
        DefaultTableModel tableModel = new DefaultTableModel(data,
                new Object[]{"ID Solicitud", "ID Solicitante", "Estado", "ID Perito", "Descripción"});
        view.getTableInforme().setModel(tableModel);
    }

    public void iniciar() {
        view.setVisible(true);
    }

}
