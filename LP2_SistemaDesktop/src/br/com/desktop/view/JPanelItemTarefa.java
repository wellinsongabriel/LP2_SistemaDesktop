package br.com.desktop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import br.com.desktop.dao.DAO;
import br.com.desktop.model.BordaCantoArredondado;
import br.com.desktop.model.Tarefa;

public class JPanelItemTarefa extends JPanel {

	/**
	 * Create the panel.
	 */
	protected int id;
	protected String tituloTarefa;
	protected String descricaoTarefa;
	protected String nomeEtiquetaTarefa;
	protected int corEtiquetaTarefa;
	protected int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTituloTarefa() {
		return tituloTarefa;
	}

	public void setTituloTarefa(String tituloTarefa) {
		this.tituloTarefa = tituloTarefa;
	}

	public String getDescricaoTarefa() {
		return descricaoTarefa;
	}

	public void setDescricaoTarefa(String descricaoTarefa) {
		this.descricaoTarefa = descricaoTarefa;
	}

	public String getNomeEtiquetaTarefa() {
		return nomeEtiquetaTarefa;
	}

	public void setNomeEtiquetaTarefa(String nomeEtiquetaTarefa) {
		this.nomeEtiquetaTarefa = nomeEtiquetaTarefa;
	}

	public int getCorEtiquetaTarefa() {
		return corEtiquetaTarefa;
	}

	public void setCorEtiquetaTarefa(int corEtiquetaTarefa) {
		this.corEtiquetaTarefa = corEtiquetaTarefa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JPanelItemTarefa(Tarefa tarefa, JFrame jFramePrincipal) {
		
		
		// MouseListener m = new MouseListener();
		// JFrame janela = new JFrame();
		// janela.setUndecorated(true); //retira a barra da janela
		this.setBorder(new BordaCantoArredondado());
		this.setMinimumSize(new Dimension(250, 100));
		this.setMaximumSize(new Dimension(250, 100));
		JPopupMenu menuContextoItemTarefa = new JPopupMenu();
		JMenuItem opcaoExcluir = new JMenuItem("Excluir");
		menuContextoItemTarefa.add(opcaoExcluir);
		// janela.add(menuContextoItemTarefa);
		this.status = tarefa.getStatus();
		setBackground(new Color(255,255,255));
		setLayout(null);

		JButton btnNomeEtiqueta = new JButton("New button");
		btnNomeEtiqueta.setText(tarefa.getNomeEtiqueta());
		this.nomeEtiquetaTarefa = tarefa.getNomeEtiqueta();
		btnNomeEtiqueta.setBackground(new Color(tarefa.getCorEtiqueta()));
		btnNomeEtiqueta.setBounds(28, 11, 126, 23);
		btnNomeEtiqueta.setBorder(new EmptyBorder(getInsets()));
		this.add(btnNomeEtiqueta);

		JLabel lblTituloTarefa = new JLabel("Titulo");
		Font font = lblTituloTarefa.getFont();
		lblTituloTarefa.setText(tarefa.getTitulo());
		lblTituloTarefa.setBounds(28, 44, 383, 14);
		lblTituloTarefa.setFont(font.deriveFont(font.getStyle() | Font.BOLD));//negrito
		this.add(lblTituloTarefa);

		JLabel lblDescicaoTarefa = new JLabel("Descricao");
		font = lblDescicaoTarefa.getFont();
		lblDescicaoTarefa.setText(tarefa.getDescricao());
		lblDescicaoTarefa.setBounds(28, 69, 383, 14);
		this.add(lblDescicaoTarefa);
		lblDescicaoTarefa.setFont(font.deriveFont(font.getStyle() & ~Font.BOLD));//normal
		this.setVisible(true);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 JPanelItemTarefa panel = (JPanelItemTarefa) e.getSource();
				
				if (e.getButton() == 1) {//clique esquerdo
					FormNovaTarefa novaTarefa = new FormNovaTarefa(tarefa, jFramePrincipal,((JPanelItemTarefa) e.getComponent()).getStatus());
					novaTarefa.setUndecorated(true); // retira a barra da janela
					novaTarefa.setResizable(false); // desabilitar maximar
					novaTarefa.setLocationRelativeTo(null);// alinhar ao centro
					novaTarefa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					novaTarefa.setVisible(true);
				}
				if (e.getButton() == 3) {//clique direito
					id = (int)((JPanelItemTarefa) e.getComponent()).getId(); //pegando o item da lista que foi clicado
					menuContextoItemTarefa.show(e.getComponent(), e.getX(), e.getY());
				}

			}
			
		});

		opcaoExcluir.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir a tarefa " +id+ " ?", "Aviso",
						JOptionPane.INFORMATION_MESSAGE);
				System.out.println(resposta);
				DAO dao = new DAO();
				if (resposta ==  JOptionPane.YES_OPTION) {
					
					try {
						dao.excluirTarefa(id);
//						jFramePrincipal.dispose();
						JFrameDashboard formListaTarefas = new JFrameDashboard();
						//formListaTarefas.setUndecorated(true); // retira a barra da janela
						formListaTarefas.setResizable(false); // desabilitar maximar
						formListaTarefas.setLocationRelativeTo(null);// alinhar ao centro
						formListaTarefas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						formListaTarefas.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		this.setBackground(Color.white);
		
	}

	
	
	//estilização
	
	public int getRoundTopLeft() {
        return roundTopLeft;
    }

    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public int getRoundTopRight() {
        return roundTopRight;
    }

    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    public int getRoundBottomRight() {
        return roundBottomRight;
    }

    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }

    public void setAllRound(int round) {
		this.roundTopLeft = round;
		this.roundTopRight = round;
		this.roundBottomLeft = round;
		this.roundBottomRight = round;
		repaint();
	}
    
    private int roundTopLeft = 0;
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;
	 @Override
	    protected void paintComponent(Graphics grphcs) {
	        Graphics2D g2 = (Graphics2D) grphcs.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setColor(getBackground());
	        Area area = new Area(createRoundTopLeft());
	        if (roundTopRight > 0) {
	            area.intersect(new Area(createRoundTopRight()));
	        }
	        if (roundBottomLeft > 0) {
	            area.intersect(new Area(createRoundBottomLeft()));
	        }
	        if (roundBottomRight > 0) {
	            area.intersect(new Area(createRoundBottomRight()));
	        }
	        g2.fill(area);
	        g2.dispose();
	        super.paintComponent(grphcs);
	    }

	    private Shape createRoundTopLeft() {
	        int width = getWidth();
	        int height = getHeight();
	        int roundX = Math.min(width, roundTopLeft);
	        int roundY = Math.min(height, roundTopLeft);
	        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
	        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
	        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
	        return area;
	    }

	    private Shape createRoundTopRight() {
	        int width = getWidth();
	        int height = getHeight();
	        int roundX = Math.min(width, roundTopRight);
	        int roundY = Math.min(height, roundTopRight);
	        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
	        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
	        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
	        return area;
	    }

	    private Shape createRoundBottomLeft() {
	        int width = getWidth();
	        int height = getHeight();
	        int roundX = Math.min(width, roundBottomLeft);
	        int roundY = Math.min(height, roundBottomLeft);
	        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
	        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
	        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
	        return area;
	    }

	    private Shape createRoundBottomRight() {
	        int width = getWidth();
	        int height = getHeight();
	        int roundX = Math.min(width, roundBottomRight);
	        int roundY = Math.min(height, roundBottomRight);
	        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
	        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
	        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
	        return area;
	    }
}
