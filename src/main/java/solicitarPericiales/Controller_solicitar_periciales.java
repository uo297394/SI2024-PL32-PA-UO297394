package solicitarPericiales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;


public class Controller_solicitar_periciales {
	
	private Model_solicitar_periciales model;
	private View_solicitar_periciales view;
	String nombre,apellido,DNI,direccion,correo,telefono,detalles,caracter;
	Date fechaNacimiento;
	
	private String fechaNacimientoStr; // Cadena con la fecha formateada
	
	public Controller_solicitar_periciales(Model_solicitar_periciales m, View_solicitar_periciales v) {
		this.model=m;
		this.view=v;
		
	}
	
	public void initController() {
		initView();
		 view.getBotonEnviarSoli().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nombre=view.getTfNombre().getText();
				apellido=view.getTfApellido().getText();
				DNI=view.getTfDNI().getText();
				direccion=view.getTfDireccion().getText();
				correo=view.getTfCorreo().getText();
				telefono=view.getTfTelefono().getText();
				fechaNacimiento=(Date) view.getFechaNacimiento().getValue();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				fechaNacimientoStr = sdf.format(fechaNacimiento);
				detalles=view.getDetalles().getText();
				caracter=view.getCaracter().getSelectedItem().toString();
				
				// Validaciones básicas
				if(nombre.isEmpty() || apellido.isEmpty() || DNI.isEmpty() || direccion.isEmpty() ||
				           correo.isEmpty() || telefono.isEmpty()) {
				            JOptionPane.showMessageDialog(view, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
				            return;
				        }
				// Validar DNI: supongamos que debe tener 9 caracteres (8 dígitos y 1 letra)
				 if(DNI.length() != 9) {
			            JOptionPane.showMessageDialog(view, "El DNI debe tener 9 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
				 
				// Validar teléfono: por ejemplo, 9 dígitos
				 if(telefono.length() != 9 || !telefono.matches("\\d+")) {
			            JOptionPane.showMessageDialog(view, "El teléfono debe contener 9 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
				enviarSolicitud();
			}
			 
		 });
	}

	public void initView() {
        view.setVisible(true);
        // Deshabilitar el botón de enviar solicitud inicialmente
    }
	
	private void enviarSolicitud() {
	    int idSolicitante = model.registrarSolicitante(nombre, apellido, DNI, direccion, correo, telefono, fechaNacimientoStr);
	    
	    if (idSolicitante > 0) { // Si se registró correctamente el solicitante
	        boolean solicitudRegistrada = model.registrarSolicitudPericial(idSolicitante, detalles,caracter);
	        
	        if (solicitudRegistrada) {
	            String mensaje = "La solicitud de periciales ha sido enviada:\n" +
	                    "Nombre: " + nombre + "\n" +
	                    "Apellido: " + apellido + "\n" +
	                    "Dirección: " + direccion + "\n" +
	                    "Correo: " + correo + "\n" +
	                    "Teléfono: " + telefono + "\n" +
	                    "DNI: " + DNI + "\n" +
	                    "Fecha de Nacimiento: " + fechaNacimientoStr + "\n" +
	                    "Información adicional: " + detalles+ "\n" +
	                    "Caracter: "+caracter;

	            JOptionPane.showMessageDialog(view, mensaje, "Justificante solicitud", JOptionPane.INFORMATION_MESSAGE);
	            limpiarFormulario();
	        } else {
	            JOptionPane.showMessageDialog(view, "No se pudo registrar la solicitud en la tabla Periciales.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(view, "No se pudo registrar el solicitante.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


private void limpiarFormulario() {
    view.getTfNombre().setText("");
    view.getTfApellido().setText("");
    view.getTfDNI().setText("");
    view.getTfDireccion().setText("");
    view.getTfCorreo().setText("");
    view.getTfTelefono().setText("");
    view.getDetalles().setText("");
    // Reiniciar el spinner a la fecha actual, por ejemplo:
    view.getFechaNacimiento().setValue(new Date());
    // Si tienes otros componentes, reinícialos también
}

}
