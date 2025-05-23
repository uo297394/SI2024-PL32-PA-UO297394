package cobrarRecibos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CobrarRecibosVista {

	private JFrame frame;
	private JTable tablaRecibos;
	private JButton btEmitirRecibos;
	private JButton btCobrarRecibos;
	private JLabel etiquetaAño;
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
		frame.setBounds(100, 100, 555, 321);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 66, 412, 205);
		frame.getContentPane().add(scrollPane);
		
		tablaRecibos = new JTable();
		scrollPane.setViewportView(tablaRecibos);
		
		JLabel lblNewLabel = new JLabel("Estado actual de los recibos");
		lblNewLabel.setBounds(23, 41, 197, 14);
		frame.getContentPane().add(lblNewLabel);
		
		 btEmitirRecibos = new JButton("Emitir recibos");
		 btEmitirRecibos.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		btEmitirRecibos.setBounds(415, 181, 124, 23);
		frame.getContentPane().add(btEmitirRecibos);
		
		JLabel lblNewLabel_1 = new JLabel("Emision de recibos");
		lblNewLabel_1.setBounds(160, 11, 182, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		btCobrarRecibos = new JButton("Cobrar recibos");
		btCobrarRecibos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btCobrarRecibos.setBounds(415, 211, 124, 21);
		frame.getContentPane().add(btCobrarRecibos);
		
		 etiquetaAño = new JLabel("Año:");
		etiquetaAño.setBounds(256, 41, 87, 14);
		frame.getContentPane().add(etiquetaAño);
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
	public JButton getBotonRecibos() {
		return this.btEmitirRecibos;
	}
	public JButton getBotonCobrarRecibos() {
		return this.btCobrarRecibos;
	}
	public void setEtiquetaAño(String año) {
		this.etiquetaAño.setText("Año:"+año);
	}
}
