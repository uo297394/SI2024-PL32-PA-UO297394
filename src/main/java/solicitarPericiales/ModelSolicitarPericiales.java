package solicitarPericiales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.Database;

public class ModelSolicitarPericiales {

	private Database db = new Database();

	public int registrarSolicitante(String nombre, String apellido, String DNI, String direccion, String correo,
			String telefono, String fechaNacimiento) {
		int idSolicitante = -1; // Variable para almacenar el ID generado

		// Crear una instancia de la clase Database para obtener la conexión
		Database db = new Database();

		// Consulta SQL para insertar un nuevo solicitante en la base de datos
		String sql = "INSERT INTO Solicitante (nombre, apellidos, DNI, direccion, correo, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = db.getConnection(); // Obtener conexión a la base de datos
				// Preparar la sentencia SQL y permitir recuperar claves generadas
				// automáticamente
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			// Asignar los valores a los parámetros de la consulta
			stmt.setString(1, nombre);
			stmt.setString(2, apellido);
			stmt.setString(3, DNI);
			stmt.setString(4, direccion);
			stmt.setString(5, correo);
			stmt.setString(6, telefono);
			stmt.setString(7, fechaNacimiento);

			int affectedRows = stmt.executeUpdate();// Ejecutar la actualización e insertar el nuevo registro
			if (affectedRows > 0) {// Si se insertó al menos una fila, recuperar el ID generado
				try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						idSolicitante = generatedKeys.getInt(1); // Obtener el ID autogenerado
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // Imprimir cualquier error en la consola
		}
		return idSolicitante; // Retornar el ID del nuevo solicitante o -1 si hubo un error
	}

	public boolean registrarSolicitudPericial(int idSolicitante, String descripcion, String caracter) {
		String sql = "INSERT INTO Periciales (id,idSolicitante, descripcion,caracter, estado,fecha_pericial) VALUES (?,?,?,?, 'Pendiente',?)";
		Database db = new Database();

		try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, lastID());
			stmt.setInt(2, idSolicitante);
			stmt.setString(3, descripcion);
			stmt.setString(4, caracter);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fechaActualFormateada = sdf.format(new java.util.Date());
			stmt.setString(5, fechaActualFormateada);
			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private int lastID() {
		String ide = "SELECT COUNT(id) FROM Periciales";
		Object[] numerocolegiados = db.executeQueryArray(ide).get(0);
		int numerocoleg = (int) numerocolegiados[0];
		return numerocoleg + 1;
	}
}
