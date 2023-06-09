package br.com.desktop.model;




import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class MenuItem extends javax.swing.JPanel {


	private static final long serialVersionUID = 4328615621213558378L;
	private boolean selected;
    private boolean over;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbName;

    public MenuItem(ModelMenu data) {
        initComponents();
        setOpaque(false);
        if (data.getTipo() == ModelMenu.TipoMenu.MENU ) {//&& !"".equalsIgnoreCase(data.getIcone())) {
          if(!"".equalsIgnoreCase(data.getIcone()) && data.getNome().equalsIgnoreCase("Novo projeto") 
        		  || data.getNome().equalsIgnoreCase("Sobre") || data.getNome().equalsIgnoreCase("Relatório")
        		  || data.getNome().equalsIgnoreCase("Usuários") || data.getNome().equalsIgnoreCase("Sair") 
        		  || data.getNome().equalsIgnoreCase("Backup")) {  
        	lbIcon.setIcon(data.toIcone());
          }
            lbName.setText(data.getNome());
        } else if (data.getTipo() == ModelMenu.TipoMenu.TITULO) {
            lbIcon.setText(data.getNome());
            lbIcon.setFont(new Font("sansserif", 1, 12));
            lbName.setVisible(false);
        } else {
            lbName.setText(" ");
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public void setOver(boolean over) {
        this.over = over;
        repaint();
    }

    private void initComponents() {

        lbIcon = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();

        lbIcon.setForeground(new java.awt.Color(255, 255, 255));

        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("Menu Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbIcon)
                .addGap(18, 18, 18)
                .addComponent(lbName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (selected || over) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (selected) {
                g2.setColor(new Color(255, 255, 255, 80));
            } else {
                g2.setColor(new Color(255, 255, 255, 20));
            }
            g2.fillRoundRect(10, 0, getWidth() - 20, getHeight(), 5, 5);
        }
        super.paintComponent(grphcs);
    }

    
}