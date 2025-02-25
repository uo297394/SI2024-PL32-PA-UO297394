package aperturaInscripciones;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;

import javax.swing.table.TableModel;


import util.SwingUtil;


/**
 * Controlador para la funcionalidad de visualizacion de carreras para la inscripcion.
 * Es el punto de entrada de esta pantalla que se invocará:
 * -instanciando el controlador con la vista y el modelo
 * -ejecutando initController que instalara los manejadores de eventos
 */
public class AperturaInscripcionesController {
	private AperturaInscripcionesModel model;
	private AperturaInscripcionesView view;
	private String lastSelectedKey="";

	public AperturaInscripcionesController(AperturaInscripcionesModel m, AperturaInscripcionesView v) {
		this.model = m;
		this.view = v;
		//no hay inicializacion especifica del modelo, solo de la vista
		this.initView();
	}
	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los objetos del UI.
	 * Cada manejador de eventos se instancia de la misma forma, para que invoque un metodo privado
	 * de este controlador, encerrado en un manejador de excepciones generico para mostrar ventanas
	 * emergentes cuando ocurra algun problema o excepcion controlada.
	 */
	public void initController() {
		view.getBtnRegPlazo().addActionListener(e -> SwingUtil.exceptionWrapper(() -> updatePlazo()));
		
		view.getTablaCursos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//no usa mouseClicked porque al establecer seleccion simple en la tabla de carreras
				//el usuario podria arrastrar el raton por varias filas e interesa solo la ultima
				SwingUtil.exceptionWrapper(() -> updateDetail());
			}
		});
	}
	
	public void initView() {
		this.getListaCursos();
		view.getFrame().setVisible(true); 
	}
	/**
	 * La obtencion de la lista de cursos y insercion de la misma en la tabla de los cursos
	 */
	public void getListaCursos() {
		List<AperturaInscripcionesDisplayDTO> cursos=model.getListaCursos();
		TableModel tmodel=SwingUtil.getTableModelFromPojos(cursos, new String[] {"id","titulo_curso","descripcion","fecha_inicio_curso","fecha_fin_curso","duracion","max_plazas","cuota","colectivos"});
		view.getTablaCursos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablaCursos());
		
		//Como se guarda la clave del ultimo elemento seleccionado, restaura la seleccion de los detalles
		this.restoreDetail();
	}
	
	public void restoreDetail() {
		//Utiliza la ultimo valor de la clave (que se reiniciara si ya no existe en la tabla)
		this.lastSelectedKey=SwingUtil.selectAndGetSelectedKey(view.getTablaCursos(), this.lastSelectedKey);
		//Si hay clave para seleccionar en la tabla muestra el detalle, si no, lo reinicia
		if ("".equals(this.lastSelectedKey)) { 
		} else {
			this.updateDetail();
		}
	}
	
	/**
	 * Actualiza la seleccion de los cursos */
	public void updateDetail() {
		
		this.lastSelectedKey=SwingUtil.getSelectedKey(view.getTablaCursos());
	}
	/**
	 * Actualizacion de los plazos de inscripcion del curso selecionado y actualización de la tabla
	 */
	public void updatePlazo() {
		if(this.lastSelectedKey !="") {
		String inicio = view.getTfFechaInicio();
		String fin = view.getTfFechaFin();
		model.updateAperturaCurso(model.getListaCursos().get(view.getTablaCursos().getSelectedRow()).getTitulo_curso(), inicio, fin);
		getListaCursos();
		}
	}

}
