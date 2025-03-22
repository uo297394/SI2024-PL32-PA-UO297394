package actualizarInscritos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.table.TableModel;

import aperturaInscripciones.AperturaInscripcionesDisplayDTO;
import inscritos_cursos_formacion.InscripcionDisplayDTO;
import util.ApplicationException;
import util.SwingUtil;

public class ActualizarInscritosController {

	private ActualizarInscritosModel model;
	private ActualizarInscritosView view;
	private String lastSelectedKey="";
	
	public ActualizarInscritosController(ActualizarInscritosModel m, ActualizarInscritosView v) {
		this.model = m;
		this.view = v;
		//no hay inicializacion especifica del modelo, solo de la vista
		this.initView();
	}
	public void initController() {
		view.getBtnComprobar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> actualizarInscripcion(model.getInscripcionesPorCurso().get(view.getTableInscripciones().getSelectedRow()))));

		view.getTableInscripciones().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//no usa mouseClicked porque al establecer seleccion simple en la tabla de carreras
				//el usuario podria arrastrar el raton por varias filas e interesa solo la ultima
				SwingUtil.exceptionWrapper(() -> updateDetail());
			}
		});
	}
	public void initView() {
		this.getListaInscripcionesPorCurso();
		initController();
		view.setVisible(true); 
	}
	/**
	 * La obtencion de la lista de cursos y insercion de la misma en la tabla de los cursos
	 */
	public void getListaInscripcionesPorCurso() {
		List<InscripcionDisplayDTO> inscripciones=model.getInscripcionesPorCurso();
		TableModel tmodel=SwingUtil.getTableModelFromPojos(inscripciones, new String[] {"id","nombre","apellido","DNI","estado","fechaInscripcion","telefono","correo","cuota","tituloCurso"});
		view.getTableInscripciones().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableInscripciones());
		
		//Como se guarda la clave del ultimo elemento seleccionado, restaura la seleccion de los detalles
		this.restoreDetail();
	}
	
	public void restoreDetail() {
		//Utiliza la ultimo valor de la clave (que se reiniciara si ya no existe en la tabla)
		this.lastSelectedKey=SwingUtil.selectAndGetSelectedKey(view.getTableInscripciones(), this.lastSelectedKey);
		//Si hay clave para seleccionar en la tabla muestra el detalle, si no, lo reinicia
		if ("".equals(this.lastSelectedKey)) { 
		} else {
			this.updateDetail();
		}
	}
	
	/**
	 * Actualiza la seleccion de los cursos */
	public void updateDetail() {
		this.lastSelectedKey=SwingUtil.getSelectedKey(view.getTableInscripciones());
	}
	
	private void actualizarInscripcion(InscripcionDisplayDTO insc) {
		boolean ap = true;
		boolean found = false;
		String inputFile = "src/main/resources/transacciones.csv";
	    String outputFile = "src/main/resources/deudas.csv";

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        
	        try (BufferedReader br = Files.newBufferedReader(Paths.get(inputFile));
	             BufferedWriter bw = Files.newBufferedWriter(Paths.get(outputFile), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
	            
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] fields = line.split(",");
	                if (fields.length < 5) continue;
	                
	                String dni = fields[0].trim();
	                String cuenta = fields[4].trim();
	                String concepto = fields[2].trim();
	                LocalDateTime fechaTransaccion = LocalDateTime.parse(fields[3].trim()+" 00:00:00", formatter);
	                
	                double cuota = Double.parseDouble(fields[1].trim());
	                //COMPROBAR QUE NO SE TRATE UNA TRANSACCION YA TRATADA DE LA MISMA PERSONA
	                if (dni.equals(insc.getDNI()) && concepto.equals(insc.getTituloCurso())) {
	                	found = true;
	                    long hoursDifference = Duration.between(LocalDateTime.parse(insc.getFechaInscripcion()+" 00:00:00", formatter), fechaTransaccion).toHours();
	                    if (hoursDifference >= 0 && hoursDifference <= 48) {
	                        if (cuota < Float.parseFloat(insc.getCuota())) {
	                            bw.write(dni + "," + cuenta + "," + cuota);
	                            bw.newLine();
	                            ap = false;
	                        } else if (cuota > Float.parseFloat(insc.getCuota())) {
	                            double diferencia = cuota - Float.parseFloat(insc.getCuota());
	                            bw.write(dni + "," + cuenta + "," + diferencia);
	                            bw.newLine();
	                        }
	                    }
	                }
	                if(!found) {
	                	long hoursDifference = Duration.between(LocalDateTime.parse(insc.getFechaInscripcion()+" 00:00:00", formatter), LocalDateTime.now()).toHours();
	                    if (hoursDifference < 0 || hoursDifference > 48) { // HA PASADO EL PLAZO Y NO SE HA PAGADO
	                    	ap = false;
	                    }
	                        
	                }
	            }
	            
	        } catch (IOException | NumberFormatException e) {
	            e.printStackTrace();
	        }
		if(!found && ap)throw new ApplicationException("No hay ningún pago de este usuario, sin embargo sigue dentro del plazo"); //Dentro de plazo, ningún pago
		model.actualizaInscripcion(ap, insc.getDNI());
		getListaInscripcionesPorCurso();
		if(!ap)throw new ApplicationException("El usuario no realizó el pago correcto o lo hizo fuera de plazo"); //Fuera de plazo/ No pago
		throw new ApplicationException("Inscripcion aceptada");
		
	}
}
