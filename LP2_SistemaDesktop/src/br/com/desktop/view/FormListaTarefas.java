package br.com.desktop.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.desktop.dao.DAO;
import br.com.desktop.model.BordaCantoArredondado;
import br.com.desktop.model.ObjectListPanel;
import br.com.desktop.model.Tarefa;

import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class FormListaTarefas extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					FormListaTarefas frame = new FormListaTarefas();
					DAO dao = new DAO();
					
					// frame.setUndecorated(true); //retira a barra da janela
					frame.setResizable(false); // desabilitar maximar
					frame.setLocationRelativeTo(null);// alinhar ao centro
					ArrayList<Tarefa> tarefas = null;
					try {
						 tarefas = dao.listar();
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ObjectListPanel objectListPanel = new ObjectListPanel(tarefas);
					frame.add(objectListPanel);
						frame.pack();
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
		
	}
}
