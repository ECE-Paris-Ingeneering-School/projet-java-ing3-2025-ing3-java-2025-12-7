package Vue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Controleur.ControleurAdmin;
import Controleur.ControleurClient;
import DAO.DAOFactory;
import Modele.User;
import Vue.VueCompteAdmin;



//
//rgb(93, 124, 166) bleu moyen
//rgb(2, 48, 89) bleu foncé
// rgb(115, 162, 191) bleu clair
// rgb(245, 225, 207) beige
//rgb(89, 54, 46) marron
//

//classe qui gere les boutons de navigation dans le site
public class Nav extends JPanel {
    private JButton[] boutons;
    private Mywindow parent;

    public Nav(Mywindow parent) {
        this.parent = parent;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setMaximumSize(new Dimension(800, 50));
        setBackground(new Color(245, 225, 207));
        String[] nomsBoutons = {"Produits", "Boissons", "Biscuits", "Best-Sellers", "Marques", "Nous connaitre"};
        boutons = new JButton[nomsBoutons.length];

        for (int i = 0; i < nomsBoutons.length; i++) {
            boutons[i] = new JButton(nomsBoutons[i]);
            boutons[i].setForeground(new Color(255, 255, 255));
            boutons[i].setBackground(new Color(170, 54, 54));
            final int index = i;
            boutons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    getButtonNav(index);

                    switch(index) {
                        case 0:
                            VueProduits produits = new VueProduits(parent);
                            parent.addAndShowPanel(produits, "produits");
                            break;
                        case 1:
                            VueProduitsBoissons boissons = new VueProduitsBoissons(parent);
                            parent.addAndShowPanel(boissons, "boissons");
                            break;
                        case 2:
                            VueProduitsBiscuits biscuits = new VueProduitsBiscuits(parent);
                            parent.addAndShowPanel(biscuits, "biscuits");
                            break;
                        case 3:
                            VueBestSellers bestSellers = new VueBestSellers(parent);
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
            boutons[i].setBackground(new Color(115, 162, 191));
        }

        // Met en surbrillance le bouton cliqué
        if (y >= 0 && y < boutons.length) {
            boutons[y].setBackground(new Color(2, 48, 89));
        }
    }
}