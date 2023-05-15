package br.com.desktop.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.desktop.model.Projeto;
import br.com.desktop.model.Tarefa;
import br.com.desktop.model.Usuario;

public class FacadeDAO {
	private UsuarioDAO usuarioDAO;
	private TarefaDAO tarefaDao;
	private ProjetoDAO projetoDAO;

	public FacadeDAO() {
		usuarioDAO = new UsuarioDAO();
		tarefaDao = new TarefaDAO();
		projetoDAO = new ProjetoDAO();
	}
	
	//USUARIOS
	public Usuario consultarUsuario(String nomeUsuario, String senhaCriptografada) throws Exception {
		return usuarioDAO.consultarUsuario(nomeUsuario, senhaCriptografada);
	}
	
	public ArrayList<Usuario> listarUsuarios() throws Exception {
		return usuarioDAO.listarUsuarios();
	}
	
	// TAREFAS
	public void cadastrarTarefa(Tarefa tarefa, Projeto projeto) throws SQLException {
		tarefaDao.cadastrarTarefa(tarefa, projeto);
	}

	public Tarefa consultarTarefa(int id) throws Exception {
		return tarefaDao.consultarTarefa(id);
	}

	public void alterarTarefa(int id, Tarefa tarefa) throws Exception {
		tarefaDao.alterarTarefa(id, tarefa);
	}
	
	public void excluirTarefa(int id) throws Exception {
		tarefaDao.excluirTarefa(id);
	}

	public ArrayList<Tarefa> listarTarefa(int status, Projeto projeto) throws Exception {
		return tarefaDao.listarTarefa(status, projeto);
	}

	// PROJETOS
	public ArrayList<Projeto> listarProjetos() throws Exception {
		return projetoDAO.listarProjetos();
	}

}
