package solicitarPericiales;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import util.Database;

public class Model_solicitar_periciales {
	
	private Database db = new Database();
	
	public int registrarSolicitante(String nombre, String apellido, String DNI, String direccion, String correo, String telefono, String fechaNacimiento) {
	    int idSolicitante = -1;  // Variable para almacenar el ID generado
	    Database db = new Database();
	    String sql = "INSERT INTO Solicitante (nombre, apellidos, DNI, direccion, correo, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = db.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        stmt.setString(1, nombre);
	        stmt.setString(2, apellido);
	        stmt.setString(3, DNI);
	        stmt.setString(4, direccion);
	        stmt.setString(5, correo);
	        stmt.setString(6, telefono);
	        stmt.setString(7,fechaNacimiento);

	        int affectedRows = stmt.executeUpdate();
	        if (affectedRows > 0) {
	            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    idSolicitante = generatedKeys.getInt(1);
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return idSolicitante;
	}
	public boolean registrarSolicitudPericial(int idSolicitante, String descripcion,String caracter) {
	    String sql = "INSERT INTO Periciales (idSolicitante, descripcion,caracter, estado) VALUES (?, ?,?, 'Pendiente')";
	    Database db = new Database();

	    try (Connection conn = db.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, idSolicitante);
	        stmt.setString(2, descripcion);

	        return stmt.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}





}
