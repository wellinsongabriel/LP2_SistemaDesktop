package br.com.desktop.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.com.desktop.dao.FacadeDAO;
import br.com.desktop.model.Usuario;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class JPanelGerenciaUsuario extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2187918638528307571L;
	private JTextField textField;
	private JPasswordField passwordField;

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JPanelGerenciaUsuario(JPanel mainPanel, JFrameDashboard jFrame, Usuario usuarioLogado) {
		setOpaque(true);
		setBounds(44, 86, 1000, 800);
		setLayout(null);
		setBackground(Color.white);
		
		JLabel lblNewLabel = new JLabel("Novo Usuário");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(47, 23, 112, 14);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(47, 97, 231, 35);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.setBounds(389, 166, 89, 23);
		add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(47, 81, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(49, 143, 46, 14);
		add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Comum", "Administrador"}));
		comboBox.setBounds(330, 103, 187, 35);
		add(comboBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(47, 160, 231, 35);
		add(passwordField);
		
		
		FacadeDAO facadeDAO = new FacadeDAO();
		ArrayList<Usuario> usuarios = new ArrayList<>();
		try {
			usuarios = facadeDAO.listarUsuarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] arrayUsuarios =  usuarios.stream()
                .map(Usuario::getUsuario)
                .collect(Collectors.toCollection(ArrayList::new))
                .toArray(new String[usuarios.size()]);

		@SuppressWarnings("unused")
		DefaultListModel<String> model = new DefaultListModel<String>();

		JList jJistaUsuarios = new JList();
//		jJistaUsuarios.setBounds(20, 240, 924, 74);
		jJistaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jJistaUsuarios.setListData(arrayUsuarios);
		
		String[] columnNames = {"ID", "Nome"};
		Object[][] data = new Object[usuarios.size()][2];

		for (int i = 0; i < usuarios.size(); i++) {
		    data[i][0] = usuarios.get(i).getId();
		    data[i][1] = usuarios.get(i).getUsuario();
		}
		
		JTable table = new JTable(data, columnNames);
		table.setBackground(Color.WHITE);
		table.setOpaque(true);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 240, 924, 300);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setOpaque(true);
		add(scrollPane);
		
		mainPanel.add(this);
	}
}
