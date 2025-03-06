package inscribirColegiado;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class InscribirColegiadoViewPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton btnInscColeg = new JButton("Inscribir Colegiado");
	private JTextField tfNumColeg;
	/**
	 * Create the panel.
	 */
	public InscribirColegiadoViewPanel() {
		this.setLayout(new MigLayout("", "[grow]", "[][][grow][][][][][][][][]"));
		//Label
				JLabel lblNumColeg = new JLabel("Numero de colegiado");
				this.add(lblNumColeg, "cell 0 1");
				//Adicion del textfield para introducir el numero de colegiado
				tfNumColeg = new JTextField();
				this.add(tfNumColeg, "cell 0 2,growx");
				tfNumColeg.setColumns(10);
				
				//Adicion del boton que se utiliza para registrar el plazo del curso seleccionado
				this.add(btnInscColeg, "cell 0 5");
	}
	public JButton getBtnInscColeg() {
		return btnInscColeg;
	}
	public void setBtnInscColeg(JButton btnInscColeg) {
		this.btnInscColeg = btnInscColeg;
	}
	public JTextField getTfNumColeg() {
		return tfNumColeg;
	}
	public void setTfNumColeg(JTextField tfNumColeg) {
		this.tfNumColeg = tfNumColeg;
	}

}
