package inscribirColegiado;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.TableModel;
import util.SwingUtil;
import util.Util;


/**
 * Clase encargada de unir la interfaz grafica proporcionada por la vista con las utilidades proporcionadas por el modelo.
 * Se utiliza en el main.
 */
public class InscribirColegiadoController {
	private InscribirColegiadoModel model;
	private InscribirColegiadoView view;
	private String lastSelectedKey="";

	public InscribirColegiadoController(InscribirColegiadoModel m, InscribirColegiadoView v) {
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
		view.getBtnInscColeg().addActionListener(e -> SwingUtil.exceptionWrapper(() -> updatePlazo()));
		
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
		List<InscribirColegiadoDisplayDTO> cursos=model.getListaCursos();
		Date today = Util.isoStringToDate(Util.getTodayISO());
		TableModel tmodel=SwingUtil.getTableModelFromPojos(cursos, new String[] {"id","tituloCurso","descripcion","fechaInicioCurso","fechaFinCurso","duracion","maxPlazas","cuota","colectivos","fechaInicioInscripcion","fechaFinInscripcion"});
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
		String numColeg = view.getTfNumColeg().getText();
		if(this.lastSelectedKey !="" && numColeg != "") {
		model.insertInscColegiado(numColeg,model.getListaCursos().get(view.getTablaCursos().getSelectedRow()).getId());
		getListaCursos();
		}
	}

}
