package br.com.desktop.model;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class MenuItem extends JPanel {

	public MenuItem() {
		setLayout(null);
		
		JLabel lblIcone = new JLabel("");
		lblIcone.setBounds(20, 0, 1, 35);
		add(lblIcone);

	}
}
