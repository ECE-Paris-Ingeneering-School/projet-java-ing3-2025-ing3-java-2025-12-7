package Vue;
import javax.swing.*;
import java.awt.*;


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
        this.utilisateur.setBackground(new Color(255, 255, 255));
        this.panier = new JButton("🛒");
        this.panier.setBackground(new Color(255, 255, 255));



        icons.add(utilisateur);
        icons.add(panier);

        add(nomSite, BorderLayout.WEST);
        add(BarreRecherche, BorderLayout.CENTER);
        add(icons, BorderLayout.EAST);
    }
    public JButton getIconTop(int x){
        switch(x){
            case 0 :
                utilisateur.setBackground(new Color(220, 223, 197));
                panier.setBackground(new Color(255, 255, 255));
                return utilisateur;

            case 1 :
                panier.setBackground(new Color(220, 223, 197));
                utilisateur.setBackground(new Color(255, 255, 255));
                return panier;

        }

        return null;
    }
}

