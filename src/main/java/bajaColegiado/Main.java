package bajaColegiado;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BajaColegiadoModelo m=new BajaColegiadoModelo();
		BajaColegiadoVista v=new BajaColegiadoVista();
		v.getFrame().setVisible(true);
		BajaColegiadoControlador c=new BajaColegiadoControlador(v,m);
	}

}
