package br.com.desktop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.desktop.controller.Conexao;
import br.com.desktop.model.Projeto;

public class ProjetoDAO {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;
	
	private static final  String DRIVER = "org.sqlite.JDBC";
	private static final String BD = "jdbc:sqlite:resources/bdprojeto.db";

	private static final String CRIAR_PROJETO = " INSERT INTO PROJETO (ID, TITULO, STATUS) " + 
	" VALUES (NULL, ?, ?) ";
	
	private static final String CONSULTAR_PROJETO = " SELECT * FROM PROJETO WHERE ID = ? ";
			
	private static final String ALTERAR_PROJETO = " UPDATE PROJETO SET TITULO = ?, STATUS = ?  WHERE ID = ? ";
	
	private static final String LISTAR_PROJETO = " SELECT * FROM PROJETO ";
	
	private static final String EXCLUIR_PROJETO = " DELETE FROM PROJETO WHERE ID = ? ";
	
	private Projeto montarProjeto(ResultSet rs) throws SQLException {
		return new Projeto(rs.getInt("ID"), rs.getString("TITULO"),  rs.getInt("STATUS"));
	}
	
	public void criarProjeto(Projeto projeto) throws SQLException {
		if (validarDadosProjeto(projeto)) {
			try {
				abrirConexao();
				String sql = CRIAR_PROJETO;
				preparedStatement = connection.prepareStatement(sql);
				int i = 1;
				preparedStatement.setString(i++, projeto.getTitulo());
				preparedStatement.setInt(i++, projeto.getStatus());
				preparedStatement.execute();
				connection.commit();
			} finally {
				fecharConexao();
			}
		}
	}
	
	public Projeto consultarProjeto(int id)  throws Exception {
		Projeto projeto = null;
		try {
			abrirConexao();
			
			String sql = CONSULTAR_PROJETO;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
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
	
	public ArrayList<Projeto> listaProjeto() throws Exception {
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
	
	private boolean validarDadosProjeto(Projeto projeto) {
		if (projeto.getTitulo().isEmpty() || projeto.getTitulo() == null) {
			JOptionPane.showMessageDialog(null, "Confira se o campos titulo está preenchido",
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
			rs.close();
			preparedStatement.close();
			Conexao.getInstancia().fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
