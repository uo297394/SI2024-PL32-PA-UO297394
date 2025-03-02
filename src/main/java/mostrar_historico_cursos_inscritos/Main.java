package mostrar_historico_cursos_inscritos;

import java.util.Arrays;
import java.util.List;

import util.Database;

public class Main {
	private static Database db=new Database();
    public static void main(String[] args) {
        // Crear la vista
        CursosInscritosVista vista = new CursosInscritosVista();
        
        // Crear el modelo
        CursosInscritosModelo modelo = new CursosInscritosModelo();
        
        // Crear el controlador y conectar las capas
        CursosInscritosControlador controlador = new CursosInscritosControlador(vista, modelo);
        
        // Hacer visible la vista
       // try {
          /**  List<Object[]> resultado = db.executeQueryArray("PRAGMA table_info(Cursos);");

            if (resultado.isEmpty()) {
                System.out.println("⚠️ La tabla Cursos no existe o no tiene columnas.");
            } else {
                for (Object[] fila : resultado) {
                    System.out.println(Arrays.toString(fila)); // Imprime cada columna correctamente
                }**/
       /**     }
        } catch (Exception e) {
            System.out.println("❌ Error al ejecutar PRAGMA: " + e.getMessage());
        }**/
        vista.getFrame().setVisible(true);
        List<Object[]> resultados = db.executeQueryArray("SELECT i.idColegiado, c.titulo_curso, c.fecha_inicio_curso, c.fecha_fin_curso, c.duracion\r\n"
        		+ "FROM Inscripciones i\r\n"
        		+ "JOIN Cursos c ON i.idCurso = c.id WHERE i.idColegiado=1;");
        for (Object[] fila : resultados) {
            System.out.println(Arrays.toString(fila));
        }
    }
}