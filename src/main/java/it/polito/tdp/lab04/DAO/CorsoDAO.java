package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				Corso c = new Corso(codins,numeroCrediti,nome,periodoDidattico);
				corsi.add(c);
			}

			conn.close();
			st.close();
			rs.close();
			return corsi;
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	
	public List<Studente> getStudenti(String codiceCorso) {

		final String sql = "SELECT s.* "
				+ "FROM corso c, iscrizione i, studente s "
				+ "WHERE c.codins = i.codins && i.matricola = s.matricola && c.codins=?";
		Studente studente = null;
		
		List<Studente> listaStudenti = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, codiceCorso);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				Integer matr = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
				
				System.out.println(matr + " " + cognome + " " + nome + " " + cds);

				studente = new Studente(matr,cognome,nome,cds);
				listaStudenti.add(studente);
			}

			conn.close();
			st.close();
			rs.close();
			return listaStudenti;
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore db", e);
		}
	}
	
	
	
	public void getStudentiIscrittiAlCorso(Corso corso) {
		// TODO
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}
	
	
	public List<Corso> cercaCorsiFromStudente(int matricola) {
		
		final String sql = "SELECT c.* "
				+ "FROM iscrizione i, corso c "
				+ "WHERE i.codins=c.codins && i.matricola=?";
		Corso corso = null;
		
		List<Corso> listaCorsi = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				String codins = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				Integer pd = rs.getInt("pd");
				
				System.out.println(codins + " " + crediti + " " + nome + " " + pd);

				corso = new Corso(codins,crediti,nome,pd);
				listaCorsi.add(corso);
			}

			conn.close();
			st.close();
			rs.close();
			return listaCorsi;
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore db", e);
		}
	}

}
