
package registrar_Sesiones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import registrarCursos.CursoDisplayDTO;
import registrarCursos.ModelH2;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class SesionManagerView extends JFrame {
    // Tablas para mostrar cursos y sesiones
    private JTable cursosTable;
    private DefaultTableModel cursosTableModel;
    
    private JTable sesionesTable;
    private DefaultTableModel sesionesTableModel;
    
    // Campos para agregar una nueva sesión
    private JTextField txtSesionNombre;
    private JFormattedTextField txtSesionFecha;
    private JFormattedTextField txtSesionHora;
    private JTextField txtSesionDuracion;
    
    // Botón único para guardar sesión
    private JButton btnGuardarSesion;
    
    public SesionManagerView() {
        setTitle("Gestión de Sesiones por Curso");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));
        
        // ────────── Panel superior: Cursos ──────────
        JPanel panelCursos = new JPanel(new BorderLayout());
        panelCursos.setBorder(BorderFactory.createTitledBorder("Cursos Registrados"));
        panelCursos.setPreferredSize(new Dimension(650, 100));
        
        cursosTableModel = new DefaultTableModel(
            new Object[]{"ID", "Título", "Inicio", "Fin", "Duración"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cursosTable = new JTable(cursosTableModel);
        // Ocultar la columna de ID para que el usuario no la vea
        cursosTable.removeColumn(cursosTable.getColumnModel().getColumn(0));
        
        JScrollPane scrollCursos = new JScrollPane(cursosTable);
        scrollCursos.setPreferredSize(new Dimension(600, 80));
        panelCursos.add(scrollCursos, BorderLayout.CENTER);
        
        // ────────── Panel central: Sesiones ──────────
        JPanel panelSesiones = new JPanel(new BorderLayout());
        panelSesiones.setBorder(BorderFactory.createTitledBorder("Sesiones del Curso Seleccionado"));
        panelSesiones.setPreferredSize(new Dimension(650, 150));
        
        sesionesTableModel = new DefaultTableModel(
            new Object[]{"Nombre", "Fecha", "Hora Inicio", "Duración"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        sesionesTable = new JTable(sesionesTableModel);
        JScrollPane scrollSesiones = new JScrollPane(sesionesTable);
        scrollSesiones.setPreferredSize(new Dimension(600, 120));
        panelSesiones.add(scrollSesiones, BorderLayout.CENTER);
        
        // ────────── Panel inferior: Añadir Sesión ──────────
        JPanel panelAgregarSesion = new JPanel(new GridBagLayout());
        panelAgregarSesion.setBorder(BorderFactory.createTitledBorder("Añadir Sesión"));
        panelAgregarSesion.setPreferredSize(new Dimension(650, 180));
        
        GridBagConstraints gbc;
        
        // Fila 0: Nombre
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        panelAgregarSesion.add(new JLabel("Nombre:"), gbc);
        
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1; gbc.gridy = 0;
        txtSesionNombre = new JTextField(12);
        panelAgregarSesion.add(txtSesionNombre, gbc);
        
        // Fila 1: Fecha
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 1;
        panelAgregarSesion.add(new JLabel("Fecha (AAAA-MM-DD):"), gbc);
        
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1; gbc.gridy = 1;
        try {
            MaskFormatter dateMask = new MaskFormatter("####-##-##");
            dateMask.setPlaceholderCharacter('_');
            txtSesionFecha = new JFormattedTextField(dateMask);
            txtSesionFecha.setColumns(10);
        } catch (ParseException e) {
            txtSesionFecha = new JFormattedTextField();
        }
        panelAgregarSesion.add(txtSesionFecha, gbc);
        
        // Fila 2: Hora inicio
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 2;
        panelAgregarSesion.add(new JLabel("Hora inicio (HH:MM):"), gbc);
        
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1; gbc.gridy = 2;
        try {
            MaskFormatter timeMask = new MaskFormatter("##:##");
            timeMask.setPlaceholderCharacter('_');
            txtSesionHora = new JFormattedTextField(timeMask);
            txtSesionHora.setColumns(10);
        } catch (ParseException e) {
            txtSesionHora = new JFormattedTextField();
        }
        panelAgregarSesion.add(txtSesionHora, gbc);
        
        // Fila 3: Duración
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 3;
        panelAgregarSesion.add(new JLabel("Duración (horas):"), gbc);
        
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1; gbc.gridy = 3;
        txtSesionDuracion = new JTextField(5);
        panelAgregarSesion.add(txtSesionDuracion, gbc);
        
        // Fila 4: Botón único para guardar la sesión
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        btnGuardarSesion = new JButton("Guardar Sesión");
        panelAgregarSesion.add(btnGuardarSesion, gbc);
        
        // Añadir paneles a la ventana principal
        add(panelCursos, BorderLayout.NORTH);
        add(panelSesiones, BorderLayout.CENTER);
        add(panelAgregarSesion, BorderLayout.SOUTH);
        
        // Cargar cursos desde la base de datos
        cargarCursos();
        
        // Actualizar el estado del botón según la selección de curso
        cursosTable.getSelectionModel().addListSelectionListener(e -> {
            boolean seleccionado = cursosTable.getSelectedRow() >= 0;
            btnGuardarSesion.setEnabled(seleccionado);
        });
    }
    
    public void cargarCursos() {
        ModelH2 modelH2 = new ModelH2();
        List<CursoDisplayDTO> listaCursos = modelH2.getAllCursosSesiones();
        cursosTableModel.setRowCount(0);
        for (CursoDisplayDTO curso : listaCursos) {
            cursosTableModel.addRow(new Object[]{
                curso.getId(),
                curso.getTitulo_curso(),
                curso.getFecha_inicio_curso(),
                curso.getFecha_fin_curso(),
                curso.getDuracion()
            });
        }
    }
    
    public void refreshSesionesTable(List<SesionDTO> sesiones) {
        sesionesTableModel.setRowCount(0);
        for (SesionDTO s : sesiones) {
            sesionesTableModel.addRow(new Object[]{
                s.getNombre(),
                s.getFecha(),
                s.getHoraInicio(),
                s.getDuracion()
            });
        }
    }
    
    public int getSelectedCursoId() {
        int row = cursosTable.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(cursosTableModel.getValueAt(row, 0).toString());
            return id;
        }
        return -1;
    }
    
    public int getSelectedCursoDuracion() {
        int row = cursosTable.getSelectedRow();
        if (row >= 0) {
            return Integer.parseInt(cursosTableModel.getValueAt(row, 4).toString());
        }
        return 0;
    }
    
    // Recupera los datos de la sesión ingresada en el formulario
    public SesionDTO getSesionFromForm() {
        String nombre = txtSesionNombre.getText().trim();
        String fecha = txtSesionFecha.getText().trim();
        String hora = txtSesionHora.getText().trim();
        String duracionStr = txtSesionDuracion.getText().trim();
        
        if (nombre.isEmpty() || fecha.isEmpty() || hora.isEmpty() || duracionStr.isEmpty()) {
            showError("Complete todos los campos para la sesión.");
            return null;
        }
        
        int duracion;
        try {
            duracion = Integer.parseInt(duracionStr);
        } catch (NumberFormatException ex) {
            showError("La duración debe ser un número entero.");
            return null;
        }
        
     // Validación para que la hora no supere las 23:59
        try {
            LocalTime t = LocalTime.parse(hora);
            if (t.isAfter(LocalTime.of(23, 59))) {
                showError("La hora de inicio no puede ser mayor a 23:59.");
                return null;
            }
        } catch (DateTimeParseException e) {
            showError("Formato de hora inválido.");
            return null;
        }
        
        return new SesionDTO(nombre, fecha, hora, duracion);
    }
    
    // Configuración del listener para el botón único de guardar sesión
    public void setGuardarSesionListener(ActionListener listener) {
        btnGuardarSesion.addActionListener(listener);
    }
    
    public LocalDate getSelectedCursoInicio() {
        int row = cursosTable.getSelectedRow();
        if (row >= 0) {
            String inicioStr = cursosTableModel.getValueAt(row, 2).toString();
            return LocalDate.parse(inicioStr);
        }
        return null;
    }
    
    public LocalDate getSelectedCursoFin() {
        int row = cursosTable.getSelectedRow();
        if (row >= 0) {
            String finStr = cursosTableModel.getValueAt(row, 3).toString();
            return LocalDate.parse(finStr);
        }
        return null;
    }
    
    // Notifica cuando se selecciona un curso en la tabla
    public void setCursoSelectedListener(Runnable listener) {
        cursosTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listener.run();
            }
        });
    }
    
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Limpia los campos del formulario tras guardar la sesión
    public void clearSesionForm() {
        txtSesionNombre.setText("");
        txtSesionFecha.setValue(null);
        txtSesionHora.setValue(null);
        txtSesionDuracion.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SesionManagerView view = new SesionManagerView();
            view.setVisible(true);
        });
    }
}