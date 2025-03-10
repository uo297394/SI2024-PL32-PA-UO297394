package enviarSolicitud;

import java.util.List;

import javax.swing.table.TableModel;

import util.SwingUtil;

public class EnviarSolicitudControlador {
private EnviarSolicitudVista v;
private EnviarSolicitudModelo m;
public EnviarSolicitudControlador(EnviarSolicitudVista v, EnviarSolicitudModelo m) {
	this.v=v;
	this.m=m;
	this.RellenaTabla();
}
public void RellenaTabla() {
	List<ColegiadoDTO> listaColegiados=this.m.getListaColegiados();
	String[] columnas= {"DNI", "nombre", "apellido"};
	String [] ticolumnas= {"DNI", "nombre", "apellido"};
	TableModel tablaCursos = SwingUtil.getTableModelFromPojos(listaColegiados, columnas);
	this.v.setTabla(tablaCursos);
	SwingUtil.autoAdjustColumns(this.v.getTabla());

}
}
