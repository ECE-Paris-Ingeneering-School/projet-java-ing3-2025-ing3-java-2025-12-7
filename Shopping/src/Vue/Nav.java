package Vue;
import javax.swing.*;
import java.awt.*;

public class Nav extends JPanel{
    public Nav() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setMaximumSize(new Dimension(800, 50));
        setBackground(new Color(50, 50, 50));
        String[] boutons = {"Produits", "Boissons", "Biscuits", "Best-Sellers", "Marques", "Nous connaitre"};
        for (String texte : boutons) {
            add(new JButton(texte));
        }
    }
}

