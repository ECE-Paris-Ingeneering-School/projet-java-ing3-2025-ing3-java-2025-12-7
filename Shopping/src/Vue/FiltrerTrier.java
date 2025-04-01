package Vue;
import javax.swing.*;
import java.awt.*;

public class FiltrerTrier extends JPanel {
    public FiltrerTrier() {
        setLayout(new FlowLayout(FlowLayout.LEFT));


        setMaximumSize(new Dimension(800, 50));
        setBackground(new Color(230, 230, 230));

        // Bouton Filtres
        JButton filtreBouton = new JButton("Filtres");
        JPopupMenu sctFiltres = new JPopupMenu();
        sctFiltres.setLayout(new BoxLayout(sctFiltres, BoxLayout.Y_AXIS));
        sctFiltres.add(new JLabel("Filtres"));
        JCheckBoxMenuItem filtre1 = new JCheckBoxMenuItem("Filtre 1");
        JCheckBoxMenuItem filtre2 = new JCheckBoxMenuItem("Filtre 2");
        JCheckBoxMenuItem filtre3 = new JCheckBoxMenuItem("Filtre 3");
        sctFiltres.add(filtre1);
        sctFiltres.add(filtre2);
        sctFiltres.add(filtre3);

        //  menu filtres déroulant
        filtreBouton.addActionListener(e -> sctFiltres.show(filtreBouton, 0, filtreBouton.getHeight()));

        // ComboBox pour le tri
        JComboBox<String> BTrie = new JComboBox<>(new String[]{" ","Prix croissant", "Prix décroissant", "Recommandé"});

        add(filtreBouton);
        add(new JLabel("Trier par "));
        add(BTrie);
    }

}

