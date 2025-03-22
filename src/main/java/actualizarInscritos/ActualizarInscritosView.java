package actualizarInscritos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class ActualizarInscritosView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableInscripciones;
	private JButton btnComprobar = new JButton("Comprobar Pago");


	/**
	 * Create the frame.
	 */
	public ActualizarInscritosView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 572, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 10, 548, 282);
		contentPane.add(scrollPane);
		
		tableInscripciones = new JTable();
		scrollPane.setViewportView(tableInscripciones);
		
		btnComprobar.setBounds(361, 302, 187, 21);
		contentPane.add(btnComprobar);
	}

	public JTable getTableInscripciones() {
		return tableInscripciones;
	}

	public void setTableInscripciones(JTable tableInscripciones) {
		this.tableInscripciones = tableInscripciones;
	}

	public JButton getBtnComprobar() {
		return btnComprobar;
	}
	
	
}
