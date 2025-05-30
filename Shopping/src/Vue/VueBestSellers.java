package Vue;
import Modele.Article;
import Modele.Client;
import DAO.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Controleur.ControleurPanier;
import Modele.User;

//classe qui affiche les produits les plus achetes
public class VueBestSellers extends JPanel {
    private final Color backgroundColor = new Color(220, 223, 197);
    private Mywindow parent;

    public VueBestSellers(Mywindow parent) {
        setLayout(new BorderLayout());

        this.parent = parent;
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColor);

        mainPanel.add(new FiltrerTrier());

        JPanel titrePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titrePanel.setBackground(backgroundColor);
        JLabel titre = new JLabel("Nos Best-Sellers");
        titre.setFont(new Font("Serif", Font.BOLD, 26));
        titre.setForeground(new Color(0, 102, 59));
        titrePanel.add(titre);
        mainPanel.add(titrePanel);
        mainPanel.add(Box.createVerticalStrut(15)); // Espace après le titre

        // Conteneur principal pour le contenu
        JPanel contentContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentContainer.setBackground(backgroundColor);
        JPanel content = createBestSellersPanel(parent);
        contentContainer.add(content);
        mainPanel.add(contentContainer);

        // espace avant le footer
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(new Bottom());

        add(mainPanel, BorderLayout.CENTER);
        setPreferredSize(new Dimension(800, 600));
    }

    private JPanel createBestSellersPanel(Mywindow parent) {
        this.parent = parent;
        // Conteneur principal
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBackground(backgroundColor);
        containerPanel.setPreferredSize(new Dimension(900, 500));

        // Panel qui contiendra tous les produits
        JPanel productsPanel = new JPanel();
        productsPanel.setLayout((new GridLayout(0, 3, 20, 20)));
        productsPanel.setBackground(backgroundColor);
        productsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//        // Données des produits avec leur prix unitaire
//
//        Object[][] produits = {
//                {"Thé Glacé", "Boisson rafraîchissante au thé noir et citron", 2.50, "the_glace"},
//                {"Smoothie Fraise", "Smoothie aux fraises fraîches et yaourt", 3.80, "smoothie_fraise"},
//                {"Café Latte", "Café doux au lait entier", 2.90, "cafe_latte"},
//                {"Jus d'Orange", "Pur jus d'orange pressé", 2.70, "jus_orange"},
//                {"Limonade Artisanale", "Limonade pétillante au citron naturel", 3.20, "limonade_artisanale"},
//                {"Eau Pétillante", "Eau gazeuse naturelle", 1.50, "eau_petillante"},
//                {"Chocolat Chaud", "Boisson chaude au chocolat noir", 3.00, "chocolat_chaud"},
//                {"Matcha Latte", "Boisson japonaise au thé vert matcha", 3.60, "matcha_latte"},
//                {"Bubble Tea", "Thé au lait avec perles de tapioca", 4.50, "bubble_tea"}
//        };
//
//        // Ajout de chaque produit dans son propre cadre
//        for (Object[] produit : produits) {
//            JPanel productPanel = ProductPanelFactory.createProductPanel((String)produit[0], (String)produit[1], (double)produit[2], (String)produit[3]);
//            productsPanel.add(productPanel);
//
//        }

        // Récupérer tous les produits depuis la base de données
        DAOFactory daoFactory = DAOFactory.getInstance("shoppingbd", "root", "root");

        // Utiliser DAOFactory pour obtenir une instance de DAOArticle
        DAOArticle daoArticle = daoFactory.getDAOArticle();

        // Récupérer tous les produits depuis la base de données via la méthode getAll() de DAOArticle
        ArrayList<Article> produits = daoArticle.getAll();

        // Si la liste de produits est vide
        if (produits.isEmpty()) {
            JLabel noProductsLabel = new JLabel("Aucun produit disponible");
            productsPanel.add(noProductsLabel);
        } else {
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

                    ControleurPanier controleurPanier = new ControleurPanier();
                    // Ajout de chaque produit dans son propre cadre
                    for (Article article : produits) {
                        String type= article.getNomArticle();
                        System.out.println(type);
                        if (article.getIDArticle()==6||article.getIDArticle()==11||article.getIDArticle()==23||article.getIDArticle()==28||article.getIDArticle()==36||article.getIDArticle()==37||article.getIDArticle()==44||article.getIDArticle()==49||article.getIDArticle()==56||article.getIDArticle()==62) {
                            JButton bouton = new JButton();
                            // Création du panneau produit à partir des informations de l'article
                            JPanel productPanel = ProductPanelFactory.createProductPanel(
                                    article.getNomArticle(),         // Nom du produit
                                    article.getCategorieArticle(),   // Description ou catégorie
                                    article.getPrixArticle(),        // Prix
                                    article.getImageArticle(),      // Nom du porduit
                                    bouton
                            );
                            controleurPanier.attacherBouton(bouton,article, currentUser);
                            productsPanel.add(productPanel);
                        }
                    }

                }
            }

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
}
