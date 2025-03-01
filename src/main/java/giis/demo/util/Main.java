package giis.demo.util;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;

import registrar_colegiado.*;
import util.Database;
import visualizar_cursos.*;
import inscribirse_peritos.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Pantalla inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(5, 29, 652, 42);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnInicializarBaseDatos = new JButton("Inicializar base de datos");
		btnInicializarBaseDatos.setBounds(32, 10, 193, 21);
		panel.add(btnInicializarBaseDatos);
		btnInicializarBaseDatos.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
			}
		});
		
		JButton btnCargarBaseDatos = new JButton("Cargar base de datos");
		btnCargarBaseDatos.setBounds(273, 10, 157, 21);
		panel.add(btnCargarBaseDatos);
		btnCargarBaseDatos.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
				db.loadDatabase();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(5, 112, 652, 228);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		JButton btnHistoria3 = new JButton("Visualizar cursos");
		btnHistoria3.setBounds(205, 144, 177, 30);
		panel_1.add(btnHistoria3);
		
		JButton btnNewButton_1 = new JButton("Abrir una inscripción");
		btnNewButton_1.setBounds(205, 64, 177, 30);
		panel_1.add(btnNewButton_1);
		
		JButton btnSoliColeg = new JButton("Solicitud de colegiación");
		btnSoliColeg.setBounds(413, 64, 177, 30);
		panel_1.add(btnSoliColeg);
		
		JButton btnNewButton_2 = new JButton("Apertura curso");
		btnNewButton_2.setBounds(205, 104, 177, 30);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Inscribirse a un curso");
		btnNewButton_3.setBounds(36, 144, 161, 30);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Visualizar inscritos curso");
		btnNewButton_4.setBounds(205, 184, 177, 30);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Mostrar mis cursos");
		btnNewButton_5.setBounds(36, 64, 159, 30);
		panel_1.add(btnNewButton_5);
		
		JButton btnInscribirserPeritos = new JButton("Inscribirse en peritos");
		btnInscribirserPeritos.setBounds(36, 104, 159, 30);
		panel_1.add(btnInscribirserPeritos);
		
		JLabel lblNewLabel_2 = new JLabel("COLEGIADO");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(36, 22, 159, 32);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("RESPONSABLE");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(207, 27, 159, 22);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("TITULADO");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(386, 27, 159, 22);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("INICIO Y CARGA DE LA BASE DE  DATOS ");
		lblNewLabel.setBounds(5, 0, 246, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("FUNCIONES DISPONIBLES");
		lblNewLabel_1.setBounds(5, 81, 246, 31);
		contentPane.add(lblNewLabel_1);
		btnHistoria3.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				ControllerH3 controller=new ControllerH3(new ModelH3(), new ViewH3());
				controller.initController();
			}
		});
		btnSoliColeg.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				 Registrar_colegiadoVista vcolegiado=new Registrar_colegiadoVista();
				 Registrar_colegiadoModelo mcolegiado=new Registrar_colegiadoModelo();
				Registrar_colegiadoControlador controller=new Registrar_colegiadoControlador(vcolegiado,
						mcolegiado);
				vcolegiado.getFrame().setVisible(true);
				
			}
		});
		btnInscribirserPeritos.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Controller_inscribirse_peritos controller=new Controller_inscribirse_peritos(new Model_inscribirse_peritos(), 
						new View_inscribirse_peritos());
				controller.initController();
				
			}
		});
	}
}
