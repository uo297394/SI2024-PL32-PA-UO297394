package bajaColegiado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class BajaColegiadoControlador {
	private BajaColegiadoVista v;
	private BajaColegiadoModelo m;
	private int idColegiado;
	public BajaColegiadoControlador(BajaColegiadoVista v, BajaColegiadoModelo m) {
	this.v=v;
	this.m=m;
	this.v.getBotonCancelacion().addActionListener(new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	darseDeBaja();
	        }});
	}
	
	public void darseDeBaja() {
		try {
		idColegiado=Integer.parseInt(this.v.getiIdColegiado());}
		catch(NumberFormatException e) {
			System.out.print("El id del colegiado introducido debe tener un formato válido");
			return;
		}
		if(this.m.EstaCancelado(idColegiado)) {
			JOptionPane.showMessageDialog(null,"El colegiado con id:"+idColegiado+ "  ya se había dado de baja, no puede volver a darse","Resultado",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String motivosCancelacion=this.v.geteMotivosCancelacion();
		this.m.cambiarCancelado(idColegiado, motivosCancelacion);
		String DNI=this.m.getDNI(idColegiado);
		JOptionPane.showMessageDialog(null,"El colegiado con id:"+idColegiado+"y DNI:"+DNI +"Se ha dado de baja","Resultado",JOptionPane.INFORMATION_MESSAGE);
	}

}
