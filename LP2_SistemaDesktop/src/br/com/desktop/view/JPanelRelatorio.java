package br.com.desktop.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

import br.com.desktop.controller.GeraRelatorio;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import datechooser.beans.DateChooserCombo;

public class JPanelRelatorio extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelRelatorio() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(251, 37, 515, 280);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Relatório de Tarefas");
		lblNewLabel_1.setBounds(171, 25, 172, 21);
		lblNewLabel_1.setForeground(new Color(255, 128, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Projeto");
		lblNewLabel_2_1_1.setBounds(38, 64, 46, 14);
		panel.add(lblNewLabel_2_1_1);
		lblNewLabel_2_1_1.setForeground(Color.GRAY);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JComboBox cbProjeto = new JComboBox();
		cbProjeto.setBackground(Color.WHITE);
		cbProjeto.setBounds(38, 80, 439, 28);
		panel.add(cbProjeto);
		cbProjeto.setToolTipText("Projeto");
		
		JButton btnGerarRelatrio = new JButton("Gerar relatório");
		btnGerarRelatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeraRelatorio rel = new GeraRelatorio();
			}
		});
		
		
		
		btnGerarRelatrio.setBounds(172, 223, 171, 28);
		panel.add(btnGerarRelatrio);
		btnGerarRelatrio.setToolTipText("Salvar");
		btnGerarRelatrio.setForeground(Color.WHITE);
		btnGerarRelatrio.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGerarRelatrio.setBorder(null);
		btnGerarRelatrio.setBackground(new Color(255, 128, 0));
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Data inicial");
		lblNewLabel_2_1_1_1.setBounds(195, 125, 73, 14);
		panel.add(lblNewLabel_2_1_1_1);
		lblNewLabel_2_1_1_1.setForeground(Color.GRAY);
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Data final");
		lblNewLabel_2_1_1_1_1.setBounds(352, 125, 73, 14);
		panel.add(lblNewLabel_2_1_1_1_1);
		lblNewLabel_2_1_1_1_1.setForeground(Color.GRAY);
		lblNewLabel_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Status da tarefa");
		lblNewLabel_2_1_1_2.setBounds(38, 125, 88, 14);
		panel.add(lblNewLabel_2_1_1_2);
		lblNewLabel_2_1_1_2.setForeground(Color.GRAY);
		lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JComboBox cbStatusProjeto = new JComboBox();
		cbStatusProjeto.setBounds(38, 141, 125, 28);
		panel.add(cbStatusProjeto);
		cbStatusProjeto.setToolTipText("Status");
		
		DateChooserCombo dateInicio = new DateChooserCombo();
		dateInicio.setBounds(195, 141, 125, 28);
		panel.add(dateInicio);
		
		DateChooserCombo dateFim = new DateChooserCombo();
		dateFim.setBounds(352, 141, 125, 28);
		panel.add(dateFim);
		
	}
}
