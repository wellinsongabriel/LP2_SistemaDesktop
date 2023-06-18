package br.com.desktop.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import br.com.desktop.controller.GeraRelatorio;
import br.com.desktop.dao.FacadeDAO;
import br.com.desktop.model.Projeto;
import br.com.desktop.model.Usuario;
import datechooser.beans.DateChooserCombo;

public class JPanelRelatorio extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 187843406235523267L;
	FacadeDAO dao = new FacadeDAO();
	String[] nomesProjetos = null;
	ArrayList<Projeto> projetos = new ArrayList<>();

	@SuppressWarnings("rawtypes")
	public JPanelRelatorio(JPanel mainPanel, JFrameDashboard jFrame, Usuario usuarioLogado) {
		setOpaque(true);
		setBounds(44, 86, 1000, 800);
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

		try {
			projetos = dao.listarProjetosUsuario(usuarioLogado.getId());
			nomesProjetos = projetos.stream().map(Projeto::getTitulo).filter(titulo -> !titulo.equalsIgnoreCase(""))
					.collect(Collectors.toList()).toArray(new String[projetos.size()]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JComboBox<String> cbProjeto = new JComboBox<>(nomesProjetos);
		cbProjeto.setBackground(Color.WHITE);
		cbProjeto.setBounds(38, 80, 439, 28);
		panel.add(cbProjeto);
		cbProjeto.setToolTipText("Projeto");

		
		JButton btnGerarRelatrio = new JButton("Gerar relatório");
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

		String[] status = new String[] {"Todas","A Fazer", "Em Andamento", "Concluída"};
		@SuppressWarnings("unchecked")
		JComboBox cbStatusProjeto = new JComboBox(status);
		cbStatusProjeto.setBounds(38, 141, 125, 28);
		panel.add(cbStatusProjeto);
		cbStatusProjeto.setToolTipText("Status");

		DateChooserCombo dateInicio = new DateChooserCombo();
		dateInicio.setBounds(195, 141, 125, 28);
		panel.add(dateInicio);

		DateChooserCombo dateFim = new DateChooserCombo();
		dateFim.setBounds(352, 141, 125, 28);
		panel.add(dateFim);
		
		
		
		btnGerarRelatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Projeto projetoSelecionado = projetos.stream()
				        .filter(projeto -> projeto.getTitulo()
				        .equalsIgnoreCase(cbProjeto.getSelectedItem()
				        .toString()))
				        .findFirst()
				        .orElse(null);
				new GeraRelatorio(projetoSelecionado.getId(), cbStatusProjeto.getSelectedIndex()-1, 
						dateInicio.getSelectedDate().getTime(), dateFim.getSelectedDate().getTime());
			}
		});

		mainPanel.add(this);
	}
}
