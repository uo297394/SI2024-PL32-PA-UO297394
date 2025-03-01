package inscribirse_peritos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.table.TableModel;

import util.SwingUtil;
import visualizar_cursos.CursoDisplayDTO;
import visualizar_cursos.ModelH3;
import visualizar_cursos.ViewH3;

public class Controller_inscribirse_peritos {
	
	private Model_inscribirse_peritos model;
	private View_inscribirse_peritos view;
	private String id="";

	public Controller_inscribirse_peritos(Model_inscribirse_peritos model, View_inscribirse_peritos view) {
		this.model= model;
		this.view = view;
		this.initView();
	}
	
	public void initController() {
		view.getBotonMostrarDatos().addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Solo procesamos el evento cuando se selecciona un ítem (y no cuando se deselecciona
		            id = view.getId().getText();
		            getListaDatosPersonales(id);
		            
		        }
		    
		});
	}
	
	public void initView() {
		view.setVisible(true); 
	}
	
		public void getListaDatosPersonales(String id) {
		    List<ColegiadoDisplayDTO> datos = model.getDatosPersonales(id);
		    TableModel tmodel = SwingUtil.getTableModelFromPojos(datos, 
		            new String[] {"nombre", "apellido", "direccion", "correo", "telefono", "dni", "fecha_nacimiento"});
		    view.getTableDatosPersonales().setModel(tmodel);
		    
		    // Establecer los títulos deseados en cada columna:
		    String[] titulos = {"Nombre", "Apellido", "Dirección", "Correo", "Teléfono", "DNI", "Fecha de Nacimiento"};
		    for (int i = 0; i < titulos.length; i++) {
		        view.getTableDatosPersonales().getColumnModel().getColumn(i).setHeaderValue(titulos[i]);
		    }
		    
		    SwingUtil.autoAdjustColumns(view.getTableDatosPersonales());
		}

}
