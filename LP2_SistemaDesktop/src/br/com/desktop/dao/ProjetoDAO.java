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

	private static final String CRIAR_PROJETO = " INSERT INTO PROJETO (ID, NOME, STATUS, DATA_CRIACAO, DATA_CONCLUSAO) " + 
	" VALUES (NULL, ?, ?, ?, NULL) ";
	
	private static final String CRIAR_PROJETO_USUARIO = " INSERT INTO PROJETO_USUARIO (ID, ID_PROJETO, ID_USUARIO) " + 
			" VALUES (NULL, ?, ?) ";
	
	private static final String CONSULTAR_PROJETO = " SELECT * FROM PROJETO WHERE 1=1 ";
	
	private static final String AND_ID = " AND ID = ? "; 
			
	private static final String ALTERAR_PROJETO = " UPDATE PROJETO SET NOME = ?, STATUS = ?  WHERE ID = ? ";
	
	private static final String LISTAR_PROJETO = " SELECT * FROM PROJETO WHERE 1=1 ";
	
	private static final String LISTAR_USUARIOS_PROJETO = " SELECT  * "
			+ " FROM PROJETO P "
			+ " INNER JOIN "
			+ " PROJETO_USUARIO PU "
			+ " ON(P.ID = PU.ID_PROJETO)"
			+ " INNER JOIN "
			+ " USUARIO U "
			+ " ON(PU.ID_USUARIO = U.ID) "
			+ " WHERE 1=1 "
			+ " AND PU.ID_PROJETO = ? ";
	
//	private static final String LISTAR_PROJETO_INNER_JOIN_PROJETO_USUARIO = " SELECT P.ID, P.NOME, P.STATUS, PU.ID_USUARIO " + 
//	" FROM PROJETO  P " +
//	" INNER JOIN PROJETO_USUARIO PU " +
//	" ON(P.ID = PU.ID_PROJETO) " +
//	" WHERE   P.ID = ? ";
	
	private static final String AND_DATA_CRIACAO_MAIS_RECENTE = " AND DATA_CRIACAO >= ? "; 
	
//	private static final String EXCLUIR_PROJETO = " DELETE FROM PROJETO WHERE ID = ? ";
	
	
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
	
	public void alterarProjeto(Projeto projeto) throws Exception {
		if (validarDadosProjeto(projeto)) {
			try {
				abrirConexao();
				String sql = ALTERAR_PROJETO;
				preparedStatement = connection.prepareStatement(sql);
				int i = 1;
				preparedStatement.setString(i++, projeto.getTitulo());
				preparedStatement.setInt(i++, projeto.getStatus());
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
			JOptionPane.showMessageDialog(null, "Não foi possível localizar o projeto selecionada", "",
					JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar o projeto selecionada");

		}
		return projeto;
	}
	
	public ArrayList<String> listarUsuariosProjetos(int idProjeto) throws Exception {
		ArrayList<String> usuariosProjetos = new ArrayList<>();

		try {
			abrirConexao();

			String sql = LISTAR_USUARIOS_PROJETO;

			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, idProjeto);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				usuariosProjetos.add(rs.getString("USUARIO"));
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
		JOptionPane.showMessageDialog(null, "Projeto incluído com sucesso");		
	}
	
	
	private Projeto montarProjeto(ResultSet rs) throws SQLException {
		return new Projeto(rs.getInt("ID"), rs.getString("NOME"),  rs.getInt("STATUS"), rs.getDate("DATA_CRIACAO"), rs.getDate("DATA_CRIACAO"));
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
	
	private void abrirConexao() {
		connection = Conexao.getInstancia().abriConexao();
	}

	private void fecharConexao() {
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
