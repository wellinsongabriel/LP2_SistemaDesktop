package br.com.desktop.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import br.com.desktop.dao.FacadeDAO;
import br.com.desktop.model.Header;
import br.com.desktop.model.Menu;
import br.com.desktop.model.PanelBorder;
import br.com.desktop.model.Projeto;
import br.com.desktop.model.Usuario;

public class JFrameDashboard extends javax.swing.JFrame {




	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1664356117049813898L;
	private Header header2;
    private javax.swing.JPanel mainPanel;
    private Menu menu;
    private PanelBorder panelBorder;

    public JFrameDashboard(Usuario usuario) {
        initComponents(this, usuario);
        setBackground(new Color(0, 0, 0, 0));
        setExtendedState(MAXIMIZED_VERT);
        setIconImage(Toolkit.getDefaultToolkit()
				.getImage(JFrameDashboard.class.getResource("/br/com/desktop/image/logoTaskMaster115.png")));
        menu.initMoving(JFrameDashboard.this);       
    }
    
  
    private void initComponents(JFrameDashboard jFrame, Usuario usuarioLogado) {

        panelBorder = new PanelBorder();
        mainPanel = new javax.swing.JPanel();
        menu = new Menu(usuarioLogado,mainPanel, jFrame);
        header2 = new Header(usuarioLogado);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder.setBackground(new java.awt.Color(242, 242, 242));

        header2.setFont(new java.awt.Font("sansserif", 0, 14)); 

        mainPanel.setOpaque(false);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder);
        panelBorder.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(238, 238, 238));
        mainPanel.setBounds(0, 0, 937, 543);
        mainPanel.setSize(800, 600);
       
//        exibirTarefas(this, usuarioLogado);
        
        // Obter o ambiente gráfico
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // Obter o kit de ferramentas padrão
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        // Obter a dimensão da tela primária
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        
     // Obter a área de trabalho disponível (considerando a barra de tarefas, etc.)
        Dimension desktopSize = ge.getMaximumWindowBounds().getSize();
        int desktopWidth = desktopSize.width;
        int desktopHeight = desktopSize.height;

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(panelBorder, GroupLayout.PREFERRED_SIZE, desktopWidth, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(panelBorder, GroupLayout.PREFERRED_SIZE, desktopHeight, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(JFrameDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFrameDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFrameDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFrameDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameDashboard(null).setVisible(true);
            }
        });
    }
    
    
    
   private void exibirTarefas(JFrameDashboard jFrame, Usuario usuarioLogado) {
   FacadeDAO facadeDAO = new FacadeDAO();

	ArrayList<Projeto> projetos = new ArrayList<>();
	try {
		projetos = facadeDAO.listarProjetos();
	} catch (Exception e) {
		e.printStackTrace();
	}
	mainPanel.removeAll();
	
	if(projetos.size()>0) {
	for (int i=0; i<=1; i++) {
		new JPanelListaTarefas(mainPanel, jFrame, usuarioLogado, projetos.get(0));
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	}
	
}
    
    
}