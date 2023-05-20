package br.com.desktop.model;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Header extends javax.swing.JPanel {

    
	private static final long serialVersionUID = -449475467760326392L;
	private JLabel jLabel1;
    private JLabel jLabel2;
    private Usuario usuario;
    
	public Header(Usuario usuarioLogado) {
		this.usuario = usuarioLogado;
        initComponents();
        setOpaque(false);
    }

    

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel(this.usuario.getUsuario());
        
        jLabel1.setIcon(new ImageIcon(Header.class.getResource("/br/com/desktop/image/user.png")));

        setBackground(new Color(255, 255, 255));
        
        jLabel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, 25, getHeight());
        g2.fillRect(getWidth() - 25, getHeight() - 25, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }
    
}