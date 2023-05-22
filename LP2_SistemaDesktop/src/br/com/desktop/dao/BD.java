package br.com.desktop.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import br.com.desktop.controller.Criptografia;
import br.com.desktop.controller.SistemaOperacional;

public class BD {
	private static Connection connection = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static final SistemaOperacional so = SistemaOperacional.getIstancia();
	private static String DRIVER = "org.sqlite.JDBC";
	private static String BD = "jdbc:sqlite:resources/bdtarefas.db";

	private static String CRIAR_USUARIO_ADMINISTRADOR = " INSERT INTO USUARIO   (ID, USUARIO, SENHA, TIPO_USUARIO) "
			+ "	SELECT NULL, ?, ?, ? "
			+ "	WHERE NOT EXISTS (SELECT 1 FROM USUARIO WHERE USUARIO =  'Admin')  ";

	public static void main(String args[]) {

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(BD);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		JOptionPane.showMessageDialog(null,"Base criada com sucesso");

		try {
			rodarScript("CREATE_TABLE_USUARIO.sql");
			rodarScript("CREATE_TABLE_PROJETO.sql");
			rodarScript("CREATE_TABLE_TAREFA.sql");
			rodarScript("CREATE_TABLE_PROJETO_USUARIO.sql");
			criarUsuarioAdministrador();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	

	private static void rodarScript(String nomeScript) throws SQLException {

		try {
			connection = DriverManager.getConnection(BD);
			statement = connection.createStatement();
			String sql = lerArquivo(nomeScript);
			statement.executeUpdate(sql);

			JOptionPane.showMessageDialog(null,"O script "+nomeScript+ " foi executado com sucesso");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			fecharConexao(statement, connection);
		}

	}
	

	private static void criarUsuarioAdministrador() throws SQLException {

		try {
			
			connection = DriverManager.getConnection(BD);
			connection.setAutoCommit(false);
			String sql = CRIAR_USUARIO_ADMINISTRADOR;
			preparedStatement = connection.prepareStatement(sql);
			int i = 1;
			preparedStatement.setString(i++, "Admin");
//			Criptografia criptografia = new Criptografia("123456", Criptografia.SHA256).criptografar();
			preparedStatement.setString(i++, new Criptografia("123456", Criptografia.SHA256).criptografar());
			preparedStatement.setString(i++, "ADMINISTRADOR");
			
			preparedStatement.execute();
			connection.commit();

			JOptionPane.showMessageDialog(null,"Usuario administrador criado com sucesso");
		} catch (SQLException e) {
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
	
	private static String lerArquivo(String nomeScript) {
		
		String script= null;
		try {
		File file = new File("BD.java");

		String pathAbsolutoAtual = file.getAbsolutePath();
		String pathAbsolutoParcial = null;
		if (so.sistemaWindows()) {
			pathAbsolutoParcial = pathAbsolutoAtual.substring(0, pathAbsolutoAtual.lastIndexOf('\\'));
		} else {
			pathAbsolutoParcial = pathAbsolutoAtual.substring(0, pathAbsolutoAtual.lastIndexOf('/'));
		}
		FileReader reader = new FileReader(pathAbsolutoParcial+"/scripts/"+nomeScript);
		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(reader);
		String linha;
		StringBuilder stringBuilder = new StringBuilder();
			while ((linha = bufferedReader.readLine()) != null) {
			    stringBuilder.append(linha).append("\n");
			}		
		script = stringBuilder.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return script;
		
	}

}
