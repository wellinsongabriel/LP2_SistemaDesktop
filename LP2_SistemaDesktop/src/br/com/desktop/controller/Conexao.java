package br.com.desktop.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static Conexao instancia;
	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String BD = "jdbc:sqlite:resources/bdtarefas.db";
	private static Connection conexao;

	private Conexao() {
		
	}
	

	public static synchronized Conexao getInstancia() {
		if (instancia == null) {
			instancia = new Conexao();
		}
		return instancia;

	}

	public synchronized Connection abriConexao() {
		try {
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(BD);
			conexao.setAutoCommit(false);
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Erro na conexão com o banco de dados: " + e.getMessage());
		}
		return conexao;
	}
	
	
	
	public synchronized void fecharConexao() {
		try {
	        if (conexao != null && !conexao.isClosed()) {
	            conexao.close();
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao fechar a conexão: " + e.getMessage());
	    } finally {
	        conexao = null;
	    }
	}
}
