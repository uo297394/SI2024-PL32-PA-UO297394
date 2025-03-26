package mostrar_historico_cursos_inscritos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import util.ApplicationException;
import util.SwingUtil;

public class CursosInscritosControlador {
	private CursosInscritosVista v;
	private CursosInscritosModelo m;
public CursosInscritosControlador(CursosInscritosVista v, CursosInscritosModelo m) {
	this.v=v;
	this.m=m;
	this.v.getBoton().addActionListener(new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent e) {
	      metodos();
	        }});
}
public void RellenaTabla(String DNI) {
	
	List<CursosInscritosDTO> listaCursos=this.m.getListaTodosCursos(DNI);
	String[] columnas= {"titulo_curso", "fecha_inicio_curso", "fecha_fin_curso", "duracion", "estado"};
	String [] ticolumnas= {"titulo", "fechaInicio", "fechaFin","duracion", "estado"};
	TableModel tablaCursos = SwingUtil.getTableModelFromPojos(listaCursos, columnas);
	
	v.setTablaCursos(tablaCursos);
	for(int i=0;i<ticolumnas.length;i++) {
	this.v.getTablaCursos().getColumnModel().getColumn(i).setHeaderValue(ticolumnas[i]);
}

	SwingUtil.autoAdjustColumns(v.getTablaCursos());
	 this.v.getTablaCursos().getTableHeader().repaint();

	
}
public void metodos() {
	try {
		String DNI=this.v.getDNI();
		this.m.hayDatos(DNI);
		RellenaTabla(DNI);
		RellenaTextArea(DNI);
	}
	catch(ApplicationException e) {
		return;	
	}
	
}
public void RellenaTextArea(String DNI) {
	int numeroCursos=this.m.getTotalCursos(DNI);
	int numeroHoras=this.m.getTotalHoras(DNI);
	String b=String.format("Total de cursos: %d \n Total de horas: %d", numeroCursos, numeroHoras);
	this.v.setTotalCursos(b);
}
}
