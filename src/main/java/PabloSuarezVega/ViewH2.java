package PabloSuarezVega;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import javax.swing.text.DateFormatter;
import javax.swing.text.NumberFormatter;

public class ViewH2 extends JFrame {
    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JTextField txtDuracion;
    private JTextField txtMaxPlazas;
    private JTextField txtCuota;
    private JComboBox<String> comboColectivos;
    private JButton btnRegistrar;
    private JPanel panel;

    public ViewH2() {
        setTitle("Planificación de Nuevo Curso");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(490, 677);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Título del Curso:"));
        txtTitulo = new JTextField();
        panel.add(txtTitulo);

        panel.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextArea(3, 20);
        panel.add(new JScrollPane(txtDescripcion));
        
        panel.add(new JLabel("Fecha de Inicio (Año-Mes-Día):"));
        txtFechaInicio = new JTextField();
        panel.add(txtFechaInicio);

        panel.add(new JLabel("Fecha de Fin (Año-Mes-Día):"));
        txtFechaFin = new JTextField();
        panel.add(txtFechaFin);

        panel.add(new JLabel("Duración (horas):"));
        txtDuracion = new JTextField();
        panel.add(txtDuracion);

        panel.add(new JLabel("Máximo de Plazas:"));
        txtMaxPlazas = new JTextField();
        panel.add(txtMaxPlazas);

        panel.add(new JLabel("Cuota:"));
        txtCuota = new JTextField();
        txtCuota.setEnabled(false);
        txtCuota.setEditable(false);
        txtCuota.setText("2.0");
        panel.add(txtCuota);

        panel.add(new JLabel("Colectivo:"));
        // Solo se incluyen "colegiados" y "precolegiados"
        comboColectivos = new JComboBox<>(new String[] {"colegiados", "precolegiados"});
        panel.add(comboColectivos);
        
        comboColectivos.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Solo se procesa el evento cuando se selecciona el item
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String seleccionado = (String) comboColectivos.getSelectedItem();
                    if ("colegiados".equals(seleccionado)) {
                        txtCuota.setText("2.0");
                    } else if ("precolegiados".equals(seleccionado)) {
                        txtCuota.setText("3.0");
                    }
                }
            }
        });
        
        btnRegistrar = new JButton("Registrar Curso");
        // Para ocupar dos columnas
        panel.add(new JLabel());
        panel.add(btnRegistrar);

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    // Getters para que el controlador pueda acceder a los datos
    public JTextField getTxtTitulo() { return txtTitulo; }
    public JTextArea getTxtDescripcion() { return txtDescripcion; }
    public JTextField getTxtFechaInicio() { return txtFechaInicio; }
    public JTextField getTxtFechaFin() { return txtFechaFin; }
    public JTextField getTxtDuracion() { return txtDuracion; }
    public JTextField getTxtMaxPlazas() { return txtMaxPlazas; }
    public JTextField getTxtCuota() { return txtCuota; }
    public JComboBox<String> getComboColectivos() { return comboColectivos; }
    public JButton getBtnRegistrar() { return btnRegistrar; }

}