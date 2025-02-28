package inscritos_cursos_formacion;

import java.util.List;
import javax.swing.table.TableModel;
import util.SwingUtil;

public class ControllerInscripciones {
    private ModelInscripciones modelInscripciones;
    private ModelCursos modelCursos; // Uso el modelo que ya tenía en este package
    private ViewInscripciones view;

    public ControllerInscripciones(ModelInscripciones modelInscripciones, ModelCursos modelCursos, ViewInscripciones view) {
        this.modelInscripciones = modelInscripciones;
        this.modelCursos = modelCursos;
        this.view = view;
        initController();
    }

    private void initController() {
        // Cargar la lista de cursos en el combo box
        List<CursoDisplayDTO2> cursos = modelCursos.getListaTodosCursos();
        for (CursoDisplayDTO2 curso : cursos) {
            view.getComboCursos().addItem(curso);
        }

        // Agregar listener para que, al cambiar la selección, se recarguen las inscripciones
        view.getComboCursos().addActionListener(e -> {
            CursoDisplayDTO2 seleccionado = (CursoDisplayDTO2) view.getComboCursos().getSelectedItem();
            if (seleccionado != null) {
                loadInscripciones(seleccionado.getId());
            }
        });
        
        // Mostrar la ventana
        view.setVisible(true);
    }

    /**
     * Carga las inscripciones para el curso con id 'idCurso' y actualiza la tabla y total.
     */
    public void loadInscripciones(int idCurso) {
        List<InscripcionDisplayDTO> inscripciones = modelInscripciones.getInscripcionesPorCurso(idCurso);
        TableModel tmodel = SwingUtil.getTableModelFromPojos(inscripciones, new String[] {
            "nombre", "apellido", "DNI", "estado"
        });
        view.getTable().setModel(tmodel);
        SwingUtil.autoAdjustColumns(view.getTable());
        view.getLblTotal().setText("Total inscritos: " + inscripciones.size());
    }
}