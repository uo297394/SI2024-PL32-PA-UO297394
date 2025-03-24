package registrarCursos;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;


public class ViewH2 extends JFrame {

    // Campos para datos del curso
    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JFormattedTextField txtFechaInicio;
    private JFormattedTextField txtFechaFin;
    private JTextField txtDuracion;
    private JTextField txtMaxPlazas;

    // Botones
    private JButton btnRegistrar;
    private JButton btnVerCursos;
    private JButton btnRegistrarSesiones;

    public ViewH2() {
        setTitle("Registrar Curso");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Se aumentó el ancho de la ventana para que se muestren los 3 botones sin necesidad de agrandar
        setSize(600, 450);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout para separar el formulario de los botones
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel para el formulario en el centro, usando GridBagLayout para lograr simetría
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Fila 0: Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Título:"), gbc);
        txtTitulo = new JTextField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtTitulo, gbc);

        // Fila 1: Descripción
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        formPanel.add(new JLabel("Descripción:"), gbc);
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(scrollDesc, gbc);

        // Fila 2: Fecha Inicio
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Fecha Inicio (AAAA-MM-DD):"), gbc);
        txtFechaInicio = createFormattedDateField();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtFechaInicio, gbc);

        // Fila 3: Fecha Fin
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Fecha Fin (AAAA-MM-DD):"), gbc);
        txtFechaFin = createFormattedDateField();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtFechaFin, gbc);

        // Fila 4: Duración
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Duración (horas):"), gbc);
        txtDuracion = new JTextField(10);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtDuracion, gbc);

        // Fila 5: Máximo de Plazas
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Máximo de Plazas:"), gbc);
        txtMaxPlazas = new JTextField(10);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtMaxPlazas, gbc);

        // Añadimos el formulario al panel principal en el centro
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Panel inferior para los 3 botones, centrados
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnRegistrarSesiones = new JButton("Registrar Sesiones");
        btnVerCursos = new JButton("Registrar Cuota");
        btnRegistrar = new JButton("Registrar Curso");
        panelBotones.add(btnRegistrarSesiones);
        panelBotones.add(btnVerCursos);
        panelBotones.add(btnRegistrar);

        // Añadimos el panel de botones en la parte inferior del panel principal
        mainPanel.add(panelBotones, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Método para crear campos de fecha formateados
    private JFormattedTextField createFormattedDateField() {
        try {
            MaskFormatter dateMask = new MaskFormatter("####-##-##");
            dateMask.setPlaceholderCharacter('_');
            return new JFormattedTextField(dateMask);
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }
    
    // Getters
    public JTextField getTxtTitulo() { return txtTitulo; }
    public JTextArea getTxtDescripcion() { return txtDescripcion; }
    public JFormattedTextField getTxtFechaInicio() { return txtFechaInicio; }
    public JFormattedTextField getTxtFechaFin() { return txtFechaFin; }
    public JTextField getTxtDuracion() { return txtDuracion; }
    public JTextField getTxtMaxPlazas() { return txtMaxPlazas; }
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnVerCursos() { return btnVerCursos; }
    public JButton getBtnRegistrarSesiones() { return btnRegistrarSesiones; }

}