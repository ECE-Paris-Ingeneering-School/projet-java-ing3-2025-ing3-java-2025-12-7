package Vue;
import DAO.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Modele.Article;
//classe pour gerer les filtres et le tri
public class FiltrerTrier extends JPanel {
    private VueProduitsBiscuits vueBiscuits;
    private JCheckBoxMenuItem filtre1, filtre2, filtre3, reinitialiser;

    public FiltrerTrier() {

        filtre1 = new JCheckBoxMenuItem("Boissons");
        filtre2 = new JCheckBoxMenuItem("Biscuits");
        filtre3 = new JCheckBoxMenuItem("Marque");
        reinitialiser = new JCheckBoxMenuItem("Réinitialiser");
        setLayout(new FlowLayout(FlowLayout.LEFT));


        setMaximumSize(new Dimension(800, 50));
        setBackground(new Color(220, 223, 197));

        // Bouton Filtres
        JButton filtreBouton = new JButton("Filtres");
        filtreBouton.setBackground(new Color(255, 255, 255));
        JPopupMenu sctFiltres = new JPopupMenu();
        sctFiltres.setLayout(new BoxLayout(sctFiltres, BoxLayout.Y_AXIS));
        sctFiltres.setBackground(new Color(255, 255, 255));
        sctFiltres.add(new JLabel("Filtres"));
        JCheckBoxMenuItem filtre1 = new JCheckBoxMenuItem("Boissons");
        JCheckBoxMenuItem filtre2 = new JCheckBoxMenuItem("Biscuits");
        JCheckBoxMenuItem filtre3 = new JCheckBoxMenuItem("Marque");
        JCheckBoxMenuItem reinitialiser = new JCheckBoxMenuItem("Réinitialiser");
        sctFiltres.add(filtre1);
        sctFiltres.add(filtre2);
        sctFiltres.add(filtre3);
        sctFiltres.add(reinitialiser);
        //  menu filtres déroulant
        filtreBouton.addActionListener(e -> sctFiltres.show(filtreBouton, 0, filtreBouton.getHeight()));
        reinitialiser.addActionListener(e -> {
            filtre1.setSelected(false);
            filtre2.setSelected(false);
            filtre3.setSelected(false);
            reinitialiser.setSelected(false);
        });
        /*filtre1.addItemListener(e -> appliquerFiltrage());
        filtre2.addItemListener(e -> appliquerFiltrage());
        filtre3.addItemListener(e -> appliquerFiltrage());*/
        // ComboBox pour le tri
        JComboBox<String> BTrie = new JComboBox<>(new String[]{" ","Prix croissant", "Prix décroissant", "Recommandé","Reinitialiser"});
        BTrie.setBackground(new Color(255, 255, 255));
        BTrie.addActionListener(e -> {
            String selected = (String) BTrie.getSelectedItem();
            if ("Reinitialiser".equals(selected)) {
                BTrie.setSelectedIndex(0);

            }
        });
        add(filtreBouton);
        add(new JLabel("Trier par "));
        add(BTrie);
    }
    public void getFiltre(int x){
        switch(x){
            case 0: //Boissons
                getParent().setVisible(true);
                VueProduitsBoissons boissons = new VueProduitsBoissons();
                break;
            case 1: //Biscuits
                break;
            case 2: //Marque
                break;

        }

    }
    public void getTrie(){

    }
    /*private void appliquerFiltrage() {
        DAOArticle daoArticle = DAOFactory.getInstance("shoppingbd","root","root").getDAOArticle();
        ArrayList<Article> articles = daoArticle.getAll();

        ArrayList<Article> filtres = new ArrayList<>();

        for (Article a : articles) {
            boolean ok = true;

            if (filtre1.isSelected() && !a.getTypeArticle().equals("Boisson")) ok = false;
            if (filtre2.isSelected() && !a.getTypeArticle().equals("Biscuit")) ok = false;
            if (filtre3.isSelected() && !a.getMarqueArticle().equals("")) ok = false;

            if (ok) filtres.add(a);
        }

        vueBiscuits.updateProducts(filtres);
    }*/
}

