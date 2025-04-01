package cursosActions;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import aperturaInscripciones.AperturaInscripcionesDisplayDTO;
import aperturaInscripciones.AperturaInscripcionesViewPanel;
import inscribirColegiado.InscribirColegiadoViewPanel;
import inscritos_cursos_formacion.InscritosCursosViewPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class CursosActionsView {

	private JFrame frame;
	private JTable tabSelecionCurso;
	JComboBox<Object> cbFiltrado = new JComboBox<>();
	JPanel abrirInscripcion = new AperturaInscripcionesViewPanel();
	JPanel inscribirColegiado = new InscribirColegiadoViewPanel();
	JPanel inscritosCurso = new InscritosCursosViewPanel();

	/**
	 * Create the application.
	 */
	public CursosActionsView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Inicializado del frame
		frame = new JFrame();
		frame.setBounds(0, 0, 1050, 600);
		frame.setTitle("Acciones con Cursos");
		frame.setName("Acciones con Cursos");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][][][grow][][][][][][][][]"));
		
		JPanel panelFiltrado = new JPanel();
		frame.getContentPane().add(panelFiltrado, "cell 0 0,grow");
		
		JLabel lblFiltrado = new JLabel("Seleccione el colectivo deseado: ");
		panelFiltrado.add(lblFiltrado);
		panelFiltrado.add(cbFiltrado);
		
		//Label
		JLabel lblSelecionaCurso = new JLabel("Seleccione el curso:");
		frame.getContentPane().add(lblSelecionaCurso, "cell 0 1");
		//Adicion de la tabla
		tabSelecionCurso = new JTable();
		tabSelecionCurso.setName("tabSelecionCurso");
		tabSelecionCurso.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabSelecionCurso.setDefaultEditor(Object.class, null); //readonly
		JScrollPane tablePanel = new JScrollPane(tabSelecionCurso);
		frame.getContentPane().add(tablePanel, "cell 0 2,grow");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "cell 0 3,grow");
		tabbedPane.addTab("Inscribir colegiado a Curso", null, inscribirColegiado, null);
		tabbedPane.addTab("Abrir inscripción a Curso", null, abrirInscripcion, null);
		tabbedPane.addTab("Mostrar inscritos al curso", null, inscritosCurso, null);
	}
	// GETTERS Y SETTERS
	public JFrame getFrame() {
		return frame;
	}
	// APERTURA INSCRIPCIONES
	public String getTfFechaInicio() {
		return ((AperturaInscripcionesViewPanel) abrirInscripcion).getTfFechaInicio().getText();
	}
	public String getTfFechaFin() {
		return ((AperturaInscripcionesViewPanel) abrirInscripcion).getTfFechaFin().getText();
	}
	public JButton getBtnRegPlazo() { return ((AperturaInscripcionesViewPanel) abrirInscripcion).getBtnRegPlazo(); }
	
	// INSCRIPCION DE COLEGIADOS Y OTROS
	public JTextField getTfNumColeg() {
		return ((InscribirColegiadoViewPanel)inscribirColegiado).getTfNumColeg();
	}
	public void setTfNumColeg(JTextField tfNumColeg) {
		((InscribirColegiadoViewPanel)inscribirColegiado).setTfNumColeg(tfNumColeg);
	}
	public JButton getBtnInscColeg() {
		return ((InscribirColegiadoViewPanel)inscribirColegiado).getBtnInscColeg();
	}
	public JComboBox<Object> getCbColectivos() {
		return ((InscribirColegiadoViewPanel)inscribirColegiado).getCbColectivo();
	}
	public void setCbColectivo(JComboBox<Object> cbColectivo) {
		((InscribirColegiadoViewPanel)inscribirColegiado).setCbColectivo(cbColectivo);
	}
	public JTextField getDNI() {
		return ((InscribirColegiadoViewPanel)inscribirColegiado).getTfDNI();
	}
	public JRadioButton getRadBut() {
		return ((InscribirColegiadoViewPanel)inscribirColegiado).getRdbtnColegiado();
	}
	public boolean muestraPanelInsc() {
		((InscribirColegiadoViewPanel)inscribirColegiado).muestraPanel(((InscribirColegiadoViewPanel)inscribirColegiado).isSelectedCol());
		return ((InscribirColegiadoViewPanel)inscribirColegiado).isSelectedCol();
	}
	public void rellenaDatos(ColegiadoDisplayDTO i) {
		if(i == null) {
			((InscribirColegiadoViewPanel)inscribirColegiado).estaRegistrado(false);
			((InscribirColegiadoViewPanel)inscribirColegiado).rellenaDatos("","","","","","");
			return;
		}
		((InscribirColegiadoViewPanel)inscribirColegiado).estaRegistrado(true);
		((InscribirColegiadoViewPanel)inscribirColegiado).rellenaDatos(i.getNombre(),i.getApellido(),i.getCorreo(),i.getDireccion(),i.getTelefono(),i.getFechaNacimiento());
	}
	// VER INSCRITOS A CURSO
	public JTable getTable() {
        return ((InscritosCursosViewPanel) inscritosCurso).getTable();
    }
    public JLabel getLblTotal() {
        return ((InscritosCursosViewPanel) inscritosCurso).getLblTotal();
    }
	//COMUN A TODOS
	public JTable getTablaCursos() {return this.tabSelecionCurso;}

	public JComboBox<Object> getCbFiltrado() {
		return cbFiltrado;
	}
	public void setCbFiltrado(JComboBox<Object> cbFiltrado) {
		this.cbFiltrado = cbFiltrado;
	}


}
