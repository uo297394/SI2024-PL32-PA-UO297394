package pericialesAnuales;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;

public class ViewPericialesAnuales extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableInforme;
	private JComboBox comboEstado;
	private JSpinner spinnerYear;
	private JButton btnGenerarInforme;
	private  JSpinner.DateEditor añoColegiacion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPericialesAnuales frame = new ViewPericialesAnuales();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewPericialesAnuales() {
		setTitle("Informe periciales");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 727, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Calendar cal = Calendar.getInstance();
		Date currentDate = cal.getTime();

		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		Date maxDate = cal.getTime();
		
		SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, maxDate, java.util.Calendar.YEAR);
		
		spinnerYear = new JSpinner(dateModel);
		añoColegiacion = new JSpinner.DateEditor(spinnerYear, "yyyy");
		spinnerYear.setEditor(añoColegiacion);
		spinnerYear.setBounds(51, 11, 98, 20);
		contentPane.add(spinnerYear);
		
		JLabel lblNewLabel = new JLabel("Año:");
		lblNewLabel.setBounds(10, 10, 48, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Estado:");
		lblNewLabel_1.setBounds(159, 14, 48, 17);
		contentPane.add(lblNewLabel_1);
		
		String[] estados = {"todas", "pendiente", "asignado", "cancelada"};
		comboEstado = new JComboBox<>(estados);
		comboEstado.setBounds(204, 10, 86, 21);
		contentPane.add(comboEstado);
		
		btnGenerarInforme = new JButton("Generar informe");
		btnGenerarInforme.setBounds(339, 10, 141, 21);
		contentPane.add(btnGenerarInforme);
		
		
		tableInforme = new JTable();
		DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID Solicitud", "ID Solicitante", "Estado", "ID Perito", "Descripción"}, 0);
        tableInforme.setModel(model);
        
        
        JScrollPane scrollPane = new JScrollPane(tableInforme);
		scrollPane.setBounds(10, 53, 693, 292);
		contentPane.add(scrollPane);
		
		
	}
	public JSpinner getSpinnerYear() {
        return spinnerYear;
    }

    public JComboBox<String> getComboEstado() {
        return comboEstado;
    }

    public JButton getBtnGenerarInforme() {
        return btnGenerarInforme;
    }

    public JTable getTableInforme() {
        return tableInforme;
    }
}
