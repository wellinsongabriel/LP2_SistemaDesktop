package br.com.desktop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.desktop.controller.Conexao;
import br.com.desktop.controller.Criptografia;
import br.com.desktop.model.Usuario;

public class UsuarioDAO {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet rs = null;
	
	private static final String CONSULTAR_USUARIO = " SELECT ID, USUARIO, SENHA, TIPO_USUARIO "
			+ " FROM USUARIO "
			+ " WHERE USUARIO = ? "
			+ " AND SENHA = ? ";
	
	private static final String LISTAR_USUARIOS = " SELECT * FROM USUARIO WHERE 1=1 ";
	
	private static final String LISTAR_USUARIOS_EXCETO_RESPONSAVEL = " SELECT DISTINCT U.ID, U.USUARIO, U.TIPO_USUARIO   "
			+ " FROM USUARIO U "
			+ " INNER JOIN PROJETO P "
			+ " ON U.ID <> P.ID_RESPONSAVEL "
			+ " WHERE 1=1 ";
	
	private static final String CADASTRAR_USUARIO = " INSERT INTO USUARIO "
			+ " (ID, USUARIO, SENHA, TIPO_USUARIO) "
			+ " VALUES (null, ?, ?, ?)";
	
	private static final String ALTERAR_USUARIO = " UPDATE USUARIO SET " + " USUARIO = ?, " + " SENHA = ?, "
			+ " TIPO_USUARIO = ? "
			+ " WHERE ID = ? ";	
	
	private static final String EXCLUIR_USUARIO = " DELETE FROM USUARIO WHERE ID = ? ";
	
	private static final String AND_NAO_LISTAR_USUARIO =  " AND  U.ID <> ? ";
	
	public Usuario consultarUsuario(String nomeUsuario, String senha) throws Exception {
		
		Criptografia criptografia = new Criptografia(senha, Criptografia.SHA256);		
		Usuario usuario = null;
		try {
			abrirConexao();
			String sql = CONSULTAR_USUARIO;
			preparedStatement = connection.prepareStatement(sql);

			int i = 1;

			preparedStatement.setString(i++, nomeUsuario);
			preparedStatement.setString(i++, criptografia.criptografar());
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
	
	public ArrayList<Usuario> listarUsuarios(ArrayList<Usuario> usuarioNaoListar) throws Exception {
		ArrayList<Usuario> usuarios = new ArrayList<>();

		try {
			abrirConexao();

			StringBuilder sql = new StringBuilder(LISTAR_USUARIOS);
			
			if(usuarioNaoListar!=null) {
				sql = new StringBuilder(LISTAR_USUARIOS_EXCETO_RESPONSAVEL);
			for(int i=0; i< usuarioNaoListar.size(); i++) {
				sql.append(AND_NAO_LISTAR_USUARIO);
			}
			}

			
			preparedStatement = connection.prepareStatement(sql.toString());
			
			if(usuarioNaoListar!=null) {
			int i = 1;
			for(Usuario participante: usuarioNaoListar) {
//				System.out.println(participante.getId());
				preparedStatement.setInt(i++, participante.getId());
			}
			}

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
	
	public void cadastrarUsuario(Usuario usuario) throws SQLException {
//		if (validarDadosTarefa(tarefa)) {
			try {
				abrirConexao();
				
				String sql = CADASTRAR_USUARIO;
				Criptografia criptografia = new Criptografia(usuario.getSenha(), Criptografia.SHA256);
				preparedStatement = connection.prepareStatement(sql);
				
				int i = 1;
				
				preparedStatement.setString(i++, usuario.getUsuario());
				preparedStatement.setString(i++,  criptografia.criptografar());
				preparedStatement.setString(i++, usuario.getTipoUsuario());
				
				preparedStatement.execute();
				connection.commit();
				
				JOptionPane.showMessageDialog(null, "Usuário incluído com sucesso");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Problema ao efetuar a operação", "",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}finally {
			
				fecharConexao();
			}
			
//		}
	}
	
	public void alterarUsuario(Usuario usuario) throws Exception {

//		if (consultarTarefa(id) != null) {
		Criptografia criptografia = new Criptografia(usuario.getSenha(), Criptografia.SHA256);	
			try {
				abrirConexao();

				String sql = ALTERAR_USUARIO;
				preparedStatement = connection.prepareStatement(sql);

				int i = 1;
				preparedStatement.setString(i++, usuario.getUsuario());
				preparedStatement.setString(i++, criptografia.criptografar());
				preparedStatement.setString(i++, usuario.getTipoUsuario());
				
				preparedStatement.setInt(i++, usuario.getId());
				
				preparedStatement.execute();
				connection.commit();
				
				JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso");

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Problema ao efetuar a operação", "",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} finally {
				fecharConexao();
			}
//		} 
//	else {
//			JOptionPane.showMessageDialog(null, "Não foi possível localizar esse item", "", JOptionPane.ERROR_MESSAGE);
//			throw new Exception("Não foi possível localizar esse item");
//		}
//	}
	}
	
	private Usuario montarUsuario(ResultSet rs) throws SQLException {
		return new Usuario(rs.getInt("ID"), rs.getString("USUARIO"), rs.getString("TIPO_USUARIO"));
	}
	
	public void excluirUsuario(int id) throws Exception {
		try {
			abrirConexao();
			String sql = EXCLUIR_USUARIO;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problema ao efetuar a operação", "",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}finally {
			fecharConexao();
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
