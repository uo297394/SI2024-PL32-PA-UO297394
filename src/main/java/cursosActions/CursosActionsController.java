package cursosActions;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.TableModel;
import aperturaInscripciones.AperturaInscripcionesDisplayDTO;
import cancelacion_cursos.CancelacionController;
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
	private static final String MSG_INSCRITO = "La inscripción se ha realizado con exito";
	private static final String MSG_CUENTA = "En caso de haber escogido pago por transferencia, recuerde que la cuota se debe abonar al numero de cuenta: XXXXXXXXX";
	private static final String MSG_FECHA_INSC = "La fecha de plazo para inscribirse al curso ha cambiado con exito";
	protected boolean estaColegiado = true;
	private CancelacionController cancelacionController;

	public CursosActionsController(CursosActionsModel m, CursosActionsView v) {
		this.model = m;
		this.view = v;
		this.cancelacionController = new CancelacionController();
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
            	if(view.getDNI().getText().length() != 9 || estaColegiado){
            		view.rellenaDatos(null);
            		return;
            	}
                ColegiadoDisplayDTO col = buscaPersona(view.getDNI().getText());
                view.rellenaDatos(col);
            } 
        });
		view.getTfNumColeg().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
	
            @Override
            public void keyPressed(KeyEvent e) {
            	
            } 
            @Override
            public void keyReleased(KeyEvent e) {
            	if(view.getTfNumColeg().getText().length() == 0 || !estaColegiado) {
            		view.rellenaDatos(null);
            		return;
            	}
                ColegiadoDisplayDTO col = buscaColegiado(view.getTfNumColeg().getText());
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
		
		view.getBtnCancelarCurso().addActionListener(e -> SwingUtil.exceptionWrapper(() -> intentarMarcarCancelado()));
	}
	
	public void initView() {
		view.getBtnCancelarCurso().setEnabled(false); // Asegurar estado inicial
		this.setCbFiltrado();
		view.getCbFiltrado().setSelectedItem("Todos");
		this.getListaCursos("Todos");
		view.getFrame().setVisible(true); 
		//
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
		view.getBtnCancelarCurso().setEnabled(false);
		TableModel tmodel=SwingUtil.getTableModelFromPojos(cursos, new String[] {"id","tituloCurso","descripcion","fechaInicioCurso","fechaFinCurso","duracion","maxPlazas","cuotas","colectivos","fechaInicioInscripcion","fechaFinInscripcion","cancelable","cancelado"});
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
		boolean enableButton = false;
		//
		this.lastSelectedKey=SwingUtil.getSelectedKey(view.getTablaCursos());
	    view.getBtnCancelarCurso().setEnabled(false); // Deshabilitar por defecto

	    if (!"".equals(this.lastSelectedKey)) {
	         int selectedRow = view.getTablaCursos().getSelectedRow();
	         if (selectedRow != -1) { // Doble check por si acaso
	             AperturaInscripcionesDisplayDTO cursoSel = model.getListaCursos(view.getCbFiltrado().getSelectedItem().toString()).get(selectedRow);
	             
	             if (cursoSel.isCancelable() && !cursoSel.isCancelado()) {
                     enableButton = true;
                 }
	             
	             // Cargar inscripciones y colectivos como antes
	             this.loadInscripciones(Integer.parseInt(cursoSel.getId()));
	             this.setCbColectivos();
	         }
	    }
	    view.getBtnCancelarCurso().setEnabled(enableButton);
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
		String dni = view.getDNI().getText();
		boolean camposCubiertos = true;
		List<String> l = view.getPDatos();
		Iterator<String> it = l.iterator();
		while(it.hasNext()) {
			String s = it.next();
			camposCubiertos = camposCubiertos && (s.length()>0);
		}
		if(this.lastSelectedKey !="" && numColeg != "" && estaColegiado && camposCubiertos) {
			AperturaInscripcionesDisplayDTO disp = model.getListaCursos(view.getCbFiltrado().getSelectedItem().toString()).get(view.getTablaCursos().getSelectedRow());
			if(disp.getFechaInicioInscripcion() == null || disp.getFechaFinInscripcion() == null) throw new ApplicationException(MSG_CURSO_NO_ABIERTO);
			else {
				if(!model.estaInscrito(numColeg, disp.getId())) {
					if(model.icModel.listaEspera(disp.getId()) && !model.icModel.hayPlazas(disp.getId())) {
						this.guardarInscripcion(4);
					}else if(model.icModel.hayPlazas(disp.getId())){
					// Ventana pagos
					PagoInscripcionView piv = new PagoInscripcionView(this);
					piv.setVisible(true);
					//FIN ventana pagos
					}else this.guardarInscripcion(0); //No hay plazas asi que da error
				}
			}
		}
		else if(this.lastSelectedKey !="" && dni != "" && !estaColegiado && camposCubiertos) {
			AperturaInscripcionesDisplayDTO disp = model.getListaCursos(view.getCbFiltrado().getSelectedItem().toString()).get(view.getTablaCursos().getSelectedRow());
			if(disp.getFechaInicioInscripcion() == null || disp.getFechaFinInscripcion() == null) throw new ApplicationException(MSG_CURSO_NO_ABIERTO);
			else {
				if(!model.estaInscritoOtro(dni, disp.getId())) {
					l.add(dni);
					model.guardaDatosOtro(l);
					if(model.icModel.listaEspera(disp.getId()) && !model.icModel.hayPlazas(disp.getId())) {
						this.guardarInscripcion(4);
					}else if(model.icModel.hayPlazas(disp.getId())){
					// Ventana pagos
					PagoInscripcionView piv = new PagoInscripcionView(this);
					piv.setVisible(true);
					//FIN ventana pagos
					}else this.guardarInscripcion(0); //No hay plazas asi que da error
				}
			}
		}
		if(estaColegiado && !camposCubiertos)throw new ApplicationException("Usted no está colegiado");
		if(!estaColegiado && !camposCubiertos)throw new ApplicationException("Rellene los campos");
	}
	
	//Cancelacion Curso
	private void intentarMarcarCancelado() {
        int selectedRow = view.getTablaCursos().getSelectedRow();

        if (selectedRow == -1) {
            SwingUtil.showMessage("Por favor, seleccione un curso de la tabla primero.", "Selección Requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        AperturaInscripcionesDisplayDTO cursoSeleccionado = null;
        try {
             cursoSeleccionado = model.getListaCursos(view.getCbFiltrado().getSelectedItem().toString()).get(selectedRow);
        } catch (IndexOutOfBoundsException e) {
             // O maneja el error de forma más robusta
             SwingUtil.showMessage("Error al obtener los datos del curso seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
             return;
        } catch (Exception e) { // Captura genérica por si acaso
            SwingUtil.showMessage("Error inesperado al obtener datos del curso: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        // *** NUEVA COMPROBACIÓN: Ya está cancelado? ***
        if (cursoSeleccionado.isCancelado()) {
             SwingUtil.showMessage("El curso '" + cursoSeleccionado.getTituloCurso() + "' ya se encuentra cancelado.",
                                  "Operación No Necesaria", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Comprobar si ES cancelable (la precondición)
        if (!cursoSeleccionado.isCancelable()) {
            SwingUtil.showMessage("El curso '" + cursoSeleccionado.getTituloCurso() + "' NO se puede cancelar en este momento (no es cancelable).",
                                  "Operación No Permitida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmación del usuario (menos severa que la de borrar)
        int confirm = JOptionPane.showConfirmDialog(
                view.getFrame(),
                "¿Está seguro de que desea marcar el curso '" + cursoSeleccionado.getTituloCurso() + "' como CANCELADO?",
                "Confirmar Cancelación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE); // Opcional: cambiar icono

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Llama al controlador de cancelación
        try {
            int cursoId = Integer.parseInt(cursoSeleccionado.getId());
            // Llamar al método renombrado del controlador
            cancelacionController.procesarMarcadoCanceladoYAjustarDeuda(cursoId);

            // Mensaje de éxito
            SwingUtil.showMessage("El curso '" + cursoSeleccionado.getTituloCurso() + "' ha sido marcado como CANCELADO con éxito.",
                                  "Cancelación Completada", JOptionPane.INFORMATION_MESSAGE);

            // Refrescar la tabla para mostrar el cambio (si muestras el estado)
            this.getListaCursos(view.getCbFiltrado().getSelectedItem().toString());

        } catch (NumberFormatException e) {
             SwingUtil.showMessage("Error interno: El ID del curso no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
             System.err.println("Error al parsear ID del curso: " + cursoSeleccionado.getId());
        } catch (ApplicationException ae) { // Capturar error lógico del modelo
             SwingUtil.showMessage("No se pudo cancelar el curso: " + ae.getMessage(),
                                  "Error de Lógica", JOptionPane.WARNING_MESSAGE);
             ae.printStackTrace();
        } catch (SQLException e) {
            SwingUtil.showMessage("Error de Base de Datos durante la cancelación: " + e.getMessage(),
                                  "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
             SwingUtil.showMessage("Ocurrió un error inesperado durante la cancelación: " + e.getMessage(),
                                  "Error Inesperado", JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
        }
    }
	
	
	public void guardarInscripcion(int estado) {
		String identificador;
		if(view.getRadBut().isSelected()) identificador= view.getTfNumColeg().getText();
		else identificador = view.getDNI().getText();
		AperturaInscripcionesDisplayDTO disp = model.getListaCursos(view.getCbFiltrado().getSelectedItem().toString()).get(view.getTablaCursos().getSelectedRow());
		model.insertInscColegiado(identificador,disp.getId(),estado,view.getCbColectivos().getSelectedItem().toString());
		view.getCbFiltrado().setSelectedItem("Todos");
		String cuota = model.getCuota(view.getCbColectivos().getSelectedItem().toString(),disp.getId());
		ColegiadoDisplayDTO col = model.aiModel.getColegiado(identificador);
		getListaCursos("Todos");
		if(estado != 4)SwingUtil.showMessage(MSG_INSCRITO+"\n"+col.toString()+"\n"+"Fecha de solicitud realizada el: "+Util.getTodayISO()+"\nCuota: "+cuota+"\n"+MSG_CUENTA,"Inscripción Completada",JOptionPane.INFORMATION_MESSAGE);
		else SwingUtil.showMessage("Usted ha pasado a lista de espera, no se le realizará el cobro de la inscripción","Inscripción Completada",JOptionPane.INFORMATION_MESSAGE);
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
	public ColegiadoDisplayDTO buscaColegiado(String DNI) {
		return model.buscaColegiado(DNI);
	}
}
