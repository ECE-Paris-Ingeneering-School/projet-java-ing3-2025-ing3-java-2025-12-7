package Vue;

import javax.swing.*;
import java.awt.*;
//classe qui affiche les marques
public class Marques extends JPanel {
    private final Color backgroundColor = new Color(220, 223, 197);

    public Marques() {
        setLayout(new BorderLayout());

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColor);

        // Titre
        JPanel titrePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titrePanel.setBackground(backgroundColor);
        JLabel titre = new JLabel("Nos Marques");
        titre.setFont(new Font("Serif", Font.BOLD, 26));
        titre.setForeground(new Color(128, 154, 61));
        titrePanel.add(titre);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(titrePanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // Conteneur principal pour les marques
        JPanel contentContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentContainer.setBackground(backgroundColor);
        JPanel content = createMarquesPanel();
        contentContainer.add(content);
        mainPanel.add(contentContainer);


        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(new Bottom());

        add(mainPanel, BorderLayout.CENTER);
        setPreferredSize(new Dimension(800, 600));
    }

    private JPanel createMarquesPanel() {
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBackground(backgroundColor);
        containerPanel.setPreferredSize(new Dimension(900, 500));

        JPanel marquesPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        marquesPanel.setBackground(backgroundColor);
        marquesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[][] marques = {
                {"FIZZ", "Shopping/PHOTOS/fizz.jpg"},
                {"Alain Milliat", "Shopping/PHOTOS/milliat.jpg"},
                {"Meneau", "Shopping/PHOTOS/meneau.jpg"},
                {"Buddy", "Shopping/PHOTOS/buddy.jpg"},
                {"Ossa", "Shopping/PHOTOS/ossa.jpg"},
                {"Monin", "Shopping/PHOTOS/monin.jpg"},
                {"Fauchon", "Shopping/PHOTOS/fauchon.jpg"},
                {"Maison Colibri", "Shopping/PHOTOS/colibri.jpg"},
                {"La Sablésienne", "Shopping/PHOTOS/sablesienne.jpg"},
                {"Generous", "Shopping/PHOTOS/generous.jpg"},
                {"KoRo", "Shopping/PHOTOS/koro.jpg"}
        };

        for (String[] marque : marques) {
            JPanel marquePanel = ProductPanelFactory.createMarquePanel(marque[0], marque[1]);
            marquesPanel.add(marquePanel);
        }

        JScrollPane scrollPane = new JScrollPane(marquesPanel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(backgroundColor);

        containerPanel.add(scrollPane, BorderLayout.CENTER);
        return containerPanel;
    }
}
