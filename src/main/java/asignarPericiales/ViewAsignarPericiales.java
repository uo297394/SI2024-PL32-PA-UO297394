package asignarPericiales;

import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class ViewAsignarPericiales extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIdSolicitud;
	private JTextField txtIdPerito;
	private JButton btnAsignar;
	private JScrollPane scrollListaPeritos;
	private JScrollPane scrollListaSolicitudes;
	private JTextField tFEstadoActual;
	private JButton btnCambioEstado;
	private JTextArea textAreaCambioJustificante;
	private JComboBox comboBoxCambioEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAsignarPericiales frame = new ViewAsignarPericiales();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewAsignarPericiales() {
		setTitle("Asignar periciales");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 706, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] columnasSolicitudes = { "ID", "Estado", "idSolicitante", "Caracter", "Descripcion" };
		DefaultTableModel modeloSolicitudes = new DefaultTableModel(columnasSolicitudes, 0);
		JTable tablaSolicitudes = new JTable(modeloSolicitudes);

		scrollListaSolicitudes = new JScrollPane(tablaSolicitudes);
		scrollListaSolicitudes.setBounds(36, 56, 617, 113);
		contentPane.add(scrollListaSolicitudes);

		JLabel lblNewLabel = new JLabel("Solicitudes de periciales");
		lblNewLabel.setBounds(36, 24, 241, 22);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Lista de peritos");
		lblNewLabel_1.setBounds(36, 196, 151, 22);
		contentPane.add(lblNewLabel_1);

		String[] columnasPeritos = { "ID", "Nombre", "Apellido", "Correo", "Teléfono", "Orden_TAP" };
		DefaultTableModel modeloPeritos = new DefaultTableModel(columnasPeritos, 0);
		JTable tablaPeritos = new JTable(modeloPeritos);

		scrollListaPeritos = new JScrollPane(tablaPeritos);
		scrollListaPeritos.setBounds(36, 229, 617, 113);
		contentPane.add(scrollListaPeritos);

		btnAsignar = new JButton("Asignar");
		btnAsignar.setBounds(131, 447, 85, 21);
		contentPane.add(btnAsignar);
		
		JLabel lblNewLabel_2 = new JLabel("Cambiar estado solicitud");
		lblNewLabel_2.setBounds(321, 352, 187, 22);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(321, 370, 332, 154);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Estado actual");
		lblNewLabel_3.setBounds(10, 10, 115, 19);
		panel.add(lblNewLabel_3);
		
		tFEstadoActual = new JTextField();
		tFEstadoActual.setEditable(false);
		tFEstadoActual.setBounds(158, 7, 132, 19);
		panel.add(tFEstadoActual);
		tFEstadoActual.setColumns(10);
		
		comboBoxCambioEstado = new JComboBox();
		comboBoxCambioEstado.addItem("pendiente");
		comboBoxCambioEstado.addItem("aceptada");
		comboBoxCambioEstado.addItem("cancelada");
		comboBoxCambioEstado.setBounds(158, 39, 132, 21);
		panel.add(comboBoxCambioEstado);
		
		JLabel lblNewLabel_4 = new JLabel("Nuevo estado");
		lblNewLabel_4.setBounds(10, 43, 138, 17);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Justificante cambio de estado");
		lblNewLabel_5.setBounds(10, 70, 156, 19);
		panel.add(lblNewLabel_5);
		
		textAreaCambioJustificante = new JTextArea();
		textAreaCambioJustificante.setBounds(10, 99, 149, 45);
		panel.add(textAreaCambioJustificante);
		
		btnCambioEstado = new JButton("Cambiar estado");
		btnCambioEstado.setBounds(169, 101, 121, 32);
		panel.add(btnCambioEstado);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(36, 370, 275, 154);
		contentPane.add(panel_1);
				panel_1.setLayout(null);
		
				JLabel lblIdPerito = new JLabel("Id del perito seleccionado");
				lblIdPerito.setBounds(10, 37, 174, 22);
				panel_1.add(lblIdPerito);
				
						JLabel lblIdSolicitud = new JLabel("Id de la solicitud seleccionada");
						lblIdSolicitud.setBounds(10, 5, 174, 22);
						panel_1.add(lblIdSolicitud);
						
								txtIdPerito = new JTextField();
								txtIdPerito.setBounds(190, 39, 51, 19);
								panel_1.add(txtIdPerito);
								txtIdPerito.setEditable(false);
								txtIdPerito.setColumns(10);
								
										txtIdSolicitud = new JTextField();
										txtIdSolicitud.setBounds(190, 8, 51, 19);
										panel_1.add(txtIdSolicitud);
										txtIdSolicitud.setEditable(false);
										txtIdSolicitud.setColumns(10);
										
										JLabel lblNewLabel_6 = new JLabel("Asignar perito");
										lblNewLabel_6.setBounds(36, 357, 187, 13);
										contentPane.add(lblNewLabel_6);

	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getTxtIdSolicitud() {
		return txtIdSolicitud;
	}

	public JTextField getTxtIdPerito() {
		return txtIdPerito;
	}

	public JButton getBtnAsignar() {
		return btnAsignar;
	}

	public JScrollPane getScrollListaPeritos() {
		return scrollListaPeritos;
	}

	public JScrollPane getScrollListaSolicitudes() {
		return scrollListaSolicitudes;
	}
	public JComboBox<String> getEstadoNuevo(){
		return comboBoxCambioEstado;
	}
	public JTextField getEstadoActual() {
		return tFEstadoActual;
	}
	public JButton getCambioEstado() {
		return btnCambioEstado;
	}
	public JTextArea getJustificacion() {
		return textAreaCambioJustificante;
	}
}
