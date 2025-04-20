package bajaColegiado;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BajaColegiadoVista {

	private JFrame frame;
	private JTextField idColegiado;
	private JTextArea motivosCancelacion;
	private JButton btnCancelarColegiacion;
	private JTable situacionColegiado;
	private JButton btnIniciarSesion;
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
		frame.setBounds(100, 100, 678, 333);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		 motivosCancelacion = new JTextArea();
		motivosCancelacion.setBounds(460, 106, 194, 125);
		frame.getContentPane().add(motivosCancelacion);
		
		 btnCancelarColegiacion = new JButton("Cancelar colegiacion");
		btnCancelarColegiacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelarColegiacion.setBounds(470, 253, 174, 21);
		frame.getContentPane().add(btnCancelarColegiacion);
		
		JLabel lblNewLabel = new JLabel("Motivos de la cancelación");
		lblNewLabel.setBounds(470, 83, 194, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cancelar colegiación");
		lblNewLabel_1.setBounds(206, 10, 187, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		idColegiado = new JTextField();
		idColegiado.setBounds(10, 59, 96, 19);
		frame.getContentPane().add(idColegiado);
		idColegiado.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Id colegiado");
		lblNewLabel_2.setBounds(10, 40, 79, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 110, 440, 125);
		frame.getContentPane().add(scrollPane);
		
		situacionColegiado = new JTable();
		scrollPane.setViewportView(situacionColegiado);
		
		 btnIniciarSesion = new JButton("Iniciar sesion");
		btnIniciarSesion.setBounds(132, 58, 139, 21);
		frame.getContentPane().add(btnIniciarSesion);
		
		JLabel lblNewLabel_3 = new JLabel("Situacion actual");
		lblNewLabel_3.setBounds(10, 83, 213, 13);
		frame.getContentPane().add(lblNewLabel_3);
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
	public JButton getBtnIniciarSesion() {
		return this.btnIniciarSesion;
	}
	public JFrame getFrame() {
		return this.frame;
	}
	public JTable getTablaColegiado() {
		return this.situacionColegiado;
	}
	public void setTablaColegiado(TableModel t) {
		 this.situacionColegiado.setModel(t);
	}
	public void cambiarEnabledCancelar(boolean estado) {
		this.btnCancelarColegiacion.setEnabled(estado);
	}
	public void cambiarEnabledMotivos(boolean estado) {
		this.motivosCancelacion.setEnabled(estado);
	}
}
