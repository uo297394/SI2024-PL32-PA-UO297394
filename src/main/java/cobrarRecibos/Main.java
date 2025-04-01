package cobrarRecibos;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 CobrarRecibosVista v=new CobrarRecibosVista();
		 CobrarRecibosModelo m=new CobrarRecibosModelo();
		 CobrarRecibosControlador c=new CobrarRecibosControlador(v,m);
		v.getFrame().setVisible(true);
	}

}
