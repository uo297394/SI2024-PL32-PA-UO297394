package registrarCursos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.List;

public class ViewH2 extends JFrame {

    // Campos para datos del curso
    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JFormattedTextField txtFechaInicio;
    private JFormattedTextField txtFechaFin;
    private JTextField txtDuracion;
    private JTextField txtMaxPlazas;
    private JButton btnRegistrar;

    // Tabla para cuotas
    private JTable tableCuotas;
    private DefaultTableModel cuotasTableModel;

    public ViewH2() {
        setTitle("Registrar Curso");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        // Panel principal con GridBagLayout para una distribución más estética
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Título del curso
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Título:"), gbc);
        txtTitulo = new JTextField(20);
        gbc.gridx = 1;
        gbc.weightx = 1;
        mainPanel.add(txtTitulo, gbc);

        // Fila 1: Descripción (usamos un JTextArea en JScrollPane)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Descripción:"), gbc);
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        gbc.gridx = 1;
        gbc.weightx = 1;
        mainPanel.add(scrollDesc, gbc);

        // Fila 2: Fecha de Inicio
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Fecha Inicio (AAAA-MM-DD):"), gbc);
        try {
            MaskFormatter dateMask = new MaskFormatter("####-##-##");
            dateMask.setPlaceholderCharacter('_');
            txtFechaInicio = new JFormattedTextField(dateMask);
        } catch (ParseException e) {
            txtFechaInicio = new JFormattedTextField();
        }
        txtFechaInicio.setColumns(10);
        gbc.gridx = 1;
        gbc.weightx = 1;
        mainPanel.add(txtFechaInicio, gbc);

        // Fila 3: Fecha de Fin
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Fecha Fin (AAAA-MM-DD):"), gbc);
        try {
            MaskFormatter dateMask = new MaskFormatter("####-##-##");
            dateMask.setPlaceholderCharacter('_');
            txtFechaFin = new JFormattedTextField(dateMask);
        } catch (ParseException e) {
            txtFechaFin = new JFormattedTextField();
        }
        txtFechaFin.setColumns(10);
        gbc.gridx = 1;
        gbc.weightx = 1;
        mainPanel.add(txtFechaFin, gbc);

        // Fila 4: Duración
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Duración (horas):"), gbc);
        txtDuracion = new JTextField(10);
        gbc.gridx = 1;
        gbc.weightx = 1;
        mainPanel.add(txtDuracion, gbc);

        // Fila 5: Máximo de Plazas
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Máximo de Plazas:"), gbc);
        txtMaxPlazas = new JTextField(10);
        gbc.gridx = 1;
        gbc.weightx = 1;
        mainPanel.add(txtMaxPlazas, gbc);

        // Fila 6: Tabla de Cuotas (ocultando la columna de ID)
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        cuotasTableModel = new DefaultTableModel();
        cuotasTableModel.addColumn("ID Cuota");
        cuotasTableModel.addColumn("Colectivo");
        cuotasTableModel.addColumn("Cuota");
        tableCuotas = new JTable(cuotasTableModel);
        // Ocultar la columna "ID Cuota"
        tableCuotas.getColumnModel().getColumn(0).setMinWidth(0);
        tableCuotas.getColumnModel().getColumn(0).setMaxWidth(0);
        tableCuotas.getColumnModel().getColumn(0).setPreferredWidth(0);
        JScrollPane tableScroll = new JScrollPane(tableCuotas);
        tableScroll.setPreferredSize(new Dimension(350, 150));
        mainPanel.add(tableScroll, gbc);

        // Fila 7: Botón Registrar Curso (centrado)
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        btnRegistrar = new JButton("Registrar Curso");
        mainPanel.add(btnRegistrar, gbc);

        add(mainPanel);
    }

    // Método para poblar la tabla de cuotas
    public void populateCuotasTable(List<CuotaDTO> cuotas) {
        cuotasTableModel.setRowCount(0);
        for (CuotaDTO cuota : cuotas) {
            Object[] row = new Object[]{
                cuota.getIdCuota(), // Columna oculta
                cuota.getColectivo(),
                cuota.getCuota()
            };
            cuotasTableModel.addRow(row);
        }
    }

    // Getters para los componentes que se utilizarán en el controlador
    public JTextField getTxtTitulo() { return txtTitulo; }
    public JTextArea getTxtDescripcion() { return txtDescripcion; }
    public JFormattedTextField getTxtFechaInicio() { return txtFechaInicio; }
    public JFormattedTextField getTxtFechaFin() { return txtFechaFin; }
    public JTextField getTxtDuracion() { return txtDuracion; }
    public JTextField getTxtMaxPlazas() { return txtMaxPlazas; }
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JTable getTableCuotas() { return tableCuotas; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewH2 view = new ViewH2();
            view.setVisible(true);
        });
    }
}