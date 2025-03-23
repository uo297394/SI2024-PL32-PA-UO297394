package enviarSolicitud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import util.SwingUtil;
import util.Util;

public class EnviarSolicitudControlador {
private EnviarSolicitudVista v;
private EnviarSolicitudModelo m;
public EnviarSolicitudControlador(EnviarSolicitudVista v, EnviarSolicitudModelo m) {
	this.v=v;
	this.m=m;
	this.RellenaTabla();
	//Escuchamos el botón para generar el fichero cuando se pulsa
	this.v.getBotonEnviar().addActionListener(new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	generaFichero();
	    	RellenaTabla();
	        }});
	this.v.getBotonComprobarTitulos().addActionListener(new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	procesarFichero();
	    	modificaEtiqueta();
	    	deshabilitarEnviar();
	    	RellenaTablaRecibido();
	        }});
	
}
//Rellenamos la tabla con los colegiados en estado pendiente
public void RellenaTabla() {
	List<ColegiadoDTO> listaColegiados=this.m.getListaColegiados();
	String[] columnas= {"DNI", "nombre", "apellido","estado_solicitud" };
	String [] ticolumnas= {"DNI", "nombre", "apellido","estado_solicitud"};
	TableModel tablaCursos = SwingUtil.getTableModelFromPojos(listaColegiados, columnas);
	this.v.setTabla(tablaCursos);
	SwingUtil.autoAdjustColumns(this.v.getTablaColegiados());

}
public void RellenaTablaRecibido() {
	List<ColegiadoDTO> listaColegiados=this.m.getListaColegiadosAceptadosRechazados();
	String[] columnas= {"DNI", "nombre", "apellido","estado_solicitud" };
	String [] ticolumnas= {"DNI", "nombre", "apellido","estado_solicitud"};
	TableModel tablaCursos = SwingUtil.getTableModelFromPojos(listaColegiados, columnas);
	this.v.setTabla(tablaCursos);
	SwingUtil.autoAdjustColumns(this.v.getTablaColegiados());

}
//generamos el fichero a enviar con los DNIS de los colegiados
public void generaFichero() {
	JTable tabla=this.v.getTablaColegiados();
	int [] filas=tabla.getSelectedRows();
	String fileName = "ColegiadosPendientes.txt";
    String encoding = "UTF-8";
    try {
    	File file=new File(fileName);
    	file.createNewFile();
    
      PrintWriter writer = new PrintWriter(new FileWriter(file, false)); 
	for(int f:filas) {
		String DNI=tabla.getValueAt(f, 0).toString();
		this.m.cambiarEnviado(DNI);
		System.out.print(DNI);
		writer.print(DNI);
		writer.print(';');
		
	}
	writer.close();
    }
	catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
}
//Funciones relativas a la historia modificar estado de la solicitud
public void procesarFichero() {
	List<String> listaAceptados=new LinkedList<>();
	List<String> listaRechazados=new LinkedList<>();
	int numeroColegiadosAceptadosRechazados=0;
	String [] separadores= {";"};
	String DNI="";
	List<String[]> listaTitulaciones=Util.procesarFichero("titulaciones.txt", ";");
	for(String [] l:listaTitulaciones) {
		DNI=l[0];
		for(int i=1;i<l.length;i++) {
			if(m.EstaColegiado(DNI) && m.comprobarEnviado(DNI)) {
				numeroColegiadosAceptadosRechazados++;
				if(l[i].equals("Ingeniero en Informática") || l[i].equals("Licenciado en Ingeniería Informática") || l[i].equals("Máster en Ingeniería Informática")) {
						m.cambiarAprobado(DNI);
						listaAceptados.add(DNI);
						break;
				}
						m.cambiarDenegado(DNI);
						listaRechazados.add(DNI);
						
			}
			
					}
	}
	if(numeroColegiadosAceptadosRechazados==0) {
		JOptionPane.showMessageDialog(null,"Ningún título nuevo comprobado","Resultado",JOptionPane.INFORMATION_MESSAGE);
	}
	else {
	String aceptados=listaAceptados.toString();
	String rechazados=listaRechazados.toString();
	JOptionPane.showMessageDialog(null,"Colegiados aceptados:" + aceptados +"\n" +"Colegiados rechazados:"+rechazados.toString(), "Resultado",JOptionPane.INFORMATION_MESSAGE);
}
	}
public void modificaEtiqueta() {
	this.v.setEiqueta("Solicitudes");
}
public void deshabilitarEnviar() {
	this.v.getBotonEnviar().setEnabled(false);
}

}
