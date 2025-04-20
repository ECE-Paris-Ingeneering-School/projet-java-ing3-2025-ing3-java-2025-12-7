package Vue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//classe qui gere les boutons de navigation dans le site
public class Nav extends JPanel {
    private JButton[] boutons;
    private Mywindow parent;

    public Nav(Mywindow parent) {
        this.parent = parent;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setMaximumSize(new Dimension(800, 50));
        setBackground(new Color(51, 85, 140));
        String[] nomsBoutons = {"Produits", "Boissons", "Biscuits", "Best-Sellers", "Marques", "Nous connaitre"};
        boutons = new JButton[nomsBoutons.length];

        for (int i = 0; i < nomsBoutons.length; i++) {
            boutons[i] = new JButton(nomsBoutons[i]);
            boutons[i].setBackground(new Color(255, 255, 255));
            final int index = i;
            boutons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    getButtonNav(index);

                    switch(index) {
                        case 0:
                            VueProduits produits = new VueProduits();
                            parent.addAndShowPanel(produits, "produits");
                            break;
                        case 1:
                            VueProduitsBoissons boissons = new VueProduitsBoissons();
                            parent.addAndShowPanel(boissons, "boissons");
                            break;
                        case 2:
                            VueProduitsBiscuits biscuits = new VueProduitsBiscuits();
                            parent.addAndShowPanel(biscuits, "biscuits");
                            break;
                        case 3:
                            VueBestSellers bestSellers = new VueBestSellers();
                            parent.addAndShowPanel(bestSellers, "bestSellers");
                            break;
                        case 4:
                            Marques marques = new Marques();
                            parent.addAndShowPanel(marques, "marques");
                            break;
                        case 5:
                            NousConnaitre nousConnaitre = new NousConnaitre();
                            parent.addAndShowPanel(nousConnaitre, "nousConnaitre");
                            break;
                    }
                }
            });
            add(boutons[i]);
        }
    }

    public void getButtonNav(int y) {
        // Réinitialiser tous les boutons
        for (int i = 0; i < boutons.length; i++) {
            boutons[i].setBackground(new Color(255, 255, 255));
        }

        // Met en surbrillance le bouton cliqué
        if (y >= 0 && y < boutons.length) {
            boutons[y].setBackground(new Color(220, 223, 197));
        }
    }
}