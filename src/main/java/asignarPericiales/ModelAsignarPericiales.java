package asignarPericiales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import util.Database;

public class ModelAsignarPericiales {

	private Database db;

	public ModelAsignarPericiales() {
		this.db = new Database();
	}

	/**
	 * Obtiene la lista de solicitudes de periciales con estado "pendiente" para
	 * asignación.
	 * 
	 * @return Lista de SolicitudesDisplayDTO
	 */
	public List<SolicitudesDisplayDTO> getListaSolicitudes() {
		String sql = "SELECT id, estado, idSolicitante, caracter, descripcion "
				+ "FROM Periciales WHERE estado LIKE 'pendiente'";
		return db.executeQueryPojo(SolicitudesDisplayDTO.class, sql);
	}

	/**
	 * Obtiene la lista de peritos disponibles, ordenados por orden_TAP.
	 * 
	 * @return Lista de ColegiadosDisplayDTO
	 */
	public List<ColegiadosDisplayDTO> getListaPeritos() {
		String sql = "SELECT id, nombre, apellido, correo, telefono, orden_TAP "
				+ "FROM Colegiados WHERE es_perito = true ORDER BY orden_TAP ASC";
		return db.executeQueryPojo(ColegiadosDisplayDTO.class, sql);
	}

	/**
	 * Ejecuta una actualización SQL y devuelve el número de filas afectadas.
	 */
	public int executeUpdate(String sql, Object... params) {
		try (Connection conn = db.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			for (int i = 0; i < params.length; i++) {
				stmt.setObject(i + 1, params[i]);
			}

			return stmt.executeUpdate(); // Devuelve el número de filas afectadas
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; // En caso de error, devuelve 0 filas afectadas
		}
	}

	/**
	 * Asigna un perito a una solicitud: - Actualiza la solicitud en la tabla
	 * Periciales asignándole el perito (idColegiado) y cambiando su estado a
	 * 'asignado'. - Luego, actualiza el perito para que su orden_TAP pase al final
	 * de la lista FIFO (maxOrden + 1). Todo ello en una transacción.
	 * 
	 * @param idSolicitud ID de la solicitud a asignar
	 * @param idPerito    ID del perito a asignar
	 * @return true si la asignación se realizó correctamente, false en caso
	 *         contrario.
	 */
	public boolean asignarPerito(int idSolicitud, int idPerito) {
		// SQL para actualizar la solicitud: asigna el perito y cambia el estado
		String sqlUpdateSolicitud = "UPDATE Periciales SET idColegiado = ?, estado = 'asignado' WHERE id = ?";
		// SQL para obtener el valor máximo de orden_TAP entre los peritos
		String sqlMaxOrden = "SELECT MAX(orden_TAP) AS maxOrden FROM Colegiados WHERE es_perito = true";
		// SQL para actualizar el orden_TAP del perito asignado
		String sqlUpdateOrden = "UPDATE Colegiados SET orden_TAP = ? WHERE id = ?";

		try (Connection conn = db.getConnection()) {
			conn.setAutoCommit(false); // Iniciar la transacción

			// 1. Actualizar la solicitud: asignar perito y cambiar estado
			try (PreparedStatement stmtSolicitud = conn.prepareStatement(sqlUpdateSolicitud)) {
				stmtSolicitud.setInt(1, idPerito);
				stmtSolicitud.setInt(2, idSolicitud);
				int filasActualizadas = stmtSolicitud.executeUpdate();
				if (filasActualizadas == 0) {
					conn.rollback();
					return false;
				}
			}

			// 2. Obtener el valor máximo actual de orden_TAP entre los peritos
			int maxOrden = 0;
			try (PreparedStatement stmtMax = conn.prepareStatement(sqlMaxOrden);
					ResultSet rs = stmtMax.executeQuery()) {
				if (rs.next()) {
					maxOrden = rs.getInt("maxOrden");
				}
			}

			// 3. Actualizar el perito asignado: moverlo al final (orden_TAP = maxOrden + 1)
			try (PreparedStatement stmtUpdateOrden = conn.prepareStatement(sqlUpdateOrden)) {
				stmtUpdateOrden.setInt(1, maxOrden + 1);
				stmtUpdateOrden.setInt(2, idPerito);
				stmtUpdateOrden.executeUpdate();
			}

			conn.commit(); // Confirmar la transacción
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Obtiene las solicitudes en formato de tabla (para JTable).
	 * 
	 * @return Arreglo de objetos con los datos de las solicitudes.
	 */
	public Object[][] obtenerSolicitudesParaTabla() {
		List<SolicitudesDisplayDTO> solicitudes = getListaSolicitudes();
		Object[][] data = new Object[solicitudes.size()][5];

		for (int i = 0; i < solicitudes.size(); i++) {
			SolicitudesDisplayDTO solicitud = solicitudes.get(i);
			data[i][0] = solicitud.getId();
			data[i][1] = solicitud.getEstado();
			data[i][2] = solicitud.getIdSolicitante();
			data[i][3] = solicitud.getCaracter();
			data[i][4] = solicitud.getDescripcion();
		}
		return data;
	}

	/**
	 * Obtiene los peritos en formato de tabla (para JTable).
	 * 
	 * @return Arreglo de objetos con los datos de los peritos.
	 */
	public Object[][] obtenerPeritosParaTabla() {
		List<ColegiadosDisplayDTO> peritos = getListaPeritos();
		Object[][] data = new Object[peritos.size()][6];

		for (int i = 0; i < peritos.size(); i++) {
			ColegiadosDisplayDTO perito = peritos.get(i);
			data[i][0] = perito.getId();
			data[i][1] = perito.getNombre();
			data[i][2] = perito.getApellido();
			data[i][3] = perito.getCorreo();
			data[i][4] = perito.getTelefono();
			data[i][5] = perito.getOrden_TAP();
		}
		return data;
	}
}
