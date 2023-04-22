package br.com.desktop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
	public static void main(String args[]) {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:resources/bdtarefas.db");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Base criada com sucesso");	

		try {
			criarTabelaTarefa(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void criarTabelaTarefa(Connection c) throws SQLException {
		Statement stmt = null;

		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE TAREFA " + ""
					+ " ( "
					+ " ID INTEGER NOT NULL PRIMARY KEY	AUTOINCREMENT, "
					+ " TITULO CHAR(100), "
					+ " DESCRICAO CHAR(244), "
					+ " NOME_ETIQUETA CHAR(100), "
					+ " COR_ETIQUETA INTEGER, "
					+ " DATA_CRIACAO	DATE NOT NULL, " 
					+ " DATA_CONCLUSAO DATE, "
					+ "	STATUS CHAR(20) " 
					+ " ) ";
			stmt.executeUpdate(sql);
			
			System.out.println("Tabela TAREFA criada com sucesso");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}finally {
			stmt.close();
			c.close();
		}
		
	}

}
