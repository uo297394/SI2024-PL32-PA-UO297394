package enviarSolicitud;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

public class EnviarSolicitudVista {

	private JFrame frame;
	private JTable table;
	private JButton BotonEnviar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnviarSolicitudVista window = new EnviarSolicitudVista();
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
	public EnviarSolicitudVista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame =  new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 183, 175);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);
		table.setBounds(10, 133, 345, 128);	
		BotonEnviar = new JButton("Enviar");
		BotonEnviar.setBounds(341, 232, 85, 21);
		frame.getContentPane().add(BotonEnviar);
		
		JLabel lblNewLabel = new JLabel("Enviar solicitud de colegiacion");
		lblNewLabel.setBounds(120, 10, 183, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Solicitudes pendientes");
		lblNewLabel_1.setBounds(10, 33, 178, 13);
		frame.getContentPane().add(lblNewLabel_1);
	}
	public void setTabla(TableModel t) {
		 this.table.setModel(t);
	}
	public  JTable getTablaColegiados() {
		return this.table;
	}
	public JFrame getFrame() {return this.frame;}
	public JButton getBotonEnviar() {return this.BotonEnviar;}
}
