package giis.aperturaInscripciones;

import java.awt.EventQueue;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;

public class AperturaInscripcionesView {

	private JFrame frame;
	private JTextField tfFechaInicio;
	private JTextField tfFechaFin;
	JComboBox<Object> cbSelecionCurso = new JComboBox<>();
	JLabel lblError = new JLabel("\r\n");
	JButton btnRegPlazo = new JButton("Registrar Plazo");

	/**
	 * Create the application.
	 */
	public AperturaInscripcionesView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 492, 422);
		frame.setTitle("Apertura de Inscripciones");
		frame.setName("Apertura de Inscripciones");
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[][][grow][][][][][][][][]"));
		
		JLabel lblSelecionaCurso = new JLabel("Seleccione el curso:");
		frame.getContentPane().add(lblSelecionaCurso, "cell 0 0");
		
		frame.getContentPane().add(cbSelecionCurso, "cell 0 1,growx");
		
		JLabel lblFechaInicio = new JLabel("Fecha de Inicio");
		frame.getContentPane().add(lblFechaInicio, "cell 0 3");
		
		tfFechaInicio = new JTextField();
		frame.getContentPane().add(tfFechaInicio, "cell 0 4,growx");
		tfFechaInicio.setColumns(10);
		
		JLabel lblFechaFin = new JLabel("Fecha de Fin");
		frame.getContentPane().add(lblFechaFin, "cell 0 5");
		
		tfFechaFin = new JTextField();
		frame.getContentPane().add(tfFechaFin, "cell 0 6,growx");
		tfFechaFin.setColumns(10);
		
		
		lblError.setForeground(new Color(255, 0, 0));
		frame.getContentPane().add(lblError, "cell 0 7");
		
		JLabel lblNota = new JLabel("Nota: Las fechas deben estar en formato ISO , ejemplo : 01-01-2000");
		frame.getContentPane().add(lblNota, "cell 0 9");
		
		
		frame.getContentPane().add(btnRegPlazo, "cell 0 10");
		
		
	}

	public JFrame getFrame() {
		return frame;
	}
	public String getTfFechaInicio() {
		return tfFechaInicio.getText();
	}
	public String getTfFechaFin() {
		return tfFechaFin.getText();
	}
	public void setError(String error) {
		this.lblError.setText(error);
	}
	public void setCursos(ComboBoxModel<Object> Cursos) {
		this.cbSelecionCurso.setModel(Cursos);
	}
	public JButton getBtnRegPlazo() { return this.btnRegPlazo; }
	public String getCbSelected() {return (String) this.cbSelecionCurso.getSelectedItem();}

}
