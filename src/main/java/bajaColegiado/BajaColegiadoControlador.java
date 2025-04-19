package bajaColegiado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import cobrarRecibos.RecibosDTO;
import util.SwingUtil;
import util.Util;

public class BajaColegiadoControlador {
	private BajaColegiadoVista v;
	private BajaColegiadoModelo m;
	private int idColegiado;
	public BajaColegiadoControlador(BajaColegiadoVista v, BajaColegiadoModelo m) {
	this.v=v;
	this.m=m;
	this.v.cambiarEnabledCancelar(false);
	this.v.cambiarEnabledMotivos(false);
	this.v.getBtnIniciarSesion().addActionListener(new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	iniciarSesion();
	    	RellenaTabla();
	    	
	        }});
	
	this.v.getBotonCancelacion().addActionListener(new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	darseDeBaja();
	    	RellenaTabla();
	        }});
	}
	
	public void darseDeBaja() {
		
		if(this.m.EstaCancelado(idColegiado)) {
			JOptionPane.showMessageDialog(null,"El colegiado con id:"+idColegiado+ "  ya se había dado de baja, no puede volver a darse","Resultado",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String motivosCancelacion=this.v.geteMotivosCancelacion();
		if(motivosCancelacion.isEmpty()){
			JOptionPane.showMessageDialog(null, "El campo motivos no puede ser nulo", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.m.cambiarCancelado(idColegiado, motivosCancelacion);
		String DNI=this.m.getDNI(idColegiado);
		JOptionPane.showMessageDialog(null,"El colegiado con id:"+idColegiado+"y DNI:"+DNI +"Se ha dado de baja","Resultado",JOptionPane.INFORMATION_MESSAGE);
		int año=Integer.parseInt(Util.getTodayISO().split("-")[0]); 
		if(this.m.Emitido(idColegiado,año)|| !this.m.Recibo(idColegiado, año)) {
			JOptionPane.showMessageDialog(null,"Aún no se le ha cobrado la cuota, se pasará a su cobro próximamente","Recibo",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	private void RellenaTabla() {
		int año=2025;
		if(!this.m.EstaColegiado(idColegiado)) {return;}
		List<BajaColegiadoDTO> listaRecibos=this.m.Colegiados(idColegiado, año);
		String[] columnas= {"idColegiado", "nombre","estado_solicitud","es_perito", "DNI","idRecibo","estado"};
		String [] ticolumnas= {"id", "nombre","estadoSolicitud","esPerito", "DNI","idRecibo","estadoRecibo"};
		TableModel tablaColegiado = SwingUtil.getTableModelFromPojos(listaRecibos, columnas);
		this.v.setTablaColegiado(tablaColegiado);
		for(int i=0;i<ticolumnas.length;i++) {
			this.v.getTablaColegiado().getColumnModel().getColumn(i).setHeaderValue(ticolumnas[i]);
		}
		SwingUtil.autoAdjustColumns(this.v.getTablaColegiado());
		 this.v.getTablaColegiado().getTableHeader().repaint();
		 this.v.cambiarEnabledCancelar(true);
		 this.v.cambiarEnabledMotivos(true);
		
	}
	public void iniciarSesion() {
		try {
			idColegiado=Integer.parseInt(this.v.getiIdColegiado());}
		catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "El id del colegiado introducido debe tener un formato válido", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		if(!this.m.EstaColegiado(idColegiado)) {
			JOptionPane.showMessageDialog(null, "El id introducido no corresponde a ningún colegiado", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(null, "Iniciada sesion como colegiado con id:"+idColegiado, "Information", JOptionPane.INFORMATION_MESSAGE);
	}

}
