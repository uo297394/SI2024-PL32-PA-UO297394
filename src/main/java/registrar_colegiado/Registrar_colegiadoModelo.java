package registrar_colegiado;

import java.util.*;
import giis.demo.util.Util;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
import javax.swing.JOptionPane;
/**
 * Acceso a los datos de colegiados.
 **/
public class Registrar_colegiadoModelo {
	private Database db=new Database();
	public static final String registrar_colegiado="INSERT INTO Colegiados (id, nombre, apellido, DNI, direccion, fecha_nacimiento, numero_cuenta, banco, precolegiados, estado_solicitud, fecha_solicitud, titulacion)\r\n"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

	public  void registro(String nombre,String apellidos,String DNI, String direccion, 
			String fecha_nacimiento, int numero_cuenta, String banco, 
			boolean precolegiados, String estado, String fecha_solicitud, String titulacion) {
		int pre=0;
		int id=ultimoID();
		if(precolegiados==true) {pre=1;}
			db.executeUpdate(registrar_colegiado,id, nombre,apellidos, DNI, direccion, fecha_nacimiento, numero_cuenta, banco, pre,estado,fecha_solicitud, titulacion);
	}

	public int ultimoID() {
		 String ide = "SELECT COUNT(id) FROM Colegiados";
		    List<Object[]> resultado = db.executeQueryArray(ide);
		    Object[] numerocolegiados=db.executeQueryArray(ide).get(0);
		    int numerocoleg=(int) numerocolegiados[0];
		    return numerocoleg+1;}
	
	
	public void EstaColegiado(String dni) {
		String sql="SELECT COUNT(*) from Colegiados WHERE DNI = ?";	
		Object[] numerocolegiados=db.executeQueryArray(sql,dni).get(0);
		int numerocoleg=(int) numerocolegiados[0];
		if(numerocoleg>0) {
			JOptionPane.showMessageDialog(null, "Usted ya está colegiado", "Error", JOptionPane.ERROR_MESSAGE);
			throw new ApplicationException("Ya existe un colegiado con ese DNI");
		}}
public void noNULO(String dni, String nombre, String apellidos, int bancario, String direccion, String titulacion, String banco) {
	if(dni.isEmpty()) {
		JOptionPane.showMessageDialog(null, "El campo DNI no puede ser nulo", "Error", JOptionPane.ERROR_MESSAGE);
		throw new ApplicationException("El campo DNI no puede ser nulo");
	}
	if(nombre.isEmpty()) {
		JOptionPane.showMessageDialog(null, "El campo nombre no puede ser nulo", "Error", JOptionPane.ERROR_MESSAGE);
		throw new ApplicationException("El campo nombre no puede ser nulo");
	
	}
	if(apellidos.isEmpty()) {
		JOptionPane.showMessageDialog(null, "El campo apellidos no puede ser nulo", "Error", JOptionPane.ERROR_MESSAGE);
		throw new ApplicationException("El campo apellidos no puede ser nulo");
	}
	if(titulacion.isEmpty()) {
		JOptionPane.showMessageDialog(null, "El campo titulación no puede ser nulo", "Error", JOptionPane.ERROR_MESSAGE);
		throw new ApplicationException("El campo titulación no puede ser nulo");
	}
	if(direccion.isEmpty()) {
		JOptionPane.showMessageDialog(null, "El campo direccion no puede ser nulo", "Error", JOptionPane.ERROR_MESSAGE);
		throw new ApplicationException("El campo direccion no puede ser nulo");
	}
	if(banco.isEmpty()) {
		JOptionPane.showMessageDialog(null, "El campo banco no puede ser nulo", "Error", JOptionPane.ERROR_MESSAGE);
		throw new ApplicationException("El campo banco no puede ser nulo");
	}

}

	
}