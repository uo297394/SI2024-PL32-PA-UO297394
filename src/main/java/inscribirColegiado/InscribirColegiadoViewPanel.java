package inscribirColegiado;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;

public class InscribirColegiadoViewPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton btnInscColeg = new JButton("Realizar Inscripción");
	JComboBox<Object> cbColectivo = new JComboBox<>();
	private JTextField tfNumColeg;
	private final JRadioButton rdbtnColegiado = new JRadioButton("Soy un Colegiado / Precolegiado");
	private final JRadioButton rdbtnOtroColectivo = new JRadioButton("Pertenezco a un colectivo externo");
	private final JPanel pColegiadoDatos = new JPanel();
	private final JPanel pOtroColectivo = new JPanel();
	private final JLabel lblDNI = new JLabel("Introduzca su DNI:");
	private final JTextField tfDNI = new JTextField();
	private final JPanel pDatos = new JPanel();
	private final JLabel lblNombre = new JLabel("Nombre:");
	private final JTextField tfNombre = new JTextField();
	private final JLabel lblApellidos = new JLabel("Apellidos:");
	private final JTextField tfApellidos = new JTextField();
	private final JLabel lblDireccion = new JLabel("Dirección:");
	private final JTextField tfDireccion = new JTextField();
	private final JLabel lblCorreo = new JLabel("Correo electrónico:");
	private final JTextField tfCorreo = new JTextField();
	private final JLabel lblTelefono = new JLabel("Número de teléfono:");
	private final JTextField tfNumTelef = new JTextField();
	private final JLabel lblFechaNac = new JLabel("Fecha de nacimiento:");
	private final JTextField tfFechaNac = new JTextField();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Create the panel.
	 */
	public InscribirColegiadoViewPanel() {
		tfFechaNac.setColumns(10);
		tfNumTelef.setColumns(10);
		tfCorreo.setColumns(10);
		tfDireccion.setColumns(10);
		tfApellidos.setColumns(10);
		tfNombre.setColumns(10);
		this.setLayout(new MigLayout("", "[grow]", "[][][grow][grow][grow][grow][][][][][][][][]"));
				buttonGroup.add(rdbtnColegiado);
				rdbtnColegiado.setSelected(true);
				
				add(rdbtnColegiado, "flowx,cell 0 1");
				
				add(pColegiadoDatos, "flowx,cell 0 2,grow");
		//Label
				JLabel lblNumColeg = new JLabel("Numero de colegiado");
				pColegiadoDatos.add(lblNumColeg);
				//Adicion del textfield para introducir el numero de colegiado
				tfNumColeg = new JTextField();
				pColegiadoDatos.add(tfNumColeg);
				tfNumColeg.setColumns(10);
				
				add(pDatos, "cell 0 4,grow");
				pDatos.setLayout(new GridLayout(0, 2, 0, 0));
				
				pDatos.add(lblNombre);
				
				pDatos.add(tfNombre);
				
				pDatos.add(lblApellidos);
				
				pDatos.add(tfApellidos);
				
				pDatos.add(lblDireccion);
				
				pDatos.add(tfDireccion);
				
				pDatos.add(lblCorreo);
				
				pDatos.add(tfCorreo);
				
				pDatos.add(lblTelefono);
				
				pDatos.add(tfNumTelef);
				
				pDatos.add(lblFechaNac);
				
				pDatos.add(tfFechaNac);
				
				JLabel lblColectivo = new JLabel("Seleccione el colectivo:");
				add(lblColectivo, "cell 0 6");
				add(cbColectivo, "cell 0 7,growx");
				
				//Adicion del boton que se utiliza para registrar el plazo del curso seleccionado
				this.add(btnInscColeg, "cell 0 8");
				buttonGroup.add(rdbtnOtroColectivo);
				
				add(rdbtnOtroColectivo, "cell 0 1");
				tfDNI.setColumns(10);
				
				add(pOtroColectivo, "cell 0 2,grow");
				
				pOtroColectivo.add(lblDNI);
				
				pOtroColectivo.add(tfDNI);
				this.muestraPanel(this.rdbtnColegiado.isSelected());
	}
	// GETTERS AND SETTERS
	public JButton getBtnInscColeg() {
		return btnInscColeg;
	}
	public void setBtnInscColeg(JButton btnInscColeg) {
		this.btnInscColeg = btnInscColeg;
	}
	public JTextField getTfNumColeg() {
		return tfNumColeg;
	}
	public void setTfNumColeg(JTextField tfNumColeg) {
		this.tfNumColeg = tfNumColeg;
	}
	public JComboBox<Object> getCbColectivo() {
		return cbColectivo;
	}
	public void setCbColectivo(JComboBox<Object> cbColectivo) {
		this.cbColectivo = cbColectivo;
	}
	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}
	public JTextField getTfDNI() {
		return tfDNI;
	}
	public JRadioButton getRdbtnColegiado() {
		return rdbtnColegiado;
	}
	public JTextField getTfNombre() {
		return tfNombre;
	}
	public JTextField getTfApellidos() {
		return tfApellidos;
	}
	public JTextField getTfDireccion() {
		return tfDireccion;
	}
	public JTextField getTfCorreo() {
		return tfCorreo;
	}
	public JTextField getTfNumTelef() {
		return tfNumTelef;
	}
	public JTextField getTfFechaNac() {
		return tfFechaNac;
	}
	//FIN GETTERS AND SETTERS

	
	/**
	 * Muestra el panel de inscripcion según el valor de b: <br>
	 * 	- <b>Verdadero:</b> Colegiados y Precolegiados <br>
	 * 	- <b>Falso:</b> Otros Colectivos
	 * @param b Panel a mostrar
	 */
	public void muestraPanel(boolean b) {
		this.pColegiadoDatos.setVisible(b);
		this.pOtroColectivo.setVisible(!b);
	}
	
	/**
	 * Deja editar los textfields del panel pDatos unicamente si el booleano toma valor Verdadero
	 * @param b el booleano
	 */
	public void estaRegistrado(boolean b) {
		this.tfNombre.setEditable(!b);
		this.tfApellidos.setEditable(!b);
		this.tfCorreo.setEditable(!b);
		this.tfDireccion.setEditable(!b);
		this.tfFechaNac.setEditable(!b);
		this.tfNumTelef.setEditable(!b);
	}
	
	/**
	 * Cambia el contenido de los textFields del panel pDatos a los que se pasan por argumento
	 * @param nombre tfNombre
	 * @param apellidos tfApellidos
	 * @param correo tfCorreo
	 * @param direccion tfDireccion
	 * @param fechaNac tfFechaNac
	 */
	public void rellenaDatos(String nombre, String apellidos, String correo, String direccion,String numTelf, String fechaNac) {
		
		this.tfNombre.setText(nombre);
		this.tfApellidos.setText(apellidos);
		this.tfCorreo.setText(correo);
		this.tfDireccion.setText(direccion);
		this.tfNumTelef.setText(numTelf);
		this.tfFechaNac.setText(fechaNac);
		
	}
	
	/**
	 * Retorna si el radio button colegiados está seleccionado
	 * @return booleano que indica si esta seleccionado o no
	 */
	public boolean isSelectedCol() {
		return this.rdbtnColegiado.isSelected();
	}

}
