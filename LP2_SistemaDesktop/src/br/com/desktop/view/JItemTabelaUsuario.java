package br.com.desktop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.desktop.model.PanelRound;
import br.com.desktop.model.Usuario;

public class JItemTabelaUsuario extends JPanel {

	private Usuario usuario;
	private boolean selecionado = false;
	

	public JItemTabelaUsuario(Usuario usuario) {
		this.usuario = usuario;
		setLayout(new BorderLayout());
		PanelRound panelLinhaTabela = new PanelRound();
		panelLinhaTabela.setBackground(Color.white);
		panelLinhaTabela.setOpaque(true);
//		setBounds(0, 0, 875, 30);
		setPreferredSize(new Dimension(875, 30));
		add(panelLinhaTabela);
		panelLinhaTabela.setLayout(null);

		JCheckBox checkBoxSelecao = new JCheckBox("");
		checkBoxSelecao.setBackground(new Color(255, 255, 255));
		checkBoxSelecao.setBounds(854, 0, 21, 30);
		checkBoxSelecao.setPreferredSize(new Dimension(21, 30));
		panelLinhaTabela.add(checkBoxSelecao);

		JLabel lblNomeUsuario = new JLabel("  " + usuario.getUsuario());
		lblNomeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeUsuario.setBounds(0, 0, 853, 30);

		panelLinhaTabela.add(lblNomeUsuario);

		checkBoxSelecao.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
//				System.out.println(usuariosAdicionados);
				JCheckBox checkBox = (JCheckBox) e.getSource();
				JPanel panelLinhaTabela = (JPanel) checkBox.getParent();

				if (e.getStateChange() == ItemEvent.SELECTED) {
					
					selecionado = true;
					panelLinhaTabela.setBackground(new Color(197, 197, 210));
					panelLinhaTabela.setOpaque(true);
					checkBox.setBackground(new Color(197, 197, 210));
					checkBox.setOpaque(true);
					revalidate();
					repaint();

				} else {
					selecionado = false;
					panelLinhaTabela.setBackground(Color.white);
					panelLinhaTabela.setOpaque(true);
					checkBox.setBackground(Color.white);
					checkBox.setOpaque(true);
					revalidate();
					repaint();
				}
			}
		});

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

}
