package br.com.desktop.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.desktop.controller.Backup;
import br.com.desktop.model.Usuario;



public class JPanelBackup extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6368853484873256262L;
	
	private String itemSelecionado;
	ArrayList<String> arquivosBackup ;
	private Backup backup ;
	private String[] arrayPaths;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public JPanelBackup(JPanel mainPanel, JFrameDashboard jFrame, Usuario usuarioLogado) {
		setOpaque(true);
		setBounds(44, 86, 1000, 800);
		setLayout(null);
		

		
		


		arquivosBackup = new ArrayList<>();
		backup = new Backup();
		arquivosBackup = backup.listarArquivos();
		arrayPaths = arquivosBackup.toArray(new String[arquivosBackup.size()]);

		@SuppressWarnings("unused")
		DefaultListModel<String> model = new DefaultListModel<String>();

		@SuppressWarnings("rawtypes")
		JList jListaBackups = new JList();
		jListaBackups.setBounds(10, 83, 724, 74);
//		list.setModel(model);
		jListaBackups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListaBackups.setListData(arrayPaths);
		add(jListaBackups);

		// Adiciona um evento de mouse na lista
//        list.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                // Obtém o índice do item que está sob o mouse
//                int index = list.locationToIndex(e.getPoint());
//
//                // Obtém o valor do item e mostra no console
//                String item = arrayPaths[index];
//                System.out.println("Mouse sobre o item: " + item);
//            }
//        });
		JButton btnRestaurar = new JButton("Restaurar backup");
		btnRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Deseja restaurar o bacukp selecionado? ")==JOptionPane.YES_OPTION) {
					
				
					try {
						backup.descompactarArquivo(itemSelecionado);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}	
				
			}
		});
		btnRestaurar.setBounds(321, 243, 139, 23);
		btnRestaurar.setEnabled(false);
		add(btnRestaurar);
		
		JScrollPane scrollPane = new JScrollPane(jListaBackups);
		scrollPane.setBounds(10, 83, 725, 80);
		add(scrollPane);
		
		JButton btnNewButton = new JButton("Gerar backup");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Deseja gerar um novo bacukp? ")==JOptionPane.YES_OPTION) {
				backup.compactarDiretorio();
				arquivosBackup = backup.listarArquivos();
				arrayPaths = arquivosBackup.toArray(new String[arquivosBackup.size()]);
				jListaBackups.setListData(arrayPaths);
				revalidate();
				repaint();
				}
				
			}
		});
		btnNewButton.setBounds(10, 243, 125, 23);
		add(btnNewButton);
		
		
		
		jListaBackups.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
		            if (jListaBackups.getSelectedIndex() == -1) {//forçar seleção caso tente tirar a seleção da linha
		                jListaBackups.setSelectedIndex(event.getFirstIndex());
		            }
					itemSelecionado = ((JList<String>)event.getSource()).getSelectedValue();
		            if (itemSelecionado != null) {//habilitar botao após selecionar uma linha
		                btnRestaurar.setEnabled(true);
		            }
		        }
			}
		});
		
		mainPanel.add(this);
	
	}

}
