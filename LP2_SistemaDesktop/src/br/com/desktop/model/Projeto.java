package br.com.desktop.model;

public class Projeto {
	protected int id;
	protected String titulo;
	protected int status;
	
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

}
