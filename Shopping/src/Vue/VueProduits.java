package Vue;

import Controleur.ControleurPanier;
import DAO.DAOArticle;
import DAO.DAOFactory;
import Modele.Article;
import Modele.Client;
import Modele.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//classe qui affiche tous les produits
public class VueProduits extends JPanel {
    private final Color backgroundColor = new Color(198, 120, 96);
    private Mywindow parent;

    public VueProduits(Mywindow parent) {
        setLayout(new BorderLayout());

        this.parent = parent;
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColor);

        mainPanel.add(new FiltrerTrier());

        // Titre
        JPanel titrePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titrePanel.setBackground(backgroundColor);
        JLabel titre = new JLabel("Tous nos Produits");
        titre.setFont(new Font("Serif", Font.BOLD, 26));
        titre.setForeground(new Color(51, 51, 0));
        titrePanel.add(titre);
        mainPanel.add(titrePanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // Conteneur principal
        JPanel contentContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentContainer.setBackground(backgroundColor);
        contentContainer.add(createMixedProductsPanel(parent));
        mainPanel.add(contentContainer);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(new Bottom());

        add(mainPanel, BorderLayout.CENTER);
        setPreferredSize(new Dimension(800, 600));
    }

    private JPanel createMixedProductsPanel(Mywindow parent) {
        this.parent = parent;
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBackground(backgroundColor);
        containerPanel.setPreferredSize(new Dimension(900, 500));

        JPanel productsPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        productsPanel.setBackground(backgroundColor);
        productsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//        Object[][] produitsBiscuits = {
//                {"Cookies Pépites Chocolat", "Cookies croustillants aux pépites de chocolat noir et lait", 5.99, "cookies_chocolat"},
//                {"Sablés Vanille", "Sablés fondants à la vanille de Madagascar", 4.50, "sables_vanille"},
//                {"Galettes Bretonnes", "Galettes pur beurre de tradition bretonne", 3.99, "galettes_bretonnes"},
//                {"Spéculoos", "Biscuits croustillants à la cannelle et aux épices", 4.25, "speculoos"},
//                {"Macarons Amandes", "Délicats macarons à l'amande sans gluten", 6.99, "macarons_amandes"},
//                {"Palmiers", "Biscuits feuilletés caramélisés en forme de cœur", 4.95, "palmiers"},
//                {"Crinkles Chocolat", "Biscuits moelleux au chocolat saupoudrés de sucre glace", 5.50, "crinkles_chocolat"},
//                {"Langues de Chat", "Fins biscuits allongés au beurre et à la vanille", 3.75, "langues_chat"},
//                {"Palets Bretons", "Palets dorés au beurre salé de Bretagne", 4.80, "palets_bretons"}
//        };
//
//        Object[][] produitsBoissons = {
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
//        // j ai alterne chaque produit comme ca on a un melange boissons et bicuits
//        int maxLength = Math.max(produitsBiscuits.length, produitsBoissons.length);
//        for (int i = 0; i < maxLength; i++) {
//            if (i < produitsBiscuits.length) {
//                Object[] p = produitsBiscuits[i];
//                productsPanel.add(ProductPanelFactory.createProductPanel((String)p[0], (String)p[1], (Double)p[2], "C:\\Users\\kawid\\OneDrive\\Desktop\\ING3\\POO Java\\Projet\\welcomeSSSite.jpg"));
//            }
//            if (i < produitsBoissons.length) {
//                Object[] p = produitsBoissons[i];
//                productsPanel.add(ProductPanelFactory.createProductPanel((String)p[0], (String)p[1], (Double)p[2], "C:\\Users\\kawid\\OneDrive\\Desktop\\ING3\\POO Java\\Projet\\welcomeSSSite.jpg"));
//            }
//        }
        // Récupérer tous les produits depuis la base de données
        DAOFactory daoFactory = DAOFactory.getInstance("shoppingbd","root","root");

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
                        String type= article.getImageArticle();
                        System.out.println(type);

                        JButton bouton = new JButton();
                        // Création du panneau produit à partir des informations de l'article
                        JPanel productPanel = ProductPanelFactory.createProductPanel(
                                article.getNomArticle(),         // Nom du produit
                                article.getCategorieArticle(),   // Description ou catégorie
                                article.getPrixArticle(),        // Prix
                                article.getImageArticle(),        // Nom du porduit
                                bouton
                        );
                        controleurPanier.attacherBouton(bouton,article,currentUser);
                        productsPanel.add(productPanel);


                    }

                }
            }


        }

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
