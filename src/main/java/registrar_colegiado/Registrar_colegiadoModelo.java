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

public boolean EstaColegiado() {
String sql="SELECT COUNT(*) from Colegiados WHERE DNI = ?";	
return false;
}
}