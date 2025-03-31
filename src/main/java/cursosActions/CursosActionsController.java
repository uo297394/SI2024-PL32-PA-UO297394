package cursosActions;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.TableModel;
import aperturaInscripciones.AperturaInscripcionesDisplayDTO;
import inscritos_cursos_formacion.InscripcionDisplayDTO;
import pagoInscripcion.PagoInscripcionView;
import util.ApplicationException;
import util.SwingUtil;
import util.Util;

public class CursosActionsController {
	private CursosActionsModel model;
	private CursosActionsView view;
	private String lastSelectedKey="";
	private static final String MSG_CURSO_NO_ABIERTO = "Este curso no está abierto";
	private static final String MSG_COLEG_INSCRITO = "La inscripción se ha realizado con exito";
	private static final String MSG_CUENTA = "En caso de haber escogido pago por transferencia, recuerde que la cuota se debe abonar al numero de cuenta: XXXXXXXXX";
	private static final String MSG_FECHA_INSC = "La fecha de plazo para inscribirse al curso ha cambiado con exito";
	protected boolean estaColegiado = true;

	public CursosActionsController(CursosActionsModel m, CursosActionsView v) {
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
		view.getRadBut().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                    estaColegiado = view.muestraPanelInsc();
                    if(estaColegiado)view.rellenaDatos(null);
            }
        });
		view.getDNI().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
	
            @Override
            public void keyPressed(KeyEvent e) {
            	
            } 
            @Override
            public void keyReleased(KeyEvent e) {
            	if(view.getDNI().getText().length() != 9 || estaColegiado) return;
                ColegiadoDisplayDTO col = buscaPersona(view.getDNI().getText());
                view.rellenaDatos(col);
            } 
        });
		view.getBtnRegPlazo().addActionListener(e -> SwingUtil.exceptionWrapper(() -> updatePlazo()));
		view.getBtnInscColeg().addActionListener(e -> SwingUtil.exceptionWrapper(() -> inscribir()));
		view.getTablaCursos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//no usa mouseClicked porque al establecer seleccion simple en la tabla de carreras
				//el usuario podria arrastrar el raton por varias filas e interesa solo la ultima
				SwingUtil.exceptionWrapper(() -> updateDetail());
			}
		});
		view.getCbFiltrado().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				SwingUtil.exceptionWrapper(() -> getListaCursos(view.getCbFiltrado().getSelectedItem().toString()));
			}
		});
	}
	
	public void initView() {
		this.setCbFiltrado();
		view.getCbFiltrado().setSelectedItem("Todos");
		this.getListaCursos("Todos");
		view.getFrame().setVisible(true); 
	}
	private void setCbFiltrado() {
		List<Object[]> colectivos = model.getListaColectivos();
		colectivos.add(new Object[] {"Todos"});
		ComboBoxModel<Object> cbmodel = SwingUtil.getComboModelFromList(colectivos);
		view.getCbFiltrado().setModel(cbmodel);
	}
	private void setCbColectivos() {
		String filtro = view.getCbFiltrado().getSelectedItem().toString();
		if(filtro == null)filtro = "Todos";
		int sel = view.getTablaCursos().getSelectedRow();
		List<Object[]> colectivos = model.getColectivosDeCurso(model.getListaCursos(filtro).get(sel));
		ComboBoxModel<Object> cbmodel = SwingUtil.getComboModelFromList(colectivos);
		view.getCbColectivos().setModel(cbmodel);
	}
	/**
	 * La obtencion de la lista de cursos y insercion de la misma en la tabla de los cursos
	 */
	public void getListaCursos(String colectivo) {
		List<AperturaInscripcionesDisplayDTO> cursos;
		if(colectivo.equals("Todos")) {
			cursos=model.getListaCursos();
		}else {
			cursos=model.getListaCursos(colectivo);
		}
		TableModel tmodel=SwingUtil.getTableModelFromPojos(cursos, new String[] {"id","tituloCurso","descripcion","fechaInicioCurso","fechaFinCurso","duracion","maxPlazas","cuotas","colectivos","fechaInicioInscripcion","fechaFinInscripcion"});
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
		this.loadInscripciones(Integer.parseInt(model.getListaCursos(view.getCbFiltrado().getSelectedItem().toString()).get(view.getTablaCursos().getSelectedRow()).getId()));
		this.setCbColectivos();
		
	}
	
	
	public void updatePlazo() {
		if(this.lastSelectedKey !="") {
		String inicio = view.getTfFechaInicio();
		String fin = view.getTfFechaFin();
		model.updateAperturaCurso(model.getListaCursos(view.getCbFiltrado().getSelectedItem().toString()).get(view.getTablaCursos().getSelectedRow()).getTituloCurso(), inicio, fin);
		getListaCursos("Todos");
		view.getCbFiltrado().setSelectedItem("Todos");
		throw new ApplicationException(MSG_FECHA_INSC);
		}
	}
	
	public void inscribir() {
		String numColeg = view.getTfNumColeg().getText();
		if(this.lastSelectedKey !="" && numColeg != "") {
			AperturaInscripcionesDisplayDTO disp = model.getListaCursos(view.getCbFiltrado().getSelectedItem().toString()).get(view.getTablaCursos().getSelectedRow());
			if(disp.getFechaInicioInscripcion() == null || disp.getFechaFinInscripcion() == null) throw new ApplicationException(MSG_CURSO_NO_ABIERTO);
			else {
				if(!model.estaInscrito(numColeg, disp.getId())) {
					// Ventana pagos
					PagoInscripcionView piv = new PagoInscripcionView(this);
					piv.setVisible(true);
					//FIN ventana pagos
				}
			}
		}
	}
	public void guardarInscripcion(int estado) {
		String numColeg = view.getTfNumColeg().getText();
		AperturaInscripcionesDisplayDTO disp = model.getListaCursos(view.getCbFiltrado().getSelectedItem().toString()).get(view.getTablaCursos().getSelectedRow());
		model.insertInscColegiado(numColeg,disp.getId(),estado,view.getCbColectivos().getSelectedItem().toString());
		view.getCbFiltrado().setSelectedItem("Todos");
		String cuota = model.getCuota(view.getCbColectivos().getSelectedItem().toString(),disp.getId());
		ColegiadoDisplayDTO col = model.aiModel.getColegiado(numColeg);
		getListaCursos("Todos");
		SwingUtil.showMessage(MSG_COLEG_INSCRITO+"\n"+col.toString()+"\n"+"Fecha de solicitud realizada el: "+Util.getTodayISO()+"\nCuota: "+cuota+"\n"+MSG_CUENTA,"Inscripción Completada",JOptionPane.INFORMATION_MESSAGE);
	}
	public void loadInscripciones(int idCurso) {
        List<InscripcionDisplayDTO> inscripciones = model.getInscripcionesPorCurso(idCurso);
        TableModel tmodel = SwingUtil.getTableModelFromPojos(inscripciones, new String[] {
            "nombre", "apellido", "DNI", "telefono", "correo"});
        
        view.getTable().setModel(tmodel);
        SwingUtil.autoAdjustColumns(view.getTable());
        view.getLblTotal().setText("Total inscritos: " + inscripciones.size());
    }
	public ColegiadoDisplayDTO buscaPersona(String DNI) {
		return model.buscaPersona(DNI);
	}
}
