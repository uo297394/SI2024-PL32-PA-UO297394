package cursosActions;

import java.util.List;

import aperturaInscripciones.AperturaInscripcionesDisplayDTO;
import aperturaInscripciones.AperturaInscripcionesModel;
import inscribirColegiado.InscribirColegiadoDisplayDTO;
import inscribirColegiado.InscribirColegiadoModel;
import inscritos_cursos_formacion.InscripcionDisplayDTO;
import inscritos_cursos_formacion.ModelInscripciones;
import util.Util;

public class CursosActionsModel {
	AperturaInscripcionesModel aiModel;
	InscribirColegiadoModel icModel;
	ModelInscripciones iModel;
	
	
	
	public CursosActionsModel() {
		 aiModel = new AperturaInscripcionesModel();
		 icModel = new InscribirColegiadoModel();
		 iModel = new ModelInscripciones();
	}
	
	public List<InscripcionDisplayDTO> getInscripcionesPorCurso(int idCurso) {
		return iModel.getInscripcionesPorCurso(idCurso);
	}
	public List<AperturaInscripcionesDisplayDTO> getListaCursos() {
		return aiModel.getListaCursos();
	}
	public List<AperturaInscripcionesDisplayDTO> getListaCursos(String colectivo) {
		if(colectivo == "Todos") return aiModel.getListaCursos();
		return aiModel.getListaCursos(colectivo);
	}
	public void insertInscColegiado(String idColeg, String idCurso, int estado, String colectivo) {
		icModel.insertInscColegiado(idColeg, idCurso, estado, colectivo);
	}
	public void updateAperturaCurso(String titulo, String inicio, String fin) {
		aiModel.updateAperturaCurso(titulo, inicio, fin);
	}

	public List<Object[]> getListaColectivos() {
		return aiModel.getListaColectivos();

	}

	public List<Object[]> getColectivosDeCurso(AperturaInscripcionesDisplayDTO aperturaInscripcionesDisplayDTO) {
		return aiModel.getColectivosDeCurso(aperturaInscripcionesDisplayDTO);
	}

	public String getCuota(String string, String string2) {
		return aiModel.getCuota(string,string2);
	}
	public boolean estaInscrito(String idColeg, String idCurso) {
		return icModel.estaInscrito(idColeg, idCurso);
	}

	public ColegiadoDisplayDTO buscaPersona(String DNI) {
		return icModel.buscaPersona(DNI);
	}
}
