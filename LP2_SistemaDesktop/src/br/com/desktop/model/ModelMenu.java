package br.com.desktop.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ModelMenu {

	private String icone;
	private String nome;
	private TipoMenu tipo;
	private int identificador;
	
	public String getIcone() {
		return icone;
	}


	public void setIcone(String icone) {
		this.icone = icone;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public TipoMenu getTipo() {
		return tipo;
	}


	public void setTipo(TipoMenu tipo) {
		this.tipo = tipo;
	}


	public ModelMenu(String icone, String nome, TipoMenu tipo, int identificador) {
		super();
		this.icone = icone;
		this.nome = nome;
		this.tipo = tipo;
		this.identificador = identificador;
	}


	public ModelMenu(String icone, String nome, TipoMenu tipo) {
		this.icone = icone;
		this.nome = nome;
		this.tipo = tipo;
	}

	
	public ModelMenu() {
	}

	
	public Icon toIcone() {
		return new ImageIcon(ModelMenu.class.getResource("/br/com/desktop/image/" + icone + ".png"));
	}


	public static enum TipoMenu {
		TITULO, MENU, VAZIO
	}


	public int getIdentificador() {
		return identificador;
	}


	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
}
