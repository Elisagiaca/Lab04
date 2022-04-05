package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	CorsoDAO corsoDao;
	StudenteDAO studenteDao;
	
	public Model() {
		super();
		this.corsoDao = new CorsoDAO();
		this.studenteDao = new StudenteDAO();
	}

	public List<Corso> getCorsi(){
		return this.corsoDao.getTuttiICorsi();
	}
	
	public Studente getStudenteModel(Integer m) {
		return this.studenteDao.getStudente(m);
	}
	
	public List<Studente> getStudentiModel(String codice) {
		return corsoDao.getStudenti(codice);
	}
	
	public List<Corso> getCorsiModel(int m){
		return this.corsoDao.cercaCorsiFromStudente(m);
	}
}
