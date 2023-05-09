package br.com.desktop.model;

public class Usuario {
	private int id;
	private String usuario;
	private String senha;
	private String tipoUsuario;
	private boolean selecionado; 
	
	
	public Usuario(int id, String usuario, String senha, String tipoUsuario) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}
	
	public Usuario(String usuario, String senha, String tipoUsuario) {
		super();
		this.usuario = usuario;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}
	
	
	public Usuario(int id, String usuario, String tipoUsuario) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.tipoUsuario = tipoUsuario;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", senha=" + senha + ", tipoUsuario=" + tipoUsuario + "]";
	}
	
	
	
}
