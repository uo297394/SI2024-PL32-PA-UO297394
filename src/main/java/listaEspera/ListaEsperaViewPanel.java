package listaEspera; // O el paquete que elijas

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel; // Para limpiar la tabla

import net.miginfocom.swing.MigLayout;

public class ListaEsperaViewPanel extends JPanel {
    private JTable tableListaEspera;
    private JLabel lblTotalListaEspera;

    public ListaEsperaViewPanel() {
        setLayout(new MigLayout("", "[grow]", "[][grow]"));

        lblTotalListaEspera = new JLabel("Total en lista de espera: 0");
        add(lblTotalListaEspera, "cell 0 0");

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, "cell 0 1,grow");

        tableListaEspera = new JTable();
        tableListaEspera.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableListaEspera.setDefaultEditor(Object.class, null); // Readonly
        scrollPane.setViewportView(tableListaEspera);
    }

    public JTable getTable() {
        return tableListaEspera;
    }

    public JLabel getLblTotal() {
        return lblTotalListaEspera;
    }
}