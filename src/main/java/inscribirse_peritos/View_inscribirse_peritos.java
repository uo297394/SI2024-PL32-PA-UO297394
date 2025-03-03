package inscribirse_peritos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

public class View_inscribirse_peritos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable tableDatosPersonales;
	private JButton btnMostrarDatos;
	private JTextArea textArea;
	private  JSpinner.DateEditor añoColegiacion;
	private JSpinner spinner;
	private JButton btnEnviarSoli;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_inscribirse_peritos frame = new View_inscribirse_peritos();
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
	public View_inscribirse_peritos() {
		setResizable(false);
		setTitle("Inscribirse en la lista de peritos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 665, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 641, 378);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("New label");
		label.setBounds(10, 48, 18, -1);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("Número de colegiado");
		lblNewLabel_1.setBounds(10, 2, 182, 17);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(10, 29, 96, 19);
		panel.add(textField);
		textField.setColumns(10);
		 ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
	            @Override
	            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
	                    throws BadLocationException {
	                if (string.matches("\\d+")) { // Solo dígitos
	                    super.insertString(fb, offset, string, attr);
	                }
	            }

	            @Override
	            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
	                    throws BadLocationException {
	                if (text.matches("\\d+")) { // Solo dígitos
	                    super.replace(fb, offset, length, text, attrs);
	                }
	            }
	        });
		JLabel lblNewLabel_2 = new JLabel("Datos personales");
		lblNewLabel_2.setBounds(10, 88, 134, 20);
		panel.add(lblNewLabel_2);
		
		
		
		
		btnMostrarDatos = new JButton("Mostrar datos personales");
		btnMostrarDatos.setBounds(10, 57, 182, 21);
		panel.add(btnMostrarDatos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 118, 621, 56);
		panel.add(scrollPane);
		
		tableDatosPersonales = new JTable();
		panel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(tableDatosPersonales);
		
		JLabel lblNewLabel = new JLabel("Introduzca el año de realización del curso oficial de periciales");
		lblNewLabel.setBounds(10, 184, 621, 17);
		panel.add(lblNewLabel);
		
		SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.YEAR);
	    spinner = new JSpinner(dateModel);
		añoColegiacion = new JSpinner.DateEditor(spinner, "yyyy");
        spinner.setEditor(añoColegiacion);
		spinner.setBounds(10, 212, 149, 20);
		panel.add(spinner);
		
		JLabel lblNewLabel_3 = new JLabel("Introduzca su experiencia o formación acreditativa en periciales");
		lblNewLabel_3.setBounds(10, 242, 631, 17);
		panel.add(lblNewLabel_3);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 268, 621, 56);
		panel.add(textArea);
		
		btnEnviarSoli = new JButton("Enviar la solicitud");
		btnEnviarSoli.setBounds(222, 334, 159, 34);
		panel.add(btnEnviarSoli);
		
		
	}
	
	public JTable getTableDatosPersonales() { return tableDatosPersonales;}
	public JTextField getId() { return textField;}
	public JButton getBotonMostrarDatos() { return btnMostrarDatos;}
	public JTextArea getAreaTexto() {return textArea;}
	public JSpinner getSpinner() {return spinner;}
	public JButton getBotonSoli() { return btnEnviarSoli;}
}
