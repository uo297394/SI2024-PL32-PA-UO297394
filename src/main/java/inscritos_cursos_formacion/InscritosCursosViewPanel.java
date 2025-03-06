package inscritos_cursos_formacion;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;

public class InscritosCursosViewPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
    private JLabel lblTotal;

	/**
	 * Create the panel.
	 */
	public InscritosCursosViewPanel() {
		this.setLayout(new MigLayout("", "[grow]", "[][][grow][][][][][][][][]"));
		// Panel central para la tabla
        table = new JTable();
        table.setName("tabInscritosCursos");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null); //readonly
        JScrollPane tablePanel = new JScrollPane(table);
        add(tablePanel, "cell 0 1,grow");
        
        // Panel inferior para mostrar el total de inscritos
        lblTotal = new JLabel("Total inscritos: 0");
        add(lblTotal, "cell 0 2,grow");
	}
	public JTable getTable() {
        return table;
    }
    public JLabel getLblTotal() {
        return lblTotal;
    }

}
