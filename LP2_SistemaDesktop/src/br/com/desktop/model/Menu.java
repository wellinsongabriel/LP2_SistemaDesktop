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
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.desktop.dao.FacadeDAO;
import br.com.desktop.view.JFrameDashboard;

public class Menu extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -884336599351434558L;
	@SuppressWarnings("unused")
	private JLabel jLabel;
	private ListMenu<String> listMenu;
	private javax.swing.JPanel panelMoving;
	@SuppressWarnings("unused")
	private EventMenuSelected event;
	private Usuario usuarioLogado;
	private JPanel mainPanel;

	public void addEventMenuSelected(EventMenuSelected event) {
		this.event = event;
		listMenu.addEventMenuSelected(event);
	}

	public Menu(Usuario usuario, JPanel mainPanel, JFrameDashboard jFrame) {
		this.mainPanel = mainPanel;
		this.usuarioLogado = usuario;
		initComponents(jFrame);
		setOpaque(false);
		listMenu.setOpaque(false);
		init();
	}

	private void init() {

		listMenu.addItem(new ModelMenu("", "Dashboard", ModelMenu.TipoMenu.MENU));
		listMenu.addItem(new ModelMenu("", " ", ModelMenu.TipoMenu.VAZIO));
		listMenu.addItem(new ModelMenu("report", "Relatório", ModelMenu.TipoMenu.MENU));
		listMenu.addItem(new ModelMenu("", "Todos projetos", ModelMenu.TipoMenu.TITULO));
		
		FacadeDAO facadeDAO = new FacadeDAO();
		ArrayList<Projeto> projetos = new ArrayList<>();
		try {
			 projetos = facadeDAO.listarProjetosUsuario(usuarioLogado.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<projetos.size();i++) {//fazer for em projetos
			listMenu.addItem(new ModelMenu(String.valueOf(i+1), projetos.get(i).getTitulo(), ModelMenu.TipoMenu.MENU, projetos.get(i).getId()));
		}
		listMenu.addItem(new ModelMenu("add_project", "Novo projeto", ModelMenu.TipoMenu.MENU));
		listMenu.addItem(new ModelMenu("", " ", ModelMenu.TipoMenu.VAZIO));

		listMenu.addItem(new ModelMenu("", "Gerenciar", ModelMenu.TipoMenu.TITULO));
		if (usuarioLogado.getTipoUsuario().equalsIgnoreCase("administrador")) {
			listMenu.addItem(new ModelMenu("users", "Usuários", ModelMenu.TipoMenu.MENU));
		}
		listMenu.addItem(new ModelMenu("backup", "Backup", ModelMenu.TipoMenu.MENU));
		listMenu.addItem(new ModelMenu("", " ", ModelMenu.TipoMenu.VAZIO));
		listMenu.addItem(new ModelMenu("exit", "Sair", ModelMenu.TipoMenu.MENU));
		listMenu.addItem(new ModelMenu("", "", ModelMenu.TipoMenu.VAZIO));
		listMenu.addItem(new ModelMenu("", "", ModelMenu.TipoMenu.VAZIO));
		listMenu.addItem(new ModelMenu("", "", ModelMenu.TipoMenu.VAZIO));
		listMenu.addItem(new ModelMenu("info", "Sobre", ModelMenu.TipoMenu.MENU));
	}


	private void initComponents(JFrameDashboard jFrame) {

		panelMoving = new javax.swing.JPanel();
		jLabel = new javax.swing.JLabel();
		listMenu = new ListMenu<>(mainPanel, jFrame, usuarioLogado);

		panelMoving.setOpaque(false);

		jLabel.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
		jLabel.setForeground(new java.awt.Color(255, 255, 255));
		jLabel.setIcon(
				new javax.swing.ImageIcon(Menu.class.getResource("/br/com/desktop/image/logoTaskMaster45.png"))); // NOI18N
		jLabel.setText("TaskMaster");

		javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
		panelMoving.setLayout(panelMovingLayout);
		panelMovingLayout
				.setHorizontalGroup(panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(panelMovingLayout.createSequentialGroup().addContainerGap()
								.addComponent(jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
								.addContainerGap()));
		panelMovingLayout
				.setVerticalGroup(panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovingLayout.createSequentialGroup()
								.addGap(15, 15, 15).addComponent(jLabel).addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(listMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(15, 15, 15)
						.addComponent(listMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)));
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
	
	
	

	
}