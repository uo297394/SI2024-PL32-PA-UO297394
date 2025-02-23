package registrar_colegiado;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
public class Registrar_colegiadoVista {

	private JFrame frame;
	private JTextField nombre_colegiado;
	private JLabel lblNewLabel;
	private JTextField apellidos_colegiado;
	private JLabel lblNewLabel_1;
	private JTextField DNI_colegiado;
	private JLabel lblNewLabel_2;
	private JTextField direccion_colegiado;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField titulación_colegiado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrar_colegiadoVista window = new Registrar_colegiadoVista();
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
	public Registrar_colegiadoVista() {
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
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		nombre_colegiado = new JTextField();
		nombre_colegiado.setBounds(23, 75, 96, 19);
		frame.getContentPane().add(nombre_colegiado);
		nombre_colegiado.setColumns(10);
		
		JLabel la = new JLabel("Nombre:");
		la.setBounds(26, 52, 80, 13);
		frame.getContentPane().add(la);
		
		lblNewLabel = new JLabel("Apellidos:");
		lblNewLabel.setBounds(244, 52, 86, 13);
		frame.getContentPane().add(lblNewLabel);
		
		apellidos_colegiado = new JTextField();
		apellidos_colegiado.setBounds(210, 75, 174, 19);
		frame.getContentPane().add(apellidos_colegiado);
		apellidos_colegiado.setColumns(10);
		
		lblNewLabel_1 = new JLabel("DNI:");
		lblNewLabel_1.setBounds(26, 115, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		DNI_colegiado = new JTextField();
		DNI_colegiado.setBounds(23, 138, 132, 19);
		frame.getContentPane().add(DNI_colegiado);
		DNI_colegiado.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Dirección");
		lblNewLabel_2.setBounds(244, 115, 86, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		direccion_colegiado = new JTextField();
		direccion_colegiado.setBounds(210, 138, 161, 19);
		frame.getContentPane().add(direccion_colegiado);
		direccion_colegiado.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Registrar colegiado");
		lblNewLabel_3.setBounds(146, 10, 225, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Titulación");
		lblNewLabel_4.setBounds(33, 167, 96, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		titulación_colegiado = new JTextField();
		titulación_colegiado.setBounds(23, 197, 161, 19);
		frame.getContentPane().add(titulación_colegiado);
		titulación_colegiado.setColumns(10);
		
		JButton registrar_colegiado = new JButton("Registrar");
		registrar_colegiado.setBounds(320, 232, 106, 21);
		frame.getContentPane().add(registrar_colegiado);

	        // Crear el selector de fecha
	        JDateChooser nacimiento_colegiado = new JDateChooser();
	        nacimiento_colegiado.setBounds(210, 197, 161, 19);
	        // Agregarlo a la ventana
	        frame.getContentPane().add(nacimiento_colegiado);
	        
	        JLabel lblNewLabel_5 = new JLabel("Fecha de nacimiento");
	        lblNewLabel_5.setBounds(220, 167, 45, 13);
	        frame.getContentPane().add(lblNewLabel_5);

	}
	//Getters y setters 
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public String getNombre_colegiado() {
		return this.nombre_colegiado.getText();
	}

	public void setNombre_colegiado(String nombre_colegiado) {
		this.nombre_colegiado.setText(nombre_colegiado); 
	}

	public String getApellidos_colegiado() {
		return apellidos_colegiado.getText();
	}

	public void setApellidos_colegiado(JTextField apellidos_colegiado) {
		this.apellidos_colegiado.setText(getApellidos_colegiado()); 
	}

	public String getDNI_colegiado() {
		return DNI_colegiado.getText();
	}

	public void setDNI_colegiado(String dNI_colegiado) {
		DNI_colegiado.setText(dNI_colegiado);
	}

	public String getDireccion_colegiado() {
		return direccion_colegiado.getText();
	}

	public void setDireccion_colegiado(String direccion_colegiado) {
		this.direccion_colegiado.setText(direccion_colegiado);
	}

	public String getTitulación_colegiado() {
		return titulación_colegiado.getText();
	}

	public void setTitulación_colegiado(String titulación_colegiado) {
		this.titulación_colegiado.setText(titulación_colegiado);
	}
}
