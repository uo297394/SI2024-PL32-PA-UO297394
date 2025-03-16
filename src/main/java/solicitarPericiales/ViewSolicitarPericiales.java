package solicitarPericiales;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;


public class ViewSolicitarPericiales extends JFrame {

	private  JSpinner.DateEditor añoNacimiento;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDNI;
	private JTextField tfDireccion;
	private JTextField tfCorreo;
	private JFormattedTextField ftfTelefono;
	private JSpinner sFechaNacimiento;
	private JButton btnEnviarSolicitud;
	private JTextArea taDetalles;
	private JComboBox cbCaracterSoli;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSolicitarPericiales frame = new ViewSolicitarPericiales();
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
	public ViewSolicitarPericiales() {
		setTitle("Solicitar periciales");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Introduzca los datos personales");
		lblNewLabel.setBounds(0, 0, 181, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 23, 89, 13);
		contentPane.add(lblNewLabel_1);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(10, 46, 106, 19);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setBounds(136, 23, 89, 13);
		contentPane.add(lblNewLabel_2);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(136, 46, 124, 19);
		contentPane.add(tfApellido);
		tfApellido.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("DNI");
		lblNewLabel_3.setBounds(303, 23, 89, 13);
		contentPane.add(lblNewLabel_3);
		
		tfDNI = new JTextField();
		tfDNI.setBounds(296, 46, 96, 19);
		contentPane.add(tfDNI);
		tfDNI.setColumns(10);
		
		tfDNI.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = tfDNI.getText();

                if (text.length() < 8) {
                    // Solo permite números en los primeros 8 caracteres
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                } else if (text.length() == 8) {
                    // Solo permite una letra en la posición 9
                    if (!Character.isLetter(c)) {
                        e.consume();
                    } else {
                        e.setKeyChar(Character.toUpperCase(c)); // Convierte la letra a mayúscula
                    }
                } else {
                    e.consume(); // Bloquea cualquier carácter adicional
                }
            }
        });
		
		JLabel lblNewLabel_4 = new JLabel("Dirección");
		lblNewLabel_4.setBounds(191, 83, 71, 13);
		contentPane.add(lblNewLabel_4);
		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(191, 106, 213, 19);
		contentPane.add(tfDireccion);
		tfDireccion.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Correo");
		lblNewLabel_5.setBounds(13, 83, 45, 13);
		contentPane.add(lblNewLabel_5);
		
		tfCorreo = new JTextField();
		tfCorreo.setBounds(10, 106, 171, 19);
		contentPane.add(tfCorreo);
		tfCorreo.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Teléfono");
		lblNewLabel_6.setBounds(413, 23, 96, 13);
		contentPane.add(lblNewLabel_6);
		
		
		
		JLabel lblNewLabel_7 = new JLabel("Fecha nacimiento");
		lblNewLabel_7.setBounds(413, 83, 124, 13);
		contentPane.add(lblNewLabel_7);
		
		Date currentDate = new Date();

		// Crea el modelo, estableciendo currentDate como valor máximo
		SpinnerDateModel dateModel = new SpinnerDateModel(currentDate, null, currentDate, Calendar.DAY_OF_MONTH);

		// Crea el JSpinner con ese modelo
		sFechaNacimiento = new JSpinner(dateModel);

		// Establece el editor para mostrar la fecha en el formato deseado (día/mes/año)
		JSpinner.DateEditor añoNacimiento = new JSpinner.DateEditor(sFechaNacimiento, "dd/MM/yyyy");
		sFechaNacimiento.setEditor(añoNacimiento);
		sFechaNacimiento.setBounds(414, 106, 95, 20);
		contentPane.add(sFechaNacimiento);

		MaskFormatter formatoTelefono = null;
        try {
            formatoTelefono = new MaskFormatter("#########"); // Para España (ej: 600 123 456)
            formatoTelefono.setPlaceholderCharacter('_'); // Caracter temporal mientras se escribe
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
		ftfTelefono = new JFormattedTextField(formatoTelefono);
		ftfTelefono.setBounds(410, 46, 99, 19);
		contentPane.add(ftfTelefono);
		
		taDetalles = new JTextArea();
		taDetalles.setBounds(10, 167, 597, 54);
		contentPane.add(taDetalles);
		
		JLabel lblNewLabel_8 = new JLabel("Indique una breve descripción de lo que desea realizar");
		lblNewLabel_8.setBounds(10, 144, 499, 13);
		contentPane.add(lblNewLabel_8);
		
		String[] opciones = {"Normal","Urgente"};
		cbCaracterSoli = new JComboBox(opciones);
		cbCaracterSoli.setBounds(10, 263, 124, 21);
		contentPane.add(cbCaracterSoli);
		
		JLabel lblNewLabel_9 = new JLabel("Indique el carácter de la solicitud");
		lblNewLabel_9.setBounds(13, 240, 212, 13);
		contentPane.add(lblNewLabel_9);
		
		btnEnviarSolicitud = new JButton("Enviar solicitud");
		btnEnviarSolicitud.setBounds(235, 285, 157, 31);
		contentPane.add(btnEnviarSolicitud);
	}

	public JTextField getTfNombre() {return tfNombre;}

	public JTextField getTfApellido() {return tfApellido;}

	public JTextField getTfDNI() {return tfDNI;}

	public JTextField getTfDireccion() {return tfDireccion;}

	public JTextField getTfCorreo() {return tfCorreo;}

	public JFormattedTextField getTfTelefono() {return ftfTelefono;}
	
	public JSpinner getFechaNacimiento() { return sFechaNacimiento;}
	
	public JButton getBotonEnviarSoli() {return btnEnviarSolicitud;}
	
	public JTextArea getDetalles() {return taDetalles;}
	
	public JComboBox getCaracter() {return cbCaracterSoli;}
	
}
