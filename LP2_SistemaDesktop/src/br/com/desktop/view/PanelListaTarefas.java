package br.com.desktop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import br.com.desktop.dao.DAO;
import br.com.desktop.model.BordaCantoArredondado;
import br.com.desktop.model.PanelRound;
import br.com.desktop.model.ScrollBarPersonalizado;
import br.com.desktop.model.Tarefa;
import br.com.desktop.model.Usuario;

public class PanelListaTarefas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scroll;
	private ScrollBarPersonalizado sp;
	private PanelRound panelAFazer;
	private PanelRound panelEmAndamento;
	private Usuario usuarioLogado;
//	private PanelRound panelConcluido;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//					PanelListaTarefas frame = new PanelListaTarefas();
//
//					//frame.setUndecorated(true); // retira a barra da janela
////					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////					frame.setResizable(false); // desabilitar maximar
////					frame.setLocationRelativeTo(null);// alinhar ao centro
//					frame.setVisible(true);
//					
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public PanelListaTarefas(JPanel mainPanel, JFrame jframe, Usuario usuario) {
		usuarioLogado = usuario;
		
	
		setOpaque(false);
		setBounds(10, 10, 1000, 800);
		setLayout(null);
		
		
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
		PanelRound panelItenAFazer = criarPainel(0, jframe);
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
		PanelRound panelItenEmAndamento = criarPainel(1, jframe);
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
		PanelRound panelItenConcluido = criarPainel(2, jframe);
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
				FormNovaTarefa formNovaTarefa = new FormNovaTarefa(null, jframe,0,usuarioLogado);
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
				FormNovaTarefa formNovaTarefa = new FormNovaTarefa(null, jframe,1,usuarioLogado);
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
				FormNovaTarefa formNovaTarefa = new FormNovaTarefa(null, jframe,2,usuarioLogado);
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

	mainPanel.add(this);
    }

	public  PanelRound criarPainel(int status,  JFrame jframe) {
		PanelRound panel = new PanelRound();
		panel.setAllRound(50);
		DAO dao = new DAO();
		ArrayList<Tarefa> tarefas = null;
		try {
			tarefas = dao.listarTarefa(status);
			panel.setLayout(new GridLayout(tarefas.size() < 3 ? 3 : tarefas.size(), 1, 10, 10));
			panel.setBackground(new Color(255, 255, 255));
			for (int i = 0; i < tarefas.size(); i++) {
				for (int j = 0; j < 1; j++) {
					JPanelItemTarefa jPanelItemTarefa = new JPanelItemTarefa(tarefas.get(i), jframe,usuarioLogado);
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
	
	
	
}
