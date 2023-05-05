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
import br.com.desktop.model.PanelRound;
import br.com.desktop.model.ScrollBarPersonalizado;
import br.com.desktop.model.Tarefa;

public class PanelListaTarefas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scroll;
	private ScrollBarPersonalizado sp;
	private PanelRound panelAFazer;
	private PanelRound panelEmAndamento;
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
	public PanelListaTarefas(JPanel mainPanel, JFrame jframe) {
		setOpaque(false);

//		setVisible(true);
//		setBounds(100, 100, 1080, 582);

		PanelRound panelAFazer = new PanelRound();
		panelAFazer.setAllRound(50);
		panelAFazer.setLayout(new BorderLayout());
		panelAFazer.setBounds(30, 100, 300, 800);
		panelAFazer.setBackground(new Color(255, 255, 255));
		PanelRound o = new PanelRound();
		o.setAllRound(50);
		PanelRound panel = criarPainel(0, jframe);
		panel.setAllRound(100);
		panel.setBackground(new Color(255, 255, 255));
		o.add(panel);
		panelAFazer.add(o);
		scroll = new JScrollPane();
		scroll.setViewportView(o);
		scroll.setBorder(null);
		scroll.setVerticalScrollBar(new ScrollBarPersonalizado());
		panelAFazer.add(scroll, BorderLayout.CENTER);
		panelAFazer.setOpaque(true);
		panelAFazer.setVisible(true);
		mainPanel.add(panelAFazer);
		
		
		
		PanelRound panelEmAndamento = new PanelRound();
		panelEmAndamento.setAllRound(50);
		panelEmAndamento.setLayout(new BorderLayout());
		panelEmAndamento.setBounds(340, 100, 300, 800);
		panelEmAndamento.setBackground(new Color(255, 255, 255));
		PanelRound o1 = new PanelRound();
		o1.setAllRound(50);
		PanelRound panel1 = criarPainel(1, jframe);
		panel1.setAllRound(100);
		panel1.setBackground(new Color(255, 255, 255));
		o1.add(panel1);
		panelEmAndamento.add(o1);
		scroll = new JScrollPane();
		scroll.setViewportView(o1);
		scroll.setBorder(null);
		scroll.setVerticalScrollBar(new ScrollBarPersonalizado());
		panelEmAndamento.add(scroll, BorderLayout.CENTER);
		panelEmAndamento.setOpaque(true);
		panelEmAndamento.setVisible(true);
		mainPanel.add(panelEmAndamento);
		
		PanelRound panelConcluido = new PanelRound();
		panelConcluido.setAllRound(50);
		panelConcluido.setLayout(new BorderLayout());
		panelConcluido.setBounds(660, 100, 300, 800);
		panelConcluido.setBackground(new Color(255, 255, 255));
		PanelRound o2 = new PanelRound();
		o2.setAllRound(50);
		PanelRound panel2 = criarPainel(2, jframe);
		panel2.setAllRound(100);
		panel2.setBackground(new Color(255, 255, 255));
		o2.add(panel2);
		panelConcluido.add(o2);
		scroll = new JScrollPane();
		scroll.setViewportView(o2);
		scroll.setBorder(null);
		scroll.setVerticalScrollBar(new ScrollBarPersonalizado());
		panelConcluido.add(scroll, BorderLayout.CENTER);
		panelConcluido.setOpaque(true);
		panelConcluido.setVisible(true);
		mainPanel.add(panelConcluido);
		

		
		JLabel lblNewLabel = new JLabel("A fazer");
		lblNewLabel.setBounds(30, 80, 129, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		mainPanel.add(lblNewLabel);

		JLabel novoAfazer = new JLabel("+");
		novoAfazer.setBounds(280, 80, 20, 14);
		novoAfazer.setFont(new Font("Tahoma", Font.BOLD, 18));
		mainPanel.add(novoAfazer);

		novoAfazer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FormNovaTarefa formNovaTarefa = new FormNovaTarefa(null, jframe,0);
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

		JLabel lblIniciado = new JLabel("Em Andamento");
		lblIniciado.setBounds(340, 80, 140, 14);
		lblIniciado.setFont(new Font("Tahoma", Font.BOLD, 18));
		mainPanel.add(lblIniciado);

		JLabel novoIniciado = new JLabel("+");
		novoIniciado.setBounds(610, 80, 20, 14);
		novoIniciado.setFont(new Font("Tahoma", Font.BOLD, 18));
		mainPanel.add(novoIniciado);
		
		
		novoIniciado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FormNovaTarefa formNovaTarefa = new FormNovaTarefa(null, jframe,1);
				formNovaTarefa.setUndecorated(true);
				formNovaTarefa.setLocationRelativeTo(null);
				formNovaTarefa.setVisible(true);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				novoIniciado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		        
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				novoIniciado.setCursor(Cursor.getDefaultCursor());
			}
		});

		JLabel lblConcludo = new JLabel("Concluida");
		lblConcludo.setBounds(660, 80, 89, 14);
		lblConcludo.setFont(new Font("Tahoma", Font.BOLD, 18));
		mainPanel.add(lblConcludo);

		JLabel novoConcluido = new JLabel("+");
		novoConcluido.setBounds(930, 80, 20, 14);
		novoConcluido.setFont(new Font("Tahoma", Font.BOLD, 18));
		mainPanel.add(novoConcluido);

		novoConcluido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FormNovaTarefa formNovaTarefa = new FormNovaTarefa(null, jframe,2);
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

	
    }

	public  PanelRound criarPainel(int status,  JFrame jframe) {
		PanelRound panel = new PanelRound();
		panel.setAllRound(50);
		DAO dao = new DAO();
		ArrayList<Tarefa> tarefas = null;
		try {
			tarefas = dao.listarTarefa(status);
			// System.out.println(tarefas.size());
			panel.setLayout(new GridLayout(tarefas.size() < 5 ? 5 : tarefas.size(), 1, 10, 10));
			panel.setBackground(new Color(255, 255, 255));
			for (int i = 0; i < tarefas.size(); i++) {
				for (int j = 0; j < 1; j++) {
					JPanelItemTarefa jPanelItemTarefa = new JPanelItemTarefa(tarefas.get(i), jframe);
					jPanelItemTarefa.setTituloTarefa(tarefas.get(i).getTitulo());
					jPanelItemTarefa.setDescricaoTarefa(tarefas.get(i).getDescricao());
					jPanelItemTarefa.setId(tarefas.get(i).getId());
					jPanelItemTarefa.setPreferredSize(new Dimension(250, 125));
//					jPanelItemTarefa.setRoundTopRight(100);
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
	
	
	
	public void criarPanel(JPanel mainPanel, JFrame jframe) {
		int x = 30;	
		
		for(int i=0; i<3; i++) {
		
		}
	}
	
	
}
