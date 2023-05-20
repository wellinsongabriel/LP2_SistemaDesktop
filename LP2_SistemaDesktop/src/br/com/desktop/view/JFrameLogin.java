package br.com.desktop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.desktop.dao.FacadeDAO;
import br.com.desktop.model.Usuario;

public class JFrameLogin extends JFrame {

	private static final long serialVersionUID = -8739077760698271069L;
	
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private JFrameLogin formLogin;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameLogin frame = new JFrameLogin();
					// frame.setUndecorated(true); //retira a barra da janela
					frame.setLocationRelativeTo(null);// alinhar ao centro
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public JFrameLogin() {
		formLogin = this;
		setTitle("TaskMaster - Login");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(JFrameLogin.class.getResource("/br/com/desktop/image/logoTaskMaster.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 378);
		contentPane = new JPanel();
		
		contentPane.setPreferredSize(new Dimension(10, 378));
		contentPane.setMinimumSize(new Dimension(10, 378));
		contentPane.setMaximumSize(new Dimension(10, 378));
		contentPane.setBackground(Color.WHITE);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 378));
		panel.setMinimumSize(new Dimension(10, 378));
		panel.setMaximumSize(new Dimension(10, 378));
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 316, 376);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(JFrameLogin.class.getResource("/br/com/desktop/image/logoTaskMaster.png")));
		lblNewLabel.setBounds(93, 100, 114, 93);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1_2 = new JLabel("TaskMaster");
		lblNewLabel_1_2.setBounds(58, 184, 207, 71);
		panel.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setForeground(new Color(255, 128, 0));
		lblNewLabel_1_2.setFont(new Font("DejaVu Sans", Font.BOLD, 30));


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

				FacadeDAO dao = new FacadeDAO();
				try {

					Usuario usuario = dao.consultarUsuario(textFieldUsuario.getText(), new String(passwordField.getPassword()));

					if (usuario != null) {
						JFrameDashboard jFrameDashboard = new JFrameDashboard(usuario);
						formLogin.dispose();
						jFrameDashboard.setVisible(true);
						
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

		
		
		textFieldUsuario.setText("Admin");
		passwordField.setText("123456");
	}

}
