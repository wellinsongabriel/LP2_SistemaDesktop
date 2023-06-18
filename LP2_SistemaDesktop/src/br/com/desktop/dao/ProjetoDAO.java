package br.com.desktop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import br.com.desktop.controller.Conexao;
import br.com.desktop.model.Projeto;
import br.com.desktop.model.Usuario;

public class ProjetoDAO {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;

	private static final String CRIAR_PROJETO = " INSERT INTO PROJETO (ID, NOME, ID_RESPONSAVEL, STATUS, DATA_CRIACAO, DATA_CONCLUSAO) " + 
	" VALUES (NULL, ?, ?, ?, ?, NULL) ";
	
	private static final String CRIAR_PROJETO_USUARIO = " INSERT INTO PROJETO_USUARIO (ID, ID_PROJETO, ID_USUARIO) " + 
			" VALUES (NULL, ?, ?) ";
	
	private static final String CONSULTAR_PROJETO = " SELECT * FROM PROJETO WHERE 1=1 ";
	
	private static final String AND_ID = " AND ID = ? "; 
			
	private static final String ALTERAR_NOME_PROJETO = " UPDATE PROJETO SET NOME = ? WHERE ID = ? ";
	
	private static final String LISTAR_PROJETO = " SELECT * FROM PROJETO WHERE 1=1 ";
	
	private static final String LISTAR_PROJETO_USUARIO = " SELECT DISTINCT P.ID, P.NOME, P.ID_RESPONSAVEL , P.STATUS, P.DATA_CRIACAO, P.DATA_CONCLUSAO  "
			+ " FROM PROJETO P "
			+ " INNER JOIN PROJETO_USUARIO PU "
			+ " ON (P.ID = PU.ID_PROJETO) "
			+ " WHERE P.ID_RESPONSAVEL =  ? OR PU.ID_USUARIO  = ? ";

	
	private static final String LISTAR_PARTICIPANTES_PROJETO = " SELECT  *, U.ID AS IDUSUARIO "
			+ " FROM PROJETO P "
			+ " INNER JOIN "
			+ " PROJETO_USUARIO PU "
			+ " ON(P.ID = PU.ID_PROJETO)"
			+ " INNER JOIN "
			+ " USUARIO U "
			+ " ON(PU.ID_USUARIO = U.ID) "
			+ " WHERE 1=1 "
			+ " AND PU.ID_PROJETO = ? ";
	
	private static final String LISTAR_PROJETO_INNER_JOIN_PROJETO_USUARIO = " SELECT P.ID, P.NOME, P.STATUS, PU.ID_USUARIO " + 
	" FROM PROJETO  P " +
	" INNER JOIN PROJETO_USUARIO PU " +
	" ON(P.ID = PU.ID_PROJETO) " +
	" WHERE   P.ID = ? ";
	
	private static final String AND_DATA_CRIACAO_MAIS_RECENTE = " AND DATA_CRIACAO >= ? "; 
	
	private static final String REMOVER_USUARIO_PROJETO = " DELETE FROM PROJETO_USUARIO WHERE 1=1 AND ID_PROJETO = ? AND ID_USUARIO = ? ";
	
	private static final String EXCLUIR_PROJETO = " DELETE FROM PROJETO WHERE ID = ? ";
	
	
	public ProjetoDAO() {
		
	}
	public void criarProjeto(Projeto projeto) throws SQLException {
		
		if (validarDadosProjeto(projeto)) {
			try {
				abrirConexao();
				String sql = CRIAR_PROJETO;
				preparedStatement = connection.prepareStatement(sql);
				int i = 1;
				preparedStatement.setString(i++, projeto.getTitulo());
				preparedStatement.setInt(i++, projeto.getUsuarioResponsavel().getId());
				preparedStatement.setInt(i++, 0);
				preparedStatement.setDate(i++, new java.sql.Date(new Date().getTime()));
				preparedStatement.execute();
				connection.commit();
				
			} finally {
				fecharConexao();
			}
		}
	}
	
	public Projeto consultarProjeto(int id, long dataHoraCriacao)  throws Exception {
		
		Projeto projeto = null;
		try {
			
			abrirConexao();
			
			StringBuilder sql = new StringBuilder(CONSULTAR_PROJETO);
			
			

			if(id!=0) {
				sql.append(AND_ID);
			}
			
			if(id!=dataHoraCriacao) {
				sql.append(AND_DATA_CRIACAO_MAIS_RECENTE);
			}
			
			
			
			preparedStatement = connection.prepareStatement(sql.toString());
			int i = 1;
			
			if(id!=0) {
				preparedStatement.setInt(i++, id);
			}
			
			if(id!=dataHoraCriacao) {
				preparedStatement.setDate(i++, new java.sql.Date(dataHoraCriacao));
			}
			
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// int id = rs.getInt("id");
				projeto = montarProjeto(rs);
			}
		} finally {
			fecharConexao();
		}
		if (projeto == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar o projeto selecionada", "",
					JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar o projeto selecionada");

		}
		return projeto;
	}
	
	public void alterarNomeProjeto(Projeto projeto) throws Exception {
			try {
				abrirConexao();
				String sql = ALTERAR_NOME_PROJETO;
				preparedStatement = connection.prepareStatement(sql);
				
				int i = 1;
				
				preparedStatement.setString(i++, projeto.getTitulo());
				preparedStatement.setInt(i++, projeto.getId());
				
				preparedStatement.execute();
				connection.commit();
				
			} finally {
				fecharConexao();
			}
		
	}
	
	public ArrayList<Projeto> listarProjetos() throws Exception {
		ArrayList<Projeto> projeto = new ArrayList<>();

		try {
			abrirConexao();

			StringBuilder sql = new StringBuilder(LISTAR_PROJETO);

			
			preparedStatement = connection.prepareStatement(sql.toString());

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				projeto.add(montarProjeto(rs));
			}

		} finally {
			fecharConexao();

		}
		if (projeto.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar o projeto selecionado", "",
					JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar o projeto selecionada");

		}
		return projeto;
	}
	
	public ArrayList<Projeto> listarProjetosUsuario(int idUsuarioLogado) throws Exception {
		ArrayList<Projeto> projeto = new ArrayList<>();

		try {
			abrirConexao();

			StringBuilder sql = new StringBuilder(LISTAR_PROJETO_USUARIO);

			preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setInt(1, idUsuarioLogado);
			preparedStatement.setInt(2, idUsuarioLogado);
			
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				projeto.add(montarProjeto(rs));
			}
		} finally {
			fecharConexao();
		}
		return projeto;
	}
	
	public ArrayList<Usuario> listarParticipantesProjeto(int idProjeto) throws Exception {
		ArrayList<Usuario> usuariosProjetos = new ArrayList<>();

		try {
			abrirConexao();

			String sql = LISTAR_PARTICIPANTES_PROJETO;

			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, idProjeto);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				usuariosProjetos.add(new Usuario(rs.getInt("IDUSUARIO"),rs.getString("USUARIO"), rs.getString("TIPO_USUARIO")));
			}

		} finally {
			fecharConexao();

		}
		if (usuariosProjetos.size() < 0) {
			throw new Exception("Nenhum usuário vinculado ao projeto");

		}
		return usuariosProjetos;
	}
	
	public void criarProjetoUsuario(Projeto projeto, ArrayList<Usuario> usuariosSelecionados) throws SQLException {

		for(Usuario usuario: usuariosSelecionados) {
			try {
				abrirConexao();
				String sql = CRIAR_PROJETO_USUARIO;
				preparedStatement = connection.prepareStatement(sql);
				int i = 1;
				preparedStatement.setInt(i++, projeto.getId());
				preparedStatement.setInt(i++, usuario.getId());
				preparedStatement.execute();
				connection.commit();

//				System.out.println("Usuario "+usuario.getUsuario()+" vinculado ao projeto "+projeto.getTitulo());
			} finally {
				fecharConexao();
			}			
		}				
	}
	
	public void removerParticipanteProjeto(int idProjeto, int idUsuario) throws Exception {
		try {
			abrirConexao();
			String sql = REMOVER_USUARIO_PROJETO;
			preparedStatement = connection.prepareStatement(sql);
			int i = 1;
			preparedStatement.setInt(i++, idProjeto);
			preparedStatement.setInt(i++, idUsuario);
			preparedStatement.execute();
			connection.commit();

			JOptionPane.showMessageDialog(null, "Participante removido com sucesso");
		} finally {
			fecharConexao();
		}			
	}
	
	public void excluirProjeto(int idProjeto) throws Exception {
		try {
			abrirConexao();
			String sql = EXCLUIR_PROJETO;
			preparedStatement = connection.prepareStatement(sql);
			int i = 1;
			preparedStatement.setInt(i++, idProjeto);
			preparedStatement.execute();
			connection.commit();

			JOptionPane.showMessageDialog(null, "Projeto excluído com sucesso");
		} finally {
			fecharConexao();
		}			
	}
	
	private Projeto montarProjeto(ResultSet rs) throws SQLException {
		return new Projeto(rs.getInt("ID"), rs.getString("NOME"),  rs.getInt("STATUS"), rs.getDate("DATA_CRIACAO"), rs.getDate("DATA_CONCLUSAO"));
	}
	
	private boolean validarDadosProjeto(Projeto projeto) {
		if (projeto.getTitulo().isEmpty() || projeto.getTitulo() == null) {
			JOptionPane.showMessageDialog(null, "Confira se o campo nome do projeto está preenchido",
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
