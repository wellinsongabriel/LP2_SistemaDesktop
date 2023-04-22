package br.com.desktop.model;

import java.util.Date;

public class Tarefa {
	protected int id;
	protected String titulo;
	protected String descricao;
	protected String nomeEtiqueta;
	protected int corEtiqueta;
	protected Date dataCriacao;
	protected Date dataConclusao;
	protected int status;
	
	public Tarefa(int id, String titulo, String descricao, String nomeEtiqueta, int corEtiqueta, Date dataCriacao,
			Date dataConclusao, int status) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.nomeEtiqueta = nomeEtiqueta;
		this.corEtiqueta = corEtiqueta;
		this.dataCriacao = dataCriacao;
		this.dataConclusao = dataConclusao;
		this.status = status;
	}

	public Tarefa(String titulo, String descricao, String nomeEtiqueta, int corEtiqueta, Date dataCriacao,
			Date dataConclusao, int status) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.nomeEtiqueta = nomeEtiqueta;
		this.corEtiqueta = corEtiqueta;
		this.dataCriacao = dataCriacao;
		this.dataConclusao = dataConclusao;
		this.status = status;
	}
	
	public Tarefa() {
		
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeEtiqueta() {
		return nomeEtiqueta;
	}

	public void setNomeEtiqueta(String nomeEtiqueta) {
		this.nomeEtiqueta = nomeEtiqueta;
	}

	public int getCorEtiqueta() {
		return corEtiqueta;
	}

	public void setCorEtiqueta(int corEtiqueta) {
		this.corEtiqueta = corEtiqueta;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", nomeEtiqueta=" + nomeEtiqueta
				+ ", corEtiqueta=" + corEtiqueta + ", dataCriacao=" + dataCriacao + ", dataConclusao=" + dataConclusao
				+ ", status=" + status + "]";
	}	
	
	
	
}
