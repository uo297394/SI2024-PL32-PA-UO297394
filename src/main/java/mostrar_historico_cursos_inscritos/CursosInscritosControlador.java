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
public void RellenaTabla(int numeroColegiado) {
	
	List<CursosInscritosDTO> listaCursos=this.m.getListaTodosCursos(numeroColegiado);
	String[] columnas= {"titulo_curso", "fecha_inicio_curso", "fecha_fin_curso", "duracion"};
	String [] ticolumnas= {"titulo", "fechaInicio", "fechaFin","duracion"};
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
	int numeroColegiado=Integer.parseInt(this.v.getIdColegiado());
	this.m.EstaColegiado(numeroColegiado);
	RellenaTabla(numeroColegiado);
	RellenaTextArea(numeroColegiado);}
	catch(NumberFormatException e){
		JOptionPane.showMessageDialog(null, "Número de colegiado Incorrecto. Debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
		return;
	}
	catch(ApplicationException e) {
		JOptionPane.showMessageDialog(null, "Número de colegiado incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
		return;	
	}
	
}
public void RellenaTextArea(int numeroColegiado) {
	int numeroCursos=this.m.getTotalCursos(numeroColegiado);
	int numeroHoras=this.m.getTotalHoras(numeroColegiado);
	System.out.print(numeroCursos);
	String b=String.format("Total de cursos: %d \n Total de horas: %d", numeroCursos, numeroHoras);
	this.v.setTotalCursos(b);
}
}
