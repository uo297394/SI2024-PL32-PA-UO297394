package enviarSolicitud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import util.SwingUtil;

public class EnviarSolicitudControlador {
private EnviarSolicitudVista v;
private EnviarSolicitudModelo m;
public EnviarSolicitudControlador(EnviarSolicitudVista v, EnviarSolicitudModelo m) {
	this.v=v;
	this.m=m;
	this.RellenaTabla();
	this.v.getBoton().addActionListener(new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	generaFichero();
	        }});
}

public void RellenaTabla() {
	List<ColegiadoDTO> listaColegiados=this.m.getListaColegiados();
	String[] columnas= {"DNI", "nombre", "apellido"};
	String [] ticolumnas= {"DNI", "nombre", "apellido"};
	TableModel tablaCursos = SwingUtil.getTableModelFromPojos(listaColegiados, columnas);
	this.v.setTabla(tablaCursos);
	SwingUtil.autoAdjustColumns(this.v.getTabla());

}
public void generaFichero() {
	JTable tabla=this.v.getTabla();
	int [] filas=tabla.getSelectedRows();
	String fileName = "my-file.txt";
    String encoding = "UTF-8";
    try {
    	File file=new File(fileName);
    	file.createNewFile();
    
      PrintWriter writer = new PrintWriter(new FileWriter(file, false)); 
	for(int f:filas) {
		String DNI=tabla.getValueAt(f, 0).toString();
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
}
