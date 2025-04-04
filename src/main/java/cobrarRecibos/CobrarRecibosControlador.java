package cobrarRecibos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JTable;
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
	this.v.getBotonRecibos().addActionListener(new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	generaFichero();
	    	RellenaTabla();
	        }});
	
	
	
	}
	public void RellenaTabla() {
		int año=2025;
		List<RecibosDTO> listaRecibos=this.m.ColegiadosRecibos(año);
		String[] columnas= {"idColegiado", "nombre", "idRecibo","estado","cuota" };
		String [] ticolumnas= {"idColegiado", "nombre", "idRecibo","estadoRecibo","cuota" };
		TableModel tablaCursos = SwingUtil.getTableModelFromPojos(listaRecibos, columnas);
		this.v.setTablaRecibos(tablaCursos);
		for(int i=0;i<ticolumnas.length;i++) {
			this.v.getTablaRecibos().getColumnModel().getColumn(i).setHeaderValue(ticolumnas[i]);
		}
		SwingUtil.autoAdjustColumns(this.v.getTablaRecibos());
		 this.v.getTablaRecibos().getTableHeader().repaint();
		
	}
	public void generaFichero() {
		JTable tabla=this.v.getTablaRecibos();
		int numeroFilas=tabla.getRowCount();
		String fileName = "RecibosEmitidos.txt";
	    String encoding = "UTF-8";
	    try {
	    	File file=new File(fileName);
	    	file.createNewFile();
	    
	      PrintWriter writer = new PrintWriter(new FileWriter(file, false)); 
		for(int i=0;i<numeroFilas;i++) {
			String id=tabla.getValueAt(i, 0).toString();
			int idEntero=Integer.parseInt(id);
			if(!(m.Recibo(idEntero, 2025))) {
		    int idRecibo=this.m.lastID();
			this.m.insertarRecibo(idRecibo, idEntero);
			System.out.print(id);
			writer.print(id);
			writer.print('\n');
			}
		}
		writer.close();
	
	
}
	    
	    catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	}}
