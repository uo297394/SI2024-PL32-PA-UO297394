package aperturaInscripciones;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class AperturaInscripcionesViewPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfFechaInicio;
	private JTextField tfFechaFin;
	JButton btnRegPlazo = new JButton("Registrar Plazo");

	/**
	 * Create the panel.
	 */
	public AperturaInscripcionesViewPanel() {
		this.setLayout(new MigLayout("", "[grow]", "[][][grow][][][][][][][][]"));
		//Label
		JLabel lblFechaInicio = new JLabel("Fecha de Inicio");
		this.add(lblFechaInicio, "cell 0 1");
		//Adicion del textfield para introducir la fecha de inicio
		tfFechaInicio = new JTextField();
		this.add(tfFechaInicio, "cell 0 2,growx");
		tfFechaInicio.setColumns(10);
		
		//Label
		JLabel lblFechaFin = new JLabel("Fecha de Fin");
		this.add(lblFechaFin, "cell 0 3");
		
		//Adicion del textfield para introducir la fecha de fin
		tfFechaFin = new JTextField();
		this.add(tfFechaFin, "cell 0 4,growx");
		tfFechaFin.setColumns(10);
		
		//Label
		JLabel lblNota = new JLabel("Nota: Las fechas deben estar en formato YYYY-MM-DD , ejemplo : 2000-01-01");
		this.add(lblNota, "cell 0 6");
		
		//Adicion del boton que se utiliza para registrar el plazo del curso seleccionado
		this.add(btnRegPlazo, "cell 0 7");
	}

	public JTextField getTfFechaInicio() {
		return tfFechaInicio;
	}

	public void setTfFechaInicio(JTextField tfFechaInicio) {
		this.tfFechaInicio = tfFechaInicio;
	}

	public JTextField getTfFechaFin() {
		return tfFechaFin;
	}

	public void setTfFechaFin(JTextField tfFechaFin) {
		this.tfFechaFin = tfFechaFin;
	}

	public JButton getBtnRegPlazo() {
		return btnRegPlazo;
	}

	public void setBtnRegPlazo(JButton btnRegPlazo) {
		this.btnRegPlazo = btnRegPlazo;
	}
	

}
