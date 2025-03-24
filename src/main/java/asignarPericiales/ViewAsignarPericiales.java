package asignarPericiales;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ViewAsignarPericiales extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIdSolicitud;
	private JTextField txtIdPerito;
	JButton btnAsignar;
	JScrollPane scrollListaPeritos;
	JScrollPane scrollListaSolicitudes;

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
		setBounds(100, 100, 677, 481);
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

		JLabel lblIdSolicitud = new JLabel("Id de la solicitud seleccionada");
		lblIdSolicitud.setBounds(36, 370, 174, 22);
		contentPane.add(lblIdSolicitud);

		JLabel lblIdPerito = new JLabel("Id del perito seleccionado");
		lblIdPerito.setBounds(36, 400, 174, 22);
		contentPane.add(lblIdPerito);

		txtIdSolicitud = new JTextField();
		txtIdSolicitud.setEditable(false);
		txtIdSolicitud.setBounds(226, 372, 51, 19);
		contentPane.add(txtIdSolicitud);
		txtIdSolicitud.setColumns(10);

		txtIdPerito = new JTextField();
		txtIdPerito.setEditable(false);
		txtIdPerito.setBounds(226, 402, 51, 19);
		contentPane.add(txtIdPerito);
		txtIdPerito.setColumns(10);

		btnAsignar = new JButton("Asignar");
		btnAsignar.setBounds(313, 389, 85, 21);
		contentPane.add(btnAsignar);

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
}
