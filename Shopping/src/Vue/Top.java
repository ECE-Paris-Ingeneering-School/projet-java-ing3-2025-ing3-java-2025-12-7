package Vue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Top extends JPanel {
    public JButton utilisateur;
    public JButton panier;
    public Top() {

        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(800, 50));
        setBackground(new Color(51, 85, 140));
        JLabel nomSite = new JLabel("Biscuits&Drinks.co", JLabel.LEFT);
        nomSite.setForeground(Color.WHITE);

        JTextField BarreRecherche = new JTextField("Rechercher");
        BarreRecherche.setEditable(true);

        JPanel icons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        icons.setBackground(new Color(51, 85, 140));
        this.utilisateur = new JButton("👤");
        this.panier = new JButton("🛒");


        utilisateur.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getIconTop(0);// Change la couleur de l'icône utilisateur
                VueCompteClient pageClient = new VueCompteClient();
            }
        });
        panier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getIconTop(1); // Change la couleur de l'icône panier
                VuePanier pagePanier = new VuePanier();

            }
        });
        icons.add(utilisateur);
        icons.add(panier);

        add(nomSite, BorderLayout.WEST);
        add(BarreRecherche, BorderLayout.CENTER);
        add(icons, BorderLayout.EAST);
    }
    public void getIconTop(int x){
        switch(x){
            case 0 :
                utilisateur.setBackground(new Color(220, 223, 197));
                panier.setBackground(new Color(255, 255, 255));
                break;
            case 1 :
                panier.setBackground(new Color(220, 223, 197));
                utilisateur.setBackground(new Color(255, 255, 255));
                break;

        }

    }
}

