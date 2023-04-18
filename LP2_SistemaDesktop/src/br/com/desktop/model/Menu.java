package br.com.desktop.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Menu {

	private String icone;
	private String nome;
	private TipoMenu tipo;
	
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


	public Menu(String icone, String nome, TipoMenu tipo) {
		this.icone = icone;
		this.nome = nome;
		this.tipo = tipo;
	}

	
	public Menu() {
	}

	
	public Icon toIcone() {
		return new ImageIcon(getClass().getResource("/br/com/desktop/image" + icone + ".png"));
	}


	public static enum TipoMenu {
		TITULO, MENU, VAZIO
	}
}
