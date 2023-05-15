package br.com.desktop.model;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarPersonalizado extends JScrollBar {

    /**
	 * 
	 */
	private static final long serialVersionUID = 821281231694130670L;

	public ScrollBarPersonalizado() {
        setUI(new ScrollBarUIPersonalizado());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(71,101,130));//cor cursor da barra de navegação
        setBackground(Color.WHITE);
    }
}
