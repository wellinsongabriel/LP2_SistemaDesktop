package br.com.desktop.model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ObjectListPanel extends JPanel {

    private ArrayList<Tarefa> tarefas;

    public ObjectListPanel(ArrayList<Tarefa> tarefas) {
        this.tarefas = tarefas;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // define o layout do painel
        setMinimumSize(new Dimension(800, 465));
        setMaximumSize(new Dimension(800, 465));
        setPreferredSize(new Dimension(800, 465));
        setBorder(new BordaCantoArredondado());
		

        // itera sobre a lista de objetos e adiciona um painel para cada objeto
        for (Tarefa tarefa : tarefas) {
            JPanel objectPanel = new JPanel();
            objectPanel.setLayout(new BorderLayout());
            objectPanel.setMinimumSize(new Dimension(250, 300));
            objectPanel.setMaximumSize(new Dimension(250, 300));
            objectPanel.setBorder(new BordaCantoArredondado());
            objectPanel.setBounds(10, 31, 250, 465);
            //objectPanel.setBounds(10, 31, 230, 97);

            // adicione componentes de Swing ao painel para exibir as informações do objeto
            JLabel nameLabel = new JLabel(tarefa.getTitulo());
            objectPanel.add(nameLabel, BorderLayout.WEST);

            JLabel valueLabel = new JLabel(tarefa.getDescricao());
            objectPanel.add(valueLabel, BorderLayout.EAST);

            add(objectPanel); // adiciona o painel ao painel principal
        }
    }

    // método para atualizar a lista de objetos exibida no painel
    public void setObjects(ArrayList<Tarefa> tarefas) {
        this.tarefas = tarefas;
        removeAll(); // remove todos os componentes do painel
        // itera sobre a nova lista de objetos e adiciona um painel para cada objeto
        for (Tarefa tarefa : tarefas) {
            // código similar ao construtor
        }
        revalidate(); // atualiza a interface do usuário
        repaint();
    }
}
