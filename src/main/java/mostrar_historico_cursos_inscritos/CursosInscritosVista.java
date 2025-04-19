package mostrar_historico_cursos_inscritos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class CursosInscritosVista {

	private JFrame frame;
	private JTextField tfdni;
	private JTable tablaCursos;
	private JButton mostrarCursos;
	private JTextArea totalCursos;
	private JButton btnCancelar = new JButton("Cancelar Inscripción");
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
		
		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setBounds(7, 34, 93, 13);
		frame.getContentPane().add(lblNewLabel);
		
		tfdni = new JTextField();
		tfdni.setBounds(7, 47, 96, 19);
		frame.getContentPane().add(tfdni);
		tfdni.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Histórico de cursos inscritos");
		lblNewLabel_1.setBounds(179, 10, 184, 13);
		frame.getContentPane().add(lblNewLabel_1);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 77, 621, 171);
		frame.getContentPane().add(scrollPane);
		tablaCursos = new JTable();
		//frame.getContentPane().add(tablaCursos);
		scrollPane.setViewportView(tablaCursos);
		tablaCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tablaCursos.setBounds(10, 133, 345, 128);
		tablaCursos.setDefaultEditor(Object.class, null); 
		 mostrarCursos = new JButton("Mostrar cursos");
		mostrarCursos.setBackground(new Color(240, 240, 240));
		mostrarCursos.setBounds(113, 47, 127, 21);
		frame.getContentPane().add(mostrarCursos);
		
		 totalCursos = new JTextArea();
		 totalCursos.setEditable(false);
		totalCursos.setBounds(346, 28, 162, 40);
		frame.getContentPane().add(totalCursos);
		
		btnCancelar.setBounds(7, 259, 162, 23);
		frame.getContentPane().add(btnCancelar);
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public JTable getTablaCursos() {
		return this.tablaCursos;
	}
	public String getDNI() {
		return this.tfdni.getText();
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

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
}

