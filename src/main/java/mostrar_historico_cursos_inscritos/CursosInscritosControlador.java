package mostrar_historico_cursos_inscritos;

import java.util.List;

import javax.swing.table.TableModel;

import util.SwingUtil;

public class CursosInscritosControlador {
	private CursosInscritosVista v;
	private CursosInscritosModelo m;
public CursosInscritosControlador(CursosInscritosVista v, CursosInscritosModelo m) {
	this.v=v;
	this.m=m;
}
public void RellenaTabla() {
	int numeroColegiado=Integer.parseInt(this.v.getIdColegiado());
	List<CursosInscritosDTO> listaCursos=this.m.getListaTodosCursos(numeroColegiado);
	String[] columnas= {"titulo", "fechaInicio","fechaFin", "horas"};
	TableModel tablaCursos = SwingUtil.getTableModelFromPojos(listaCursos, columnas);
	this.v.setTablaCursos(tablaCursos);
	
}
}
