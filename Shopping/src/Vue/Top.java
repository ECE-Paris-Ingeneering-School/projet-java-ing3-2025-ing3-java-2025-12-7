package Vue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//classe qui gere la barre de recherche et les icones
public class Top extends JPanel {
    public JButton utilisateur;
    public JButton panier;
    private Mywindow parent;

    public Top(Mywindow parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(800, 50));
        setBackground(new Color(245, 225, 207));
        JLabel nomSite = new JLabel("Biscuits&Drinks.co", JLabel.LEFT);
        nomSite.setForeground(Color.BLACK);

        JTextField BarreRecherche = new JTextField("Rechercher");
        BarreRecherche.setEditable(true);

        JPanel icons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        icons.setBackground(new Color(245, 225, 207));
        this.utilisateur = new JButton("👤");
        this.utilisateur.setBackground(new Color(255, 255, 255));
        this.panier = new JButton("🛒");
        this.panier.setBackground(new Color(255, 255, 255));

        utilisateur.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getIconTop(0);
                //VueCompteClient pageClient = new VueCompteClient(parent,1);
                //parent.addAndShowPanel(pageClient, "compteClient");
                VueCompteAdmin pageAdmin = new VueCompteAdmin();
                parent.addAndShowPanel(pageAdmin, "compteAdmin");
            }
        });

        panier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getIconTop(1);
                VuePanier pagePanier = new VuePanier();
                parent.addAndShowPanel(pagePanier, "panier");
            }
        });

        // evenement pour revenir à l'accueil
        nomSite.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Réinitialiser tous les boutons
                getIconTop(-1);
                parent.addAndShowPanel(new JPanel(), "home");
            }
        });

        icons.add(utilisateur);
        icons.add(panier);

        add(nomSite, BorderLayout.WEST);
        add(BarreRecherche, BorderLayout.CENTER);
        add(icons, BorderLayout.EAST);
    }

    public JButton getIconTop(int x) {
        // Réinitialiser tous les boutons
        utilisateur.setBackground(new Color(255, 255, 255));
        panier.setBackground(new Color(255, 255, 255));

        switch(x) {
            case 0:
                utilisateur.setBackground(new Color(115, 162, 191));
                return utilisateur;
            case 1:
                panier.setBackground(new Color(115, 162, 191));
                return panier;
            default:
                return null;
        }
    }
}
