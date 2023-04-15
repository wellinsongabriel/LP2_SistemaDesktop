package br.com.desktop.model;

import java.util.Date;

public class Tarefa {
	protected int id;
	protected String titulo;
	protected String descricao;
	protected String nomeEtiqueta;
	protected String corEtiqueta;
	protected Date dataCriacao;
	protected Date dataConclusao;
	protected String status;
	
	public Tarefa(int id, String titulo, String descricao, String nomeEtiqueta, String corEtiqueta, Date dataCriacao,
			Date dataConclusao, String status) {
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

	public Tarefa(String titulo, String descricao, String nomeEtiqueta, String corEtiqueta, Date dataCriacao,
			Date dataConclusao, String status) {
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

	public String getCorEtiqueta() {
		return corEtiqueta;
	}

	public void setCorEtiqueta(String corEtiqueta) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", nomeEtiqueta=" + nomeEtiqueta
				+ ", corEtiqueta=" + corEtiqueta + ", dataCriacao=" + dataCriacao + ", dataConclusao=" + dataConclusao
				+ ", status=" + status + "]";
	}	
	
	
	
}
