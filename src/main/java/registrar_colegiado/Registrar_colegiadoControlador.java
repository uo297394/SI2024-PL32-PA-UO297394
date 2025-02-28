package registrar_colegiado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import util.ApplicationException;
import util.Util;

import javax.swing.JButton;
public class Registrar_colegiadoControlador {
private Registrar_colegiadoVista v;
private Registrar_colegiadoModelo m;
	
public Registrar_colegiadoControlador(Registrar_colegiadoVista v, Registrar_colegiadoModelo m) {
	this.v=v;
	this.m=m;
	
	/**El registro del colegiado y las comprobaciones se realizarán después de pulsar el botón registrar**/
this.v.getBotonColegiado().addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        registrarColegiado();
        }});
    }

	
	public void registrarColegiado() {
		String nombre=this.v.getNombre_colegiado();
		String apellidos=this.v.getApellidos_colegiado();
		String DNI=this.v.getDNI_colegiado();
		String direccion=this.v.getDireccion_colegiado();
		String titulacion=this.v.getTitulación_colegiado();
		String banco=this.v.getBanco();
		
		JTextField cuentaText=v.getTextNumeroCuenta();
		//JDateChooser fechaChooser=this.v.getFechaChooser();
		this.m.CuentaNoNula(cuentaText);
		try{
		int cuenta=this.v.getNumeroCuenta();
		
		
		this.m.noNULO(DNI, nombre, apellidos, cuenta, direccion, titulacion,banco);
		String fecha=this.v.getFecha();
		this.m.EstaColegiado(DNI);
		//String fechaHoy=Util.dateToIsoString(LocalDate.now());
		String fecha_hoy=Util.getTodayISO();
		
		this.m.registro(nombre, apellidos, DNI, direccion,fecha, cuenta, banco, false, "aprobado", fecha_hoy, titulacion);
		//justificante impreso por pantalla con los datos introducidos
		System.out.print("Registrado: \n "+"Nombre:"+nombre+ "  "+ "Apellidos: "+apellidos+"\n DNI:"+DNI+ "  "+ "Direccion:"+direccion+ "\n fecha:"+fecha+"  "+ "Cuenta bancaria:"+cuenta+"\n Banco:"+banco+"  "+"Precolegiado?:"+false+"\n Estado solicitud:"+ "  "+"aprobado"+ "\n Fecha de solicitud:"+fecha_hoy+ "  "+ "Titulacion:"+titulacion);
	}
		catch(NumberFormatException  e) {
			JOptionPane.showMessageDialog(null, "Número de cuenta inválido. Debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
}
		catch(ApplicationException e) {
			return;
			
		}
}}
