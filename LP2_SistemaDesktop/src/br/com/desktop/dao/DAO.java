package br.com.desktop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.desktop.controller.Conexao;
import br.com.desktop.model.Tarefa;
import br.com.desktop.model.Usuario;

public class DAO {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;
	
	private static final  String DRIVER = "org.sqlite.JDBC";
	private static final String BD = "jdbc:sqlite:resources/bdtarefas.db";

	private static final String CADASTRAR_TAREFA = "INSERT INTO TAREFA "
			+ " (ID, TITULO, DESCRICAO, NOME_ETIQUETA, COR_ETIQUETA, DATA_CRIACAO, DATA_CONCLUSAO, STATUS) "
			+ " VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String CONSULTAR_TAREFA = "SELECT * FROM TAREFA WHERE ID = ? ";
	
	private static final String ALTERAR_TAREFA = " UPDATE TAREFA SET " + " TITULO = ?, " + " DESCRICAO = ?, "
			+ " NOME_ETIQUETA = ?, " + " COR_ETIQUETA = ?, " + " DATA_CRIACAO = ?, " + " DATA_CONCLUSAO = ?, "
			+ " STATUS = ? " + " WHERE ID = ? ";
	
	private static final String EXCLUIR_TAREFA = "DELETE FROM TAREFA WHERE ID = ? ";

	private static final String LISTAR_TAREFAS = "SELECT * FROM TAREFA WHERE 1=1";

	private static final String AND_STATUS_A_FAZER = " AND STATUS = 0 ";

	private static final String AND_STATUS_EM_ANDAMENTO = " AND STATUS = 1 ";

	private static final String AND_STATUS_CONCLUIDO = " AND STATUS = 2 ";
	
	private static final String CONSULTAR_USUARIO = " SELECT USUARIO, SENHA, TIPO_USUARIO "
			+ " FROM USUARIO "
			+ " WHERE USUARIO = ? "
			+ " AND SENHA = ? ";
	
	public DAO() {
		connection = Conexao.getInstancia().abriConexao();
	}

	public void cadastrarTarefa(Tarefa tarefa) throws SQLException {
		
		if (validarDadosTarefa(tarefa)) {
			try {
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

			} finally {
				fecharConexao();
			}
			JOptionPane.showMessageDialog(null, "Tarefa incluída com sucesso");
		}
	}

	
	public Tarefa consultarTarefa(int id) throws Exception {
		Tarefa tarefa = null;
		try {

			String sql = CONSULTAR_TAREFA;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// int id = rs.getInt("id");
				tarefa = montarTarefa(rs);
			}
			connection.close();

		} finally {
			fecharConexao();
		}
		if (tarefa == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar a tarefa selecionada", "",
					JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar a tarefa selecionada");

		}
		return tarefa;
	}

	
	public void alterarTarefa(int id, Tarefa tarefa) throws Exception {
		
		
		if (consultarTarefa(id) != null) {
			try {

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

			} finally {
				fecharConexao();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar esse item", "", JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar esse item");
		}
	}

	
	public void excluirTarefa(int id) throws Exception {
		try {

			String sql = EXCLUIR_TAREFA;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			connection.commit();
		} finally {
			fecharConexao();
		}
	}

	public ArrayList<Tarefa> listarTarefa(int status) throws Exception {
		ArrayList<Tarefa> tarefas = new ArrayList<>();
		
		try {
			 

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

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				tarefas.add(montarTarefa(rs));
			}
			

		} finally {
			fecharConexao();			
			
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
		if (tarefa.getTitulo().isEmpty() || tarefa.getTitulo() == null) {
			JOptionPane.showMessageDialog(null, "Confira se os campos titulo e nome da etiqueta estão preenchidos",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else {
			return true;
		}

	}
	
	public Usuario consultarUsuario(String nomeUsuario, String senhaCriptografada) throws Exception {

		Usuario usuario = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(BD);
			connection.setAutoCommit(false);

			String sql = CONSULTAR_USUARIO;
			preparedStatement = connection.prepareStatement(sql);

			int i = 1;
			
			preparedStatement.setString(i++, nomeUsuario);
			preparedStatement.setString(i++, senhaCriptografada);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// int id = rs.getInt("id");
				usuario = new Usuario(rs.getString("USUARIO"), rs.getString("SENHA"), rs.getString("TIPO_USUARIO"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if (usuario == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar o usario informado, verifique seus dados!", "",
					JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar o usario informado");

		}
		return usuario;
	
	}
	
	private void fecharConexao() {
		try {
			rs.close();
			preparedStatement.close();
			Conexao.getInstancia().fecharConexao();			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
}
