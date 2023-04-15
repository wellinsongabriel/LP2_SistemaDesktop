package br.com.desktop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.desktop.model.Tarefa;

public class DAO {
	private static Connection c = null;
	private static String DRIVER = "org.sqlite.JDBC";
	private static String BD = "jdbc:sqlite:src/br/com/desktop/dao/bdtarefas.db";

	private static String CADASTRAR_TAREFA = "INSERT INTO TAREFA "
			+ " (ID, TITULO, DESCRICAO, NOME_ETIQUETA, COR_ETIQUETA, DATA_CRIACAO, DATA_CONCLUSAO, STATUS) "
			+ " VALUES (null, ?, ?, ?, ?, ?, ?, ?)";

	private static String CONSULTAR_TAREFA = "SELECT * FROM TAREFA WHERE ID = ? ";

	private static String ALTERAR_TAREFA = " UPDATE TAREFA SET " 
			+ " TITULO = ?, " 
			+ " DESCRICAO = ?, "
			+ " NOME_ETIQUETA = ?, " 
			+ " COR_ETIQUETA = ?, " 
			+ " DATA_CRIACAO = ?, " 
			+ " DATA_CONCLUSAO = ?, "
			+ " STATUS = ? " 
			+ " WHERE ID = ? ";
	
	private static String EXCLUIR_TAREFA = "DELETE FROM TAREFA WHERE ID = ? ";
	
	private static String LISTAR_TAREFAS = "SELECT * FROM TAREFA "; 

	public void cadastrar(Tarefa tarefa) throws SQLException {
//		int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Cadastrar o Registro?", "Confirmar",
//				JOptionPane.YES_NO_OPTION);
//
//		if (response == JOptionPane.NO_OPTION) {
//			JOptionPane.getDefaultLocale();
//		} else if (response == JOptionPane.YES_OPTION) {
		try {
			Class.forName(DRIVER);
			c = DriverManager.getConnection(BD);
			c.setAutoCommit(false);
			String sql = CADASTRAR_TAREFA;
			PreparedStatement stmt = c.prepareStatement(sql);
			int i = 1;
			stmt.setString(i++, tarefa.getTitulo());
			stmt.setString(i++, tarefa.getDescricao());
			stmt.setString(i++, tarefa.getNomeEtiqueta());
			stmt.setString(i++, tarefa.getCorEtiqueta());
			stmt.setDate(i++, new java.sql.Date(tarefa.getDataCriacao().getTime()));
			stmt.setDate(i++,
					tarefa.getDataConclusao() == null ? null : new java.sql.Date(tarefa.getDataConclusao().getTime()));
			stmt.setString(i++, tarefa.getStatus());
			stmt.execute();
			stmt.close();
			c.commit();
			c.close();

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar a tarefa selecionada", "",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Tarefa incluída com sucesso");
	}

	public Tarefa consultar(int id) throws Exception {
		Tarefa tarefa = null;
		try {
			Class.forName(DRIVER);
			c = DriverManager.getConnection(BD);
			c.setAutoCommit(false);

			String sql = CONSULTAR_TAREFA;
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// int id = rs.getInt("id");
				tarefa = montarTarefa(rs);
			}
			c.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
				c = DriverManager.getConnection(BD);
				c.setAutoCommit(false);

				String sql = ALTERAR_TAREFA;
				PreparedStatement stmt = c.prepareStatement(sql);

				int i = 1;
				stmt.setString(i++, tarefa.getTitulo());
				stmt.setString(i++, tarefa.getDescricao());
				stmt.setString(i++, tarefa.getNomeEtiqueta());
				stmt.setString(i++, tarefa.getCorEtiqueta());
				stmt.setDate(i++, new java.sql.Date(tarefa.getDataCriacao().getTime()));
				stmt.setDate(i++, tarefa.getDataConclusao() == null ? null
						: new java.sql.Date(tarefa.getDataConclusao().getTime()));
				stmt.setString(i++, tarefa.getStatus());
				stmt.setInt(i++, id);
				stmt.execute();
				stmt.close();
				c.commit();
				c.close();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar esse item", "", JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar esse item");
		}
	}

	public void excluir(int id) throws Exception {
		Tarefa tarefa = null;
		try {
			Class.forName(DRIVER);
			c = DriverManager.getConnection(BD);
			c.setAutoCommit(false);

			String sql = EXCLUIR_TAREFA;
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			c.commit();
			c.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (tarefa == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar a tarefa selecionada", "",
					JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar a tarefa selecionada");

		}
	}
	
	public ArrayList<Tarefa> listar() throws Exception {
		ArrayList<Tarefa> tarefas = new ArrayList<>();
		try {
			Class.forName(DRIVER);
			c = DriverManager.getConnection(BD);
			c.setAutoCommit(false);

			String sql = LISTAR_TAREFAS;
			PreparedStatement stmt = c.prepareStatement(sql);

			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// int id = rs.getInt("id");
				tarefas.add(montarTarefa(rs));
			}
			c.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (tarefas.size()<0) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar a tarefa selecionada", "",
					JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar a tarefa selecionada");

		}
		return tarefas;
	}
	
	private Tarefa montarTarefa(ResultSet rs) throws SQLException {
		return new Tarefa(rs.getInt("ID"), rs.getString("TITULO"), rs.getString("DESCRICAO"),
				rs.getString("NOME_ETIQUETA"), rs.getString("COR_ETIQUETA"), rs.getDate("DATA_CRIACAO"),
				rs.getDate("DATA_CONCLUSAO"), rs.getString("STATUS"));
	}

}
