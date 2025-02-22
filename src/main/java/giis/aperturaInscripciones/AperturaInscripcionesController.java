package giis.aperturaInscripciones;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import giis.demo.util.ApplicationException;
import giis.demo.util.SwingUtil;
import giis.demo.util.Util;

/**
 * Controlador para la funcionalidad de visualizacion de carreras para la inscripcion.
 * Es el punto de entrada de esta pantalla que se invocará:
 * -instanciando el controlador con la vista y el modelo
 * -ejecutando initController que instalara los manejadores de eventos
 */
public class AperturaInscripcionesController {
	private AperturaInscripcionesModel model;
	private AperturaInscripcionesView view;
	private String lastSelectedKey=""; //recuerda la ultima fila seleccionada para restaurarla cuando cambie la tabla de carreras

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
		getListaCursos();
	}
	
	public void initView() {
		view.getFrame().setVisible(true); 
	}
	/**
	 * La obtencion de la lista de carreras solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */
	public void getListaCursos() {
		List<Object[]> cursos=model.getListaCursosArray();
		ComboBoxModel<Object> lmodel=SwingUtil.getComboModelFromList(cursos);
		view.setCursos(lmodel);
	}
	
	/**
	 * Al seleccionar un item de la tabla muestra el detalle con el valor del porcentaje de descuento
	 * de la carrera seleccinada y los valores de esta entidad
	 */
	public void updateDetail() {
		this.getListaCursos();
	}
	public void updatePlazo() {
		String inicio = view.getTfFechaInicio();
		String fin = view.getTfFechaFin();
		model.updateAperturaCurso(view.getCbSelected(), inicio, fin);
		updateDetail();
	}

}
