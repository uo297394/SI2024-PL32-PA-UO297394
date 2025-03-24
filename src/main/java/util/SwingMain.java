package util;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import registrarCursos.ControllerH2;
import registrarCursos.ModelH2;
import registrarCursos.ViewH2;
import javax.swing.JButton;
import registrar_colegiado.*;
import visualizar_cursos.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import inscribirse_peritos.Controller_inscribirse_peritos;
import inscribirse_peritos.Model_inscribirse_peritos;
import inscribirse_peritos.View_inscribirse_peritos;
import mostrar_historico_cursos_inscritos.CursosInscritosControlador;
import mostrar_historico_cursos_inscritos.CursosInscritosModelo;
import mostrar_historico_cursos_inscritos.CursosInscritosVista;
import cursosActions.*;
import actualizarInscritos.*;
import asignarPericiales.*;
import solicitarPericiales.*;
import enviarSolicitud.*;

public class SwingMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingMain frame = new SwingMain();
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
	public SwingMain() {
		setTitle("Pantalla inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 844, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(5, 29, 815, 42);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnInicializarBaseDatos = new JButton("Inicializar base de datos");
		btnInicializarBaseDatos.setBounds(32, 10, 193, 21);
		panel.add(btnInicializarBaseDatos);
		btnInicializarBaseDatos.addActionListener(new ActionListener() { // NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db = new Database();
				db.createDatabase(false);
			}
		});

		JButton btnCargarBaseDatos = new JButton("Cargar base de datos");
		btnCargarBaseDatos.setBounds(273, 10, 157, 21);
		panel.add(btnCargarBaseDatos);
		btnCargarBaseDatos.addActionListener(new ActionListener() { // NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db = new Database();
				db.createDatabase(false);
				db.loadDatabase();
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(5, 112, 815, 252);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		/*
		JButton btnHistoria3 = new JButton("Visualizar cursos");
		btnHistoria3.setBounds(205, 144, 177, 30);
		panel_1.add(btnHistoria3);
		*/
		
		JButton btnActualizarInscripcion = new JButton("Actualizar Inscripciones");
		btnActualizarInscripcion.setBounds(205, 144, 177, 30);
		panel_1.add(btnActualizarInscripcion);
		
		JButton btnSoliColeg = new JButton("Solicitud de colegiación");
		btnSoliColeg.setBounds(413, 64, 177, 30);
		panel_1.add(btnSoliColeg);

		JButton btnHistoria2 = new JButton("Registrar Curso");
		btnHistoria2.setBounds(205, 104, 177, 30);
		panel_1.add(btnHistoria2);

		JButton btnCursosActions = new JButton("Acciones con Cursos");
		btnCursosActions.setBounds(205, 64, 177, 30);
		panel_1.add(btnCursosActions);

		JButton btnNewButton_5 = new JButton("Mostrar mis cursos");
		btnNewButton_5.setBounds(36, 64, 159, 30);
		panel_1.add(btnNewButton_5);

		JButton btnInscribirsePeritos = new JButton("Inscribirse en peritos");
		btnInscribirsePeritos.setBounds(36, 104, 159, 30);
		panel_1.add(btnInscribirsePeritos);

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

		JButton btnSolicitarPericiales = new JButton("Solicitar Periciales");
		btnSolicitarPericiales.setBounds(613, 67, 177, 25);
		panel_1.add(btnSolicitarPericiales);

		JButton enviarSolicitud = new JButton("Comprobar titulacion");
		enviarSolicitud.setBounds(413, 109, 177, 25);
		panel_1.add(enviarSolicitud);

		JLabel lblNewLabel_5 = new JLabel("Otros");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(622, 27, 125, 22);
		panel_1.add(lblNewLabel_5);

		JButton btnAsignarPericiales = new JButton("Asignar periciales");
		btnAsignarPericiales.setBounds(205, 184, 177, 30);
		panel_1.add(btnAsignarPericiales);

		JLabel lblNewLabel = new JLabel("INICIO Y CARGA DE LA BASE DE  DATOS ");
		lblNewLabel.setBounds(5, 0, 246, 31);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("FUNCIONES DISPONIBLES");
		lblNewLabel_1.setBounds(5, 81, 246, 31);
		contentPane.add(lblNewLabel_1);
		/*
		btnHistoria3.addActionListener(new ActionListener() { // NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				ControllerH3 controller = new ControllerH3(new ModelH3(), new ViewH3());
				controller.initController();
			}
		});
		*/
	
		btnActualizarInscripcion.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				ActualizarInscritosController controller=new ActualizarInscritosController
						(new ActualizarInscritosModel(), new ActualizarInscritosView());
			}
		});
		
		btnHistoria2.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {

				ControllerH2 controllerH2=new ControllerH2(new ModelH2(), new ViewH2());

			}
		});

		btnSoliColeg.addActionListener(new ActionListener() { // NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Registrar_colegiadoVista vcolegiado = new Registrar_colegiadoVista();
				Registrar_colegiadoModelo mcolegiado = new Registrar_colegiadoModelo();
				Registrar_colegiadoControlador controller = new Registrar_colegiadoControlador(vcolegiado, mcolegiado);
				vcolegiado.getFrame().setVisible(true);

			}
		});

		btnCursosActions.addActionListener(new ActionListener() { // NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				CursosActionsController controladorCursosActions = new CursosActionsController(new CursosActionsModel(),
						new CursosActionsView());
				controladorCursosActions.initController();
			}
		});
		btnInscribirsePeritos.addActionListener(new ActionListener() { // NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Controller_inscribirse_peritos controller = new Controller_inscribirse_peritos(
						new Model_inscribirse_peritos(), new View_inscribirse_peritos());
				controller.initController();

			}
		});
		btnNewButton_5.addActionListener(new ActionListener() { // NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				CursosInscritosVista v = new CursosInscritosVista();
				CursosInscritosModelo m = new CursosInscritosModelo();
				CursosInscritosControlador controller = new CursosInscritosControlador(v, m);
				v.getFrame().setVisible(true);

			}
		});

		btnSolicitarPericiales.addActionListener(new ActionListener() { // NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				ControllerSolicitarPericiales controller = new ControllerSolicitarPericiales(
						new ModelSolicitarPericiales(), new ViewSolicitarPericiales());
				controller.initController();
			}
		});
		enviarSolicitud.addActionListener(new ActionListener() { // NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				EnviarSolicitudVista v = new EnviarSolicitudVista();
				EnviarSolicitudModelo m = new EnviarSolicitudModelo();
				EnviarSolicitudControlador controller = new EnviarSolicitudControlador(v, m);
				v.getFrame().setVisible(true);

			}
		});
		btnAsignarPericiales.addActionListener(new ActionListener() { // NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				ControllerAsignarPericiales controller = new ControllerAsignarPericiales(new ModelAsignarPericiales(),
						new ViewAsignarPericiales());
				controller.initController();
			}
		});
	}
}