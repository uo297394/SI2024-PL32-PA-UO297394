import static org.junit.Assert.*;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import inscribirColegiado.InscribirColegiadoModel;
import util.Database;

public class InscripcionAUnCursoTest {
	
	@BeforeClass
	public static void iniciarBBDD() {
		Database db = new Database();
		db.createDatabase(false);
		db.loadDatabase();
	}
	
	/**
	 * El modelo busca un numero de colegiado en la base de datos registrado
	 */
	@Test
    public void testNumeroColegiadoRegistrado() {
        InscribirColegiadoModel modelo = new InscribirColegiadoModel();
        String numeroColegiado = "1";
        assertTrue(modelo.buscaColegiado(numeroColegiado)!=null);
    }

	/**
	 * El modelo busca un numero de colegiado en la base de datos no registrado
	 */
	@Test
    public void testNumeroColegiadoNoRegistrado() {
        InscribirColegiadoModel modelo = new InscribirColegiadoModel();
        String numeroColegiado = "99999";
        assertFalse(modelo.buscaColegiado(numeroColegiado)!=null);
    }

	/**
	 * El modelo busca una persona perteneciente a un colectivo externo en la base de datos que está registrada
	 */
    @Test
    public void testDNIRegistrado() {
    	InscribirColegiadoModel modelo = new InscribirColegiadoModel();
        String dni = "12345678Z";
        
        assertTrue(modelo.buscaPersona(dni)!=null);
    }

    /**
	 * El modelo busca una persona perteneciente a un colectivo externo en la base de datos que no está registrada
	 */
    @Test
    public void testDNINoRegistradoPeroValido() {
    	InscribirColegiadoModel modelo = new InscribirColegiadoModel();
        String dni = "00000000Z";
        
        assertFalse(modelo.buscaPersona(dni)!=null);
    }

    /**
	 * El modelo busca el curso seleccionado y comprueba que sus fechas de inscripción no sean nulas
	 */
    @Test
    public void testInscripcionNoAbierta() {
    	InscribirColegiadoModel modelo = new InscribirColegiadoModel();
        int idCurso = 1;
        
        assertFalse(modelo.getListaCursos().get(idCurso-1).getFechaInicioInscripcion()!=null &&
        		modelo.getListaCursos().get(idCurso-1).getFechaFinInscripcion()!=null);
    }

    /**
	 * El modelo busca el curso seleccionado y comprueba que la fecha de hoy esté dentro del rango de fechas, en este caso el plazo ya pasó
	 */
    @Test
    public void testInscripcionFueraDePlazoPosterior() {
    	InscribirColegiadoModel modelo = new InscribirColegiadoModel();
        int idCurso = 2;
        String fechaHoy = util.Util.getTodayISO();
        String fechainicio = modelo.getListaCursos().get(idCurso-1).getFechaInicioInscripcion();
        String fechafin = modelo.getListaCursos().get(idCurso-1).getFechaFinInscripcion();
        Date dateInicio = util.Util.isoStringToDate(fechainicio);
        Date dateFin = util.Util.isoStringToDate(fechafin);
        Date dateHoy = util.Util.isoStringToDate(fechaHoy);
        assertFalse(dateInicio.compareTo(dateHoy)<=0 &&
        		dateFin.compareTo(dateHoy)>=0);
    }

    /**
	 * El modelo busca el curso seleccionado y comprueba que la fecha de hoy esté dentro del rango de fechas, en este caso el plazo no ha empezado
	 */
    @Test
    public void testInscripcionFueraDePlazoAnterior() {
    	InscribirColegiadoModel modelo = new InscribirColegiadoModel();
        int idCurso = 3;
        String fechaHoy = util.Util.getTodayISO();
        String fechainicio = modelo.getListaCursos().get(idCurso-1).getFechaInicioInscripcion();
        String fechafin = modelo.getListaCursos().get(idCurso-1).getFechaFinInscripcion();
        Date dateInicio = util.Util.isoStringToDate(fechainicio);
        Date dateFin = util.Util.isoStringToDate(fechafin);
        Date dateHoy = util.Util.isoStringToDate(fechaHoy);
        assertFalse(dateInicio.compareTo(dateHoy)<=0 &&
        		dateFin.compareTo(dateHoy)>=0);
    }
    /**
	 * El modelo busca el curso seleccionado y comprueba que la fecha de hoy esté dentro del rango de fechas, en este caso está en plazo
	 */
    @Test
    public void testInscripcionEnPlazo() {
    	InscribirColegiadoModel modelo = new InscribirColegiadoModel();
        int idCurso = 4;
        String fechaHoy = util.Util.getTodayISO();
        String fechainicio = modelo.getListaCursos().get(idCurso-1).getFechaInicioInscripcion();
        String fechafin = modelo.getListaCursos().get(idCurso-1).getFechaFinInscripcion();
        Date dateInicio = util.Util.isoStringToDate(fechainicio);
        Date dateFin = util.Util.isoStringToDate(fechafin);
        Date dateHoy = util.Util.isoStringToDate(fechaHoy);
        assertTrue(dateInicio.compareTo(dateHoy)<=0 &&
        		dateFin.compareTo(dateHoy)>=0);
    }

    /**
	 * El modelo busca el curso seleccionado y comprueba si la inscripción es posible, en este caso el curso está lleno y no dispone de lista de espera
	 */
    @Test
    public void testCursoLlenoSinListaEspera() {
    	InscribirColegiadoModel modelo = new InscribirColegiadoModel();
        int idCurso = 6;
        

        assertFalse(!modelo.hayPlazas(idCurso+"") && modelo.listaEspera(idCurso+""));
    }

    /**
	 * El modelo busca el curso seleccionado y comprueba si la inscripción es posible, en este caso el curso está lleno y dispone de lista de espera
	 */
    @Test
    public void testCursoLlenoConListaEspera() {
    	InscribirColegiadoModel modelo = new InscribirColegiadoModel();
        int idCurso = 5;
        

        assertTrue(!modelo.hayPlazas(idCurso+"") && modelo.listaEspera(idCurso+""));
    }
    /**
	 * El modelo busca el curso seleccionado y comprueba si la inscripción es posible, en este caso el curso no está lleno
	 */
    @Test
    public void testCursoNoLleno() {
    	InscribirColegiadoModel modelo = new InscribirColegiadoModel();
        int idCurso = 7;
        
        assertTrue(modelo.hayPlazas(idCurso+""));
    }
}
