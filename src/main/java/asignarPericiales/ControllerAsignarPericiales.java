package asignarPericiales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControllerAsignarPericiales {

	private ModelAsignarPericiales model;
	private ViewAsignarPericiales view;

	public ControllerAsignarPericiales(ModelAsignarPericiales model, ViewAsignarPericiales view) {
		this.model = model;
		this.view = view;
	}

	public void initController() {
		initView();
		// Cargar datos en las tablas al inicio

		actualizarVista();

		// Manejar la selección de filas en tabla de solicitudes
		view.getScrollListaSolicitudes().getViewport().getView().addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				JTable tabla = (JTable) view.getScrollListaSolicitudes().getViewport().getView();
				int filaSeleccionada = tabla.getSelectedRow();
				if (filaSeleccionada != -1) {
					Object idSolicitud = tabla.getValueAt(filaSeleccionada, 0);
					view.getTxtIdSolicitud().setText(idSolicitud.toString());
					Object estadoSolicitud= tabla.getValueAt(filaSeleccionada,1);
					view.getEstadoActual().setText(estadoSolicitud.toString());
				}
			}
		});

		view.getCambioEstado().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cambioEstado();
			}
		});

		// Acción del botón "Asignar"
		view.getBtnAsignar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				asignarPerito();
			}
		});
	}
	private void cambioEstado() {
		String idSolicitud = view.getTxtIdSolicitud().getText();
		String estadoActual= view.getEstadoActual().getText();
		String estadoNuevo= view.getEstadoNuevo().getSelectedItem().toString();
		String justificacion=view.getJustificacion().getText();		
		if(idSolicitud.isEmpty() || estadoActual.isEmpty() || estadoNuevo.isEmpty() || justificacion.isEmpty()) {
			javax.swing.JOptionPane.showMessageDialog(view, "Debe seleccionar una solicitud, un estado nuevo y justificar el cambio", "Error",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(estadoActual.equals(estadoNuevo)) {
			javax.swing.JOptionPane.showMessageDialog(view, "El nuevo estado debe de ser distinto del actual", "Error",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return;
		}
		else {
			model.cambiarEstadoSolicitud(estadoNuevo,justificacion, idSolicitud);
			actualizarVista();
			String mensaje = "El cambio de estado ha sido realizado correctamente:\n" +
                    "idSolicitud: " + idSolicitud + "\n" +
                    "estado anterior: " + estadoActual + "\n" +
                    "estado nuevo: " + estadoNuevo + "\n" +
                    "Justificacion de cambio de estado: " + justificacion + "\n";

            JOptionPane.showMessageDialog(view, mensaje, "Justificante cambio de estado", JOptionPane.INFORMATION_MESSAGE);
            view.getJustificacion().setText("");
		}
		
	}

	// Método para asignar un perito a una solicitud
	private void asignarPerito() {
		try {
			String idSolicitudText = view.getTxtIdSolicitud().getText();
			String idPeritoText = view.getTxtIdPerito().getText();
			String estadoActual = view.getEstadoActual().getText();
			
			if (idSolicitudText.isEmpty() || idPeritoText.isEmpty()) {
				javax.swing.JOptionPane.showMessageDialog(view, "Debe seleccionar una solicitud y un perito", "Error",
						javax.swing.JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (!estadoActual.equalsIgnoreCase("pendiente")) {
				JOptionPane.showMessageDialog(view, "Solo se pueden asignar peritos a solicitudes en estado 'pendiente'.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int idSolicitud = Integer.parseInt(idSolicitudText);
			int idPerito = Integer.parseInt(idPeritoText);

			boolean asignado = model.asignarPerito(idSolicitud, idPerito);
			if (asignado) {
				actualizarVista();
				String mensaje = "El perito ha sido correctamente asignado a la solicitud seleccionada:\n";

	            JOptionPane.showMessageDialog(view, mensaje, "Justificante de asignación", JOptionPane.INFORMATION_MESSAGE);
	            view.getTxtIdSolicitud().setText("");
			} else {
				javax.swing.JOptionPane.showMessageDialog(view, "No se pudo asignar el perito.", "Error",
						javax.swing.JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException ex) {
			javax.swing.JOptionPane.showMessageDialog(view, "Error: Seleccione correctamente los datos.", "Error",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
	}

	// Método para actualizar la vista con los datos del modelo
	private void actualizarVista() {
		// Actualizar tabla de solicitudes
		DefaultTableModel modeloSolicitudes = (DefaultTableModel) ((JTable) view.getScrollListaSolicitudes()
				.getViewport().getView()).getModel();
		modeloSolicitudes.setRowCount(0);
		for (Object[] fila : model.obtenerSolicitudesParaTabla()) {
			modeloSolicitudes.addRow(fila);
		}

		// Actualizar tabla de peritos
		DefaultTableModel modeloPeritos = (DefaultTableModel) ((JTable) view.getScrollListaPeritos().getViewport()
				.getView()).getModel();
		modeloPeritos.setRowCount(0);
		for (Object[] fila : model.obtenerPeritosParaTabla()) {
			modeloPeritos.addRow(fila);
		}
		JTable tablaPeritos = (JTable) view.getScrollListaPeritos().getViewport().getView();
	    if (tablaPeritos.getRowCount() > 0) {
	        Object idPerito = tablaPeritos.getValueAt(0, 0);
	        view.getTxtIdPerito().setText(idPerito.toString());
	    }
	}
	
	

	// Método para iniciar la vista
	public void initView() {
		view.setVisible(true);
	}

}
