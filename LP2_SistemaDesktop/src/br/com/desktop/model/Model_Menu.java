package br.com.desktop.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_Menu {

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


	public Model_Menu(String icone, String nome, TipoMenu tipo, int identificador) {
		super();
		this.icone = icone;
		this.nome = nome;
		this.tipo = tipo;
		this.identificador = identificador;
	}


	public Model_Menu(String icone, String nome, TipoMenu tipo) {
		this.icone = icone;
		this.nome = nome;
		this.tipo = tipo;
	}

	
	public Model_Menu() {
	}

	
	public Icon toIcone() {
//		jLabel1.setIcon(new ImageIcon(Menu.class.getResource("/br/com/desktop/image/logoTaskMaster48.png")));
//		System.out.println(icone);
		return new ImageIcon(Model_Menu.class.getResource("/br/com/desktop/image/" + icone + ".png"));
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
