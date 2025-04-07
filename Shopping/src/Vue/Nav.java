package Vue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Nav extends JPanel{
    private JButton[] boutons;
    public Nav() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setMaximumSize(new Dimension(800, 50));
        setBackground(new Color(51, 85, 140));
        String[] nomsBoutons = {"Produits", "Boissons", "Biscuits", "Best-Sellers", "Marques", "Nous connaitre"};
        boutons = new JButton[nomsBoutons.length];

        for (int i = 0; i < nomsBoutons.length; i++) {
            boutons[i] = new JButton(nomsBoutons[i]);
            boutons[i].setBackground(new Color(255, 255, 255));
            final int index = i; // variable finale pour usage dans la classe anonyme
            boutons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    getButtonNav(index);
                    if (index == 5){
                        NousConnaitre nousConnaitre = new NousConnaitre();
                    }
                }
            });

            add(boutons[i]);

        }

    }
    public void getButtonNav(int y){
        for (int i = 0; i < boutons.length; i++) {
            boutons[i].setBackground(new Color(255, 255, 255));
        }

        // Met en surbrillance le bouton cliqué
        if (y >= 0 && y < boutons.length) {
            boutons[y].setBackground(new Color(220, 223, 197));
        }
    }





}

