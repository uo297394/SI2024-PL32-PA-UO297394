package registrar_colegiado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import giis.demo.util.Util;

import javax.swing.JButton;
public class Registrar_colegiadoControlador {
private Registrar_colegiadoVista v;
private Registrar_colegiadoModelo m;
	
public Registrar_colegiadoControlador(Registrar_colegiadoVista v, Registrar_colegiadoModelo m) {
	this.v=v;
	this.m=m;
this.v.getBotonColegiado().addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        registrarColegiado();
        }});
    }

	
	private void registrarColegiado() {
		String nombre=this.v.getNombre_colegiado();
		String apellidos=this.v.getApellidos_colegiado();
		String DNI=this.v.getDNI_colegiado();
		String direccion=this.v.getDireccion_colegiado();
		String titulacion=this.v.getTitulación_colegiado();
		String banco=this.v.getBanco();
		String fecha=this.v.getFecha();
		int cuenta=this.v.getNumeroCuenta();
		this.m.noNULO(DNI, nombre, apellidos, cuenta);
		this.m.EstaColegiado(DNI);
		//String fechaHoy=Util.dateToIsoString(LocalDate.now());
		this.m.registro(nombre, apellidos, DNI, direccion,fecha, cuenta, banco, false, "aprobado", "a", titulacion);
		
	}	
}
