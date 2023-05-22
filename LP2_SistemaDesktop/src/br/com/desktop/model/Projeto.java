package br.com.desktop.model;

import java.util.ArrayList;
import java.util.Date;

public class Projeto {
	protected int id;
	protected String titulo;
	protected int status;
	protected ArrayList<Usuario> usuarios;
	protected Date dataCriacao;
	protected Date dataConclusao;
	protected Usuario usuarioResponsavel;
	
	
	
	public Projeto(int id, String titulo, int status, Date dataCriacao,
			Date dataConclusao) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.status = status;
		this.dataCriacao = dataCriacao;
		this.dataConclusao = dataConclusao;
	}

	public Projeto(int id, String titulo, int status, ArrayList<Usuario> usuarios) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.status = status;
		this.usuarios = usuarios;
	}
	
	public Projeto(String titulo, ArrayList<Usuario> usuarios) {
		super();
		this.titulo = titulo;
		this.usuarios = usuarios;
	}
	
	public Projeto(String titulo, ArrayList<Usuario> usuarios, Usuario usuarioResponsavel) {
		super();
		this.titulo = titulo;
		this.usuarios = usuarios;
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Projeto(int id, String titulo, int status) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.status = status;
	}

	public Projeto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	@Override
	public String toString() {
		return "Projeto [id=" + id + ", titulo=" + titulo + ", status=" + status + ", usuarios=" + usuarios
				+ ", dataCriacao=" + dataCriacao + ", dataConclusao=" + dataConclusao + "]";
	}

	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

}
