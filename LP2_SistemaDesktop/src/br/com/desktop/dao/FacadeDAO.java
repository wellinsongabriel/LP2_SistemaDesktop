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

	// USUARIOS
	public Usuario consultarUsuario(String nomeUsuario, String senhaCriptografada) throws Exception {
		return usuarioDAO.consultarUsuario(nomeUsuario, senhaCriptografada);
	}

	public ArrayList<Usuario> listarUsuarios(ArrayList<Usuario> usuarioNaoListar) throws Exception {
		return usuarioDAO.listarUsuarios(usuarioNaoListar);
	}

	public void cadastrarUsuario(Usuario usuario) throws SQLException {
		usuarioDAO.cadastrarUsuario(usuario);
	}

	public void alterarUsuario(Usuario usuario) throws Exception {
		usuarioDAO.alterarUsuario(usuario);
	}

	public void excluirUsuario(int id) throws Exception {
		usuarioDAO.excluirUsuario(id);
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
	public void criarProjeto(Projeto projeto) throws SQLException {
		projetoDAO.criarProjeto(projeto);
	}

	public Projeto consultarProjeto(int id, long dataHoraCriacao) throws Exception {
		return projetoDAO.consultarProjeto(id, dataHoraCriacao);
	}

	public void criarProjetoUsuario(Projeto projeto, ArrayList<Usuario> usuariosSelecionados) throws SQLException {
		projetoDAO.criarProjetoUsuario(projeto, usuariosSelecionados);
	}

	public ArrayList<Projeto> listarProjetos() throws Exception {
		return projetoDAO.listarProjetos();
	}

	public ArrayList<Projeto> listarProjetosUsuario(int idUsuarioLogado) throws Exception {
		return projetoDAO.listarProjetosUsuario(idUsuarioLogado);
	}

	public ArrayList<Usuario> listarParticipantesProjeto(int idProjeto) throws Exception {
		return projetoDAO.listarParticipantesProjeto(idProjeto);
	}

	public void alterarNomeProjeto(Projeto projeto) throws Exception {
		projetoDAO.alterarNomeProjeto(projeto);
	}

	public void removerParticipanteProjeto(int idProjeto, int idUsuario) throws Exception {
		projetoDAO.removerParticipanteProjeto(idProjeto, idUsuario);
	}

	public void excluirProjeto(int idProjeto) throws Exception {
		projetoDAO.excluirProjeto(idProjeto);
	}
}
