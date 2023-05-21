package br.com.desktop.view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.ComponentOrientation;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class JPanelSobre extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelSobre() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Sobre");
		lblNewLabel_1.setIcon(new ImageIcon(JPanelSobre.class.getResource("/br/com/desktop/image/info.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(20, 11, 139, 21);
		add(lblNewLabel_1);
		
		JTextPane txtpnDesenvolvidoPorBeatriz = new JTextPane();
		txtpnDesenvolvidoPorBeatriz.setSelectedTextColor(Color.LIGHT_GRAY);
		txtpnDesenvolvidoPorBeatriz.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtpnDesenvolvidoPorBeatriz.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnDesenvolvidoPorBeatriz.setText("Este sistema foi desenvolvido para a disciplina de Linguagem de Programação II, do curso de Análise e Desenvolvimento de Sistemas do IFSP Campus Votuporanga, ministrada pelo professor Ricardo Conde.");
		txtpnDesenvolvidoPorBeatriz.setBounds(20, 233, 409, 57);
		add(txtpnDesenvolvidoPorBeatriz);
		
		JLabel lblNewLabel_1_2 = new JLabel("TaskMaster");
		lblNewLabel_1_2.setForeground(new Color(255, 128, 0));
		lblNewLabel_1_2.setFont(new Font("DejaVu Sans", Font.BOLD, 26));
		lblNewLabel_1_2.setBounds(69, 51, 219, 43);
		add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("");
		lblNewLabel_1_2_1.setIcon(new ImageIcon(JPanelSobre.class.getResource("/br/com/desktop/image/logoTaskMaster45.png")));
		lblNewLabel_1_2_1.setForeground(new Color(255, 128, 0));
		lblNewLabel_1_2_1.setFont(new Font("DejaVu Sans", Font.BOLD, 30));
		lblNewLabel_1_2_1.setBounds(20, 43, 45, 43);
		add(lblNewLabel_1_2_1);
		
		JTextPane txtpnTaskmasterUm = new JTextPane();
		txtpnTaskmasterUm.setText("TaskMaster é um sistema de gerenciamento de tarefas, criado para facilitar o controle de demandas de diversas áreas. \r\n\r\nO usuário pode criar projetos, inserir tarefas com diferentes status e pode incluir outros usuários para atuarem nas tarefas. \r\n");
		txtpnTaskmasterUm.setSelectedTextColor(Color.LIGHT_GRAY);
		txtpnTaskmasterUm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnTaskmasterUm.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtpnTaskmasterUm.setBounds(20, 105, 409, 111);
		add(txtpnTaskmasterUm);
		
		JLabel lblNewLabel = new JLabel(" Desenvolvido por Beatriz Oliveira e Wellinson Gabriel");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setIcon(new ImageIcon(JPanelSobre.class.getResource("/br/com/desktop/image/path226.png")));
		lblNewLabel.setBounds(20, 301, 391, 14);
		add(lblNewLabel);

	}
}
