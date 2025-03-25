package pagoInscripcion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import cursosActions.CursosActionsController;
import util.ApplicationException;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class PagoInscripcionView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton rdbtnTarjeta = new JRadioButton("Tarjeta");
	JRadioButton rdbtnTransferencia = new JRadioButton("Transferencia Bancaria");
	JPanel pTarjeta = new JPanel();
	JPanel pTransferencia = new JPanel();
	private final JLabel lblTransferencia = new JLabel("<html>Al aceptar el pago por transferencia, <br>dispondrá de <b>48 horas</b> para realizar una transferencia a la siguiente cuenta:<br> XXXX-XXXX-XXXX-XXXX <br>Recuerde poner como concepto el curso al que se inscribe para así poder identificar que el pago ha sido recibido correctamente </html>");
	private JTextField tfTarjeta;
	private JTextField tfCVC;
	private JTextField tfFechaCad;
	private final Action showTarjeta = new SwingAction();
	private final Action showTransferencia = new SwingAction_1();
	CursosActionsController key;

	/**
	 * Create the frame.
	 */
	public PagoInscripcionView(CursosActionsController key) {
		this.key=key;
		this.setTitle("Realizar Pago de la Inscripción");
		this.setName("Realizar Pago de la Inscripción");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 492, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		pTransferencia.setVisible(false);
		buttonGroup.add(rdbtnTarjeta);
		rdbtnTarjeta.setAction(showTarjeta);
		rdbtnTarjeta.setBounds(312, 41, 134, 34);
		contentPane.add(rdbtnTarjeta);
		
		
		buttonGroup.add(rdbtnTransferencia);
		rdbtnTransferencia.setAction(showTransferencia);
		rdbtnTransferencia.setBounds(312, 77, 235, 34);
		contentPane.add(rdbtnTransferencia);
		pTarjeta.setLayout(null);
		
		pTransferencia.setBounds(10, 125, 458, 184);
		contentPane.add(pTransferencia);
		pTransferencia.setLayout(null);
		lblTransferencia.setBounds(10, 10, 315, 113);
		pTransferencia.add(lblTransferencia);
		
		tfTarjeta = new JTextField();
		tfTarjeta.setBounds(10, 24, 214, 19);
		pTarjeta.add(tfTarjeta);
		tfTarjeta.setColumns(10);
		
		tfCVC = new JTextField();
		tfCVC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(tfCVC.getText().length() >= 3) {
					arg0.consume();
				}
			}
		});
		tfCVC.setBounds(10, 70, 44, 19);
		pTarjeta.add(tfCVC);
		tfCVC.setColumns(10);
		
		tfFechaCad = new JTextField();
		tfFechaCad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(tfFechaCad.getText().length() == 2 && !tfFechaCad.getText().contains('/'+"")) {
					tfFechaCad.setText(tfFechaCad.getText()+"/");
				}
				if(tfFechaCad.getText().length() >= 5) {
					arg0.consume();
				}
			}
		});
		tfFechaCad.setBounds(10, 113, 70, 19);
		pTarjeta.add(tfFechaCad);
		tfFechaCad.setColumns(10);
		
		JLabel lblTarjeta = new JLabel("Indique su numero de tarjeta:");
		lblTarjeta.setBounds(9, 10, 179, 13);
		pTarjeta.add(lblTarjeta);
		
		JLabel lblCVC = new JLabel("CVC:");
		lblCVC.setBounds(9, 53, 71, 13);
		pTarjeta.add(lblCVC);
		
		JLabel lblFechaCad = new JLabel("Fecha de caducidad (MM/yy):");
		lblFechaCad.setBounds(10, 99, 178, 13);
		pTarjeta.add(lblFechaCad);
		
		JLabel lblSelMetPag = new JLabel("Seleccione su metodo de pago:");
		lblSelMetPag.setBounds(296, 22, 223, 13);
		contentPane.add(lblSelMetPag);
		
		JLabel lblRelPagoIns = new JLabel("<html><b>Realice el pago de la inscripción</b><br>Si usted realiza el pago por tarjeta, su inscripción será dada por valida automáticamente,<br>sin embargo si la realiza por transferencia bancaria, tendrá 48 horas para completar el pago, <br>de lo contrario su solicitud será rechazada</html>");
		lblRelPagoIns.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblRelPagoIns.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelPagoIns.setBounds(10, 10, 276, 108);
		contentPane.add(lblRelPagoIns);
		
		JButton btnAceptarPago = new JButton("Finalizar Pago");
		btnAceptarPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				if(esPagoPorTarjeta() && camposCorrectos()) {
					disposeFrame();
					key.guardarInscripcion(0);
				}else if(esPagoPorTarjeta())throw new ApplicationException("Los datos de su tarjeta no son correctos");
				else {
					disposeFrame();
					key.guardarInscripcion(1);
				}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptarPago.setBounds(10, 333, 134, 21);
		contentPane.add(btnAceptarPago);
		pTarjeta.setBounds(10, 125, 458, 184);
		contentPane.add(pTarjeta);
		pTarjeta.setVisible(false);
	}
	
	public boolean esPagoPorTarjeta() {
		return buttonGroup.isSelected(rdbtnTarjeta.getModel());
	}
	public boolean camposCorrectos() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        YearMonth fechaTarjeta = YearMonth.parse(this.tfFechaCad.getText(), formatter);
        YearMonth fechaActual = YearMonth.now();
        boolean fecha =  !fechaTarjeta.isBefore(fechaActual);
		return this.tfCVC.getText().length() == 3 && this.tfFechaCad.getText().length() == 5 && this.tfTarjeta.getText().length() > 0 && fecha;
	}
	private void disposeFrame() {
		this.setVisible(false);
	}
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Tarjeta");
			putValue(SHORT_DESCRIPTION, "Muestra el formulario a rellenar");
		}
		public void actionPerformed(ActionEvent e) {
			pTarjeta.setVisible(true);
			pTransferencia.setVisible(false);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "Transferencia Bancaria");
			putValue(SHORT_DESCRIPTION, "Muestra el formulario a rellenar");
		}
		public void actionPerformed(ActionEvent e) {
			pTarjeta.setVisible(false);
			pTransferencia.setVisible(true);
		}
	}
}
