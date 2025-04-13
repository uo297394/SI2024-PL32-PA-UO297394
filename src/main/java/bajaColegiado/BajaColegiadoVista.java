package bajaColegiado;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BajaColegiadoVista {

	private JFrame frame;
	private JTextField idColegiado;
	private JTextArea motivosCancelacion;
	private JButton btnCancelarColegiacion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaColegiadoVista window = new BajaColegiadoVista();
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
	public BajaColegiadoVista() {
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
		
		 motivosCancelacion = new JTextArea();
		motivosCancelacion.setBounds(186, 85, 240, 125);
		frame.getContentPane().add(motivosCancelacion);
		
		 btnCancelarColegiacion = new JButton("Cancelar colegiacion");
		btnCancelarColegiacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelarColegiacion.setBounds(231, 220, 174, 21);
		frame.getContentPane().add(btnCancelarColegiacion);
		
		JLabel lblNewLabel = new JLabel("Motivos de la cancelación");
		lblNewLabel.setBounds(186, 62, 194, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cancelar colegiación");
		lblNewLabel_1.setBounds(152, 10, 187, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		idColegiado = new JTextField();
		idColegiado.setBounds(10, 88, 96, 19);
		frame.getContentPane().add(idColegiado);
		idColegiado.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Id colegiado");
		lblNewLabel_2.setBounds(10, 62, 79, 13);
		frame.getContentPane().add(lblNewLabel_2);
	}
	
	public String geteMotivosCancelacion() {
		return this.motivosCancelacion.getText();
	}
	public String getiIdColegiado() {
		return this.idColegiado.getText();
	}
	public JButton getBotonCancelacion() {
		return this.btnCancelarColegiacion;
	}
	public JFrame getFrame() {
		return this.frame;
	}
}
