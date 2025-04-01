package Vue;
import javax.swing.*;
import java.awt.*;

public class Top extends JPanel {
    public Top() {
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(800, 50));
        setBackground(new Color(0, 0, 0));
        JLabel nomSite = new JLabel("Biscuits&Drinks.co", JLabel.LEFT);
        nomSite.setForeground(Color.WHITE);

        JTextField BarreRecherche = new JTextField("Rechercher");


        JPanel icons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        icons.setBackground(new Color(0, 0, 0));
        JButton utilisateur = new JButton("👤");
        JButton panier = new JButton("🛒");
        icons.add(utilisateur);
        icons.add(panier);

        add(nomSite, BorderLayout.WEST);
        add(BarreRecherche, BorderLayout.CENTER);
        add(icons, BorderLayout.EAST);
    }
}

