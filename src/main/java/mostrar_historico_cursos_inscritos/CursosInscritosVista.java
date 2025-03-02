package mostrar_historico_cursos_inscritos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;

public class CursosInscritosVista {

	private JFrame frame;
	private JTextField numero_colegiado;
	private JTable tablaCursos;
	private JButton mostrarCursos;

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
		lblNewLabel.setBounds(10, 24, 93, 13);
		frame.getContentPane().add(lblNewLabel);
		
		numero_colegiado = new JTextField();
		numero_colegiado.setBounds(7, 47, 96, 19);
		frame.getContentPane().add(numero_colegiado);
		numero_colegiado.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Histórico de cursos inscritos");
		lblNewLabel_1.setBounds(179, 10, 184, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		tablaCursos = new JTable();
		tablaCursos.setBounds(10, 133, 289, 87);
		frame.getContentPane().add(tablaCursos);
		
		 mostrarCursos = new JButton("Mostrar cursos");
		mostrarCursos.setBackground(Color.CYAN);
		mostrarCursos.setBounds(142, 46, 127, 21);
		frame.getContentPane().add(mostrarCursos);
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

	}

