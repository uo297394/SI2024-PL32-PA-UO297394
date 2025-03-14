package enviarSolicitud;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnviarSolicitudModelo m=new EnviarSolicitudModelo();
		EnviarSolicitudVista v=new EnviarSolicitudVista();
		EnviarSolicitudControlador c=new EnviarSolicitudControlador(v,m) ;
		v.getFrame().setVisible(true);
	}

}
