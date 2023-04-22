package br.com.desktop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.desktop.model.Tarefa;

public class DAO {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static String DRIVER = "org.sqlite.JDBC";
	private static String BD = "jdbc:sqlite:resources/bdtarefas.db";

	private static String CADASTRAR_TAREFA = "INSERT INTO TAREFA "
			+ " (ID, TITULO, DESCRICAO, NOME_ETIQUETA, COR_ETIQUETA, DATA_CRIACAO, DATA_CONCLUSAO, STATUS) "
			+ " VALUES (null, ?, ?, ?, ?, ?, ?, ?)";

	private static String CONSULTAR_TAREFA = "SELECT * FROM TAREFA WHERE ID = ? ";

	private static String ALTERAR_TAREFA = " UPDATE TAREFA SET " + " TITULO = ?, " + " DESCRICAO = ?, "
			+ " NOME_ETIQUETA = ?, " + " COR_ETIQUETA = ?, " + " DATA_CRIACAO = ?, " + " DATA_CONCLUSAO = ?, "
			+ " STATUS = ? " + " WHERE ID = ? ";

	private static String EXCLUIR_TAREFA = "DELETE FROM TAREFA WHERE ID = ? ";

	private static String LISTAR_TAREFAS = "SELECT * FROM TAREFA WHERE 1=1";

	private static String AND_STATUS_A_FAZER = " AND STATUS = 0 ";

	private static String AND_STATUS_EM_ANDAMENTO = " AND STATUS = 1 ";

	private static String AND_STATUS_CONCLUIDO = " AND STATUS = 2 ";

	public void cadastrar(Tarefa tarefa) throws SQLException {
		
		if (validarDadosTarefa(tarefa)) {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(BD);
				connection.setAutoCommit(false);
				String sql = CADASTRAR_TAREFA;
				preparedStatement = connection.prepareStatement(sql);
				int i = 1;
				preparedStatement.setString(i++, tarefa.getTitulo());
				preparedStatement.setString(i++, tarefa.getDescricao());
				preparedStatement.setString(i++, tarefa.getNomeEtiqueta());
				preparedStatement.setInt(i++, tarefa.getCorEtiqueta());
				preparedStatement.setDate(i++, new java.sql.Date(tarefa.getDataCriacao().getTime()));
				preparedStatement.setDate(i++, tarefa.getDataConclusao() == null ? null
						: new java.sql.Date(tarefa.getDataConclusao().getTime()));
				preparedStatement.setInt(i++, tarefa.getStatus());
				preparedStatement.execute();
				connection.commit();

			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Não foi possível localizar a tarefa selecionada", "",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} finally {
				fecharConeaxao(preparedStatement, connection);
			}
			JOptionPane.showMessageDialog(null, "Tarefa incluída com sucesso");
		}
	}

	public Tarefa consultar(int id) throws Exception {
		Tarefa tarefa = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(BD);
			connection.setAutoCommit(false);

			String sql = CONSULTAR_TAREFA;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// int id = rs.getInt("id");
				tarefa = montarTarefa(rs);
			}
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			fecharConeaxao(preparedStatement, connection);
		}
		if (tarefa == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar a tarefa selecionada", "",
					JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar a tarefa selecionada");

		}
		return tarefa;
	}

	public void alterar(int id, Tarefa tarefa) throws Exception {
		
		
		if (consultar(id) != null) {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(BD);
				connection.setAutoCommit(false);

				String sql = ALTERAR_TAREFA;
				preparedStatement = connection.prepareStatement(sql);

				int i = 1;
				preparedStatement.setString(i++, tarefa.getTitulo());
				preparedStatement.setString(i++, tarefa.getDescricao());
				preparedStatement.setString(i++, tarefa.getNomeEtiqueta());
				preparedStatement.setInt(i++, tarefa.getCorEtiqueta());
				preparedStatement.setDate(i++, new java.sql.Date(tarefa.getDataCriacao().getTime()));
				preparedStatement.setDate(i++, tarefa.getDataConclusao() == null ? null
						: new java.sql.Date(tarefa.getDataConclusao().getTime()));
				preparedStatement.setInt(i++, tarefa.getStatus());
				preparedStatement.setInt(i++, id);
				preparedStatement.execute();
				connection.commit();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				fecharConeaxao(preparedStatement, connection);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar esse item", "", JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar esse item");
		}
	}

	public void excluir(int id) throws Exception {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(BD);
			connection.setAutoCommit(false);

			String sql = EXCLUIR_TAREFA;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			connection.commit();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar a tarefa selecionada", "",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			fecharConeaxao(preparedStatement, connection);
		}
	}

	public ArrayList<Tarefa> listar(int status) throws Exception {
		ArrayList<Tarefa> tarefas = new ArrayList<>();
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(BD);
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder(LISTAR_TAREFAS);

			if (status == 0) {
				sql.append(AND_STATUS_A_FAZER);
			}
			if (status == 1) {
				sql.append(AND_STATUS_EM_ANDAMENTO);
			}
			if (status == 2) {
				sql.append(AND_STATUS_CONCLUIDO);
			}

			preparedStatement = connection.prepareStatement(sql.toString());

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				tarefas.add(montarTarefa(rs));
			}
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			fecharConeaxao(preparedStatement, connection);
		}
		if (tarefas.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar a tarefa selecionada", "",
					JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar a tarefa selecionada");

		}
		return tarefas;
	}

	private Tarefa montarTarefa(ResultSet rs) throws SQLException {
		return new Tarefa(rs.getInt("ID"), rs.getString("TITULO"), rs.getString("DESCRICAO"),
				rs.getString("NOME_ETIQUETA"), rs.getInt("COR_ETIQUETA"), rs.getDate("DATA_CRIACAO"),
				rs.getDate("DATA_CONCLUSAO"), rs.getInt("STATUS"));
	}

	private boolean validarDadosTarefa(Tarefa tarefa) {
		if (tarefa.getNomeEtiqueta().isEmpty() || tarefa.getNomeEtiqueta() == null) {
			JOptionPane.showMessageDialog(null, "Confira se os campos titulo e nome da etiqueta estão preenchidos",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else {
			return true;
		}

	}
	
	private void fecharConeaxao(PreparedStatement preparedStatement, Connection connection) {
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
