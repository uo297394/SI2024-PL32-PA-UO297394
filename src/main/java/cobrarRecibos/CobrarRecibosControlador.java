package cobrarRecibos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import enviarSolicitud.ColegiadoDTO;
import util.SwingUtil;
import util.Util;
//Clase que gestiona el cobro de los recibos de un colegiado
public class CobrarRecibosControlador {
	private CobrarRecibosVista v;
	private CobrarRecibosModelo m;
	public CobrarRecibosControlador(CobrarRecibosVista v, CobrarRecibosModelo m) {
	this.v=v;
	this.m=m;
	this.RellenaTabla();
	establecerEtiqueta();
	this.v.getBotonRecibos().addActionListener(new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	generaFichero();
	    	RellenaTabla();
	        }});
	
	this.v.getBotonCobrarRecibos().addActionListener(new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	procesarFichero();
	    	RellenaTabla();
	        }});
	
	}
	//Función que se encarga de rellenar la tabla de la vista con los datos sobre los colegiados y sus recibos
	private void RellenaTabla() {
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
	//Función que genera el fichero a enviar al banco con los datos de los colegiados que aún no había emitido el recibo anual
	private void generaFichero() {
		JTable tabla=this.v.getTablaRecibos();
		int numeroFilas=tabla.getRowCount();
		String fileName = "RecibosEmitidos.txt";
	    String encoding = "UTF-8";
	    try {
	    	File file=new File(fileName);
	    	file.createNewFile();
	    
	      PrintWriter writer = new PrintWriter(new FileWriter(file, false)); 
	      writer.print("id;cuota;año_emitido;DNI;numero_cuenta;fecha_emitido\n");
	      String fechaHoy=Util.getTodayISO();
	      int año=Integer.parseInt(fechaHoy.split("-")[0]);
		for(int i=0;i<numeroFilas;i++) {
			String id=tabla.getValueAt(i, 0).toString();
			int idEntero=Integer.parseInt(id);
			
			if(!(m.Recibo(idEntero, año))) {
			this.m.insertarRecibo(idEntero);
			List<Object []> datos=this.m.datosColegiado(año, idEntero);
			Object [] objetos=datos.get(0);
			for(Object o:objetos) {
			writer.print(o.toString());
			writer.print(';');}
			writer.print("\n");
			}
		}
		writer.close();
	
	
}
	    
	    catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	}
	public void procesarFichero() {
		String [] separadores= {";"};
		int id=0;
		int año=Integer.parseInt(Util.getTodayISO().split("-")[0]); 
		List<String[]> listaRecibos=Util.procesarFichero("RecibosBanco.txt", ";");
		for(String [] l:listaRecibos) {
			id=Integer.parseInt(l[0]);
			//recorremos cada fila del fichero (almacenado en su conjunto en una lista de String[])
				//Solo hacemos algo si el recibo del fichero está "emitido"
				if(m.Emitido(id, año)) {
					//Variable utilizada para comprobar que se ha realizado la comprobación de al menos un título.
					//en el caso de que que el recibo esté aprobado pasará a aprobado, en caso de que esté devuelto pasará a devuelto
					if(l[1].equals("pagado") ) {
							m.cambiarPagado(id);
				}
					else if (l[1].equals("devuelto") ) {
						m.cambiarDevuelto(id);
			}
				
						}
		}	
}
	public void establecerEtiqueta() {
		String fechaHoy=Util.getTodayISO();
		String año=fechaHoy.split("-")[0];
		this.v.setEtiquetaAño(año);
		
	}
	}
