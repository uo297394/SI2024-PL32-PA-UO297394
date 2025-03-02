package inscribirColegiado;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

/**
 * Clase que proporciona la interfaz gráfica para la apertura de las inscripciones, trata de una tabla donde se muestran los cursos sin planificar,
 * dos campos de texto para añadir fechas en formato dd-MM-yyyy y un boton para registrar el plazo.
 * Es utilizada en la clase Controlador.
 */
public class InscribirColegiadoView {

	private JFrame frame;
	private JTextField tfNumColeg;
	private JTable tabSelecionCurso;
	JButton btnInscColeg = new JButton("Inscribir Colegiado");

	/**
	 * Create the application.
	 */
	public InscribirColegiadoView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Inicializado del frame
		frame = new JFrame();
		frame.setBounds(0, 0, 492, 422);
		frame.setTitle("Apertura de Inscripciones");
		frame.setName("Apertura de Inscripciones");
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[][][grow][][][][][][][][]"));
		
		//Label
		JLabel lblSelecionaCurso = new JLabel("Seleccione el curso:");
		frame.getContentPane().add(lblSelecionaCurso, "cell 0 0");
		//Adicion de la tabla
		tabSelecionCurso = new JTable();
		tabSelecionCurso.setName("tabSelecionCurso");
		tabSelecionCurso.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabSelecionCurso.setDefaultEditor(Object.class, null); //readonly
		JScrollPane tablePanel = new JScrollPane(tabSelecionCurso);
		frame.getContentPane().add(tablePanel, "cell 0 1,grow");
		//Label
		JLabel lblNumColeg = new JLabel("Numero de colegiado");
		frame.getContentPane().add(lblNumColeg, "cell 0 3");
		//Adicion del textfield para introducir el numero de colegiado
		tfNumColeg = new JTextField();
		frame.getContentPane().add(tfNumColeg, "cell 0 4,growx");
		tfNumColeg.setColumns(10);
		
		//Adicion del boton que se utiliza para registrar el plazo del curso seleccionado
		frame.getContentPane().add(btnInscColeg, "cell 0 10");
	}
	// GETTERS Y SETTERS
	public JFrame getFrame() {
		return frame;
	}
	public JTextField getTfNumColeg() {
		return tfNumColeg;
	}
	public void setTfNumColeg(JTextField tfNumColeg) {
		this.tfNumColeg = tfNumColeg;
	}
	public JButton getBtnInscColeg() {
		return btnInscColeg;
	}
	public void setBtnInscColeg(JButton btnInscColeg) {
		this.btnInscColeg = btnInscColeg;
	}
	public JTable getTablaCursos() {return this.tabSelecionCurso;}

}
