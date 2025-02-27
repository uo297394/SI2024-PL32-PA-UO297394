package registrar_colegiado;

import java.util.*;
import util.Util;
import util.ApplicationException;
import util.Database;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
/**
 * Acceso a los datos de colegiados.
 **/
public class Registrar_colegiadoModelo {
	//Creamos una base de datos y realizamos un insert en ella con los valores de la tabla de Colegiados que el controlador nos pasará
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
//Esta función se encarga de asignar un id al próximo coelgiado, el cual es asigna de manera secuencial e incremental
	public int ultimoID() {
		 String ide = "SELECT COUNT(id) FROM Colegiados";
		    List<Object[]> resultado = db.executeQueryArray(ide);
		    Object[] numerocolegiados=db.executeQueryArray(ide).get(0);
		    int numerocoleg=(int) numerocolegiados[0];
		    return numerocoleg+1;}
	
	/**Consulta a la base de datos sobre si quien está haciendo la inscripción ya está colegiado
	 * mediante una consulta que comprueba si su DNI ya está en la base de datos**/
	public void EstaColegiado(String dni) {
		String sql="SELECT COUNT(*) from Colegiados WHERE DNI = ?";	
		Object[] numerocolegiados=db.executeQueryArray(sql,dni).get(0);
		int numerocoleg=(int) numerocolegiados[0];
		if(numerocoleg>0) {
			JOptionPane.showMessageDialog(null, "Usted ya está colegiado", "Error", JOptionPane.ERROR_MESSAGE);
			throw new ApplicationException("Ya existe un colegiado con ese DNI");
		}}
/**Método que comprueba que el valor introducido en el JTextField de la cuenta no sea nulo. Esta comprobación es necesaria
 * realizarla antes de tratar de pasar el parámetro a entero**/
	public void CuentaNoNula(JTextField cuenta) {
		if(cuenta.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo cuenta no puede ser nulo", "Error", JOptionPane.ERROR_MESSAGE);
			throw new ApplicationException("El campo cuenta no puede ser nulo");
			
			
		}
		
		
	}
	/**Método que comprueba que ningún otro parámetro del formulario haya quedado en blanco**/
	public void noNULO(String dni, String nombre, String apellidos, int bancario, String direccion, String titulacion, String banco, JDateChooser fecha) {
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
	if(fecha.getDate()==null) {
		JOptionPane.showMessageDialog(null, "El campo fecha no puede ser nulo", "Error", JOptionPane.ERROR_MESSAGE);
		throw new ApplicationException("El campo fecha no puede ser nulo");
	}

}

	
}