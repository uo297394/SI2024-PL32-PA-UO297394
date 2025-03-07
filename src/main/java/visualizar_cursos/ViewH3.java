package visualizar_cursos;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;

public class ViewH3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel Label1Curso;
	private JPanel panel2;
	private JTable table1;
	private JComboBox<Object> lstColectivos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewH3 frame = new ViewH3();
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
	public ViewH3() {
		setTitle("Historia3");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 343);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 2, 0, 0));
		
		Label1Curso = new JLabel("Seleccione un colectivo para visualizar los cursos ofertados");
		panel.add(Label1Curso);
		
		panel2 = new JPanel();
		contentPane.add(panel2, BorderLayout.CENTER);
		
		lstColectivos = new JComboBox<Object>();
		lstColectivos.setLocation(0, 0);
		lstColectivos.setSize(173, 78);;
		panel2.setLayout(null);
		panel2.add(lstColectivos);
		
		JLabel lblNewLabel = new JLabel("Cursos ofertados para el colectivo solicitado");
		lblNewLabel.setBounds(0, 77, 260, 40);
		panel2.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 127, 566, 143);
		panel2.add(scrollPane);
		
	
		
		table1 = new JTable();
		scrollPane.setViewportView(table1);
		
		table1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
	}
	
	public JTable getTablaCursos() { return table1;}
	public JComboBox<Object> getListaColectivos() { return lstColectivos; }
}
