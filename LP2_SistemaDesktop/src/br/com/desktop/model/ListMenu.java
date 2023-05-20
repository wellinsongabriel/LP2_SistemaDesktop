package br.com.desktop.model;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

import br.com.desktop.dao.FacadeDAO;
import br.com.desktop.view.JFrameLogin;
import br.com.desktop.view.JFrameDashboard;
import br.com.desktop.view.JPanelBackup;
import br.com.desktop.view.JPanelGerenciaUsuario;
import br.com.desktop.view.JPanelNovoProjeto;
import br.com.desktop.view.JPanelRelatorio;
import br.com.desktop.view.JPanelListaTarefas;

@SuppressWarnings("serial")
public class ListMenu<E extends Object> extends JList<E> {

	@SuppressWarnings("rawtypes")
	private final DefaultListModel model;
	private int selectedIndex = -1;
	private int overIndex = -1;
	private EventMenuSelected event;
	private JPanel mainPanel;
	@SuppressWarnings("unused")
	private Projeto projeto;

	public void addEventMenuSelected(EventMenuSelected event) {
		this.event = event;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ListMenu(JPanel mainPanel, JFrameDashboard jFrame, Usuario usuarioLogado) {
		this.mainPanel = mainPanel;
		model = new DefaultListModel();
		setModel(model);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					int index = locationToIndex(me.getPoint());
					Object o = model.getElementAt(index);
					if (o instanceof ModelMenu) {
						ModelMenu menu = (ModelMenu) o;

						if (menu.getNome().equalsIgnoreCase("Sair")) {
							if (JOptionPane.showConfirmDialog(getComponentPopupMenu(), "Deseja sair do sistema ?",
									"Sair", 0) == 0) {
								System.exit(1);
							}
						}

						if (menu.getNome().equalsIgnoreCase("Novo projeto")) {
							exibirProjeto(jFrame, usuarioLogado);
						}

						FacadeDAO facadeDAO = new FacadeDAO();

						ArrayList<Projeto> projetos = new ArrayList<>();
						try {
							projetos = facadeDAO.listarProjetos();
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (menu.getNome().equalsIgnoreCase("Relatório")) {
							exibirRelatorio(jFrame, usuarioLogado);
						}

						if (menu.getNome().equalsIgnoreCase("Backup")) {
							exibirBackup(jFrame, usuarioLogado);
						}
						
						if (menu.getNome().equalsIgnoreCase("Usuários")) {
							exibirUsuarios(jFrame, usuarioLogado);
						}
						
						if (menu.getNome().equalsIgnoreCase("Sobre")) {
							JFrame jframe = new JFrame();
							jframe.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(JFrameLogin.class.getResource("/br/com/desktop/image/logoTaskMaster.png")));
							jframe.setLocationRelativeTo(null);
							jframe.setSize(300,400);
							jframe.setVisible(true);
						}

						for (Projeto projeto : projetos) {
							if (projeto.getId() == menu.getIdentificador()) {
								exibirTarefas(jFrame, usuarioLogado, projeto);
								break;
							}
						}

						if (menu.getTipo() == ModelMenu.TipoMenu.MENU) {
							selectedIndex = index;
							if (event != null) {
								event.selected(index);
							}
						}
					} else {
						selectedIndex = index;
					}
					repaint();
				}
			}

			@Override
			public void mouseExited(MouseEvent me) {
				overIndex = -1;
				repaint();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent me) {
				int index = locationToIndex(me.getPoint());
				if (index != overIndex) {
					Object o = model.getElementAt(index);
					if (o instanceof ModelMenu) {
						ModelMenu menu = (ModelMenu) o;
						if (menu.getTipo() == ModelMenu.TipoMenu.MENU) {
							overIndex = index;
						} else {
							overIndex = -1;
						}
						repaint();
					}
				}
			}
		});
	}

	@Override
	public ListCellRenderer<? super E> getCellRenderer() {
		return new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean selected,
					boolean focus) {
				ModelMenu data;
				if (o instanceof ModelMenu) {
					data = (ModelMenu) o;
				} else {
					data = new ModelMenu("", o + "", ModelMenu.TipoMenu.VAZIO);
				}
				MenuItem item = new MenuItem(data);
				item.setSelected(selectedIndex == index);
				item.setOver(overIndex == index);
				return item;
			}

		};
	}

	@SuppressWarnings("unchecked")
	public void addItem(ModelMenu data) {
		model.addElement(data);
	}

	@SuppressWarnings("unchecked")
	public void addItem(ModelMenu data, Projeto projeto) {
		model.addElement(data);
		this.projeto = projeto;
	}

	public void exibirProjeto(JFrameDashboard jFrame, Usuario usuarioLogado) {
		mainPanel.removeAll();
		new JPanelNovoProjeto(mainPanel, jFrame, usuarioLogado);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	private void exibirTarefas(JFrameDashboard jFrame, Usuario usuarioLogado, Projeto projeto) {
		mainPanel.removeAll();
		new JPanelListaTarefas(mainPanel, jFrame, usuarioLogado, projeto);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	public void exibirRelatorio(JFrameDashboard jFrame, Usuario usuarioLogado) {
		mainPanel.removeAll();
		new JPanelRelatorio(mainPanel, jFrame, usuarioLogado);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	private void exibirBackup(JFrameDashboard jFrame, Usuario usuarioLogado) {
		mainPanel.removeAll();
		new JPanelBackup(mainPanel, jFrame, usuarioLogado);
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	private void exibirUsuarios(JFrameDashboard jFrame, Usuario usuarioLogado) {
		mainPanel.removeAll();
		new JPanelGerenciaUsuario(mainPanel, jFrame, usuarioLogado);
		mainPanel.revalidate();
		mainPanel.repaint();
	}
}