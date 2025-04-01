package actualizarInscritos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ActualizarInscritosView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableInscripciones;
	JLabel lblAceptadosCount = new JLabel("");
	JLabel lblRechazadosCount = new JLabel("");

	/**
	 * Create the frame.
	 */
	public ActualizarInscritosView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 572, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setTitle("Inscripciones Actualizadas");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 10, 548, 282);
		contentPane.add(scrollPane);
		
		tableInscripciones = new JTable();
		tableInscripciones.setDefaultEditor(Object.class, null); //readonly
		tableInscripciones.setRowSelectionAllowed(true);
		scrollPane.setViewportView(tableInscripciones);
		
		JLabel lblAceptados = new JLabel("Inscripciones Aceptadas:");
		lblAceptados.setBounds(10, 310, 158, 13);
		contentPane.add(lblAceptados);
		
		
		lblAceptadosCount.setBounds(158, 310, 45, 13);
		contentPane.add(lblAceptadosCount);
		
		JLabel lblRechazados = new JLabel("Inscripciones Rechazadas:");
		lblRechazados.setBounds(226, 310, 202, 13);
		contentPane.add(lblRechazados);
		
		
		lblRechazadosCount.setBounds(401, 310, 45, 13);
		contentPane.add(lblRechazadosCount);
	}

	public JTable getTableInscripciones() {
		return tableInscripciones;
	}

	public void setTableInscripciones(JTable tableInscripciones) {
		this.tableInscripciones = tableInscripciones;
	}

	public JLabel getLblAceptadosCount() {
		return lblAceptadosCount;
	}

	public void setLblAceptadosCount(String lblAceptadosCount) {
		this.lblAceptadosCount.setText(lblAceptadosCount);;
	}

	public JLabel getLblRechazadosCount() {
		return lblRechazadosCount;
	}

	public void setLblRechazadosCount(String lblRechazadosCount) {
		this.lblRechazadosCount.setText(lblRechazadosCount);;
	}
	
}
