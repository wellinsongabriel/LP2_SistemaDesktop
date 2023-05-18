package br.com.desktop.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.desktop.dao.FacadeDAO;
import br.com.desktop.model.Usuario;

public class JPanelGerenciaUsuario extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2187918638528307571L;
	private JTextField textFieldNomeUsuario;
	private JPasswordField passwordField;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxTipoUsuario;
	private FacadeDAO facadeDAO;
	private JLabel lblAcaoUsuario;
	private JButton btnAcaoUsuario;
	private int modo = 0;
	private int idAlteracao;
	private JPanel jPanel;
	private JButton btnExcluirUsuario;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JPanelGerenciaUsuario(JPanel mainPanel, JFrameDashboard jFrame, Usuario usuarioLogado) {
		facadeDAO = new FacadeDAO();
		jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		jPanel.setBackground(Color.white);
		jPanel.setBounds(30, 280, 924, 300);

		setOpaque(true);
		setBounds(44, 86, 1000, 800);
		setLayout(null);
		setBackground(Color.white);

		lblAcaoUsuario = new JLabel("Novo Usuário");
		lblAcaoUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAcaoUsuario.setBounds(47, 23, 120, 14);
		add(lblAcaoUsuario);

		textFieldNomeUsuario = new JTextField();
		textFieldNomeUsuario.setBounds(47, 97, 231, 35);
		add(textFieldNomeUsuario);
		textFieldNomeUsuario.setColumns(10);

		btnAcaoUsuario = new JButton("Adicionar");
		btnAcaoUsuario.setBackground(new Color(255, 128, 0));
		btnAcaoUsuario.setForeground(Color.white);
		btnAcaoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (modo == 0) {// inclusão
					Usuario usuario = new Usuario(-1, textFieldNomeUsuario.getText(),
							new String(passwordField.getPassword()), comboBoxTipoUsuario.getSelectedItem().toString());

					try {
						facadeDAO.cadastrarUsuario(usuario);
						modo = 0;
						alternarModo(modo);
						preencherTabela();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {// alteração
					if (JOptionPane.showConfirmDialog(jFrame, "Deseja alterar o usuário") == 0) {
						Usuario usuario = new Usuario(idAlteracao, textFieldNomeUsuario.getText(),
								new String(passwordField.getPassword()),
								comboBoxTipoUsuario.getSelectedItem().toString());

						try {
							facadeDAO.alterarUsuario(usuario);
							modo = 0;
							alternarModo(modo);
							preencherTabela();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}

			}
		});
		btnAcaoUsuario.setBounds(389, 166, 89, 23);
		add(btnAcaoUsuario);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(47, 81, 46, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(49, 143, 46, 14);
		add(lblNewLabel_2);

		comboBoxTipoUsuario = new JComboBox();
		comboBoxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] { "NORMAL", "ADMINISTRADOR" }));
		comboBoxTipoUsuario.setBounds(330, 103, 187, 35);
		add(comboBoxTipoUsuario);

		passwordField = new JPasswordField();
		passwordField.setBounds(47, 160, 231, 35);
		add(passwordField);

		preencherTabela();
		add(jPanel);
		mainPanel.add(this);

		btnExcluirUsuario = new JButton("Excluir");
		btnExcluirUsuario.setVisible(false);
		btnExcluirUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(jFrame, "Deseja excluir o usuário") == 0) {
					try {
						facadeDAO.excluirUsuario(idAlteracao);
						modo = 0;
						alternarModo(modo);
						preencherTabela();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnExcluirUsuario.setForeground(new Color(255, 255, 255));
		btnExcluirUsuario.setBackground(new Color(255, 0, 0));
		btnExcluirUsuario.setBounds(865, 250, 89, 23);
		add(btnExcluirUsuario);
	}

	@SuppressWarnings("unchecked")
	private void preencherTabela() {
		jPanel.removeAll();
		ArrayList<Usuario> usuarios = new ArrayList<>();
		try {
			usuarios = facadeDAO.listarUsuarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arrayUsuarios = usuarios.stream().map(Usuario::getUsuario)
				.collect(Collectors.toCollection(ArrayList::new)).toArray(new String[usuarios.size()]);

		@SuppressWarnings("unused")
		DefaultListModel<String> model = new DefaultListModel<String>();

		@SuppressWarnings("rawtypes")
		JList jJistaUsuarios = new JList();
//		jJistaUsuarios.setBounds(20, 240, 924, 74);
		jJistaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jJistaUsuarios.setListData(arrayUsuarios);

		String[] colunasTabela = { "ID", "Nome", "Tipo" };
		Object[][] dadosTabela = new Object[usuarios.size()][3];

		for (int i = 0; i < usuarios.size(); i++) {
			dadosTabela[i][0] = usuarios.get(i).getId();
			dadosTabela[i][1] = usuarios.get(i).getUsuario();
			dadosTabela[i][2] = usuarios.get(i).getTipoUsuario();
		}

		@SuppressWarnings("serial")
		DefaultTableModel modeloTabela = new DefaultTableModel(dadosTabela, colunasTabela) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(modeloTabela);
		table.setBackground(Color.WHITE);
		table.setOpaque(true);

		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setBounds(20, 280, 924, 300);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setOpaque(true);
		jPanel.add(scrollPane);

		JLabel lblNewLabel_3 = new JLabel("Selecione um usuario para alterar");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(20, 253, 254, 14);
		add(lblNewLabel_3);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						modo = 1;
						// Obtenha os valores da linha selecionada
						idAlteracao = (int) table.getValueAt(selectedRow, 0);
						textFieldNomeUsuario.setText((String) table.getValueAt(selectedRow, 1));
						comboBoxTipoUsuario.setSelectedItem(table.getValueAt(selectedRow, 2));
						alternarModo(modo);
					} else {
						modo = 0;
						alternarModo(modo);
					}
				}
			}
		});
		jPanel.revalidate();
		jPanel.repaint();

	}

	private void alternarModo(int modo) {
		if (modo == 0) {
			lblAcaoUsuario.setText("Novo Usuário");
			btnAcaoUsuario.setText("Adicionar");
			textFieldNomeUsuario.setText("");
			comboBoxTipoUsuario.setSelectedItem("NORMAL");
			passwordField.setText("");
			btnExcluirUsuario.setVisible(false);

		} else {
			lblAcaoUsuario.setText("Alterar Usuário");
			btnAcaoUsuario.setText("Alterar");
			btnExcluirUsuario.setVisible(true);

		}

	}
}
