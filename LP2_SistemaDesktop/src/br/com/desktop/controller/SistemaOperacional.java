package br.com.desktop.controller;

public class SistemaOperacional {

	private static SistemaOperacional instancia;

	private SistemaOperacional() {

	}

	public static SistemaOperacional getIstancia() {
		if (instancia == null) {
			instancia = new SistemaOperacional();
		}
		return instancia;
	}

	public boolean sistemaWindows() {
		if (System.getProperty("os.name").contains("Windows")) {
			return true;
		} else {
			return false;
		}
	}
}
