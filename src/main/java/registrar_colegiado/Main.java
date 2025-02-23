package registrar_colegiado;

import java.util.Arrays;
import java.util.List;

import giis.demo.util.Database;

public class Main {
	private static Database db=new Database();
    public static void main(String[] args) {
        // Crear la vista
        Registrar_colegiadoVista vista = new Registrar_colegiadoVista();
        
        // Crear el modelo
        Registrar_colegiadoModelo modelo = new Registrar_colegiadoModelo();
        
        // Crear el controlador y conectar las capas
        Registrar_colegiadoControlador controlador = new Registrar_colegiadoControlador(vista, modelo);
        
        // Hacer visible la vista
        String sql="DELETE FROM Colegiados WHERE id=123";
        db.executeUpdate(sql);
        vista.getFrame().setVisible(true);
        List<Object[]> resultados = db.executeQueryArray("SELECT * FROM Colegiados");
        for (Object[] fila : resultados) {
            System.out.println(Arrays.toString(fila));
        }
    }
}