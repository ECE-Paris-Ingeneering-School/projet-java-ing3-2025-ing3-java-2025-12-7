package Vue;

import Controleur.Session;
import Modele.User;

import javax.swing.*;
import java.awt.*;

//classe principale, la fenetre qui contient les JPanel et gere le passage d'un JPanel à un autre
public class Mywindow extends JFrame {
    private User currentUser;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private Top top;
    private Nav nav;

    public Mywindow(){
        this(Session.getCurrentUser()); // Utilise la session
    }
    public Mywindow(User user) {
        super("www.Biscuits&Drinks.co");
        this.currentUser = user;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);

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

    public  User getCurrentUser(){
        return this.currentUser;
    }

    // Méthode pour ajouter un panel et l'afficher
    public void addAndShowPanel(JPanel panel, String name) {
//        boolean exists = false;
//        Component[] components = contentPanel.getComponents();
//        for (Component comp : components) {
//            if (comp.getName() != null && comp.getName().equals(name)) {
//                exists = true;
//                break;
//            }
//        }
//        if (!exists) {
//            panel.setName(name);
//            contentPanel.add(panel, name);
//        }
//        cardLayout.show(contentPanel, name);


        // Supprimer l'ancien panel s'il existe déjà
        Component[] components = contentPanel.getComponents();
        for (Component comp : components) {
            if (comp.getName() != null && comp.getName().equals(name)) {
                contentPanel.remove(comp);
                break;
            }
        }

        // Ajouter le nouveau panel
        panel.setName(name);
        contentPanel.add(panel, name);

        // Afficher le panel avec CardLayout
        cardLayout.show(contentPanel, name);

        // Rafraîchir l'affichage
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}