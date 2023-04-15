package br.com.desktop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.desktop.model.Tarefa;


public class DAO {
	private String DRIVER = "org.sqlite.JDBC";
	private String BD = "jdbc:sqlite:src/br/com/desktop/dao/bdtarefas.db";	
	private  String CADASTRAR_TAREFA = "INSERT INTO PESSOA (ID,NOME,CPF,ENDERECO,FONE)\" + \"VALUES (null,?,?,?,?)";
	
	
	public void cadastrar(Tarefa tarefa) throws SQLException {
//		int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Cadastrar o Registro?", "Confirmar",
//				JOptionPane.YES_NO_OPTION);
//
//		if (response == JOptionPane.NO_OPTION) {
//			JOptionPane.getDefaultLocale();
//		} else if (response == JOptionPane.YES_OPTION) {

			Connection c = null;
			try {
				Class.forName(DRIVER);
				c = DriverManager.getConnection(BD);
				c.setAutoCommit(false);
				String sql = CADASTRAR_TAREFA;
				PreparedStatement stmt = c.prepareStatement(sql);
				int i = 1;
				stmt.setString(1, tarefa.getDescricao());
				stmt.setDate(2, (Date) tarefa.getDataCriacao());
				stmt.setDate(3, (Date) tarefa.getDataConclusao());
				stmt.setString(4, Boolean.toString(tarefa.isConcluido()));
				stmt.execute();
				stmt.close();
				c.commit();
				c.close();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	//}
}	
