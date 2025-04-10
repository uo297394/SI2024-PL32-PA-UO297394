package mostrar_historico_cursos_inscritos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import util.ApplicationException;
import util.SwingUtil;
import util.Util;

public class CursosInscritosControlador {
	private CursosInscritosVista v;
	private CursosInscritosModelo m;
	private Object lastSelectedKey = "";
	public CursosInscritosControlador(CursosInscritosVista v, CursosInscritosModelo m) {
		this.v=v;
		this.m=m;
		this.v.getBoton().addActionListener(new ActionListener(){
		    @Override
		    public void actionPerformed(ActionEvent e) {
		      metodos();
		        }});
		this.v.getBtnCancelar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> cancelar()));
		v.getTablaCursos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//no usa mouseClicked porque al establecer seleccion simple en la tabla de carreras
				//el usuario podria arrastrar el raton por varias filas e interesa solo la ultima
				SwingUtil.exceptionWrapper(() -> updateDetail());
			}
		});
	}
	

	public void RellenaTabla(String DNI) {
		
		List<CursosInscritosDTO> listaCursos=this.m.getListaTodosCursos(DNI);
		String[] columnas= {"titulo_curso", "fecha_inicio_curso", "fecha_fin_curso", "duracion", "estado"};
		String [] ticolumnas= {"titulo", "fechaInicio", "fechaFin","duracion", "estado"};
		TableModel tablaCursos = SwingUtil.getTableModelFromPojos(listaCursos, columnas);
		
		v.setTablaCursos(tablaCursos);
		for(int i=0;i<ticolumnas.length;i++) {
		this.v.getTablaCursos().getColumnModel().getColumn(i).setHeaderValue(ticolumnas[i]);
	}
	
		SwingUtil.autoAdjustColumns(v.getTablaCursos());
		 this.v.getTablaCursos().getTableHeader().repaint();
	
		
	}
	public void metodos() {
		try {
			String DNI=this.v.getDNI();
			this.m.hayDatos(DNI);
			RellenaTabla(DNI);
			RellenaTextArea(DNI);
		}
		catch(ApplicationException e) {
			return;	
		}
		
	}
	public void RellenaTextArea(String DNI) {
		int numeroCursos=this.m.getTotalCursos(DNI);
		int numeroHoras=this.m.getTotalHoras(DNI);
		String b=String.format("Total de cursos: %d \n Total de horas: %d", numeroCursos, numeroHoras);
		this.v.setTotalCursos(b);
	}
	public void updateDetail() {
		this.lastSelectedKey =SwingUtil.getSelectedKey(v.getTablaCursos());
	}
	private void cancelar() {
		if(v.getDNI().length() != 9)throw new ApplicationException("DNI no valido");
		if(this.lastSelectedKey=="")throw new ApplicationException("Seleccione un curso");
		List<CursosInscritosDTO> l = m.getListaTodosCursos(v.getDNI());
		Iterator<CursosInscritosDTO> it = l.iterator();
		CursosInscritosDTO inst = null;
		while(it.hasNext()) {
			inst = it.next();
			if(inst.getTitulo_curso().equals(((String) this.lastSelectedKey)))break;
			}
		if(inst==null)throw new ApplicationException("Ha ocurrido un error, cierre y vuelva a abrir la ventana");
		if(inst.getFechaMaximaCancelacion()==null)throw new ApplicationException("El curso no admite cancelación");
		if(util.Util.isoStringToDate(inst.getFechaMaximaCancelacion()).before(util.Util.isoStringToDate(util.Util.getTodayISO())))
			throw new ApplicationException("La inscripción ya no puede ser cancelada") ;
		if(inst.getEstado().equals("5"))throw new ApplicationException("Su inscripción ya fue cancelada con anterioridad");
		m.cancelaInscripcion(inst.getIdInsc(),inst.getPorcentaje(),inst.getCuota());
		float adev = Float.parseFloat(inst.getPorcentaje()) * Float.parseFloat(inst.getCuota());
		SwingUtil.showMessage("<html><b>Se ha realizado la cancelación</b><br>"
									+ "La cancelación se ha realizado en la fecha: "+Util.getTodayISO()+"<br>"
									+ "Importe que se le devolverá de la cuota: "+adev+"<br>"
									+ "Titulo del Curso: "+inst.getTitulo_curso()+"<br>"
									+ "Duración del curso: "+inst.getDuracion()+"<br>"
									+ "Fechas de inicio y fin: "+inst.getFecha_inicio_curso()+" - "+inst.getFecha_fin_curso() +"</html>",
									"Cancelación realizada", JOptionPane.INFORMATION_MESSAGE);
	}
}
