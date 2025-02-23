package AlejandroMartín;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import giis.demo.tkrun.CarreraDisplayDTO;
import giis.demo.util.ApplicationException;
import giis.demo.util.SwingUtil;
import giis.demo.util.Util;

public class Controller {
	private Model model;
	private View view;
	private String lastSelectedKey="";
	
	public Controller(Model m, View v) {
		this.model=m;
		this.view=v;
		this.initView();
	}
	
	public void initController() {
		getListaColectivos();
		getListaCursosInicial();
		view.getListaColectivos().addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        // Solo procesamos el evento cuando se selecciona un ítem (y no cuando se deselecciona)
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            lastSelectedKey = e.getItem().toString();
		            getListaCursos(lastSelectedKey);
		            
		        }
		    }
		});
		
	}
	
	public void initView() {
		view.setVisible(true); 
	}
	
	public void getListaColectivos() {
		Object[] colectivos = new Object[] {"Estudiantes", "Empresas", "Investigadores", "Profesionales"};
		DefaultComboBoxModel<Object> lstColectivos = 
				new DefaultComboBoxModel<Object>(colectivos);
		view.getListaColectivos().setModel(lstColectivos);;
	}
	public void getListaCursosInicial() {
		List<CursoDisplayDTO> cursos=model.getListaTodosCursos();
		TableModel tmodel = SwingUtil.getTableModelFromPojos(cursos, 
	            new String[] {"titulo_curso", "fecha_inicio_curso", "fecha_fin_curso", 
	            		"estado", "max_plazas", "colectivos", "cuota"});
		view.getTablaCursos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablaCursos());
	}
	public void getListaCursos(String colec) {
		
		List<CursoDisplayDTO> cursos=model.getListaCursos(colec);
		TableModel tmodel = SwingUtil.getTableModelFromPojos(cursos, 
	            new String[] {"titulo_curso", "fecha_inicio_curso", "fecha_fin_curso", 
	            		"estado", "max_plazas", "colectivos", "cuota"});
		view.getTablaCursos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablaCursos());
		
	}
	
}
