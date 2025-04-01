package cobrarRecibos;

import java.util.List;

import javax.swing.table.TableModel;

import enviarSolicitud.ColegiadoDTO;
import util.SwingUtil;

public class CobrarRecibosControlador {
	private CobrarRecibosVista v;
	private CobrarRecibosModelo m;
	public CobrarRecibosControlador(CobrarRecibosVista v, CobrarRecibosModelo m) {
	this.v=v;
	this.m=m;
	this.RellenaTabla();
	}
	public void RellenaTabla() {
		int año=2025;
		List<RecibosDTO> listaRecibos=this.m.ColegiadosRecibos(año);
		String[] columnas= {"idColegiado", "nombre", "id","estado","cuota" };
		String [] ticolumnas= {"idColegiado", "nombre", "id","estadoRecibo","cuota" };
		TableModel tablaCursos = SwingUtil.getTableModelFromPojos(listaRecibos, columnas);
		this.v.setTablaRecibos(tablaCursos);
		for(int i=0;i<ticolumnas.length;i++) {
			this.v.getTablaRecibos().getColumnModel().getColumn(i).setHeaderValue(ticolumnas[i]);
		}
		SwingUtil.autoAdjustColumns(this.v.getTablaRecibos());
		 this.v.getTablaRecibos().getTableHeader().repaint();
		
	}
	
	
	
}
