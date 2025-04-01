package cobrarRecibos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class CobrarRecibosVista {

	private JFrame frame;
	private JTable tablaRecibos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CobrarRecibosVista window = new CobrarRecibosVista();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CobrarRecibosVista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 294, 184);
		frame.getContentPane().add(scrollPane);
		
		tablaRecibos = new JTable();
		scrollPane.setViewportView(tablaRecibos);
		
		JLabel lblNewLabel = new JLabel("Estado actual de los recibos");
		lblNewLabel.setBounds(21, 41, 174, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btEmitirRecibos = new JButton("Emitir recibos");
		btEmitirRecibos.setBounds(310, 213, 114, 23);
		frame.getContentPane().add(btEmitirRecibos);
		
		JLabel lblNewLabel_1 = new JLabel("Emision de recibos");
		lblNewLabel_1.setBounds(160, 11, 182, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}
	public JTable getTablaRecibos() {
		return this.tablaRecibos;
	}
	public void setTablaRecibos(TableModel t) {
		 this.tablaRecibos.setModel(t);
	}
	public JFrame getFrame() {
		return this.frame;
	}
}
