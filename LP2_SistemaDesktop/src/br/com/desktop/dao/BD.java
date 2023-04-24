package br.com.desktop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import br.com.desktop.controller.Criptografia;

public class BD {
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static String DRIVER = "org.sqlite.JDBC";
	private static String BD = "jdbc:sqlite:resources/bdtarefas.db";

	private static String CRIAR_USUARIO_ADMINISTRADOR = "INSERT INTO USUARIO " + " (ID, USUARIO, SENHA, TIPO_USUARIO) "
			+ " VALUES (null, ?, ?, ?)";

	public static void main(String args[]) {

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(BD);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Base criada com sucesso");

		try {
			criarTabelaTarefa();
			criarTabelaUsuario();
			criarUsuarioAdministrador();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void criarTabelaTarefa() throws SQLException {

		try {
			connection = DriverManager.getConnection(BD);
			statement = connection.createStatement();
			String sql = "CREATE TABLE TAREFA " + "" + " ( " + " ID INTEGER NOT NULL PRIMARY KEY	AUTOINCREMENT, "
					+ " TITULO CHAR(100), " + " DESCRICAO CHAR(244), " + " NOME_ETIQUETA CHAR(100), "
					+ " COR_ETIQUETA INTEGER, " + " DATA_CRIACAO	DATE NOT NULL, " + " DATA_CONCLUSAO DATE, "
					+ "	STATUS CHAR(20) " + " ) ";
			statement.executeUpdate(sql);

			System.out.println("Tabela TAREFA criada com sucesso");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			fecharConexao(statement, connection);
		}

	}

	private static void criarTabelaUsuario() throws SQLException {

		try {
			connection = DriverManager.getConnection(BD);
			statement = connection.createStatement();
			String sql = "CREATE TABLE USUARIO " + "" + " ( " + " ID INTEGER NOT NULL PRIMARY KEY	AUTOINCREMENT, "
					+ " USUARIO CHAR(100), " + " SENHA CHAR(300)," + " TIPO_USUARIO CHAR(100) " + " ) ";
			statement.executeUpdate(sql);

			System.out.println("Tabela USUARIO criada com sucesso");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			fecharConexao(statement, connection);
		}

	}

	private static void criarUsuarioAdministrador() throws SQLException {

		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(BD);
			connection.setAutoCommit(false);
			String sql = CRIAR_USUARIO_ADMINISTRADOR;
			preparedStatement = connection.prepareStatement(sql);
			int i = 1;
			preparedStatement.setString(i++, "Admin");
//			Criptografia criptografia = new Criptografia("123456", Criptografia.SHA256).criptografar();
			preparedStatement.setString(i++, new Criptografia("123456", Criptografia.SHA256).criptografar());
			preparedStatement.setString(i++, "Administrador");
			
			preparedStatement.execute();
			connection.commit();

			System.out.println("Usuario administrador criado com sucesso");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			fecharConexao(preparedStatement, connection);
		}

	}

	private static void fecharConexao(Statement statement, Connection connection) {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void fecharConexao(PreparedStatement preparedStatement, Connection connection) {
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
