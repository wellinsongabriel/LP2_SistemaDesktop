package br.com.desktop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import br.com.desktop.dao.DAO;
import br.com.desktop.model.BordaCantoArredondado;
import br.com.desktop.model.Tarefa;

public class JPanelItemTarefa extends JPanel {

	/**
	 * Create the panel.
	 */
	protected int id;
	protected String tituloTarefa;
	protected String descricaoTarefa;
	protected String nomeEtiquetaTarefa;
	protected int corEtiquetaTarefa;

	public String getTituloTarefa() {
		return tituloTarefa;
	}

	public void setTituloTarefa(String tituloTarefa) {
		this.tituloTarefa = tituloTarefa;
	}

	public String getDescricaoTarefa() {
		return descricaoTarefa;
	}

	public void setDescricaoTarefa(String descricaoTarefa) {
		this.descricaoTarefa = descricaoTarefa;
	}

	public String getNomeEtiquetaTarefa() {
		return nomeEtiquetaTarefa;
	}

	public void setNomeEtiquetaTarefa(String nomeEtiquetaTarefa) {
		this.nomeEtiquetaTarefa = nomeEtiquetaTarefa;
	}

	public int getCorEtiquetaTarefa() {
		return corEtiquetaTarefa;
	}

	public void setCorEtiquetaTarefa(int corEtiquetaTarefa) {
		this.corEtiquetaTarefa = corEtiquetaTarefa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JPanelItemTarefa(Tarefa tarefa, JFrame jFramePrincipal) {

		// MouseListener m = new MouseListener();
		// JFrame janela = new JFrame();
		// janela.setUndecorated(true); //retira a barra da janela
		this.setBorder(new BordaCantoArredondado());
		this.setMinimumSize(new Dimension(250, 100));
		this.setMaximumSize(new Dimension(250, 100));
		JPopupMenu menuContextoItemTarefa = new JPopupMenu();
		JMenuItem opcaoExcluir = new JMenuItem("Excluir");
		menuContextoItemTarefa.add(opcaoExcluir);
		// janela.add(menuContextoItemTarefa);

		setBackground(new Color(255, 255, 255));
		setLayout(null);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setText(tarefa.getNomeEtiqueta());
		this.nomeEtiquetaTarefa = tarefa.getNomeEtiqueta();
		btnNewButton.setBackground(new Color(tarefa.getCorEtiqueta()));
		btnNewButton.setBounds(28, 11, 89, 23);
		this.add(btnNewButton);

		JLabel lblTituloTarefa = new JLabel("Titulo");
		lblTituloTarefa.setText(tarefa.getTitulo());
		lblTituloTarefa.setBounds(28, 44, 383, 14);
		this.add(lblTituloTarefa);

		JLabel lblDescicaoTarefa = new JLabel("Descricao");
		lblDescicaoTarefa.setText(tarefa.getDescricao());
		lblDescicaoTarefa.setBounds(28, 69, 383, 14);
		this.add(lblDescicaoTarefa);
		this.setVisible(true);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// JPanelItemTarefa panel = (JPanelItemTarefa) e.getSource();
				
				if (e.getButton() == 1) {//clique esquerdo
					FormNovaTarefa novaTarefa = new FormNovaTarefa(jFramePrincipal);
					novaTarefa.setUndecorated(true); // retira a barra da janela
					novaTarefa.setResizable(false); // desabilitar maximar
					novaTarefa.setLocationRelativeTo(null);// alinhar ao centro
					novaTarefa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					novaTarefa.setVisible(true);
				}
				if (e.getButton() == 3) {//clique direito
					id = (int)((JPanelItemTarefa) e.getComponent()).getId(); //pegando o item da lista que foi clicado
					menuContextoItemTarefa.show(e.getComponent(), e.getX(), e.getY());
				}

			}
			
		});

		opcaoExcluir.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir a tarefa " +id+ " ?", "Aviso",
						JOptionPane.INFORMATION_MESSAGE);
				System.out.println(resposta);
				DAO dao = new DAO();
				if (resposta ==  JOptionPane.YES_OPTION) {
					
					try {
						dao.excluir(id);
						jFramePrincipal.dispose();
						FormListaTarefas formListaTarefas = new FormListaTarefas();
						//formListaTarefas.setUndecorated(true); // retira a barra da janela
						formListaTarefas.setResizable(false); // desabilitar maximar
						formListaTarefas.setLocationRelativeTo(null);// alinhar ao centro
						formListaTarefas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						formListaTarefas.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		
		
	}

}
