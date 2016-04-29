package br.com.developeranalyst.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/developerweb", "root", "");
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado");
			throw new RuntimeException(e);
		}
		
	}
}
