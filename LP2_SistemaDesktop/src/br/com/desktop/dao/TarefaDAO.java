package br.com.desktop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import br.com.desktop.controller.Conexao;
import br.com.desktop.model.Projeto;
import br.com.desktop.model.Tarefa;

public class TarefaDAO {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;

	private static final String CADASTRAR_TAREFA = " INSERT INTO TAREFA "
			+ " (ID, TITULO, DESCRICAO, NOME_ETIQUETA, COR_ETIQUETA, DATA_CRIACAO, DATA_CONCLUSAO, STATUS, ID_PROJETO) "
			+ " VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String CONSULTAR_TAREFA = " SELECT * FROM TAREFA WHERE ID = ? ";

	private static final String ALTERAR_TAREFA = " UPDATE TAREFA SET " + " TITULO = ?, " + " DESCRICAO = ?, "
			+ " NOME_ETIQUETA = ?, " + " COR_ETIQUETA = ?, " + " DATA_CRIACAO = ?, " + " DATA_CONCLUSAO = ?, "
			+ " STATUS = ? " + " WHERE ID = ? ";

	private static final String EXCLUIR_TAREFA = " DELETE FROM TAREFA WHERE ID = ? ";

	private static final String LISTAR_TAREFAS = " SELECT * FROM TAREFA WHERE 1=1 ";

	private static final String AND_STATUS_A_FAZER = " AND STATUS = 0 ";

	private static final String AND_STATUS_EM_ANDAMENTO = " AND STATUS = 1 ";

	private static final String AND_STATUS_CONCLUIDO = " AND STATUS = 2 ";
	
	private static final String AND_ID_PROJETO = " AND ID_PROJETO = ?";
	
	public TarefaDAO() {

	}

	public void cadastrarTarefa(Tarefa tarefa, Projeto projeto) throws SQLException {
		if (validarDadosTarefa(tarefa)) {
			try {
				abrirConexao();
				String sql = CADASTRAR_TAREFA;
				preparedStatement = connection.prepareStatement(sql);
				int i = 1;
				preparedStatement.setString(i++, tarefa.getTitulo());
				preparedStatement.setString(i++, tarefa.getDescricao());
				preparedStatement.setString(i++, tarefa.getNomeEtiqueta());
				preparedStatement.setInt(i++, tarefa.getCorEtiqueta());
				
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(tarefa.getDataCriacao());
				preparedStatement.setDate(i++, new Date(calendar.getTime().getTime()),Calendar.getInstance());
				preparedStatement.setDate(i++, tarefa.getDataConclusao() == null ? null
						: new java.sql.Date(tarefa.getDataConclusao().getTime()));
				preparedStatement.setInt(i++, tarefa.getStatus());
				preparedStatement.setInt(i++, projeto.getId());
				preparedStatement.execute();
				connection.commit();
				JOptionPane.showMessageDialog(null, "Tarefa incluída com sucesso");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Problema ao efetuar a operação", "",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}finally {
			
				fecharConexao();
			}
			
		}
	}

	public Tarefa consultarTarefa(int id) throws Exception {
		Tarefa tarefa = null;
		try {
			abrirConexao();

			String sql = CONSULTAR_TAREFA;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// int id = rs.getInt("id");
				tarefa = montarTarefa(rs);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problema ao efetuar a operação", "",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
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
				abrirConexao();

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

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Problema ao efetuar a operação", "",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
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
			abrirConexao();
			String sql = EXCLUIR_TAREFA;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problema ao efetuar a operação", "",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}finally {
			fecharConexao();
		}
	}

	public ArrayList<Tarefa> listarTarefa(int status, Projeto projeto) throws Exception {
		ArrayList<Tarefa> tarefas = new ArrayList<>();

		try {
			abrirConexao();

			StringBuilder sql = new StringBuilder(LISTAR_TAREFAS);
			int i = 1;
			if (status == 0) {
				sql.append(AND_STATUS_A_FAZER);
			}
			if (status == 1) {
				sql.append(AND_STATUS_EM_ANDAMENTO);
			}
			if (status == 2) {
				sql.append(AND_STATUS_CONCLUIDO);
			}
			
			
			sql.append(AND_ID_PROJETO);
			


			preparedStatement = connection.prepareStatement(sql.toString());

			preparedStatement.setInt(i++, projeto.getId());
			
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				tarefas.add(montarTarefa(rs));
			}

		}  catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problema ao efetuar a operação", "",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}finally {
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
			JOptionPane.showMessageDialog(null, "Confira se o campo titulo esá preenchido",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			return false;
		} else {
			return true;
		}

	}

	
	private synchronized void abrirConexao(){
		connection = Conexao.getInstancia().abriConexao();
	}

	private synchronized void fecharConexao() {
		try {
			if(rs!=null) {
				rs.close();
			}
			
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			
			
			Conexao.getInstancia().fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
