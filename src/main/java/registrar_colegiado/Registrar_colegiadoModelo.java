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
			String fecha_nacimiento, int numero_cuenta, String banco, 
			boolean precolegiados, int estado, String fecha_solicitud) {
		int pre=0;
		int id=ultimoID();
		if(precolegiados==true) {pre=1;}
			db.executeUpdate(registrar_colegiado,id, nombre,apellidos, DNI, direccion, fecha_nacimiento, numero_cuenta, banco, pre,estado,fecha_solicitud);
	}

	public int ultimoID() {
		 String ide = "SELECT COUNT(id) FROM Colegiados";
		    List<Object[]> resultado = db.executeQueryArray(ide);
		    Object[] numerocolegiados=db.executeQueryArray(ide).get(0);
		    int numerocoleg=(int) numerocolegiados[0];
		    return numerocoleg;}
	
	
	public boolean EstaColegiado() {
String sql="SELECT COUNT(*) from Colegiados WHERE DNI = ?";	
	Object[] numerocolegiados=db.executeQueryArray(sql).get(0);
	int numerocoleg=(int) numerocolegiados[0];

return numerocoleg>1;
}
}