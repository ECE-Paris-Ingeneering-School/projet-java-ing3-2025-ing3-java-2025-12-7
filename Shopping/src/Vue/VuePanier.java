package Vue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VuePanier extends JFrame{
    public VuePanier() {
        super("Panier - Biscuits&Drinks.co");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal avec disposition verticale
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(220, 223, 197));
        // Ajout des différentes sections
        Top topVCC = new Top();
        topVCC.getIconTop(1);
        mainPanel.add(topVCC);
        mainPanel.add(new Nav());


        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(220, 223, 197));

        JLabel titre = new JLabel("Votre Panier");
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titre , BorderLayout.CENTER);
        content.setMaximumSize(new Dimension(700, 300));
        content.setPreferredSize(new Dimension(680, 250));
        JPanel infoPanier = createInfoPanierPanel();
        JPanel infosPanier = createInfoPanier();
        content.add(infosPanier, BorderLayout.CENTER);
        content.add(infoPanier, BorderLayout.EAST);


        mainPanel.add(content);
        mainPanel.add(new Bottom());
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);

    }

    private JPanel createInfoPanierPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Ma commande"));
        panel.setPreferredSize(new Dimension(300, 350)); // Hauteur augmentée

        // Information de commande
        panel.add(createInfoRow("Sous total:", "XXX"));
        panel.add(createInfoRow("Nombre de produits:", "12"));
        panel.add(Box.createVerticalStrut(20));

        // Connexion
        JPanel connexionPanel = new JPanel();
        connexionPanel.setLayout(new BoxLayout(connexionPanel, BoxLayout.Y_AXIS));
        connexionPanel.setBackground(Color.WHITE);

        JLabel messageConnexion = new JLabel("Veuillez vous connecter pour commander");
        messageConnexion.setAlignmentX(Component.CENTER_ALIGNMENT);
        connexionPanel.add(messageConnexion);

        JButton connectButton = new JButton("Se Connecter");
        connectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        connectButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                VueConnexion connexion = new VueConnexion();

            }
        });
        connexionPanel.add(connectButton);

        connexionPanel.add(Box.createVerticalStrut(10));

        // Inscription
        JLabel messageInscription = new JLabel("Vous n'êtes pas encore inscrit ? ");
        messageInscription.setAlignmentX(Component.CENTER_ALIGNMENT);
        connexionPanel.add(messageInscription);

        JButton inscriptionButton = new JButton("S'inscrire");
        inscriptionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        inscriptionButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                VueConnexion connexion = new VueConnexion();
            }
        });
        connexionPanel.add(inscriptionButton);

        connexionPanel.add(Box.createVerticalStrut(20));

        JButton validerButton = new JButton("VALIDER MA COMMANDE");
        validerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        validerButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //Popup popupMessage = new Popup("Votre commande a été validée");
            }
        });
        connexionPanel.add(validerButton);
        panel.add(connexionPanel);

        return panel;
    }
    private JPanel createInfoPanier() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(200, 203, 177));
        panel.setPreferredSize(new Dimension(600, 300));

        JPanel headerPanel = new JPanel(new GridLayout(1, 5));
        headerPanel.setBackground(new Color(200, 203, 177));

        String[] Infos = {"Photo","Nom du produit", "Quantite", "Prix", "Description"};
        for (String info : Infos) {
            JLabel label = new JLabel(info, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            headerPanel.add(label);
        }
        panel.add(headerPanel, BorderLayout.NORTH);

        // Zone de contenu pour les produits
        JPanel productsPanel = new JPanel();
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
        productsPanel.setBackground(new Color(220, 223, 197));
        String[][] produits = {
                {"","Cookies chocolat", "2", "5.99 €", "Cookies aux pépites de chocolat"},
                {"","Sablés", "1", "4.50 €", "Sablés traditionnels"},
                {"","Thé Earl Grey", "1", "7.25 €", "Thé noir aromatisé à la bergamote"},
                {"","Café Arabica", "3", "8.99 €", "Café en grains, torréfaction moyenne"},
        };

        // Panneau de chaque produit
        for (String[] produit : produits) {
            JPanel produitPanel = new JPanel(new GridLayout(1, 5));
            produitPanel.setBackground(new Color(235, 238, 212));
            produitPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
            produitPanel.setMaximumSize(new Dimension(450, 30));
            produitPanel.setPreferredSize(new Dimension(450, 30));

            JLabel imageLabel = new JLabel(produit[0], SwingConstants.CENTER);
            JLabel nomLabel = new JLabel(produit[1], SwingConstants.CENTER);
            JLabel quantiteLabel = new JLabel(produit[2], SwingConstants.CENTER);
            JLabel prixLabel = new JLabel(produit[3], SwingConstants.CENTER);
            JLabel descriptionLabel = new JLabel(produit[4], SwingConstants.CENTER);

            produitPanel.add(imageLabel);
            produitPanel.add(nomLabel);
            produitPanel.add(quantiteLabel);
            produitPanel.add(prixLabel);
            produitPanel.add(descriptionLabel);

            productsPanel.add(produitPanel);
            productsPanel.add(Box.createVerticalStrut(5));
        }
        // ScrollPane pour défiler si jamais il y a plusieurs produits
        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(220, 223, 197));

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;

    }
    // Panel pour les infos total de la commande
    private JPanel createInfoRow(String label, String value) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.setBackground(Color.WHITE);
        JLabel labelComp = new JLabel(label);
        labelComp.setPreferredSize(new Dimension(100, 25));
        labelComp.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel valueComp = new JLabel(value);
        valueComp.setFont(new Font("Arial", Font.PLAIN, 12));
        row.add(labelComp);
        row.add(valueComp);

        return row;
    }
}