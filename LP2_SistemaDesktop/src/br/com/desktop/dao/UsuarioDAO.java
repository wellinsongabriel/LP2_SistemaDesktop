package br.com.desktop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.desktop.controller.Conexao;
import br.com.desktop.model.Usuario;

public class UsuarioDAO {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;
	
	private static final String CONSULTAR_USUARIO = " SELECT ID, USUARIO, SENHA, TIPO_USUARIO "
			+ " FROM USUARIO "
			+ " WHERE USUARIO = ? "
			+ " AND SENHA = ? ";
	
	private static final String LISTAR_USUARIOS = " SELECT * FROM USUARIO ";
	
	
	public Usuario consultarUsuario(String nomeUsuario, String senhaCriptografada) throws Exception {

		Usuario usuario = null;
		try {
			abrirConexao();
			String sql = CONSULTAR_USUARIO;
			preparedStatement = connection.prepareStatement(sql);

			int i = 1;

			preparedStatement.setString(i++, nomeUsuario);
			preparedStatement.setString(i++, senhaCriptografada);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// int id = rs.getInt("id");
				usuario = new Usuario(rs.getInt("ID"), rs.getString("USUARIO"), rs.getString("TIPO_USUARIO"));
			}

		}  catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problema ao efetuar a operação");
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if (usuario == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar o usario informado, verifique seus dados!",
					"", JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar o usario informado");

		}
		return usuario;
	
	}
	
	public ArrayList<Usuario> listarUsuarios() throws Exception {
		ArrayList<Usuario> usuarios = new ArrayList<>();

		try {
			abrirConexao();

			StringBuilder sql = new StringBuilder(LISTAR_USUARIOS);


			preparedStatement = connection.prepareStatement(sql.toString());

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				usuarios.add(montarUsuario(rs));
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problema ao efetuar a operação");
			e.printStackTrace();
		} finally {
			fecharConexao();

		}
		if (usuarios.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar a tarefa selecionada", "",
					JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar a tarefa selecionada");

		}
		return usuarios;
	}
	
	
	private Usuario montarUsuario(ResultSet rs) throws SQLException {
		return new Usuario(rs.getInt("ID"), rs.getString("USUARIO"), rs.getString("TIPO_USUARIO"));
	}
	
	
	private synchronized void abrirConexao(){
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
