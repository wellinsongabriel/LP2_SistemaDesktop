package br.com.desktop.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import br.com.desktop.dao.FacadeDAO;
import br.com.desktop.model.BordaCantoArredondado;
import br.com.desktop.model.PanelRound;
import br.com.desktop.model.Projeto;
import br.com.desktop.model.ScrollBarPersonalizado;
import br.com.desktop.model.Tarefa;
import br.com.desktop.model.Usuario;

public class JPanelListaTarefas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6237904463702694694L;
	private JScrollPane scroll;
	private Usuario usuarioLogado;
	private FacadeDAO facadeDao = new FacadeDAO();
	private ArrayList<Usuario> nomesUsuariosProjeto = new ArrayList<>();
	private ArrayList<Usuario> nomesNaoPartipantesProjeto  = new ArrayList<>();

	public JPanelListaTarefas(JPanel mainPanel, JFrame jframe, Usuario usuario, Projeto projeto) {
		usuarioLogado = usuario;

		setOpaque(false);
		setBounds(10, 10, 1000, 900);
		setLayout(null);

		JLabel jLabelNomeProjeto = new JLabel(projeto.getTitulo());
		jLabelNomeProjeto.setBounds(30, 30, 310, 50);
		jLabelNomeProjeto.setForeground(new Color(255, 102, 0));
		jLabelNomeProjeto.setFont(new Font("Tahoma", Font.BOLD, 22));
		add(jLabelNomeProjeto);

		ImageIcon iconeEditar = new ImageIcon(JPanelListaTarefas.class.getResource("/br/com/desktop/image/edit.png"));
		int width = 20;
		int height = 20;
		Image imagemEditar = iconeEditar.getImage();
		Image imagemEditarRedimensionada = imagemEditar.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon iconeEditarRedimensionado = new ImageIcon(imagemEditarRedimensionada);
		
		ImageIcon iconeExcluir = new ImageIcon(JPanelListaTarefas.class.getResource("/br/com/desktop/image/delete.png"));
		Image imagemExcluir = iconeExcluir.getImage();
		Image imagemExcluirRedimensionada = imagemExcluir.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon iconeExcluirRedimensionado = new ImageIcon(imagemExcluirRedimensionada);

		JLabel jLabelEditarProjeto = new JLabel(iconeEditarRedimensionado);
		jLabelEditarProjeto.setBounds(320, 30, 50, 50);
		add(jLabelEditarProjeto);

		JTextField jTextFieldNomeProjeto = new JTextField(projeto.getTitulo());
		jTextFieldNomeProjeto.setBounds(30, 30, 255, 50);
		jTextFieldNomeProjeto.setForeground(new Color(255, 102, 0));
		jTextFieldNomeProjeto.setFont(new Font("Tahoma", Font.BOLD, 22));
		jTextFieldNomeProjeto.setVisible(false);
		add(jTextFieldNomeProjeto);

		jLabelEditarProjeto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (jLabelNomeProjeto.isVisible()) {
					jLabelNomeProjeto.setVisible(false);
					jTextFieldNomeProjeto.setVisible(true);
				} else {

					jLabelNomeProjeto.setText(jTextFieldNomeProjeto.getText());

					try {
						projeto.setTitulo(jTextFieldNomeProjeto.getText());
						facadeDao.alterarNomeProjeto(projeto);
						jframe.dispose();
						JFrameDashboard jFrameDashboard = new JFrameDashboard(usuarioLogado);
						jFrameDashboard.setResizable(false); // desabilitar maximar
						jFrameDashboard.setLocationRelativeTo(null);// alinhar ao centro
						jFrameDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						jFrameDashboard.setVisible(true);

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		
		JLabel jLabelExcluirProjeto = new JLabel(iconeExcluirRedimensionado);
		jLabelExcluirProjeto.setBounds(360, 30, 50, 50);
		add(jLabelExcluirProjeto);
		
		
		jLabelExcluirProjeto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.YES_OPTION==
						JOptionPane.showConfirmDialog(null, 
								"Deseja excluir o projeto e todas as tarefas?\nEssa ação é irreversível ", "Excluir Projeto",
								JOptionPane.INFORMATION_MESSAGE)) {
					try {
						facadeDao.excluirProjeto(projeto.getId());
						jframe.dispose();
						JFrameDashboard jFrameDashboard = new JFrameDashboard(usuarioLogado);
//						formListaTarefas.setUndecorated(true); // retira a barra da janela
						jFrameDashboard.setResizable(false); // desabilitar maximar
						jFrameDashboard.setLocationRelativeTo(null);// alinhar ao centro
						jFrameDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						jFrameDashboard.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		PanelRound panelAFazer = new PanelRound();
		panelAFazer.setBorder(new BordaCantoArredondado());
		panelAFazer.setLayout(null);
		panelAFazer.setBounds(30, 100, 310, 650);
		panelAFazer.setBackground(new Color(255, 255, 255));
		panelAFazer.setOpaque(true);
		JLabel jLabelAFazer = new JLabel("A Fazer");
		jLabelAFazer.setFont(new Font("Tahoma", Font.BOLD, 18));
		jLabelAFazer.setBounds(10, 10, 100, 14);
		JLabel novoAfazer = new JLabel("+");
		novoAfazer.setBounds(280, 10, 20, 14);
		novoAfazer.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelAFazer.add(novoAfazer);
		panelAFazer.add(jLabelAFazer);
		PanelRound baseScrollPanelAFazer = new PanelRound();
		baseScrollPanelAFazer.setOpaque(true);
		baseScrollPanelAFazer.setBackground(Color.white);
		baseScrollPanelAFazer.setAllRound(50);
		PanelRound panelItenAFazer = criarPainel(0, jframe, projeto);
		panelItenAFazer.setAllRound(100);
		panelItenAFazer.setBackground(new Color(255, 255, 255));
		baseScrollPanelAFazer.add(panelItenAFazer);
		scroll = new JScrollPane();
		scroll.setViewportView(baseScrollPanelAFazer);
		scroll.setBorder(null);
		scroll.setVerticalScrollBar(new ScrollBarPersonalizado());
		scroll.setHorizontalScrollBar(new ScrollBarPersonalizado());
		scroll.setBounds(10, 30, 290, 600);
		panelAFazer.add(scroll);
		panelAFazer.setVisible(true);
		add(panelAFazer);

		PanelRound panelEmAndamento = new PanelRound();
		panelEmAndamento.setBorder(new BordaCantoArredondado());
		panelEmAndamento.setLayout(null);
		panelEmAndamento.setBounds(370, 100, 310, 650);
		panelEmAndamento.setBackground(new Color(255, 255, 255));
		panelEmAndamento.setOpaque(true);
		JLabel jLabelEmAndamento = new JLabel("Em andamento");
		jLabelEmAndamento.setFont(new Font("Tahoma", Font.BOLD, 18));
		jLabelEmAndamento.setBounds(10, 10, 150, 14);
		JLabel novoEmAndamento = new JLabel("+");
		novoEmAndamento.setBounds(280, 10, 20, 14);
		novoEmAndamento.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelEmAndamento.add(novoEmAndamento);
		panelEmAndamento.add(jLabelEmAndamento);
		PanelRound baseScrollpanelEmAndamento = new PanelRound();
		baseScrollpanelEmAndamento.setOpaque(true);
		baseScrollpanelEmAndamento.setBackground(Color.white);
		baseScrollpanelEmAndamento.setAllRound(50);
		PanelRound panelItenEmAndamento = criarPainel(1, jframe, projeto);
		panelItenEmAndamento.setAllRound(100);
		panelItenEmAndamento.setBackground(new Color(255, 255, 255));
		baseScrollpanelEmAndamento.add(panelItenEmAndamento);
		scroll = new JScrollPane();
		scroll.setViewportView(baseScrollpanelEmAndamento);
		scroll.setBorder(null);
		scroll.setVerticalScrollBar(new ScrollBarPersonalizado());
		scroll.setHorizontalScrollBar(new ScrollBarPersonalizado());
		scroll.setBounds(10, 30, 290, 600);
		panelEmAndamento.add(scroll);
		panelEmAndamento.setVisible(true);
		add(panelEmAndamento);

		PanelRound panelConcluido = new PanelRound();
		panelConcluido.setBorder(new BordaCantoArredondado());
		panelConcluido.setLayout(null);
		panelConcluido.setBounds(690, 100, 310, 650);
		panelConcluido.setBackground(new Color(255, 255, 255));
		panelConcluido.setOpaque(true);
		JLabel jLabelConcluido = new JLabel("Concluída");
		jLabelConcluido.setFont(new Font("Tahoma", Font.BOLD, 18));
		jLabelConcluido.setBounds(10, 10, 150, 14);
		JLabel novoConcluido = new JLabel("+");
		novoConcluido.setBounds(280, 10, 20, 14);
		novoConcluido.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelConcluido.add(novoConcluido);
		panelConcluido.add(jLabelConcluido);
		PanelRound baseScrollpanelConcluido = new PanelRound();
		baseScrollpanelConcluido.setOpaque(true);
		baseScrollpanelConcluido.setBackground(Color.white);
		baseScrollpanelConcluido.setAllRound(50);
		PanelRound panelItenConcluido = criarPainel(2, jframe, projeto);
		panelItenConcluido.setAllRound(100);
		panelItenConcluido.setBackground(new Color(255, 255, 255));
		baseScrollpanelConcluido.add(panelItenConcluido);
		scroll = new JScrollPane();
		scroll.setViewportView(baseScrollpanelConcluido);
		scroll.setBorder(null);
		scroll.setVerticalScrollBar(new ScrollBarPersonalizado());
		scroll.setHorizontalScrollBar(new ScrollBarPersonalizado());
		scroll.setBounds(10, 30, 290, 600);
		panelConcluido.add(scroll);
		panelConcluido.setVisible(true);
		add(panelConcluido);

		novoAfazer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrameNovaTarefa formNovaTarefa = new JFrameNovaTarefa(null, jframe, 0, usuarioLogado, projeto);
				formNovaTarefa.setUndecorated(true);
				formNovaTarefa.setLocationRelativeTo(null);
				formNovaTarefa.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				novoAfazer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				novoAfazer.setCursor(Cursor.getDefaultCursor());
			}
		});

		novoEmAndamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrameNovaTarefa formNovaTarefa = new JFrameNovaTarefa(null, jframe, 1, usuarioLogado, projeto);
				formNovaTarefa.setUndecorated(true);
				formNovaTarefa.setLocationRelativeTo(null);
				formNovaTarefa.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				novoEmAndamento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				novoEmAndamento.setCursor(Cursor.getDefaultCursor());
			}
		});

		novoConcluido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrameNovaTarefa formNovaTarefa = new JFrameNovaTarefa(null, jframe, 2, usuarioLogado, projeto);
				formNovaTarefa.setUndecorated(true);
				formNovaTarefa.setLocationRelativeTo(null);
				formNovaTarefa.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				novoConcluido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				novoConcluido.setCursor(Cursor.getDefaultCursor());
			}
		});

		JLabel jLabelParticipantesProjeto = new JLabel("Participantes do projeto");
		jLabelParticipantesProjeto.setFont(new Font("Tahoma", Font.BOLD, 16));
		jLabelParticipantesProjeto.setBounds(0, 765, 230, 20);
		add(jLabelParticipantesProjeto);

		JLabel jLabelAdicionarParticipanteProjeto = new JLabel("+");
		jLabelAdicionarParticipanteProjeto.setFont(new Font("Tahoma", Font.BOLD, 18));
		jLabelAdicionarParticipanteProjeto.setBounds(230, 765, 30, 20);
		add(jLabelAdicionarParticipanteProjeto);

		try {
			nomesUsuariosProjeto = facadeDao.listarParticipantesProjeto(projeto.getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			nomesNaoPartipantesProjeto = facadeDao.listarUsuarios(nomesUsuariosProjeto);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		JPanel panelUsuariosProjeto = new JPanel();
		panelUsuariosProjeto.setBounds(0, 750, 900, 100);
		panelUsuariosProjeto.setLayout(new BoxLayout(panelUsuariosProjeto, BoxLayout.X_AXIS));
		panelUsuariosProjeto.setBackground(new Color(242, 242, 242));
		for (Usuario participante : nomesUsuariosProjeto) {

			JLabel jLabelNomeUsuario = new JLabel(" @" + participante.getUsuario());
			jLabelNomeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
			jLabelNomeUsuario.setBackground(Color.white);
			jLabelNomeUsuario.setForeground(Color.gray);

			panelUsuariosProjeto.add(jLabelNomeUsuario);
			JPopupMenu menuContextoRemoverUsuario = new JPopupMenu();
			JMenuItem removerUsuario = new JMenuItem("X");
			menuContextoRemoverUsuario.add(removerUsuario);
			jLabelNomeUsuario.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					menuContextoRemoverUsuario.show(e.getComponent(), e.getX(), e.getY());
					removerUsuario.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								facadeDao.removerParticipanteProjeto(projeto.getId(), participante.getId());
								jframe.dispose();
								JFrameDashboard jFrameDashboard = new JFrameDashboard(usuarioLogado);
//								formListaTarefas.setUndecorated(true); // retira a barra da janela
								jFrameDashboard.setResizable(false); // desabilitar maximar
								jFrameDashboard.setLocationRelativeTo(null);// alinhar ao centro
								jFrameDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								jFrameDashboard.setVisible(true);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
					
					
				}
			});
		}

		jLabelAdicionarParticipanteProjeto.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jLabelAdicionarParticipanteProjeto.setCursor(Cursor.getDefaultCursor());
				jLabelAdicionarParticipanteProjeto.setToolTipText("");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				jLabelAdicionarParticipanteProjeto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				jLabelAdicionarParticipanteProjeto.setToolTipText("Adicionar participante");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				JPopupMenu menuContextoAdicionarUsuario = new JPopupMenu();
				for (Usuario nomeUsuario : nomesNaoPartipantesProjeto) {
					JMenuItem usuario = new JMenuItem(nomeUsuario.getUsuario());
					menuContextoAdicionarUsuario.add(usuario);
					int idUsuario = nomeUsuario.getId();
					usuario.addActionListener(new ActionListener() {	        

						@Override
						public void actionPerformed(ActionEvent e) {
							System.out.println(idUsuario);
							try {
								facadeDao.criarProjetoUsuario(projeto, new ArrayList<Usuario>(List.of(nomeUsuario)));
								jframe.dispose();
								JFrameDashboard jFrameDashboard = new JFrameDashboard(usuarioLogado);
//								formListaTarefas.setUndecorated(true); // retira a barra da janela
								jFrameDashboard.setResizable(false); // desabilitar maximar
								jFrameDashboard.setLocationRelativeTo(null);// alinhar ao centro
								jFrameDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								jFrameDashboard.setVisible(true);
								
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
				    });
				}
				menuContextoAdicionarUsuario.show(e.getComponent(), e.getX(), e.getY());
			}
		});

		add(panelUsuariosProjeto);

		mainPanel.add(this);
	}

	public PanelRound criarPainel(int status, JFrame jframe, Projeto projeto) {
		PanelRound panel = new PanelRound();
		panel.setAllRound(50);

		ArrayList<Tarefa> tarefas = null;
		try {
			tarefas = facadeDao.listarTarefa(status, projeto);
			panel.setLayout(new GridLayout(tarefas.size() < 3 ? 3 : tarefas.size(), 1, 10, 10));
			panel.setBackground(new Color(255, 255, 255));
			for (int i = 0; i < tarefas.size(); i++) {
				for (int j = 0; j < 1; j++) {
					JPanelItemTarefa jPanelItemTarefa = new JPanelItemTarefa(tarefas.get(i), jframe, usuarioLogado,
							projeto);
					jPanelItemTarefa.setTituloTarefa(tarefas.get(i).getTitulo());
					jPanelItemTarefa.setDescricaoTarefa(tarefas.get(i).getDescricao());
					jPanelItemTarefa.setId(tarefas.get(i).getId());
					jPanelItemTarefa.setPreferredSize(new Dimension(250, 100));
					jPanelItemTarefa.setAllRound(100);
					JPopupMenu menuContextoItemTarefa = new JPopupMenu();
					JMenuItem opcaoExcluir = new JMenuItem("Excluir");
					menuContextoItemTarefa.add(opcaoExcluir);

					panel.add(jPanelItemTarefa);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return panel;
	}

	//TODO terminar metodo
//	public void atualizarParticipantes(JPanel jPanelParticipantesProjeto) {
//		
//		try {
//			nomesUsuariosProjeto = facadeDao.listarParticipantesProjeto(projeto.getId());
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		
//		try {
//			nomesNaoPartipantesProjeto = facadeDao.listarUsuarios(nomesUsuariosProjeto);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		
//		jPanelParticipantesProjeto.remove(jPanelParticipantesProjeto);
//		for (Usuario nomeUsuario : nomesUsuariosProjeto) {
//
//			JLabel jLabelNomeUsuario = new JLabel(" @" + nomeUsuario.getUsuario());
//			jLabelNomeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
//			jLabelNomeUsuario.setBackground(Color.white);
//			jLabelNomeUsuario.setForeground(Color.gray);
//
//			jPanelParticipantesProjeto.add(jLabelNomeUsuario);
//			
//			jLabelNomeUsuario.addMouseListener(new MouseAdapter() {
//				
//			});
//		}
//		jPanelParticipantesProjeto.revalidate();
//		jPanelParticipantesProjeto.repaint();
//	}
}
