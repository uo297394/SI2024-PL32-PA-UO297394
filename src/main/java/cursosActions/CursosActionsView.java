package cursosActions;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import aperturaInscripciones.AperturaInscripcionesViewPanel;
import inscribirColegiado.InscribirColegiadoViewPanel;
import inscritos_cursos_formacion.InscritosCursosViewPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class CursosActionsView {

	private JFrame frame;
	private JTable tabSelecionCurso;
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
		frame.setBounds(0, 0, 492, 422);
		frame.setTitle("Acciones con Cursos");
		frame.setName("Acciones con Cursos");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "cell 0 2,grow");
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
	
	// INSCRIPCION DE COLEGIADOS
	public JTextField getTfNumColeg() {
		return ((InscribirColegiadoViewPanel)inscribirColegiado).getTfNumColeg();
	}
	public void setTfNumColeg(JTextField tfNumColeg) {
		((InscribirColegiadoViewPanel)inscribirColegiado).setTfNumColeg(tfNumColeg);
	}
	public JButton getBtnInscColeg() {
		return ((InscribirColegiadoViewPanel)inscribirColegiado).getBtnInscColeg();
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


}
