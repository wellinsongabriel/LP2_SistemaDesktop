package br.com.desktop.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

import br.com.desktop.controller.Criptografia;
import br.com.desktop.dao.DAO;
import br.com.desktop.model.Usuario;

import java.awt.Toolkit;

public class FormLogin extends JFrame {

	private JPanel contentPane;
	private static int xx;
	private static int xy;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private FormLogin formLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormLogin frame = new FormLogin();
					// frame.setUndecorated(true); //retira a barra da janela
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
	public FormLogin() {
		formLogin = this;
		setTitle("TaskMaster - Login");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FormLogin.class.getResource("/br/com/desktop/image/logoTaskMaster.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 378);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
//				int x = e.getXOnScreen();
//				int y = e.getYOnScreen();
//				setLocation(x - xx, y - xy);
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				xx = e.getX();
//				xy = e.getY();
			}
		});
		contentPane.setPreferredSize(new Dimension(10, 378));
		contentPane.setMinimumSize(new Dimension(10, 378));
		contentPane.setMaximumSize(new Dimension(32767, 378));
		contentPane.setBackground(Color.WHITE);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 378));
		panel.setMinimumSize(new Dimension(10, 378));
		panel.setMaximumSize(new Dimension(32767, 378));
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 316, 376);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FormLogin.class.getResource("/br/com/desktop/image/logoTaskMaster.png")));
		lblNewLabel.setBounds(93, 100, 114, 93);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1_2 = new JLabel("TaskMaster");
		lblNewLabel_1_2.setBounds(58, 184, 207, 71);
		panel.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setForeground(new Color(255, 128, 0));
		lblNewLabel_1_2.setFont(new Font("DejaVu Sans", Font.BOLD, 30));

		JLabel lblBotaoFecharX = new JLabel("X");
		lblBotaoFecharX.setVisible(false);
		lblBotaoFecharX.setBackground(Color.WHITE);
		lblBotaoFecharX.setOpaque(true);
		lblBotaoFecharX.setHorizontalAlignment(SwingConstants.CENTER);
		lblBotaoFecharX.setToolTipText("Fechar");
		lblBotaoFecharX.setBounds(686, 1, 32, 29);
		contentPane.add(lblBotaoFecharX);
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
		lblBotaoFecharX.setFont(new Font("Arial", Font.PLAIN, 18));

		JLabel lblNewLabel_1 = new JLabel("BEM VINDO");
		lblNewLabel_1.setForeground(new Color(255, 128, 0));
		lblNewLabel_1.setBounds(375, 62, 239, 29);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(375, 147, 275, 28);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldUsuario.setColumns(10);

		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setToolTipText("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(255, 98, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(new Color(255, 128, 0));
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DAO dao = new DAO();
				try {

					Criptografia criptografia = new Criptografia(passwordField.getText(), Criptografia.SHA256);
					Usuario usuario = dao.consultarUsuario(textFieldUsuario.getText(), criptografia.criptografar());
					System.out.println(usuario.toString());

					if (usuario != null) {
						JFrameDashboard formListaTarefas = new JFrameDashboard();
						formLogin.dispose();
						formListaTarefas.setVisible(true);
						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBorder(null);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setBounds(375, 271, 275, 28);
		contentPane.add(btnLogin);
		btnLogin.setBackground(new Color(255, 128, 0));

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setMinimumSize(new Dimension(7, 28));
		passwordField.setPreferredSize(new Dimension(7, 28));
		passwordField.setBounds(375, 205, 275, 28);
		contentPane.add(passwordField);

		JLabel lblNewLabel_1_1 = new JLabel("faça login para continuar");
		lblNewLabel_1_1.setForeground(SystemColor.textInactiveText);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(375, 82, 239, 29);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Usuário");
		lblNewLabel_1_1_1.setForeground(Color.GRAY);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(375, 132, 239, 17);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Senha");
		lblNewLabel_1_1_1_1.setForeground(Color.GRAY);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1.setBounds(375, 190, 239, 17);
		contentPane.add(lblNewLabel_1_1_1_1);

		JLabel lblBotaoMinimizar = new JLabel("-");
		lblBotaoMinimizar.setVisible(false);
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
		lblBotaoMinimizar.setBounds(654, 1, 32, 29);
		contentPane.add(lblBotaoMinimizar);
	}

}
