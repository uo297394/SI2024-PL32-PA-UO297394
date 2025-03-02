package mostrar_historico_cursos_inscritos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.TableModel;

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
	        RellenaTabla();
	        }});
}
public void RellenaTabla() {
	int numeroColegiado=Integer.parseInt(this.v.getIdColegiado());
	List<CursosInscritosDTO> listaCursos=this.m.getListaTodosCursos(numeroColegiado);
	String[] columnas= {"titulo_curso", "fecha_inicio_curso", "fecha_fin_curso", "duracion"};
	TableModel tablaCursos = SwingUtil.getTableModelFromPojos(listaCursos, columnas);
	this.v.setTablaCursos(tablaCursos);
	SwingUtil.autoAdjustColumns(v.getTablaCursos());
	
}
}
