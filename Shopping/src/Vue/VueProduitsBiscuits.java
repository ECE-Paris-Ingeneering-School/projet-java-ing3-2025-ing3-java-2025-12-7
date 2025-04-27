package Vue;
import DAO.DAOArticle;
import DAO.DAOFactory;
import Modele.Article;
import Modele.Client;
import Controleur.ControleurPanier;
import Modele.User;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
//classe qui affiche les produits biscuits
public class VueProduitsBiscuits extends JPanel {
    private final Color backgroundColor = new Color(198, 120, 96);
    private JPanel productsPanel;
    private Mywindow parent;

    public VueProduitsBiscuits(Mywindow parent) {
        setLayout(new BorderLayout());

        this.parent = parent;
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColor);

        mainPanel.add(new FiltrerTrier());

        JPanel titrePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titrePanel.setBackground(backgroundColor);
        JLabel titre = new JLabel("Nos Biscuits");
        titre.setFont(new Font("Serif", Font.BOLD, 26));
        titre.setForeground(new Color(2, 48, 89));
        titrePanel.add(titre);
        mainPanel.add(titrePanel);
        mainPanel.add(Box.createVerticalStrut(15)); // Espace après le titre

        // Conteneur principal pour le contenu
        JPanel contentContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentContainer.setBackground(backgroundColor);
        JPanel content = createBiscuitsPanel(parent);
        contentContainer.add(content);
        mainPanel.add(contentContainer);

        // espace avant le footer
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(new Bottom());

        add(mainPanel, BorderLayout.CENTER);


        setPreferredSize(new Dimension(800, 600));
    }

    private JPanel createBiscuitsPanel(Mywindow parent) {
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

////        // Données des produits avec leur prix unitaire
//        Object[][] produits = {
//               {"Cookies Pépites Chocolat", "Cookies croustillants aux pépites de chocolat noir et lait", 5.99, "cookies_chocolat"},
//              {"Sablés Vanille", "Sablés fondants à la vanille de Madagascar", 4.50, "sables_vanille"},
//              {"Galettes Bretonnes", "Galettes pur beurre de tradition bretonne", 3.99, "galettes_bretonnes"},
//              {"Spéculoos", "Biscuits croustillants à la cannelle et aux épices", 4.25, "speculoos"},
//              {"Macarons Amandes", "Délicats macarons à l'amande sans gluten", 6.99, "macarons_amandes"},
//               {"Palmiers", "Biscuits feuilletés caramélisés en forme de cœur", 4.95, "palmiers"},
//               {"Crinkles Chocolat", "Biscuits moelleux au chocolat saupoudrés de sucre glace", 5.50, "crinkles_chocolat"},
//               {"Langues de Chat", "Fins biscuits allongés au beurre et à la vanille", 3.75, "langues_chat"},
//              {"Palets Bretons", "Palets dorés au beurre salé de Bretagne", 4.80, "palets_bretons"}
//       };
////
////        // Ajout de chaque produit dans son propre cadre
//        for (Object[] produit : produits) {
//            JPanel productPanel = ProductPanelFactory.createProductPanel((String)produit[0], (String)produit[1], (double)produit[2], "C:\\Users\\kawid\\OneDrive\\Desktop\\ING3\\POO Java\\Projet\\welcomeSSSite.jpg");
//           productsPanel.add(productPanel);
//       }

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
                        String type= article.getNomArticle();
                        System.out.println(type);
                        if (article.getTypeArticle().equals("Biscuit")) {
                            JButton bouton = new JButton();
                            // Création du panneau produit à partir des informations de l'article
                            JPanel productPanel = ProductPanelFactory.createProductPanel(
                                    article.getNomArticle(),         // Nom du produit
                                    article.getCategorieArticle(),   // Description ou catégorie
                                    (Double)(double)article.getPrixArticle(),        // Prix
                                    article.getImageArticle(),       // Nom du porduit
                                    bouton
                            );
                            controleurPanier.attacherBouton(bouton,article,currentUser);
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
    public void updateProducts(ArrayList<Article> articles) {
        productsPanel.removeAll();
        for (Article article : articles) {
            JButton bouton = new JButton();
            JPanel productPanel = ProductPanelFactory.createProductPanel(
                    article.getNomArticle(),
                    article.getCategorieArticle(),
                    article.getPrixArticle(),
                    article.getNomArticle(),
                    bouton
            );
            productsPanel.add(productPanel);
        }
        productsPanel.revalidate();
        productsPanel.repaint();
    }

}