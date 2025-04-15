package Vue;
import javax.swing.*;
import java.awt.*;
//classe pour gerer les filtres et le tri
public class FiltrerTrier extends JPanel {

    public FiltrerTrier() {
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
        JCheckBoxMenuItem filtre1 = new JCheckBoxMenuItem("Filtre 1");
        JCheckBoxMenuItem filtre2 = new JCheckBoxMenuItem("Filtre 2");
        JCheckBoxMenuItem filtre3 = new JCheckBoxMenuItem("Filtre 3");
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
    public void getFiltre(){

    }
    public void getTrie(){

    }
}

