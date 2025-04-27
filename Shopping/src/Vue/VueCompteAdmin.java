
package Vue;

import Controleur.ControleurAdmin;
import Controleur.ControleurArticle;
import Modele.Administrateur;
import Vue.createInfoRow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.border.*;

public class VueCompteAdmin extends JPanel {
    private final Color backgroundColor = new Color(220, 223, 197);
    private final Color headerColor = new Color(200, 203, 177);
    private final Color contentColor = new Color(235, 238, 212);
    private JFrame parentFrame;
    private final ControleurAdmin controleurAdmin;
    private Administrateur admin;

    public VueCompteAdmin(JFrame parentFrame, int idAdmin, ControleurAdmin controleur) {
        this.controleurAdmin = controleur;
        try {
            this.admin = controleurAdmin.getAdmin(idAdmin);
            if (this.admin == null) {
                JOptionPane.showMessageDialog(parentFrame,
                        "Erreur: Impossible de charger les informations admin",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Initialisation de l'interface
            initUI();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentFrame,
                    "Erreur lors du chargement du compte admin: " + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColor);

        JPanel titrePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titrePanel.setBackground(backgroundColor);
        JLabel titre = new JLabel("Votre compte : Admin");
        titre.setFont(new Font("Serif", Font.BOLD, 26));
        titrePanel.add(titre);
        mainPanel.add(titrePanel);
        mainPanel.add(Box.createVerticalStrut(15)); // Espace après le titre

        // Panneau supérieur avec info client et bouton commander
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 15); // Espace entre les deux panneaux


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        JPanel infoClientPanel = createInfoAdminPanel();
        topPanel.add(infoClientPanel, gbc);


        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.insets = new Insets(0, 0, 0, 0);

        JButton ajouter = new JButton();
        JButton modifier = new JButton();
        JButton supprimer = new JButton();
        JPanel commanderPanel = createModifsPanelAdmin(ajouter, modifier, supprimer);
        topPanel.add(commanderPanel, gbc);

        // Ajouter le panneau supérieur au panneau principal
        JPanel topPanelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanelContainer.setBackground(backgroundColor);
        topPanelContainer.add(topPanel);
        mainPanel.add(topPanelContainer);
        mainPanel.add(Box.createVerticalStrut(15));

        // Historique des achats centré
        JPanel historiqueContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        historiqueContainer.setBackground(backgroundColor);
        JPanel historiquePanel = createHistoriqueAchatsPanel();
        historiqueContainer.add(historiquePanel);
        mainPanel.add(historiqueContainer);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(new Bottom());

        add(mainPanel, BorderLayout.CENTER);
    }

    // Constructeur sans paramètre pour la compatibilité


    private JPanel createInfoAdminPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 15, 10)); // Disposition en grille pour les informations
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setPreferredSize(new Dimension(420, 350));

        // Titre
        JLabel titreSection = new JLabel("Mes informations", JLabel.CENTER);
        titreSection.setFont(new Font("Arial", Font.BOLD, 16));
        titreSection.setForeground(new Color(51, 85, 140));

        // Ajouter le titre dans un panel séparé qui couvre les deux colonnes
        JPanel titreSectionPanel = new JPanel();
        titreSectionPanel.setBackground(Color.WHITE);
        titreSectionPanel.add(titreSection);

        // Données admin (via controleur)
        String nom = admin.getNomUser();
        String prenom = admin.getPrenomUser();
        String email = admin.getMailUser();

        JLabel nomLabel = new JLabel("Nom:");
        nomLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel nomValue = new JLabel(nom);

        JLabel prenomLabel = new JLabel("Prénom:");
        prenomLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel prenomValue = new JLabel(prenom);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel emailValue = new JLabel(email);

        panel.add(titreSectionPanel);
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.WHITE);
        panel.add(emptyPanel); // Pour que le titre occupe deux cellules

        panel.add(nomLabel);
        panel.add(nomValue);
        panel.add(prenomLabel);
        panel.add(prenomValue);
        panel.add(emailLabel);
        panel.add(emailValue);

        // Ajouter un bouton de modification en bas
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        JButton modifierButton = new JButton("Modifier mes informations");
        modifierButton.setBackground(new Color(216, 232, 173));
        modifierButton.setFocusPainted(false);
        modifierButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(modifierButton);

        // Ajouter le bouton dans un panel séparé qui couvre les deux colonnes
        panel.add(buttonPanel);
        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel2.setBackground(Color.WHITE);
        panel.add(buttonPanel2); // Cellule vide pour compléter la grille

        return panel;
    }

    private JPanel createModifsPanelAdmin(JButton ajouterProduitButton, JButton modifierProduitButton, JButton supprimerProduitButton) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setPreferredSize(new Dimension(200, 280));

        // Créer un peu d'espace vertical pour centrer le contenu
        panel.add(Box.createVerticalGlue());



        // Boutons ajouter modifier et supprimer et stats avec listener
        //JButton ajouterProduitButton = new JButton("Ajouter un Produit");
        ajouterProduitButton.setText("Ajouter un Produit");
        ajouterProduitButton.setBackground(new Color(175, 99, 76));
        ajouterProduitButton.setForeground(Color.WHITE);
        ajouterProduitButton.setFont(new Font("Arial", Font.BOLD, 10));
        ajouterProduitButton.setFocusPainted(false);
        ajouterProduitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        ajouterProduitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ajouterProduitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        ajouterProduitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                VueFicheAjouter vueAjoutP = new VueFicheAjouter();
//                vueAjoutP.setVisible(true);
//            }
//
//        });
        panel.add(ajouterProduitButton);
        panel.add(Box.createVerticalStrut(30));
        //JButton modifierProduitButton = new JButton("Modifier un Produit");
        modifierProduitButton.setText("Modifier un Produit");
        modifierProduitButton.setBackground(new Color(175, 99, 76));
        modifierProduitButton.setForeground(Color.WHITE);
        modifierProduitButton.setFont(new Font("Arial", Font.BOLD, 10));
        modifierProduitButton.setFocusPainted(false);
        modifierProduitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        modifierProduitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        modifierProduitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        modifierProduitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                VueFicheModifier vueModifP = new VueFicheModifier();
//                vueModifP.setVisible(true);
//            }
//
//        });
        panel.add(modifierProduitButton);
        panel.add(Box.createVerticalStrut(30));
        //JButton supprimerProduitButton = new JButton("Supprimer un Produit");
        supprimerProduitButton.setText("Supprimer un Produit");
        supprimerProduitButton.setBackground(new Color(175, 99, 76));
        supprimerProduitButton.setForeground(Color.WHITE);
        supprimerProduitButton.setFont(new Font("Arial", Font.BOLD, 10));
        supprimerProduitButton.setFocusPainted(false);
        supprimerProduitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        supprimerProduitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
       supprimerProduitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        supprimerProduitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                VueFicheSupprimer vueSupP = new VueFicheSupprimer();
//                vueSupP.setVisible(true);
//            }
//
//        });
        panel.add(supprimerProduitButton);
        panel.add(Box.createVerticalStrut(30));

        ControleurArticle controleurArticle = new ControleurArticle();
        controleurArticle.boutonAjouter(ajouterProduitButton);
        controleurArticle.boutonModifier(modifierProduitButton);
        controleurArticle.boutonSupprimer(supprimerProduitButton);

        JButton statButton = new JButton("Vos statistiques");
        statButton.setBackground(new Color(51, 85, 140));
        statButton.setForeground(Color.WHITE);
        statButton.setFont(new Font("Arial", Font.BOLD, 10));
        statButton.setFocusPainted(false);
        statButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        statButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        statButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        statButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VueStatistiques vueStatistiques = new VueStatistiques();
                vueStatistiques.setVisible(true);
            }


        });
        panel.add(statButton);

        // Ajouter un espace vertical pour le centrage
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createHistoriqueAchatsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setPreferredSize(new Dimension(700, 200));

        // En-tête
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(headerColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel headerLabel = new JLabel("Historique des commandes");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(headerLabel);

        panel.add(headerPanel, BorderLayout.NORTH);

        // Contenu du tableau des commandes
        String[] columns = {"N° Commande", "Date", "Produits", "Quantité", "Montant", "Statut"};
        Object[][] data = {
                {"#12345", "01/04/2025", "Biscuits Chocolat", "2", "9.98€", "Expédiée"},
                {"#12345", "01/04/2025", "Thé Earl Grey", "1", "5.50€", "Expédiée"},
                {"#12346", "25/03/2025", "Soda Citron", "6", "12.00€", "Livrée"},
                {"#12346", "25/03/2025", "Biscuits Vanille", "3", "12.99€", "Livrée"},
                {"#12347", "10/03/2025", "Café Arabica", "1", "8.50€", "Livrée"},
                {"#12347", "10/03/2025", "Cookies Pépites", "2", "7.80€", "Livrée"},
                {"#12348", "15/02/2025", "Chocolat Noir", "4", "15.96€", "Livrée"},
                {"#12349", "01/02/2025", "Tisane Bio", "2", "9.98€", "Livrée"}
        };

        // Création du tableau
        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.setGridColor(new Color(200, 200, 200));
        table.getTableHeader().setBackground(new Color(240, 240, 240));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        // Ajout du tableau dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(backgroundColor);

        panel.add(scrollPane, BorderLayout.CENTER);

        // Panneau de recap du total d'achats
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(headerColor);
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        JLabel totalLabel = new JLabel("Total des commandes: 72.71 €");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPanel.add(totalLabel);

        panel.add(totalPanel, BorderLayout.SOUTH);

        return panel;
    }
}


