package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudente(Integer matr) {
		final String sql = "SELECT * "
				+ "FROM studente s "
				+ "WHERE matricola=?";
	
		Studente s = null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matr);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Integer matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
			
				System.out.println(matricola+" "+cognome+" "+nome+" "+cds);
				s = new Studente(matricola,cognome,nome,cds);
			}
			
			conn.close();
			st.close();
			rs.close();
			
			return s;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}			
	}
	
}
