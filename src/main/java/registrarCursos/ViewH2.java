package registrarCursos;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import registrar_Sesiones.ControllerSesiones;
import registrar_Sesiones.ModelSesiones;
import registrar_Sesiones.SesionManagerView;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map; 

public class ViewH2 extends JFrame {
    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JScrollPane scrollDesc;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JTextField txtDuracion;
    private JTextField txtMaxPlazas;
    private JTextField txtCuota;
    private JComboBox<String> comboColectivos;
    private JButton btnRegistrar;
    private JPanel panel;
    
    private JButton btnAsignarSesiones;
    
    // Mapa para almacenar las posiciones y tamaños originales de cada componente (excepto el de descripción)
    private Map<Component, Rectangle> originalBounds = new HashMap<>();
    // Nuevo tamaño base (aumentamos la altura para que se vea el botón de "Asignar Sesiones")
    private final int baseWidth = 250;
    private final int baseHeight = 600; // antes era 520
    
    public ViewH2() {
        setTitle("Planificación de Nuevo Curso");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(baseWidth, baseHeight);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        panel = new JPanel(null);
        panel.setBounds(0, 0, baseWidth, baseHeight);
        getContentPane().add(panel);
        
        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Título del Curso:");
        lblTitulo.setBounds(10, 10, 120, 19);
        panel.add(lblTitulo);
        originalBounds.put(lblTitulo, lblTitulo.getBounds());
        
        txtTitulo = new JTextField();
        txtTitulo.setBounds(10, 29, 96, 19);
        panel.add(txtTitulo);
        originalBounds.put(txtTitulo, txtTitulo.getBounds());
        
        // --- DESCRIPCIÓN (no se redimensiona) ---
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(10, 60, 120, 19);
        panel.add(lblDescripcion);
        originalBounds.put(lblDescripcion, lblDescripcion.getBounds());
        
        txtDescripcion = new JTextArea();
        scrollDesc = new JScrollPane(txtDescripcion);
        scrollDesc.setBounds(10, 80, 200, 60);  // Tamaño fijo para descripción
        panel.add(scrollDesc);
        originalBounds.put(scrollDesc, scrollDesc.getBounds());
        
        // --- FECHA DE INICIO ---
        JLabel lblFechaInicio = new JLabel("Fecha de Inicio (Año-Mes-Día):");
        lblFechaInicio.setBounds(10, 150, 200, 19);
        panel.add(lblFechaInicio);
        originalBounds.put(lblFechaInicio, lblFechaInicio.getBounds());
        
        try {
            MaskFormatter dateMask = new MaskFormatter("####-##-##");
            txtFechaInicio = new JFormattedTextField(dateMask);
            txtFechaInicio.setBounds(10, 170, 96, 19);
            panel.add(txtFechaInicio);
            originalBounds.put(txtFechaInicio, txtFechaInicio.getBounds());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        // --- FECHA DE FIN ---
        JLabel lblFechaFin = new JLabel("Fecha de Fin (Año-Mes-Día):");
        lblFechaFin.setBounds(10, 200, 200, 19);
        panel.add(lblFechaFin);
        originalBounds.put(lblFechaFin, lblFechaFin.getBounds());
        
        try {
            MaskFormatter dateMask = new MaskFormatter("####-##-##");
            txtFechaFin = new JFormattedTextField(dateMask);
            txtFechaFin.setBounds(10, 220, 96, 19);
            panel.add(txtFechaFin);
            originalBounds.put(txtFechaFin, txtFechaFin.getBounds());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        // --- DURACIÓN ---
        JLabel lblDuracion = new JLabel("Duración (horas):");
        lblDuracion.setBounds(10, 250, 120, 19);
        panel.add(lblDuracion);
        originalBounds.put(lblDuracion, lblDuracion.getBounds());
        
        txtDuracion = new JTextField();
        txtDuracion.setBounds(10, 270, 96, 19);
        panel.add(txtDuracion);
        originalBounds.put(txtDuracion, txtDuracion.getBounds());
        
        // --- MÁXIMO DE PLAZAS ---
        JLabel lblMaxPlazas = new JLabel("Máximo de Plazas:");
        lblMaxPlazas.setBounds(10, 300, 120, 19);
        panel.add(lblMaxPlazas);
        originalBounds.put(lblMaxPlazas, lblMaxPlazas.getBounds());
        
        txtMaxPlazas = new JTextField();
        txtMaxPlazas.setBounds(10, 320, 96, 19);
        panel.add(txtMaxPlazas);
        originalBounds.put(txtMaxPlazas, txtMaxPlazas.getBounds());
        
        // --- CUOTA ---
        JLabel lblCuota = new JLabel("Cuota:");
        lblCuota.setBounds(10, 350, 120, 19);
        panel.add(lblCuota);
        originalBounds.put(lblCuota, lblCuota.getBounds());
        
        txtCuota = new JTextField();
        txtCuota.setBounds(10, 370, 96, 19);
        txtCuota.setEnabled(true);
        txtCuota.setEditable(false);
        txtCuota.setText("2.0");
        panel.add(txtCuota);
        originalBounds.put(txtCuota, txtCuota.getBounds());
        
        // --- COLECTIVO ---
        JLabel lblColectivo = new JLabel("Colectivo:");
        lblColectivo.setBounds(10, 400, 120, 19);
        panel.add(lblColectivo);
        originalBounds.put(lblColectivo, lblColectivo.getBounds());
        
        comboColectivos = new JComboBox<>(new String[] {"Colegiados", "Precolegiados"});
        comboColectivos.setBounds(10, 420, 96, 19);
        panel.add(comboColectivos);
        originalBounds.put(comboColectivos, comboColectivos.getBounds());
        comboColectivos.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String seleccionado = (String) comboColectivos.getSelectedItem();
                    if ("Colegiados".equals(seleccionado)) {
                        txtCuota.setText("2.0");
                    } else if ("Precolegiados".equals(seleccionado)) {
                        txtCuota.setText("3.0");
                    }
                }
            }
        });
        
        // --- BOTÓN REGISTRAR ---
        btnRegistrar = new JButton("Registrar Curso");
        btnRegistrar.setBounds(10, 450, 150, 30);
        panel.add(btnRegistrar);
        originalBounds.put(btnRegistrar, btnRegistrar.getBounds());
        
        // --- BOTÓN ASIGNAR SESIONES ---
        btnAsignarSesiones = new JButton("Asignar Sesiones");
        btnAsignarSesiones.setBounds(10, 490, 150, 30);  // Ahora queda visible por el aumento en la altura
        panel.add(btnAsignarSesiones);
        // Puedes agregar el listener para llamar a la historia de sesiones:
        btnAsignarSesiones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ControllerSesiones(new ModelSesiones(), new SesionManagerView());
            }
        });
        
        // Listener para redimensionar proporcionalmente (se excluye el área de descripción)
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                double xScale = (double) getWidth() / baseWidth;
                double yScale = (double) getHeight() / baseHeight;
                
                for (Component comp : panel.getComponents()) {
                    // Se excluye el área de descripción
                    if (comp == scrollDesc) {
                        continue;
                    }
                    Rectangle orig = originalBounds.get(comp);
                    if (orig != null) {
                        int newX = (int) (orig.x * xScale);
                        int newY = (int) (orig.y * yScale);
                        int newW = (int) (orig.width * xScale);
                        int newH = (int) (orig.height * yScale);
                        comp.setBounds(newX, newY, newW, newH);
                    }
                }
            }
        });
    }
    
    // Getters para acceder a los componentes desde el controlador
    public JTextField getTxtTitulo() { return txtTitulo; }
    public JTextArea getTxtDescripcion() { return txtDescripcion; }
    public JTextField getTxtFechaInicio() { return txtFechaInicio; }
    public JTextField getTxtFechaFin() { return txtFechaFin; }
    public JTextField getTxtDuracion() { return txtDuracion; }
    public JTextField getTxtMaxPlazas() { return txtMaxPlazas; }
    public JTextField getTxtCuota() { return txtCuota; }
    public JComboBox<String> getComboColectivos() { return comboColectivos; }
    public JButton getBtnRegistrar() { return btnRegistrar; }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewH2().setVisible(true);
        });
    }
}