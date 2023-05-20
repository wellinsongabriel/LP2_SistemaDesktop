package br.com.desktop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.com.desktop.dao.FacadeDAO;
import br.com.desktop.model.Projeto;
import br.com.desktop.model.Tarefa;
import br.com.desktop.model.Usuario;

public class JFrameNovaTarefa extends JFrame {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2124488123044572053L;
	
	private JPanel contentPane;
	private static int xx;
	private static int xy;
	private JTextField textTituloTarefa;
	private JTextField textNomeEtiquetaTarefa;
	private int corEtiqueta;
	private int status;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxStatus;
	private JButton buttonCorEtiqueta;
	JTextArea textAreaDescricaoTarefa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {
//			System.out.println("Error setting native LAF: " + e);
//		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameNovaTarefa frame = new JFrameNovaTarefa(null, null,-1,null,null);
					frame.setUndecorated(true); // retira a barra da janela
					frame.setResizable(false); // desabilitar maximar
					frame.setLocationRelativeTo(null);// alinhar ao centro
					frame.setVisible(true);
					// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JFrameNovaTarefa(Tarefa tarefaAlteracao, JFrame jframe, int statusParam, Usuario usuarioLogado, Projeto projeto) {
		setTitle("TaksManager");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(JFrameNovaTarefa.class.getResource("/br/com/desktop/image/logoTaskMaster48.png")));
		Date dataAtual = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtualString = dateFormat.format(dataAtual);

		setBackground(new Color(255, 255, 255));
		setMaximumSize(new Dimension(500, 470));
		setMinimumSize(new Dimension(500, 470));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 470);
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
				setOpacity((float) 0.8);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setOpacity(1);
			}
		});
		contentPane.setMaximumSize(new Dimension(500, 500));
		contentPane.setOpaque(false);
		contentPane.setMinimumSize(new Dimension(500, 500));
		contentPane.setBorder(new LineBorder(Color.BLACK, 1, true));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDataAtual = new JLabel(dataAtualString);
		lblDataAtual.setForeground(Color.GRAY);
		lblDataAtual.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataAtual.setBounds(362, 67, 80, 14);
		contentPane.add(lblDataAtual);

		textAreaDescricaoTarefa = new JTextArea();
		textAreaDescricaoTarefa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAreaDescricaoTarefa.setBorder(new LineBorder(new Color(171, 173, 179)));
		textAreaDescricaoTarefa.setToolTipText("Informe a descrição da tarefa");
		textAreaDescricaoTarefa.setLineWrap(true);
		textAreaDescricaoTarefa.setColumns(10);
		textAreaDescricaoTarefa.setBounds(49, 265, 393, 84);
		contentPane.add(textAreaDescricaoTarefa);

		JLabel lblNewLabel_1 = new JLabel("Nova Tarefa");
		lblNewLabel_1.setForeground(new Color(255, 128, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(10, 11, 139, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Etiqueta");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(49, 180, 46, 14);
		contentPane.add(lblNewLabel_2);

		textTituloTarefa = new JTextField();
		textTituloTarefa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textTituloTarefa.setBorder(new LineBorder(new Color(171, 173, 179)));
		textTituloTarefa.setBounds(49, 129, 393, 28);
		contentPane.add(textTituloTarefa);
		textTituloTarefa.setColumns(10);

		textNomeEtiquetaTarefa = new JTextField();
		textNomeEtiquetaTarefa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textNomeEtiquetaTarefa.setBounds(49, 195, 305, 28);
		contentPane.add(textNomeEtiquetaTarefa);
		textNomeEtiquetaTarefa.setColumns(10);

		JLabel lblNewLabel_2_2 = new JLabel("Descrição");
		lblNewLabel_2_2.setForeground(Color.GRAY);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_2.setBounds(49, 250, 70, 14);
		contentPane.add(lblNewLabel_2_2);

		JButton buttonAdicionarTarefa = new JButton("Salvar");
		buttonAdicionarTarefa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacadeDAO dao = new FacadeDAO();
				if(tarefaAlteracao==null) {
				Tarefa tarefa = new Tarefa(textTituloTarefa.getText(), textAreaDescricaoTarefa.getText(),
						textNomeEtiquetaTarefa.getText(), corEtiqueta, new Date(), null, status);
				try {
					dao.cadastrarTarefa(tarefa, projeto);
					if (jframe != null) {
						jframe.dispose();
						JFrameDashboard formListaTarefas = new JFrameDashboard(usuarioLogado);
//						formListaTarefas.setUndecorated(true); // retira a barra da janela
						formListaTarefas.setResizable(false); // desabilitar maximar
						formListaTarefas.setLocationRelativeTo(null);// alinhar ao centro
						formListaTarefas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						formListaTarefas.setVisible(true);
					}
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				}else {
					tarefaAlteracao.setTitulo(textTituloTarefa.getText());
					tarefaAlteracao.setDescricao(textAreaDescricaoTarefa.getText());
					tarefaAlteracao.setNomeEtiqueta(textNomeEtiquetaTarefa.getText());
					tarefaAlteracao.setCorEtiqueta(corEtiqueta);
					tarefaAlteracao.setDataAlteracao(new Date());
					tarefaAlteracao.setStatus(status);
					
					try {
						dao.alterarTarefa(tarefaAlteracao.getId(), tarefaAlteracao);
						if (jframe != null) {
							jframe.dispose();
							JFrameDashboard formListaTarefas = new JFrameDashboard(usuarioLogado);
							// formListaTarefas.setUndecorated(true); // retira a barra da janela
							formListaTarefas.setResizable(false); // desabilitar maximar
							formListaTarefas.setLocationRelativeTo(null);// alinhar ao centro
							formListaTarefas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							formListaTarefas.setVisible(true);
						}
						dispose();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		buttonAdicionarTarefa.setToolTipText("Salvar");
		buttonAdicionarTarefa.setForeground(Color.WHITE);
		buttonAdicionarTarefa.setFont(new Font("Tahoma", Font.BOLD, 16));
		buttonAdicionarTarefa.setBorder(null);
		buttonAdicionarTarefa.setBackground(new Color(255, 128, 0));
		buttonAdicionarTarefa.setBounds(345, 403, 98, 28);
		contentPane.add(buttonAdicionarTarefa);

		JLabel lblBotaoFecharX = new JLabel("X");
		lblBotaoFecharX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblBotaoFecharX.setBackground(new Color(255, 0, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblBotaoFecharX.setBackground(new Color(255, 255, 255));
			}
		});
		lblBotaoFecharX.setToolTipText("Fechar");
		lblBotaoFecharX.setOpaque(true);
		lblBotaoFecharX.setHorizontalAlignment(SwingConstants.CENTER);
		lblBotaoFecharX.setFont(new Font("Arial", Font.PLAIN, 18));
		lblBotaoFecharX.setBackground(Color.WHITE);
		lblBotaoFecharX.setBounds(467, 1, 32, 29);
		contentPane.add(lblBotaoFecharX);

		JLabel lblBotaoMinimizar = new JLabel("-");
		lblBotaoMinimizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBotaoMinimizar.setBackground(new Color(210, 210, 210));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblBotaoMinimizar.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		lblBotaoMinimizar.setToolTipText("Minimizar");
		lblBotaoMinimizar.setOpaque(true);
		lblBotaoMinimizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBotaoMinimizar.setFont(new Font("Arial", Font.PLAIN, 18));
		lblBotaoMinimizar.setBackground(Color.WHITE);
		lblBotaoMinimizar.setBounds(437, 1, 32, 29);
		contentPane.add(lblBotaoMinimizar);

		comboBoxStatus = new JComboBox();
		comboBoxStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status = comboBoxStatus.getSelectedIndex();
			}
		});
		comboBoxStatus.setFocusTraversalKeysEnabled(false);
		comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] { "A fazer", "Em andamento", "Concluida" }));
		comboBoxStatus.setSelectedIndex(0);
		comboBoxStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxStatus.setBounds(49, 67, 139, 28);
		contentPane.add(comboBoxStatus);

		JLabel lblNewLabel_2_1_1 = new JLabel("Título");
		lblNewLabel_2_1_1.setForeground(Color.GRAY);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_1_1.setBounds(49, 115, 46, 14);
		contentPane.add(lblNewLabel_2_1_1);

		buttonCorEtiqueta = new JButton("");
		buttonCorEtiqueta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = new Color(128, 255, 128);
				corEtiqueta = color.getRGB();
				buttonCorEtiqueta.setBackground(color);
				color = JColorChooser.showDialog(buttonCorEtiqueta, "Selecione uma cor", color);
				if (color != null) {
					buttonCorEtiqueta.setBackground(color);
					corEtiqueta = color.getRGB();
				}
			}
		});
		buttonCorEtiqueta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		buttonCorEtiqueta.setToolTipText("Selecione a cor da etiqueta");
		buttonCorEtiqueta.setForeground(Color.WHITE);
		buttonCorEtiqueta.setFont(new Font("Tahoma", Font.BOLD, 16));
		buttonCorEtiqueta.setBorder(null);
		buttonCorEtiqueta.setBackground(new Color(128, 255, 128));
		corEtiqueta = (new Color(128, 255, 128)).getRGB();
		buttonCorEtiqueta.setBounds(386, 194, 56, 28);
		contentPane.add(buttonCorEtiqueta);

		JLabel lblNewLabel_2_1 = new JLabel("Cor");
		lblNewLabel_2_1.setForeground(Color.GRAY);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(387, 180, 46, 14);
		contentPane.add(lblNewLabel_2_1);

		if (tarefaAlteracao != null) {
			tarefaExistente(tarefaAlteracao);
		}
		
		if(statusParam>-1) {
			comboBoxStatus.setSelectedIndex(statusParam);
		}

	}

	private void tarefaExistente(Tarefa tarefa) {
		comboBoxStatus.setSelectedIndex(tarefa.getStatus());
		textTituloTarefa.setText(tarefa.getTitulo());
		textNomeEtiquetaTarefa.setText(tarefa.getNomeEtiqueta());
		buttonCorEtiqueta.setBackground(new Color(tarefa.getCorEtiqueta()));
		corEtiqueta = tarefa.getCorEtiqueta();
		textAreaDescricaoTarefa.setText(tarefa.getDescricao());
	}
}
