package enviarSolicitud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class prueba {

	public static List<String[]> procesarFichero(String nombreArchivo,String sep) {
		List<String[]> ls = new ArrayList<>();
		try {
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		 String linea;
		 linea=br.readLine();
		while(linea !=null) {
			ls.add(linea.split(sep));
			linea = br.readLine();
		}
		 }
	catch(IOException e) {e.printStackTrace();}
		return ls;
		}
	
	public static void main(String[] args) {
		List<String[]> lista = procesarFichero("titulaciones.txt",";");
		Iterator<String[]> it = lista.iterator();
		while(it.hasNext()) {
			for(String s: it.next()) {
				System.out.println(s);
			}
			System.out.println("Siguiente");
		}
		
	}
	
}
