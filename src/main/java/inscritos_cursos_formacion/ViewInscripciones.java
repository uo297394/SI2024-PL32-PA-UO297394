package inscritos_cursos_formacion;

import javax.swing.*;

import registrarCursos.CursoDisplayDTO;

import java.awt.*;

public class ViewInscripciones extends JFrame {
    private JTable table;
    private JLabel lblTotal;
    private JComboBox<CursoDisplayDTO2> comboCursos; // Combo para elegir el curso

    public ViewInscripciones() {
        setTitle("Inscripciones del Curso");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel superior con el combo de cursos
        JPanel panelNorth = new JPanel();
        panelNorth.add(new JLabel("Seleccione un curso:"));
        comboCursos = new JComboBox<>();
        panelNorth.add(comboCursos);
        add(panelNorth, BorderLayout.NORTH);
        
        // Panel central para la tabla
        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        // Panel inferior para mostrar el total de inscritos
        lblTotal = new JLabel("Total inscritos: 0");
        add(lblTotal, BorderLayout.SOUTH);
    }

    // Getters
    public JTable getTable() {
        return table;
    }
    public JLabel getLblTotal() {
        return lblTotal;
    }
    public JComboBox<CursoDisplayDTO2> getComboCursos() {
        return comboCursos;
    }
}