package mostrar_historico_cursos_inscritos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class CursosInscritosVista {

	private JFrame frame;
	private JTextField numero_colegiado;
	private JTable tablaCursos;
	private JButton mostrarCursos;
	private JTextArea totalCursos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CursosInscritosVista window = new CursosInscritosVista();
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
	public CursosInscritosVista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 551, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nº colegiado:");
		lblNewLabel.setBounds(10, 51, 93, 13);
		frame.getContentPane().add(lblNewLabel);
		
		numero_colegiado = new JTextField();
		numero_colegiado.setBounds(7, 74, 96, 19);
		frame.getContentPane().add(numero_colegiado);
		numero_colegiado.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Histórico de cursos inscritos");
		lblNewLabel_1.setBounds(179, 10, 184, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		tablaCursos = new JTable();
		tablaCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaCursos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		tablaCursos.setBounds(10, 133, 345, 128);
		//String[] columnNames = {"Título", "Fecha de Inicio", "Fecha de Fin", "Duración (horas)"};
		//DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		//tablaCursos.setModel(model);
		tablaCursos.setDefaultEditor(Object.class, null); 
		frame.getContentPane().add(tablaCursos);
		
		 mostrarCursos = new JButton("Mostrar cursos");
		mostrarCursos.setBackground(Color.CYAN);
		mostrarCursos.setBounds(140, 73, 127, 21);
		frame.getContentPane().add(mostrarCursos);
		
		 totalCursos = new JTextArea();
		totalCursos.setBounds(365, 145, 162, 91);
		frame.getContentPane().add(totalCursos);
	}
	public JTable getTablaCursos() {
		return this.tablaCursos;
	}
	public String getIdColegiado() {
		return this.numero_colegiado.getText();
	}
	public void setTablaCursos(TableModel t) {
		this.tablaCursos.setModel(t);
	}
	public JButton getBoton() {
		return this.mostrarCursos;
	}
	public JFrame getFrame() {
		return this.frame;
	}
	public void setTotalCursos(String c) {
	this.totalCursos.setText(c);
	}
}

