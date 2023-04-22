package br.com.desktop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.com.desktop.controller.GeraRelatorio;
import br.com.desktop.dao.DAO;
import br.com.desktop.model.ScrollBarPersonalizado;
import br.com.desktop.model.Tarefa;

public class FormListaTarefas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scroll;
	private ScrollBarPersonalizado sp;
	private JPanel panelAFazer;
	private JPanel panelEmAndamento;
	private JPanel panelConcluido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					FormListaTarefas frame = new FormListaTarefas();

					//frame.setUndecorated(true); // retira a barra da janela
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setResizable(false); // desabilitar maximar
					frame.setLocationRelativeTo(null);// alinhar ao centro
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormListaTarefas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormListaTarefas.class.getResource("/br/com/desktop/image/logoTaskMaster48.png")));
		setBounds(100, 100, 953, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(255, 255, 255));
		panelPrincipal.setBounds(0, 0, 937, 543);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);

		panelAFazer = new JPanel();
		panelAFazer.setLayout(new BorderLayout());
		JPanel panel = criarPainel(0);
		scroll = new JScrollPane(panel);
		scroll.setBackground(new Color(255, 255, 255));
		scroll.setBorder(null);
		scroll.setVerticalScrollBar(new ScrollBarPersonalizado());
		sp = new ScrollBarPersonalizado();
		sp.setOrientation(JScrollBar.HORIZONTAL);
		scroll.setHorizontalScrollBar(sp);
		panelAFazer.add(BorderLayout.CENTER, scroll);
		panelAFazer.setSize(375, 250);
		panelAFazer.setMinimumSize(new Dimension(300, 465));
		panelAFazer.setMaximumSize(new Dimension(300, 465));
		panelAFazer.setPreferredSize(new Dimension(3, 125));
		panelAFazer.setBounds(30, 41, 300, 465);
		panelAFazer.setBackground(new Color(255, 255, 255));
		panelAFazer.setVisible(true);
		panelPrincipal.add(panelAFazer);

		panelEmAndamento = new JPanel();
		panelEmAndamento.setLayout(new BorderLayout());
		JPanel panel1 = criarPainel(1);
		scroll = new JScrollPane(panel1);
		scroll.setBackground(new Color(255, 255, 255));
		scroll.setBorder(null);
		scroll.setVerticalScrollBar(new ScrollBarPersonalizado());
		sp = new ScrollBarPersonalizado();
		sp.setOrientation(JScrollBar.HORIZONTAL);
		scroll.setHorizontalScrollBar(sp);
		panelEmAndamento.add(BorderLayout.CENTER, scroll);
		panelEmAndamento.setSize(375, 250);
		panelEmAndamento.setMinimumSize(new Dimension(300, 465));
		panelEmAndamento.setMaximumSize(new Dimension(300, 465));
		panelEmAndamento.setPreferredSize(new Dimension(3, 125));
		panelEmAndamento.setBounds(330, 41, 300, 465);
		panelEmAndamento.setBackground(new Color(255, 255, 255));
		panelEmAndamento.setVisible(true);
		panelPrincipal.add(panelEmAndamento);

		panelConcluido = new JPanel();
		panelConcluido.setLayout(new BorderLayout());
		JPanel panel2 = criarPainel(2);
		scroll = new JScrollPane(panel2);
		scroll.setBackground(new Color(255, 255, 255));
		scroll.setBorder(null);
		scroll.setVerticalScrollBar(new ScrollBarPersonalizado());
		sp = new ScrollBarPersonalizado();
		sp.setOrientation(JScrollBar.HORIZONTAL);
		scroll.setHorizontalScrollBar(sp);
		panelConcluido.add(BorderLayout.CENTER, scroll);
		panelConcluido.setSize(375, 250);
		panelConcluido.setMinimumSize(new Dimension(300, 465));
		panelConcluido.setMaximumSize(new Dimension(300, 465));
		panelConcluido.setPreferredSize(new Dimension(3, 125));
		panelConcluido.setBounds(630, 41, 300, 465);
		panelConcluido.setBackground(new Color(255, 255, 255));
		panelConcluido.setVisible(true);
		panelPrincipal.add(panelConcluido);

		
		JLabel lblNewLabel = new JLabel("A fazer");
		lblNewLabel.setBounds(30, 16, 46, 14);
		panelPrincipal.add(lblNewLabel);

		JLabel lblIniciado = new JLabel("Em Andamento");
		lblIniciado.setBounds(330, 16, 89, 14);
		panelPrincipal.add(lblIniciado);

		JLabel lblConcludo = new JLabel("Concluído");
		lblConcludo.setBounds(632, 16, 89, 14);
		panelPrincipal.add(lblConcludo);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new GeraRelatorio();
			}
		});
		btnNewButton.setBounds(821, 7, 89, 23);
		panelPrincipal.add(btnNewButton);

	}

	public  JPanel criarPainel(int status) {
		JPanel panel = new JPanel();

		DAO dao = new DAO();
		ArrayList<Tarefa> tarefas = null;
		try {
			tarefas = dao.listar(status);
			// System.out.println(tarefas.size());
			panel.setLayout(new GridLayout(tarefas.size() < 5 ? 5 : tarefas.size(), 1, 10, 10));
			panel.setBackground(new Color(255, 255, 255));
			for (int i = 0; i < tarefas.size(); i++) {
				for (int j = 0; j < 1; j++) {
					JPanelItemTarefa jPanelItemTarefa = new JPanelItemTarefa(tarefas.get(i), this);
					jPanelItemTarefa.setTituloTarefa(tarefas.get(i).getTitulo());
					jPanelItemTarefa.setDescricaoTarefa(tarefas.get(i).getDescricao());
					jPanelItemTarefa.setId(tarefas.get(i).getId());
					jPanelItemTarefa.setPreferredSize(new Dimension(250, 125));

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
