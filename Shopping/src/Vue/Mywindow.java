package Vue;

import javax.swing.*;
import java.awt.*;

//classe principale, la fenetre qui contient les JPanel et gere le passage d'un JPanel à un autre
public class Mywindow extends JFrame {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private Top top;
    private Nav nav;

    public Mywindow() {
        super("www.Biscuits&Drinks.co");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);

        // Panel principal avec BoxLayout vertical
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(220, 223, 197));

        // Créer les composants Top et Nav avec référence à cette fenêtre
        top = new Top(this);
        nav = new Nav(this);

        // Panel fixe du haut avec Top et Nav
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(top);
        headerPanel.add(nav);

        // Panel de contenu avec CardLayout pour les différentes vues, le cardlayout nous permet de naviger entre les différents panels
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Ajouter la page d'accueil par défaut
        JPanel homePage = new JPanel();
        homePage.setLayout(new BoxLayout(homePage, BoxLayout.Y_AXIS));
        homePage.add(new FiltrerTrier());
        homePage.add(new ImageP());
        homePage.add(new Bottom());
        contentPanel.add(homePage, "home");

        // Ajouter tous les composants au panel principal
        mainPanel.add(headerPanel);
        mainPanel.add(contentPanel);

        add(mainPanel);
        setVisible(true);
    }

    // Méthode pour ajouter un panel et l'afficher
    public void addAndShowPanel(JPanel panel, String name) {
        boolean exists = false;
        Component[] components = contentPanel.getComponents();
        for (Component comp : components) {
            if (comp.getName() != null && comp.getName().equals(name)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            panel.setName(name);
            contentPanel.add(panel, name);
        }
        cardLayout.show(contentPanel, name);
    }
}