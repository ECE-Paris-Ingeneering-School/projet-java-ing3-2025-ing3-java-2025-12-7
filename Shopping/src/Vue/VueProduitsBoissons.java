
package Vue;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class VueProduitsBoissons extends JFrame {
    private final Color backgroundColor = new Color(220, 223, 197);

    public VueProduitsBoissons() {
        setLayout(new BorderLayout());
        setSize(800, 600);
        // Panel principal avec disposition verticale
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColor);

        // Ajout des différentes sections

        mainPanel.add(new Top());
        mainPanel.add(new Nav());
        mainPanel.add(new FiltrerTrier());

        // Titre centré avec marge
        JPanel titrePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titrePanel.setBackground(backgroundColor);
        JLabel titre = new JLabel("Nos Boissons");
        titre.setFont(new Font("Serif", Font.BOLD, 26));
        titre.setForeground(new Color(0, 102, 59));
        titrePanel.add(titre);
        mainPanel.add(titrePanel);
        mainPanel.add(Box.createVerticalStrut(15)); // Espace après le titre

        // Conteneur principal pour le contenu, centré
        JPanel contentContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentContainer.setBackground(backgroundColor);

        // Panel de contenu avec insets
        JPanel content = createBoissonsPanel();
        contentContainer.add(content);
        mainPanel.add(contentContainer);

        // Ajouter un peu d'espace avant le footer
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(new Bottom());

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createBoissonsPanel() {
        // Conteneur principal
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBackground(backgroundColor);
        containerPanel.setPreferredSize(new Dimension(900, 500));

        // Panel qui contiendra tous les produits
        JPanel productsPanel = new JPanel();
        productsPanel.setLayout((new GridLayout(0, 3, 20, 20)));
        productsPanel.setBackground(backgroundColor);
        productsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Données des produits avec leur prix unitaire
        Object[][] produits = {
                {"Thé Glacé", "Boisson rafraîchissante au thé noir et citron", 2.50, "the_glace"},
                {"Smoothie Fraise", "Smoothie aux fraises fraîches et yaourt", 3.80, "smoothie_fraise"},
                {"Café Latte", "Café doux au lait entier", 2.90, "cafe_latte"},
                {"Jus d'Orange", "Pur jus d'orange pressé", 2.70, "jus_orange"},
                {"Limonade Artisanale", "Limonade pétillante au citron naturel", 3.20, "limonade_artisanale"},
                {"Eau Pétillante", "Eau gazeuse naturelle", 1.50, "eau_petillante"},
                {"Chocolat Chaud", "Boisson chaude au chocolat noir", 3.00, "chocolat_chaud"},
                {"Matcha Latte", "Boisson japonaise au thé vert matcha", 3.60, "matcha_latte"},
                {"Bubble Tea", "Thé au lait avec perles de tapioca", 4.50, "bubble_tea"}
        };

        // Ajout de chaque produit dans son propre cadre
        for (Object[] produit : produits) {
            JPanel productPanel = ProductPanelFactory.createProductPanel((String)produit[0], (String)produit[1], (double)produit[2], (String)produit[3]);
            productsPanel.add(productPanel);

        }

        // ScrollPane pour défiler
        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        scrollPane.setBackground(backgroundColor);
        containerPanel.add(scrollPane, BorderLayout.CENTER);

        return containerPanel;
    }



    private ImageIcon createProductIcon(String imageKey) {
        int width = 150;
        int height = 120;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return null;
    }

}