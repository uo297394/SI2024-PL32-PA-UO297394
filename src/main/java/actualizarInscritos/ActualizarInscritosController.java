package actualizarInscritos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.Normalizer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.TableModel;
import inscritos_cursos_formacion.InscripcionDisplayDTO;
import util.ApplicationException;
import util.SwingUtil;

public class ActualizarInscritosController {

	private ActualizarInscritosModel model;
	private ActualizarInscritosView view;
	private String lastSelectedKey="";
	private int aceptadas = 0;
	private int rechazadas = 0;
	
	public ActualizarInscritosController(ActualizarInscritosModel m, ActualizarInscritosView v) {
		this.model = m;
		this.view = v;
		//no hay inicializacion especifica del modelo, solo de la vista
		this.initView();
	}
	public void initController() {
		view.getTableInscripciones().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//no usa mouseClicked porque al establecer seleccion simple en la tabla de carreras
				//el usuario podria arrastrar el raton por varias filas e interesa solo la ultima
				SwingUtil.exceptionWrapper(() -> updateDetail());
			}
		});
		view.getBtnActualizar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> actualizaInsc()));
	}
	public void initView() {
		//ACTUALIZAR INSCRIPCIONES
		this.getListaInscripcionesPorCurso();
		initController();
		view.setVisible(true); 
	}
	public void actualizaInsc() {
		List<InscripcionDisplayDTO> l = model.getInscripcionesPorCurso();
		Iterator<InscripcionDisplayDTO> it = l.iterator();
		List<String> lID = new ArrayList<>();
		while(it.hasNext()) {
			InscripcionDisplayDTO n = it.next();
			lID.add(n.getIdInsc());
			this.actualizarInscripcion(n);
		}
		view.setLblAceptadosCount(aceptadas+"");
		view.setLblRechazadosCount(rechazadas+"");
		this.getListaInscripcionesPorCurso(lID);
	}
	
	/**
	 * La obtencion de la lista de cursos y insercion de la misma en la tabla de los cursos
	 */
	public void getListaInscripcionesPorCurso(List<String> lID) {
		List<InscripcionDisplayDTO> inscripciones=model.getInscripcionesActualizadas();
		Iterator<InscripcionDisplayDTO> it = inscripciones.iterator();
		while(it.hasNext()) {
			InscripcionDisplayDTO n = it.next();
			if(!lID.contains(n.getIdInsc()))it.remove();
			else n.setEstado(n.getEstado().equals("0")? "Aceptado" : n.getEstado().equals("1")?"Pendiente":"Rechazado");
		}
		TableModel tmodel=SwingUtil.getTableModelFromPojos(inscripciones, new String[] {"estado","deuda","id","nombre","apellido","DNI","fechaInscripcion","telefono","correo","cuota","tituloCurso"});
		view.getTableInscripciones().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTableInscripciones());
		//Como se guarda la clave del ultimo elemento seleccionado, restaura la seleccion de los detalles
		this.restoreDetail();
	}
	public void getListaInscripcionesPorCurso() {
		List<InscripcionDisplayDTO> inscripciones=model.getInscripcionesPorCurso();
		Iterator<InscripcionDisplayDTO> it = inscripciones.iterator();
		while(it.hasNext()) {
			InscripcionDisplayDTO n = it.next();
			n.setEstado(n.getEstado().equals("0")? "Aceptado" : n.getEstado().equals("1")?"Pendiente":n.getEstado().equals("2")?"Rechazado":"Lista de Espera");
		}
		TableModel tmodel=SwingUtil.getTableModelFromPojos(inscripciones, new String[] {"estado","deuda","id","nombre","apellido","DNI","fechaInscripcion","telefono","correo","cuota","tituloCurso"});
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
		boolean paid = false;
		String inputFile = "src/main/resources/transacciones.csv";

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        
	        try (BufferedReader br = Files.newBufferedReader(Paths.get(inputFile))) {
	            
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
	                if (quitarAcentos(concepto).equals(quitarAcentos(insc.getTituloCurso())) && dni.equals(insc.getDNI())) {
	                    long hoursDifference = Duration.between(LocalDateTime.parse(insc.getFechaInscripcion()+" 00:00:00", formatter), fechaTransaccion).toHours();
	                    if (hoursDifference >= 0 && hoursDifference <= 48) {
	                    	paid = true;
	                        if (cuota < Float.parseFloat(insc.getCuota())) {
	                            model.actualizaDeuda(dni, insc.getTituloCurso(), cuota+"");
	                            ap = false;
	                            rechazadas++;
	                        } else if (cuota > Float.parseFloat(insc.getCuota())) {
	                        	paid = true;
	                            double diferencia = cuota - Float.parseFloat(insc.getCuota());
	                            model.actualizaDeuda(dni, insc.getTituloCurso(), diferencia+"");
	                            aceptadas++;
	                        }
	                        model.actualizaInscripcion(ap, insc.getDNI(),insc.getTituloCurso());
	                    }else {
	                    	paid = true;
                        	ap = false;
                        	rechazadas++;
                        	model.actualizaDeuda(dni, insc.getTituloCurso(), cuota+"");
                        	model.actualizaInscripcion(ap, insc.getDNI(),insc.getTituloCurso());
                        }
	                }
	            }
	            long hoursDifference = Duration.between(LocalDateTime.parse(insc.getFechaInscripcion()+" 00:00:00", formatter), LocalDateTime.now()).toHours();
                if (hoursDifference < 0 || hoursDifference > 48 && !paid) { // HA PASADO EL PLAZO Y NO SE HA PAGADO
                    	ap = false;
                    	rechazadas++;
                    	model.actualizaDeuda(insc.getDNI(), insc.getTituloCurso(), "NP");
                    	model.actualizaInscripcion(ap, insc.getDNI(),insc.getTituloCurso());
                    }
                        
	            
	        } catch (NumberFormatException e) {
	            throw new ApplicationException("Error al leer el archivo, formato de numero incorrecto");
	        }
	        catch(IOException e) {
	        	throw new ApplicationException("Error al leer el archivo, error en la entrada / salida");
	        }
		
		
	}
	private static String quitarAcentos(String input) {
        if (input == null) {
            return null;
        }
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                         .replaceAll("\\p{M}", "");
    }
}
