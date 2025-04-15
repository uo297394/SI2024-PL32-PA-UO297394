package cancelacion_cursos;

import java.sql.SQLException;
import util.ApplicationException;

public class CancelacionController {

    private CancelacionModel model;

    public CancelacionController() {
        this.model = new CancelacionModel();
    }

    /**
     * Procesa la solicitud de marcar un curso como cancelado y ajustar deuda.
     * @param cursoId El ID del curso a marcar.
     * @throws SQLException Si ocurre un error en el modelo al acceder a la BD.
     * @throws ApplicationException Si ocurre un error lógico (ej. curso no encontrado)
     */
    public void procesarMarcadoCanceladoYAjustarDeuda(int cursoId) throws SQLException, ApplicationException { // <-- Renombrado para claridad
        // Llama al método actualizado del modelo
        model.marcarComoCanceladoYAjustarDeuda(cursoId);
    }
}