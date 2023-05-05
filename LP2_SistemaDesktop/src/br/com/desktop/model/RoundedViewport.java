package br.com.desktop.model;

import java.awt.*;
import javax.swing.*;

public class RoundedViewport extends JViewport {

    private int arcWidth;
    private int arcHeight;

    public RoundedViewport(int arcWidth, int arcHeight) {
        super();
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        g2d.dispose();
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += arcWidth;
        size.height += arcHeight;
        return size;
    }

}
