package it.polito.tdp.anagrammi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AnagrammaDAO {

	public boolean isCorrect(String anagramma) {
		final String sql = "SELECT nome FROM parola WHERE nome = ?";
		
		boolean esiste = false;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, anagramma);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				esiste=true;
			}

			conn.close();
			st.close();
			rs.close();
			return esiste;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
}
