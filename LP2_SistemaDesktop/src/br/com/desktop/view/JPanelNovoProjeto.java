package br.com.desktop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.exolab.castor.types.DateTime;

import br.com.desktop.dao.DAO;
import br.com.desktop.dao.ProjetoDAO;
import br.com.desktop.model.PanelRound;
import br.com.desktop.model.Projeto;
import br.com.desktop.model.ScrollBarPersonalizado;
import br.com.desktop.model.Usuario;

public class JPanelNovoProjeto extends JPanel {
	private JTextField textFieldNomeProjeto;
	private JTextField textField_1;
	private JScrollPane scroll;
	private JItemTabelaUsuario jPanelItemTarefa;
	private JButton jButtonCriarProjeto;
	private ArrayList<Usuario> usuariosSelecionados;
	private DAO dao;
	private ProjetoDAO projetoDAO;

	/**
	 * Create the panel.
	 */
	public JPanelNovoProjeto(JPanel mainPanel, JFrameDashboard jFrame, Usuario usuarioLogado) {
		dao = new DAO();
		usuariosSelecionados = new ArrayList<>();
		setBorder(null);
		setOpaque(true);
		setLayout(null);
		
		
		setBackground(Color.white);
		setOpaque(true);
		setBounds(44, 86, 1000, 800);
		setLayout(null);

//		PanelRound fundoPanelNovoProjeto = new PanelRound();
//		fundoPanelNovoProjeto.setAllRound(50);
//		fundoPanelNovoProjeto.setBackground(Color.white);
//		fundoPanelNovoProjeto.setOpaque(true);
//		fundoPanelNovoProjeto.setBounds(44, 86, 1000, 800);
//		fundoPanelNovoProjeto.setLayout(null);

		PanelRound panelInformacoesProjeto = new PanelRound();
		panelInformacoesProjeto.setAllRound(50);
		panelInformacoesProjeto.setBackground(new Color(0, 255, 0));
		panelInformacoesProjeto.setBounds(44, 86, 900, 301);
		panelInformacoesProjeto.setLayout(null);

		textFieldNomeProjeto = new JTextField();
		textFieldNomeProjeto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textFieldNomeProjeto.setBounds(47, 43, 369, 35);
		textFieldNomeProjeto.setColumns(10);
		panelInformacoesProjeto.add(textFieldNomeProjeto);

		JLabel lblNewLabel_1 = new JLabel("Nome do projeto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(47, 25, 125, 14);
		panelInformacoesProjeto.add(lblNewLabel_1);

//		textField_1 = new JTextField();
//		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		textField_1.setColumns(10);
//		textField_1.setBounds(47, 125, 369, 35);
//		panelInformacoesProjeto.add(textField_1);
//
//		JLabel lblNewLabel_1_1 = new JLabel("Nome do projeto");
//		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		lblNewLabel_1_1.setBounds(47, 107, 125, 14);
//		panelInformacoesProjeto.add(lblNewLabel_1_1);

		JLabel lblNewLabel = new JLabel("Novo Projeto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(48, 61, 134, 14);
		panelInformacoesProjeto.add(lblNewLabel);

		jButtonCriarProjeto = new JButton("Criar projeto");
		jButtonCriarProjeto.setBounds(148, 161, 134, 30);
		panelInformacoesProjeto.add(jButtonCriarProjeto);

		this.add(panelInformacoesProjeto);

		JPanel headerTabela = new JPanel();
		headerTabela.setBackground(new Color(118, 142, 150));
		headerTabela.setBounds(44, 403, 900, 30);
		headerTabela.setLayout(null);

		JLabel lblHeaderNome = new JLabel("                                                      Nome");
		lblHeaderNome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHeaderNome.setBounds(0, 0, 877, 30);
		headerTabela.add(lblHeaderNome);

		this.add(headerTabela);

		JPanel panelFundoLinhasTabela = new JPanel();
		panelFundoLinhasTabela.setBorder(new LineBorder(Color.gray));
		panelFundoLinhasTabela.setBackground(new Color(255, 255, 255));
		panelFundoLinhasTabela.setLayout(new BorderLayout());
		panelFundoLinhasTabela.setBounds(44, 432, 900, 121);
		PanelRound panelAuxLinhasTabela = new PanelRound();
		panelAuxLinhasTabela.setAllRound(3);
		JPanel panelLinhasTabela = criarPainel();
		panelLinhasTabela.setBackground(new Color(255, 255, 255));
		panelAuxLinhasTabela.add(panelLinhasTabela);
		panelFundoLinhasTabela.add(panelAuxLinhasTabela);
		scroll = new JScrollPane();
		scroll.setViewportView(panelAuxLinhasTabela);
		scroll.setBorder(null);
		scroll.setVerticalScrollBar(new ScrollBarPersonalizado());
		scroll.setHorizontalScrollBar(new ScrollBarPersonalizado());
		panelFundoLinhasTabela.add(scroll, BorderLayout.CENTER);
		panelFundoLinhasTabela.setOpaque(true);
		panelFundoLinhasTabela.setVisible(true);

		this.add(panelFundoLinhasTabela);

		mainPanel.add(this);
		
		
		jButtonCriarProjeto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println();
				for (Component panelConcluido : panelLinhasTabela.getComponents()) {
					if (panelConcluido instanceof JItemTabelaUsuario) {
						System.out.println(((JItemTabelaUsuario) panelConcluido).isSelecionado());

						if (((JItemTabelaUsuario) panelConcluido).isSelecionado()) {
							usuariosSelecionados.add(((JItemTabelaUsuario) panelConcluido).getUsuario());						
						}

					}

				}
				long diaHoraAtual = new Date().getTime();
				projetoDAO = new ProjetoDAO();
				try {
					projetoDAO.criarProjeto(
							new Projeto(textFieldNomeProjeto.getText(), usuariosSelecionados));

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Projeto projetoAtual = null;
				try {
					projetoAtual = projetoDAO.consultarProjeto(0, diaHoraAtual);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				try {
					projetoDAO.criarProjetoUsuario(projetoAtual, usuariosSelecionados);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				System.out.println(usuariosSelecionados);
				
				usuariosSelecionados = new ArrayList<>();
				textFieldNomeProjeto.setText("");
				for (Component panelConcluido : panelLinhasTabela.getComponents()) {
					if (panelConcluido instanceof JItemTabelaUsuario) {
						System.out.println(((JItemTabelaUsuario) panelConcluido).isSelecionado());						
						for (Component jItemTabelaUsuario : ((JItemTabelaUsuario) panelConcluido).getComponents()) {
							if (jItemTabelaUsuario instanceof PanelRound) {
								for (Component panelRound : ((PanelRound) jItemTabelaUsuario).getComponents()) {
									if (panelRound instanceof JCheckBox) {
//										System.out.println(((JCheckBox) panelRound).isSelected());
										if(((JCheckBox) panelRound).isSelected()) {
											((JCheckBox) panelRound).setSelected(false);
										}

									}
								}
							}
						}
					}

				}

				
				jFrame.dispose();
				JFrameDashboard jFrameDashboard = new JFrameDashboard(usuarioLogado);
//				formListaTarefas.setUndecorated(true); // retira a barra da janela
				jFrameDashboard.setResizable(false); // desabilitar maximar
				jFrameDashboard.setLocationRelativeTo(null);// alinhar ao centro
				jFrameDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jFrameDashboard.setVisible(true);
				
			}
		});

	}

	public JPanel criarPainel() {
		JPanel panel = new JPanel();

		ArrayList<Usuario> usuarios = null;
		try {
			usuarios = dao.listarUsuarios();

			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.setBackground(new Color(255, 255, 255));
			for (int i = 0; i < usuarios.size(); i++) {
				jPanelItemTarefa = new JItemTabelaUsuario(usuarios.get(i));
				jPanelItemTarefa.setUsuario(usuarios.get(i));
				panel.add(jPanelItemTarefa);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		

		return panel;
	}

}
