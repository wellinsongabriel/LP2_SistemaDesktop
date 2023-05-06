package br.com.desktop.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.com.desktop.dao.DAO;
import br.com.desktop.model.CheckBoxRenderer;
import br.com.desktop.model.ModeloTabela;
import br.com.desktop.model.PanelRound;
import br.com.desktop.model.ScrollBarPersonalizado;
import br.com.desktop.model.TableCellRenderer;
import br.com.desktop.model.Usuario;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.JTable;
import datechooser.beans.DateChooserPanel;
import datechooser.beans.DateChooserCombo;

public class JPanelNovoProjeto extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public JPanelNovoProjeto(JPanel mainPanel) {
		setOpaque(false);
		setLayout(null);
		
		PanelRound panel = new PanelRound();
		panel.setAllRound(50);
		panel.setBackground(new Color(0, 255, 0));
		panel.setBounds(44, 86, 877, 301);
//		add(panel);

		
		
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(47, 43, 369, 35);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome do projeto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(47, 25, 125, 14);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(47, 125, 369, 35);
		panel.add(textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome do projeto");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(47, 107, 125, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("Novo Projeto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(48, 61, 134, 14);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(44, 401, 877, 164);
//		add(scrollPane);
		DAO dao = new DAO();
		ArrayList<Usuario> usuarios = null;
		try {
			usuarios = dao.listarUsuarios();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModeloTabela modelo = new ModeloTabela(usuarios);
		table = new JTable();
		table.setModel(modelo);
		table.setDefaultRenderer(Object.class, new TableCellRenderer());
		scrollPane.setViewportView(table);
		scrollPane.setVisible(true);
		
		TableColumn colunaCheckBox = table.getColumnModel().getColumn(1);
		colunaCheckBox.setCellRenderer(new CheckBoxRenderer());
		colunaCheckBox.setCellEditor(new DefaultCellEditor(new JCheckBox()));
		colunaCheckBox.setPreferredWidth(20);
		colunaCheckBox.setMaxWidth(20);
		colunaCheckBox.setMinWidth(20);		
		
		mainPanel.add(scrollPane);		
		mainPanel.add(panel);
	
    }
}
