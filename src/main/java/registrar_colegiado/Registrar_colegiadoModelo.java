package registrar_colegiado;

import java.util.*;
import giis.demo.util.Util;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
/**
 * Acceso a los datos de colegiados.
 **/
public class Registrar_colegiadoModelo {
	private Database db=new Database();
	public static final String registrar_colegiado="INSERT INTO Colegiados (id, nombre, apellido, DNI, direccion, fecha_nacimiento, numero_cuenta, banco, precolegiados, estado_solicitud, fecha_solicitud)\r\n"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

	public  void registro(String nombre,String apellidos,String DNI, String direccion, 
			String fecha_nacimiento, String numero_cuenta, String banco, 
			boolean precolegiados, int estado, String fecha_solicitud) {
		int pre=0;
		if(precolegiados==true) {pre=1;}
			db.executeUpdate(registrar_colegiado,pre, nombre,apellidos, DNI, direccion, fecha_nacimiento, numero_cuenta, banco, precolegiados,estado,fecha_solicitud);
	}

	
	
	
	public boolean EstaColegiado() {
String sql="SELECT COUNT(*) from Colegiados WHERE DNI = ?";	
return false;
}
}