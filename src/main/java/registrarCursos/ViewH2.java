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
    
    // Nuevos componentes para los requisitos adicionales
    private JCheckBox chkListaEspera;
    private JCheckBox chkCancelable;
    private JFormattedTextField txtFechaCancelacion;
    private JTextField txtPorcentajeCuota;
    
    // Botones
    private JButton btnRegistrar;
    private JButton btnVerCursos;
    private JButton btnRegistrarSesiones;

    public ViewH2() {
        setTitle("Registrar Curso");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 550); // Aumentado el alto para los nuevos campos
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        // Fila 6: Checkbox Lista de Espera
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Habilitar Lista de Espera:"), gbc);
        chkListaEspera = new JCheckBox();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(chkListaEspera, gbc);

        // Fila 7: Checkbox Cancelable
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Curso Cancelable:"), gbc);
        chkCancelable = new JCheckBox();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(chkCancelable, gbc);
        
        // Fila 8: Fecha Máxima Cancelación (inicialmente deshabilitado)
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Fecha Máx Cancelación (AAAA-MM-DD):"), gbc);
        txtFechaCancelacion = createFormattedDateField();
        txtFechaCancelacion.setEnabled(false);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtFechaCancelacion, gbc);
        
        // Fila 9: Porcentaje Cuota Devuelta (inicialmente deshabilitado)
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("% Cuota Devuelta:"), gbc);
        txtPorcentajeCuota = new JTextField(10);
        txtPorcentajeCuota.setEnabled(false);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtPorcentajeCuota, gbc);

        // Habilitar/Deshabilitar campos de cancelación según checkbox
        chkCancelable.addActionListener(e -> {
            boolean selected = chkCancelable.isSelected();
            txtFechaCancelacion.setEnabled(selected);
            txtPorcentajeCuota.setEnabled(selected);
        });

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Panel inferior para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnRegistrar = new JButton("Registrar Curso");
        btnVerCursos = new JButton("Registrar Cuota");
        btnRegistrarSesiones = new JButton("Registrar Sesiones");
        panelBotones.add(btnRegistrarSesiones);
        panelBotones.add(btnVerCursos);
        panelBotones.add(btnRegistrar);
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

    // Getters para los componentes nuevos
    public JCheckBox getChkListaEspera() {
        return chkListaEspera;
    }
    public JCheckBox getChkCancelable() {
        return chkCancelable;
    }
    public JFormattedTextField getTxtFechaCancelacion() {
        return txtFechaCancelacion;
    }
    public JTextField getTxtPorcentajeCuota() {
        return txtPorcentajeCuota;
    }
    
    // Getters ya existentes...
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