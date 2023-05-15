package br.com.desktop.model;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.desktop.dao.ProjetoDAO;
import br.com.desktop.view.JFrameDashboard;

public class Menu extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -884336599351434558L;
	@SuppressWarnings("unused")
	private EventMenuSelected event;
	private Usuario usuarioLogado;
	private JPanel mainPanel;

	public void addEventMenuSelected(EventMenuSelected event) {
		this.event = event;
		listMenu1.addEventMenuSelected(event);
	}

	public Menu(Usuario usuario, JPanel mainPanel, JFrameDashboard jFrame) {
		this.mainPanel = mainPanel;
		this.usuarioLogado = usuario;
		initComponents(jFrame);
		setOpaque(false);
		listMenu1.setOpaque(false);
		init();
	}

	private void init() {

		listMenu1.addItem(new Model_Menu("", "Dashboard", Model_Menu.TipoMenu.MENU));
		listMenu1.addItem(new Model_Menu("", " ", Model_Menu.TipoMenu.VAZIO));
		listMenu1.addItem(new Model_Menu("", "Relatório", Model_Menu.TipoMenu.MENU));
		listMenu1.addItem(new Model_Menu("", "Todos projetos", Model_Menu.TipoMenu.TITULO));
		
		ProjetoDAO projetoDAO = new ProjetoDAO();
		ArrayList<Projeto> projetos = new ArrayList<>();
		try {
			 projetos = projetoDAO.listarProjetos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<projetos.size();i++) {//fazer for em projetos
			listMenu1.addItem(new Model_Menu(String.valueOf(i+1), projetos.get(i).getTitulo(), Model_Menu.TipoMenu.MENU, projetos.get(i).getId()));
		}
		listMenu1.addItem(new Model_Menu("add_project", "Novo projeto", Model_Menu.TipoMenu.MENU));
		listMenu1.addItem(new Model_Menu("", " ", Model_Menu.TipoMenu.VAZIO));

		listMenu1.addItem(new Model_Menu("", "Gerenciar", Model_Menu.TipoMenu.TITULO));
		if (usuarioLogado.getTipoUsuario().equalsIgnoreCase("administrador")) {
			listMenu1.addItem(new Model_Menu("", "Usuários", Model_Menu.TipoMenu.MENU));
		}
		listMenu1.addItem(new Model_Menu("", "Backup", Model_Menu.TipoMenu.MENU));
		listMenu1.addItem(new Model_Menu("", " ", Model_Menu.TipoMenu.VAZIO));
		listMenu1.addItem(new Model_Menu("", "Sair", Model_Menu.TipoMenu.MENU));
		listMenu1.addItem(new Model_Menu("", "", Model_Menu.TipoMenu.VAZIO));
	}


	private void initComponents(JFrameDashboard jFrame) {

		panelMoving = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		listMenu1 = new ListMenu<>(mainPanel, jFrame, usuarioLogado);

		panelMoving.setOpaque(false);

		jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(255, 255, 255));
		jLabel1.setIcon(
				new javax.swing.ImageIcon(Menu.class.getResource("/br/com/desktop/image/logoTaskMaster48.png"))); // NOI18N
		jLabel1.setText("TaskMaster");

		javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
		panelMoving.setLayout(panelMovingLayout);
		panelMovingLayout
				.setHorizontalGroup(panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(panelMovingLayout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
								.addContainerGap()));
		panelMovingLayout
				.setVerticalGroup(panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovingLayout.createSequentialGroup()
								.addGap(15, 15, 15).addComponent(jLabel1).addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(15, 15, 15)
						.addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)));
	}
	@Override
	protected void paintChildren(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint g = new GradientPaint(0, 0, Color.decode("#008080"), 0, getHeight(), Color.decode("#003D3D"));
		g2.setPaint(g);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
		g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
		super.paintChildren(grphcs);
	}

	private int x;
	private int y;

	public void initMoving(JFrame fram) {
		panelMoving.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				x = me.getX();
				y = me.getY();
			}

		});
		panelMoving.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent me) {
				fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
			}
		});
	}
	
	
	

	private javax.swing.JLabel jLabel1;
	private ListMenu<String> listMenu1;
	private javax.swing.JPanel panelMoving;
}