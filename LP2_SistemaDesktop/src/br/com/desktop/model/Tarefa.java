package br.com.desktop.model;

import java.util.Date;

public class Tarefa {
	protected int id;
	protected String descricao;
	protected Date dataCriacao;
	protected Date dataConclusao;
	protected boolean concluido;
	
	public Tarefa(int id, String descricao, Date dataCriacao, Date dataConclusao, boolean concluido) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.dataConclusao = dataConclusao;
		this.concluido = concluido;
	}
	
	public Tarefa() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}
	
	
	
	
}
