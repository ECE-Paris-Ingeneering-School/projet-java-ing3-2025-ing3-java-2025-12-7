package Vue;
import Controleur.ControleurPanier;
import DAO.DAOArticle;
import DAO.DAOFactory;
import DAO.DAOPanier;
import Modele.Article;
import Modele.Panier;
import Modele.User;
import Vue.createInfoRow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.Arrays;

public class VuePanier extends JPanel {
    private final Color backgroundColor = new Color(220, 223, 197);
    private final Color headerColor = new Color(200, 203, 177);
    private final Color productColor = new Color(235, 238, 212);
    private JPanel productsContainer;
    private JLabel totalLabel;
    private double totalPrice = 0.0;
    private int nbProducts = 0;
    private createInfoRow sousTotalRow;
    private createInfoRow nbProduitsRow;
    private Mywindow parent;

    public VuePanier(Mywindow parent) {
        setLayout(new BorderLayout());
        this.parent = parent;
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColor);

        // Titre centré avec marge
        JPanel titrePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titrePanel.setBackground(backgroundColor);
        JLabel titre = new JLabel("Votre Panier");
        titre.setFont(new Font("Serif", Font.BOLD, 26));
        titrePanel.add(titre);
        mainPanel.add(titrePanel);
        mainPanel.add(Box.createVerticalStrut(15)); // Espace après le titre

        // Conteneur principal pour le contenu
        JPanel contentContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentContainer.setBackground(backgroundColor);
        JPanel content = new JPanel(new BorderLayout(10, 0)); // Espacement horizontal entre les panneaux
        content.setBackground(backgroundColor);
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        content.setMaximumSize(new Dimension(900, 400));
        content.setPreferredSize(new Dimension(900, 400));

        // Création des panneaux d'information
        JPanel infoPanier = createInfoPanierPanel();
        JPanel infosPanier = createInfoPanier(parent);




        content.add(infosPanier, BorderLayout.CENTER);
        content.add(infoPanier, BorderLayout.EAST);

        contentContainer.add(content);
        mainPanel.add(contentContainer);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(new Bottom());

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createInfoPanierPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setPreferredSize(new Dimension(300, 400));

        // Titre de la section
        JLabel titreSection = new JLabel("Ma commande");
        titreSection.setFont(new Font("Arial", Font.BOLD, 16));
        titreSection.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titreSection);
        panel.add(Box.createVerticalStrut(80));//

        // Information de commande
        sousTotalRow = new createInfoRow("Sous total:", String.format("%.2f €", totalPrice));
        panel.add(sousTotalRow);

        nbProduitsRow= new createInfoRow("Nombre de produits:", String.valueOf(nbProducts));
        panel.add(nbProduitsRow);
        panel.add(Box.createVerticalStrut(20));

        // Séparateur
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(280, 1));
        panel.add(separator);
        panel.add(Box.createVerticalStrut(15));


        panel.add(Box.createVerticalStrut(25));

        // Bouton de validation
        JButton validerButton = new JButton("VALIDER MA COMMANDE");
        validerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        validerButton.setBackground(new Color(76, 175, 80));
        validerButton.setForeground(Color.WHITE);
        validerButton.setFont(new Font("Arial", Font.BOLD, 14));
        validerButton.setFocusPainted(false);
        validerButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        validerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        validerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showConfirmationPopup();
            }
        });
        panel.add(validerButton);

        return panel;
    }

    private JPanel createInfoPanier(Mywindow parent) {
        this.parent = parent;
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setPreferredSize(new Dimension(580, 400));

        // En-tête
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(headerColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel headerLabel = new JLabel("Produits dans votre panier");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(headerLabel);

        panel.add(headerPanel, BorderLayout.NORTH);

        // Zone de contenu pour les produits
        productsContainer = new JPanel();
        productsContainer.setLayout(new BoxLayout(productsContainer, BoxLayout.Y_AXIS));
        productsContainer.setBackground(backgroundColor);
        productsContainer.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));


        // Panneau de sous-total en bas
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(headerColor);
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        totalLabel = new JLabel(String.format("Total: %.2f €", totalPrice));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPanel.add(totalLabel);

        panel.add(totalPanel, BorderLayout.SOUTH);

//        // Données des produits avec leur prix unitaire
//        Object[][] produits = {
//                {"Cookies chocolat", "Cookies aux pépites de chocolat", 2, 5.99},
//                {"Sablés", "Sablés traditionnels", 1, 4.50},
//                {"Thé Earl Grey", "Thé noir aromatisé à la bergamote", 1, 7.25},
//                {"Café Arabica", "Café en grains, torréfaction moyenne", 3, 8.99},
//        };
//
//        // Calculer le prix total initial et nombre de produits
//        totalPrice = 0;
//        nbProducts = 0;
//
//        for (Object[] produit : produits) {
//            totalPrice += (int)produit[2] * (double)produit[3];
//            nbProducts += (int)produit[2];
//        }

        // Récupérer tous les produits depuis la base de données
        DAOFactory daoFactory = DAOFactory.getInstance("shoppingbd","root","root");

        // Utiliser DAOFactory pour obtenir une instance de DAOArticle
        DAOPanier daoPanier = daoFactory.getPanierDAO();
        DAOArticle daoArticle = daoFactory.getDAOArticle();

        Panier panier=null;
        User currentUser = parent.getCurrentUser(); // Utiliser le getter

        if (currentUser == null) {
            JOptionPane.showMessageDialog(parent,
                    "Veuillez vous connecter",
                    "Erreur",
                    JOptionPane.WARNING_MESSAGE);

        }
        //Vérification du statut de l'user pour que le bouton fonctionne ou non : Admin si statut=1 ou Client si =0
        if (currentUser != null) {
            if (currentUser.getStatutUser() == 1) { // Admin

            } else { // Client

                //Réupère le panier cu client connecté
                panier = daoPanier.UnPanier(currentUser.getIdUser());
                System.out.println("On a bien le panier du User");

            }
        }
        if (panier != null) {


            System.out.println("JESUISLA");
            //Recupère les listes des id des produits et des quantites
            String lesArticles= panier.getArticles();
            String lesQuantites= panier.getQuantite();
            int IDPanier = panier.getIDPanier();

            //Trouve la position de l'article à augmenter
            String[] articles = lesArticles.split(",");
            String[] quantites = lesQuantites.split(",");

            int[] arts=new int[articles.length];
            for (int i=0;i<articles.length;i++){
                arts[i]=Integer.parseInt(articles[i]);
            }
            int[] quants = new int[quantites.length];
            for (int i=0;i<quantites.length;i++){
                quants[i]=Integer.parseInt(quantites[i]);
            }

            // Calcul du montant total avant de créer les labels pour la première vue
            totalPrice = panier.calculMontant(arts, quants);
            nbProducts = Arrays.stream(quants).sum(); // mise à jour du nombre de produits
            totalLabel.setText(String.format("Total: %.2f €", totalPrice));

            //Création des panels pour chaque article
            for (int i=0;i<arts.length;i++){
                Article article=daoArticle.unarticle(arts[i]);
                JButton boutoncroix = new JButton();
                JButton boutonplus = new JButton();
                JButton boutonmoins = new JButton();
                JPanel productPanel = createProductPanel(
                        article.getNomArticle(),
                        article.getCategorieArticle(),
                        quants[i],
                        article.getPrixArticle(),
                        boutonmoins,
                        boutonplus,
                        boutoncroix,
                        article.getReductionArticle(),
                        article,
                        currentUser,
                        totalLabel,
                        nbProduitsRow,
                        sousTotalRow

                );
                productsContainer.add(productPanel);
                productsContainer.add(Box.createVerticalStrut(10));
            }

        }else{

            JLabel noProductsLabel = new JLabel("Votre panier est vide");
            System.out.println("Je recharge a chaque appuie");
            productsContainer.add(noProductsLabel);
        }


        // ScrollPane pour défiler si jamais il y a plusieurs produits
        JScrollPane scrollPane = new JScrollPane(productsContainer);
        scrollPane.setBorder(null);
        scrollPane.setBackground(backgroundColor);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        panel.add(scrollPane, BorderLayout.CENTER);



        return panel;
    }

    private JPanel createProductPanel(String name, String description, int quantity, double price, JButton minusButton, JButton plusButton, JButton deleteButton , float reduction,Article article,User client, JLabel totalLabel, createInfoRow nbProduitsRow, createInfoRow sousTotalRow) {
        // Création du panneau principal du produit
        JPanel productPanel = new JPanel(new BorderLayout(10, 0));
        productPanel.setBackground(productColor);
        productPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(5, 10, 10, 10)
        ));
        productPanel.setMaximumSize(new Dimension(550, 90));
        productPanel.setPreferredSize(new Dimension(550, 90));

        // Panneau supérieur pour le nom et le bouton de suppression
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(productColor);
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        topPanel.add(nameLabel, BorderLayout.WEST);

        // Bouton de suppression (X)
        //JButton deleteButton = new JButton("×");
        deleteButton.setText("×");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
        deleteButton.setForeground(Color.RED);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.setToolTipText("Supprimer ce produit");

        // Données référencées depuis la closure pour manipulation
        final double[] productTotalPrice = {quantity * price};

        topPanel.add(deleteButton, BorderLayout.EAST);
        productPanel.add(topPanel, BorderLayout.NORTH);

        // Panneau central
        JPanel contentPanel = new JPanel(new BorderLayout(10, 0));
        contentPanel.setBackground(productColor);

        // Cadre simple pour l'image
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(60, 60));
        imagePanel.setBackground(new Color(240, 240, 240));
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel imageLabel = new JLabel("Image");
        imageLabel.setForeground(Color.GRAY);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel);

        contentPanel.add(imagePanel, BorderLayout.WEST);

        // Panneau d'information
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(productColor);

        // Description
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        infoPanel.add(descLabel, BorderLayout.CENTER);

        // Panneau inférieur avec quantité et prix
        JPanel bottomInfoPanel = new JPanel(new BorderLayout(10, 0));
        bottomInfoPanel.setBackground(productColor);

        // Panneau de quantité avec contrôles + et -
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        quantityPanel.setBackground(productColor);

        JLabel quantityTextLabel = new JLabel("Quantité: ");
        quantityTextLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        //Boutons + - pour la quantite
        //JButton plusButton = new JButton("+");
        plusButton.setText("+");
        plusButton.setFont(new Font("Arial", Font.PLAIN, 12));
        plusButton.setPreferredSize(new Dimension(30, 25));
        plusButton.setMargin(new Insets(0, 0, 0, 0)); // reduction des marges internes
        plusButton.setFocusPainted(false);
        plusButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //JButton minusButton = new JButton("-");
        minusButton.setText("-");
        minusButton.setFont(new Font("Arial", Font.PLAIN, 12));
        minusButton.setPreferredSize(new Dimension(30, 25));
        minusButton.setMargin(new Insets(0, 0, 0, 0));
        minusButton.setFocusPainted(false);
        minusButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Référence au JLabel pour la quantité
        JLabel quantityLabel = new JLabel(String.valueOf(quantity));
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 12));
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quantityLabel.setPreferredSize(new Dimension(30, 25));
        quantityLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Référence au JLabel pour le prix
        JLabel priceLabel = new JLabel(String.format("%.2f €", price * quantity));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);





        ControleurPanier controleur = new ControleurPanier();

        controleur.boutonPLUS(
                plusButton,
                quantityLabel,
                priceLabel,
                totalLabel,
                sousTotalRow,
                nbProduitsRow,
                price,
                productTotalPrice,
                reduction,
                client,
                article

        );

        controleur.boutonMOINS(
                minusButton,
                quantityLabel,
                priceLabel,
                totalLabel,
                sousTotalRow,
                nbProduitsRow,
                price,
                productTotalPrice,
                reduction,
                client,
                article
        );

        controleur.boutonCROIX(
                deleteButton,
                productPanel,
                productsContainer,
                quantityLabel,
                priceLabel,
                sousTotalRow,
                totalLabel,
                nbProduitsRow,
                productTotalPrice,
                client,
                article

        );

//        // Actions pour les boutons + et -
//        minusButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int value = Integer.parseInt(quantityLabel.getText());
//                if (value > 1) {
//                    value--;
//                    quantityLabel.setText(String.valueOf(value));
//                    totalPrice -= price;
//                    productTotalPrice[0] = price * value;
//
//                    priceLabel.setText(String.format("%.2f €", price * value));
//                    totalLabel.setText(String.format("Total: %.2f €", totalPrice));
//                    sousTotalRow.setText(String.format("%.2f €", totalPrice));
//                    nbProducts--;
//                    nbProduitsRow.setText(String.valueOf(nbProducts));
//                }
//            }
//        });
//
//        plusButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int value = Integer.parseInt(quantityLabel.getText());
//                value++;
//                quantityLabel.setText(String.valueOf(value));
//
//                totalPrice += price;
//                productTotalPrice[0] = price * value;
//
//                priceLabel.setText(String.format("%.2f €", price * value));
//                totalLabel.setText(String.format("Total: %.2f €", totalPrice));
//                sousTotalRow.setText(String.format("%.2f €", totalPrice));
//                nbProducts++;
//                nbProduitsRow.setText(String.valueOf(nbProducts));
//            }
//        });
//
//        deleteButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Récupérer l'indice du produit dans le conteneur
//                int index = -1;
//                Component[] components = productsContainer.getComponents();
//                for (int i = 0; i < components.length; i++) {
//                    if (components[i] == productPanel) {
//                        index = i;
//                        break;
//                    }
//                }
//
//                if (index != -1) {
//                    // Supprimer le produit
//                    productsContainer.remove(productPanel);
//                    if (index + 1 < components.length && components[index + 1] instanceof Box.Filler) {
//                        productsContainer.remove(components[index + 1]);
//                    }
//
//                    totalPrice -= productTotalPrice[0];
//                    totalLabel.setText(String.format("Total: %.2f €", totalPrice));
//                    sousTotalRow.setText(String.format("%.2f €", totalPrice));
//                    nbProducts -= Integer.parseInt(quantityLabel.getText());
//                    nbProduitsRow.setText(String.valueOf(nbProducts));
//                    // Rafraîchir l'affichage
//                    productsContainer.revalidate();
//                    productsContainer.repaint();
//                }
//            }
//        });

        quantityPanel.add(quantityTextLabel);
        quantityPanel.add(minusButton);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(plusButton);

        bottomInfoPanel.add(quantityPanel, BorderLayout.WEST);
        bottomInfoPanel.add(priceLabel, BorderLayout.EAST);

        infoPanel.add(bottomInfoPanel, BorderLayout.SOUTH);
        contentPanel.add(infoPanel, BorderLayout.CENTER);

        productPanel.add(contentPanel, BorderLayout.CENTER);

        return productPanel;
    }

    private void showConfirmationPopup() {
        // Création de la fenêtre popup
        JDialog popup = new JDialog();

        popup.setTitle("Confirmation de commande");
        popup.setSize(350, 200);
        popup.setLocationRelativeTo(null); // Centre la fenêtre
        popup.setModal(true);

        // Panneau principal pour le popup
        JPanel popupPanel = new JPanel();
        popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.Y_AXIS));
        popupPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        popupPanel.setBackground(Color.WHITE);

        // Icône de confirmation
        JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        popupPanel.add(iconLabel);
        popupPanel.add(Box.createVerticalStrut(15));

        // Message de confirmation
        JLabel messageLabel = new JLabel("Votre commande a été validée");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        popupPanel.add(messageLabel);

        // Message supplémentaire
        JLabel detailLabel = new JLabel("Merci pour votre achat !");
        detailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        detailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        popupPanel.add(detailLabel);
        popupPanel.add(Box.createVerticalStrut(20));

        // Bouton OK
        JButton okButton = new JButton("OK");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(e -> popup.dispose());
        popupPanel.add(okButton);

        popup.add(popupPanel);
        popup.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(240, 240, 240));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Ajouter les listeners
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ;
            }
        });

        return button;
    }






}