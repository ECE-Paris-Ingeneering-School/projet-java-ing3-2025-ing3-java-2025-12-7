package Vue;
import javax.swing.*;
import java.awt.*;

public class Nav extends JPanel{
    private String[] boutons;
    public Nav() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setMaximumSize(new Dimension(800, 50));
        setBackground(new Color(51, 85, 140));
        this.boutons = new String[]{"Produits", "Boissons", "Biscuits", "Best-Sellers", "Marques", "Nous connaitre"};
        for (String texte : boutons) {
            add(new JButton(texte));
        }
    }
    public void getButtonNav(int y){

    }
}

