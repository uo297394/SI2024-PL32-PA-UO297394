package inscribirse_peritos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import util.SwingUtil;
// Asegúrate de importar el DTO correcto:
import inscribirse_peritos.ColegiadoDisplayDTO;

public class Controller_inscribirse_peritos {

    private Model_inscribirse_peritos model;
    private View_inscribirse_peritos view;
    private String id = "";

    public Controller_inscribirse_peritos(Model_inscribirse_peritos model, View_inscribirse_peritos view) {
        this.model = model;
        this.view = view;
        this.initView();
    }

    public void initController() {
        // Botón para mostrar datos asociados al ID
        view.getBotonMostrarDatos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = view.getId().getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Por favor, introduce un ID.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    view.getBotonSoli().setEnabled(false);
                    return;
                }
                // Consultar la base de datos para ver si hay datos asociados a ese ID
                List<ColegiadoDisplayDTO> datos = model.getDatosPersonales(id);
                if (datos.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "No se encontró el colegiado con el ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
                    view.getBotonSoli().setEnabled(false);
                    view.getTableDatosPersonales().setModel(new javax.swing.table.DefaultTableModel());
                } else {
                    // Habilitar el botón de enviar solicitud y actualizar la tabla
                    view.getBotonSoli().setEnabled(true);
                    getListaDatosPersonales(id);
                }
            }
        });

        // Botón para enviar la solicitud (mostrar datos en detalle)
        view.getBotonSoli().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDatosPersonales();
            }
        });
    }

    public void initView() {
        view.setVisible(true);
        // Deshabilitar el botón de enviar solicitud inicialmente
        view.getBotonSoli().setEnabled(false);
    }

    public void getListaDatosPersonales(String id) {
        List<ColegiadoDisplayDTO> datos = model.getDatosPersonales(id);
        TableModel tmodel = SwingUtil.getTableModelFromPojos(datos,
                new String[] {"nombre", "apellido", "direccion", "correo", "telefono", "dni", "fecha_nacimiento"});
        view.getTableDatosPersonales().setModel(tmodel);

        // Establecer los títulos deseados en cada columna:
        String[] titulos = {"Nombre", "Apellido", "Dirección", "Correo", "Teléfono", "DNI", "Fecha de Nacimiento"};
        for (int i = 0; i < titulos.length; i++) {
            view.getTableDatosPersonales().getColumnModel().getColumn(i).setHeaderValue(titulos[i]);
        }
        SwingUtil.autoAdjustColumns(view.getTableDatosPersonales());
    }

    private void mostrarDatosPersonales() {
        // Obtener el año de realización del curso pericial a partir del spinner
        Date fecha = (Date) view.getSpinner().getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String anioSolo = sdf.format(fecha);

        String id = view.getId().getText().trim();
        List<ColegiadoDisplayDTO> lista = model.getDatosPersonales(id);

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No se encontró el colegiado con el ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Se asume que solo se obtiene un resultado para el ID dado
        ColegiadoDisplayDTO colegiado = lista.get(0);

        // Construir el mensaje con los datos extraídos de la base de datos
        String mensaje = "La solicitud de inscripción a listas de peritos ha sido enviada:\n" +
                "Nombre: " + colegiado.getNombre() + "\n" +
                "Apellido: " + colegiado.getApellido() + "\n" +
                "Dirección: " + colegiado.getDireccion() + "\n" +
                "Correo: " + colegiado.getCorreo() + "\n" +
                "Teléfono: " + colegiado.getTelefono() + "\n" +
                "DNI: " + colegiado.getDni() + "\n" +
                "Fecha de Nacimiento: " + colegiado.getFecha_nacimiento() + "\n" +
                "Año de realización curso periciales: " + anioSolo + "\n" +
                "Información adicional: " + view.getAreaTexto().getText();

        // Mostrar el mensaje en un cuadro de diálogo
        JOptionPane.showMessageDialog(view, mensaje, "Justificante solicitud", JOptionPane.INFORMATION_MESSAGE);
    }
}

