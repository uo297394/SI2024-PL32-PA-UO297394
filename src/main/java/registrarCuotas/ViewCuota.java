package registrarCuotas;

import registrarCursos.CursoDisplayDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewCuota extends JFrame {
    private JTable tableCursos;
    private DefaultTableModel tableModel;
    private JTextField txtColectivo;
    private JTextField txtCuota;
    private JButton btnRegistrarCuota;
    
    public ViewCuota() {
        setTitle("Registrar Cuota para Curso");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        // Panel superior: Tabla con cursos
        tableModel = new DefaultTableModel(new Object[]{"ID", "Título", "Fecha Inicio", "Fecha Fin"}, 0);
        tableCursos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableCursos);
        
        // Panel inferior: Campos para cuota
        JPanel panelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Colectivo
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(new JLabel("Colectivo:"), gbc);
        txtColectivo = new JTextField(20);
        gbc.gridx = 1;
        panelCampos.add(txtColectivo, gbc);
        
        // Cuota
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCampos.add(new JLabel("Cuota:"), gbc);
        txtCuota = new JTextField(10);
        gbc.gridx = 1;
        panelCampos.add(txtCuota, gbc);
        
        // Botón para registrar cuota
        btnRegistrarCuota = new JButton("Registrar Cuota");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelCampos.add(btnRegistrarCuota, gbc);
        
        // Organizar la ventana
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelCampos, BorderLayout.SOUTH);
    }
    
    // Método para cargar los cursos en la tabla
    public void cargarCursos(List<CursoDisplayDTO> cursos) {
        tableModel.setRowCount(0); // Limpiar filas previas
        for (CursoDisplayDTO curso : cursos) {
            tableModel.addRow(new Object[]{curso.getId(), curso.getTitulo_curso(), 
                curso.getFecha_inicio_curso(), curso.getFecha_fin_curso()});
        }
    }
    
    // Getter para obtener el curso seleccionado
    public CursoDisplayDTO getCursoSeleccionado() {
        int selectedRow = tableCursos.getSelectedRow();
        if (selectedRow == -1) {
            return null;
        }
        // Se asume que el primer valor es el ID y el segundo es el título; 
        // en una aplicación real, se podría asociar todo el objeto o utilizar un TableModel personalizado.
        CursoDisplayDTO curso = new CursoDisplayDTO();
        curso.setId((int) tableModel.getValueAt(selectedRow, 0));
        curso.setTitulo_curso((String) tableModel.getValueAt(selectedRow, 1));
        curso.setFecha_inicio_curso((String) tableModel.getValueAt(selectedRow, 2));
        curso.setFecha_fin_curso((String) tableModel.getValueAt(selectedRow, 3));
        return curso;
    }
    
    public String getColectivo() {
        return txtColectivo.getText().trim();
    }
    
    public String getCuota() {
        return txtCuota.getText().trim();
    }
    
    public JButton getBtnRegistrarCuota() {
        return btnRegistrarCuota;
    }
}