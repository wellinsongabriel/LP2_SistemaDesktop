package br.com.desktop.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Canvas;
import java.awt.Label;
import java.awt.Button;
import java.awt.Panel;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.com.desktop.dao.DAO;
import br.com.desktop.model.Tarefa;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormNovaTarefa extends JFrame {

	private JPanel contentPane;
	private static int xx;
	private static int xy;
	private JTextField textTituloTarefa;
	private JTextField textNomeEtiquetaTarefa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch(Exception e) {
	        System.out.println("Error setting native LAF: " + e);
	    }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormNovaTarefa frame = new FormNovaTarefa();
					frame.setUndecorated(true); //retira a barra da janela
					frame.setResizable(false); //desabilitar maximar
					frame.setLocationRelativeTo(null);// alinhar ao centro
					frame.setVisible(true);
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormNovaTarefa() {
		Date dataAtual  = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");		
        String dataAtualString = dateFormat.format(dataAtual);
        
		setBackground(new Color(255, 255, 255));
		setMaximumSize(new Dimension(500, 500));
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - xx, y - xy);
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
				setOpacity((float)0.8);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				setOpacity(1);
			}
		});
		contentPane.setMaximumSize(new Dimension(500, 500));
		contentPane.setOpaque(false);
		contentPane.setMinimumSize(new Dimension(500, 500));
		contentPane.setBorder(new LineBorder(Color.ORANGE, 1, true));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descrição");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel.setBounds(49, 282, 70, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblDataAtual = new JLabel(dataAtualString);
		lblDataAtual.setForeground(Color.GRAY);
		lblDataAtual.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDataAtual.setBounds(373, 79, 80, 14);
		contentPane.add(lblDataAtual);
		
		JTextArea textAreaDescricaoTarefa = new JTextArea();
		textAreaDescricaoTarefa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textAreaDescricaoTarefa.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaDescricaoTarefa.setToolTipText("Informe a descrição da tarefa");
		textAreaDescricaoTarefa.setLineWrap(true);
		textAreaDescricaoTarefa.setColumns(10);
		textAreaDescricaoTarefa.setBounds(49, 301, 393, 111);
		contentPane.add(textAreaDescricaoTarefa);
		
		JLabel lblNewLabel_1 = new JLabel("Nova Tarefa");
		lblNewLabel_1.setForeground(new Color(222, 140, 18));
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 17));
		lblNewLabel_1.setBounds(10, 11, 139, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblBotaoFechar = new JLabel("X");
		lblBotaoFechar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				  setCursor(Cursor.HAND_CURSOR);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		lblBotaoFechar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBotaoFechar.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBotaoFechar.setBounds(467, 10, 17, 14);
		contentPane.add(lblBotaoFechar);
		
		Button buttonAdicionarTarefa = new Button("Adiconar");
		buttonAdicionarTarefa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tarefa tarefa = new Tarefa(textTituloTarefa.getText(), textAreaDescricaoTarefa.getText(), 
						textNomeEtiquetaTarefa.getText(),
						"", new Date(),
						null, "A fazer");
				DAO dao = new DAO();
				try {
					dao.cadastrar(tarefa);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonAdicionarTarefa.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonAdicionarTarefa.setBackground(Color.ORANGE);
		buttonAdicionarTarefa.setBounds(213, 429, 70, 22);
		contentPane.add(buttonAdicionarTarefa);
		
		JLabel lblNewLabel_2 = new JLabel("Título");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_2.setBounds(49, 95, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		textTituloTarefa = new JTextField();
		textTituloTarefa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textTituloTarefa.setBorder(new LineBorder(new Color(171, 173, 179)));
		textTituloTarefa.setBounds(49, 111, 393, 20);
		contentPane.add(textTituloTarefa);
		textTituloTarefa.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Etiqueta");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_3.setBounds(49, 144, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textNomeEtiquetaTarefa = new JTextField();
		textNomeEtiquetaTarefa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textNomeEtiquetaTarefa.setBounds(49, 158, 393, 20);
		contentPane.add(textNomeEtiquetaTarefa);
		textNomeEtiquetaTarefa.setColumns(10);
	}
}
