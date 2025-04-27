package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import java.awt.*;

import DAO.DAOArticle;
import DAO.DAOFactory;
import DAO.DAOPanier;
import DAO.DAOArticle;
import DAO.DAOCommande;

import Modele.Panier;
import Modele.Client;
import Modele.Article;
import Modele.User;

import Vue.ProductPanelFactory;
import Vue.VuePanier;
import Vue.createInfoRow;

/**
 * Contient tous les listerners des boutons de la page VuePanier, et des pages produits pour le bouton "Ajouter au panier"
 * Permet de communiquer entre les DAO et les vues, pour à la fois modifier la base de données et afficher à l'utilisateur les confirmations de ses actions
 */
public class ControleurPanier {

    private double totalPrice;
    private int nbProduits;

    /**
     * Le listener du bouton "Ajouter au panier" qui permet d'ajouter dans le panier l'article choisi par l'utilisateur
     * Créer un nouveau panier si l'utilisateur n'en a pas déjà un
     * Ajoute l'article dans le panier si l'utilisateur en a déjà un
     * Augmente la quantité de l'article dans le panier si il y a déjà un même article dans le panier
     * @param bouton : le bouton auquel on souhaite ajouter ce listener
     * @param article : recupère l'article rataché au bouton, celui que l'utilisateur veut attacher à son panier
     * @param client : l'utilisateur qui est connecté, permet d'accéder au bon panier
     */
    public void attacherBouton(JButton bouton, Article article, User client) {
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Panier panier = new Panier();
                // Récupérer tous les paniers depuis la base de données
                DAOFactory daoFactory = DAOFactory.getInstance("shoppingbd","root","");

                // Utiliser DAOFactory pour obtenir une instance de DAOPanier
                DAOPanier daoPanier = daoFactory.getPanierDAO();

                // Récupérer tous les paniers depuis la base de données via la méthode getAll() de DAOPanier
                ArrayList<Panier> lespaniers = daoPanier.getAll();

                // Si la liste de paniers est vide
                if (lespaniers.isEmpty()) {
                    daoPanier.nouveauPanier(client.getIdUser());
                    daoPanier.ajouter(article, client.getIdUser());

                } else {
                    Boolean panierClient= false;
                    for (Panier p : lespaniers) {
                        if (p.getIDClient() == client.getIdUser()) {
                            panierClient = true;

                            String str = p.getArticles();
                            String[] IDArticles = str.split(",");
                            Boolean DejadanslePanier = false;
                            for (String IDS : IDArticles) {
                                int uneID = Integer.parseInt(IDS);
                                if (uneID==article.getIDArticle()){
                                    DejadanslePanier=true;
                                    daoPanier.PLUS1(article, client.getIdUser());
                                }
                            }
                            if(DejadanslePanier==false){
                                daoPanier.ajouter(article, client.getIdUser());
                            }
                        }
                    }
                    if (panierClient==false) {
                        daoPanier.nouveauPanier(client.getIdUser());
                        daoPanier.ajouter(article, client.getIdUser());
                    }

                }
                // Logique d'ajout au panier (appel à une classe PanierModel ?)
                System.out.println(article.getNomArticle() + " ajouté au panier");

                JOptionPane.showMessageDialog(
                        null,
                        article.getNomArticle() + " ajouté à votre panier !",
                        "Produit ajouté",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }

    /**
     * Le listener du bouton "+" dans la vuePanier
     * Permet d'augmenter la quantité d'un article déjà présent dans le panier
     * Augmente la quantité de l'article dans le panier
     * Met à jour le sous total et le total du panier
     * @param bouton : Le bouton auquel on souhaite rattacher le listener
     * @param quantityLabel : pour l'affichage
     * @param priceLabel : pour l'affichage
     * @param totalLabel : pour l'affichage
     * @param sousTotalRow : pour l'affichage
     * @param nbProduitsRow : pour l'affichage
     * @param price : de l'article
     * @param productTotalPrice : pour l'affichage
     * @param reduction : de l'article
     * @param client : l'utilisateur qui est connecté, nous permet de trouver le bon panier
     * @param article : l'article auquel est rattaché le bouton
     */
    public void boutonPLUS(JButton bouton, JLabel quantityLabel, JLabel priceLabel, JLabel totalLabel, createInfoRow sousTotalRow, createInfoRow nbProduitsRow, double price, double[] productTotalPrice, float reduction,User client,Article article) {
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int value = Integer.parseInt(quantityLabel.getText());
                value++;
                quantityLabel.setText(String.valueOf(value));

                int qteAvecReduction = (value / 6) * 6;
                int qteSansReduction = value % 6;

                double prixAvecReduction = qteAvecReduction * price * (1 - reduction);
                double prixSansReduction = qteSansReduction * price;

                double prixTotal = prixAvecReduction + prixSansReduction;



                Panier panier = new Panier();
                // Récupérer tous les paniers depuis la base de données
                DAOFactory daoFactory = DAOFactory.getInstance("shoppingbd","root","");

                // Utiliser DAOFactory pour obtenir une instance de DAOPanier
                DAOPanier daoPanier = daoFactory.getPanierDAO();

                // Récupérer tous les paniers depuis la base de données via la méthode getAll() de DAOPanier
                ArrayList<Panier> lespaniers = daoPanier.getAll();

                for (Panier p : lespaniers) {
                    if (p.getIDClient() == client.getIdUser()) {

                        String str = p.getArticles();
                        String[] IDArticles = str.split(",");
                        Boolean DejadanslePanier = false;
                        for (String IDS : IDArticles) {
                            int uneID = Integer.parseInt(IDS);
                            if (uneID==article.getIDArticle()){
                                DejadanslePanier=true;
                                daoPanier.PLUS1(article, client.getIdUser());
                            }
                        }
                    }
                }

                //CALCUL DU TOTAL
                ArrayList<Panier> lesnouveauxpaniers = daoPanier.getAll();
                for (Panier p : lesnouveauxpaniers) {
                    if (p.getIDClient() == client.getIdUser()) {

                        String str = p.getArticles();
                        String[] IDArticles = str.split(",");
                        String str2 = p.getQuantite();
                        String[] Quantites = str2.split(",");

                        int[] arts=new int[IDArticles.length];
                        for (int i=0;i<IDArticles.length;i++){
                            arts[i]=Integer.parseInt(IDArticles[i]);
                        }
                        int[] quants = new int[Quantites.length];
                        for (int i=0;i<Quantites.length;i++){
                            quants[i]=Integer.parseInt(Quantites[i]);
                        }

                        totalPrice=p.calculMontant(arts,quants);

                    }
                }

                productTotalPrice[0] = price * value;

                priceLabel.setText(String.format("%.2f €", prixTotal));
                totalLabel.setText(String.format("Total: %.2f €", totalPrice));
                sousTotalRow.setText(String.format("%.2f €", totalPrice));
                nbProduits++;
                nbProduitsRow.setText(String.valueOf(nbProduits));




            }
        });
    }

    /**
     * Le listener du bouton "-" dans la vuePanier
     * Permet de diminuer la quantité d'un article déjà présent dans le panier
     * Diminue la quantité de l'article dans le panier
     * Met à jour le sous total et le total du panier
     * @param bouton : Le bouton auquel on souhaite rattacher le listener
     * @param quantityLabel : pour l'affichage
     * @param priceLabel : pour l'affichage
     * @param totalLabel : pour l'affichage
     * @param sousTotalRow : pour l'affichage
     * @param nbProduitsRow : pour l'affichage
     * @param price : de l'article
     * @param productTotalPrice : pour l'affichage
     * @param reduction : de l'article
     * @param client : l'utilisateur qui est connecté, nous permet de trouver le bon panier
     * @param article : l'article auquel est rattaché le bouton
     */
    public void boutonMOINS(JButton bouton, JLabel quantityLabel, JLabel priceLabel, JLabel totalLabel, createInfoRow sousTotalRow, createInfoRow nbProduitsRow, double price, double[] productTotalPrice,float reduction,User client,Article article ) {
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(quantityLabel.getText());
                if (value > 1) {
                    value--;

                    int qteAvecReduction = (value / 6) * 6;
                    int qteSansReduction = value % 6;

                    double prixAvecReduction = qteAvecReduction * price * (1 - reduction);
                    double prixSansReduction = qteSansReduction * price;

                    double prixTotal = prixAvecReduction + prixSansReduction;



                    Panier panier = new Panier();
                    // Récupérer tous les paniers depuis la base de données
                    DAOFactory daoFactory = DAOFactory.getInstance("shoppingbd","root","");

                    // Utiliser DAOFactory pour obtenir une instance de DAOPanier
                    DAOPanier daoPanier = daoFactory.getPanierDAO();

                    // Récupérer tous les paniers depuis la base de données via la méthode getAll() de DAOPanier
                    ArrayList<Panier> lespaniers = daoPanier.getAll();

                    for (Panier p : lespaniers) {
                        if (p.getIDClient() == client.getIdUser()) {

                            String str = p.getArticles();
                            String[] IDArticles = str.split(",");

                            Boolean DejadanslePanier = false;

                            for (String IDS : IDArticles) {
                                int uneID = Integer.parseInt(IDS);
                                if (uneID==article.getIDArticle()){
                                    DejadanslePanier=true;
                                    daoPanier.MOINS1(article, client.getIdUser());
                                }
                            }
                        }
                    }

                    //CALCUL DU TOTAL
                    ArrayList<Panier> lesnouveauxpaniers = daoPanier.getAll();
                    for (Panier p : lesnouveauxpaniers) {
                        if (p.getIDClient() == client.getIdUser()) {

                            String str = p.getArticles();
                            String[] IDArticles = str.split(",");
                            String str2 = p.getQuantite();
                            String[] Quantites = str2.split(",");

                            int[] arts=new int[IDArticles.length];
                            for (int i=0;i<IDArticles.length;i++){
                                arts[i]=Integer.parseInt(IDArticles[i]);
                            }
                            int[] quants = new int[Quantites.length];
                            for (int i=0;i<Quantites.length;i++){
                                quants[i]=Integer.parseInt(Quantites[i]);
                            }

                            totalPrice=p.calculMontant(arts,quants);

                        }
                    }

                        quantityLabel.setText(String.valueOf(value));

                        productTotalPrice[0] = price * value;



                        priceLabel.setText(String.format("%.2f €", prixTotal));
                        totalLabel.setText(String.format("Total: %.2f €", totalPrice));
                        sousTotalRow.setText(String.format("%.2f €", totalPrice));
                        nbProduits--;
                        nbProduitsRow.setText(String.valueOf(nbProduits));



                }




            }
        });
    }

    /**
     * Le listener du bouton "x" dans la vuePanier
     * Nous permet de supprimer un article du panier
     * Supprime l'article peut importe sa quantité
     * Met à jour le total du panier
     * @param bouton : bouton auquel on souhaite rattacher le listener
     * @param productPanel : pour l'affichage
     * @param productsContainer : pour l'affichage
     * @param quantityLabel : pour l'affichage
     * @param priceLabel : pour l'affichage
     * @param sousTotalRow : pour l'affichage
     * @param totalLabel : pour l'affichage
     * @param nbProduitsRow : pour l'affichage
     * @param productTotalPrice : pour l'affichage
     * @param client : l'utilisateur qui est connecté, nous permet de trouver le bon panier
     * @param article : l'article auquel est rattaché le bouton
     */
    public void boutonCROIX(JButton bouton, JPanel productPanel, JPanel productsContainer, JLabel quantityLabel, JLabel priceLabel, createInfoRow sousTotalRow, JLabel totalLabel, createInfoRow nbProduitsRow, double[] productTotalPrice, User client, Article article) {
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'indice du produit dans le conteneur
                int index = -1;
                Component[] components = productsContainer.getComponents();
                for (int i = 0; i < components.length; i++) {
                    if (components[i] == productPanel) {
                        index = i;
                        break;
                    }
                }

                if (index != -1) {
                    // Supprimer le produit
                    productsContainer.remove(productPanel);
                    if (index + 1 < components.length && components[index + 1] instanceof Box.Filler) {
                        productsContainer.remove(components[index + 1]);
                    }

                    Panier panier = new Panier();
                    // Récupérer tous les paniers depuis la base de données
                    DAOFactory daoFactory = DAOFactory.getInstance("shoppingbd", "root", "");

                    // Utiliser DAOFactory pour obtenir une instance de DAOPanier
                    DAOPanier daoPanier = daoFactory.getPanierDAO();

                    // Récupérer tous les paniers depuis la base de données via la méthode getAll() de DAOPanier
                    ArrayList<Panier> lespaniers = daoPanier.getAll();

                    for (Panier p : lespaniers) {
                        if (p.getIDClient() == client.getIdUser()) {

                            String str = p.getArticles();
                            String[] IDArticles = str.split(",");
                            Boolean DejadanslePanier = false;
                            for (String IDS : IDArticles) {
                                int uneID = Integer.parseInt(IDS);
                                if (uneID == article.getIDArticle()) {
                                    DejadanslePanier = true;
                                    daoPanier.retirer(client.getIdUser(), article);
                                }
                            }
                        }
                    }
                    //CALCUL DU TOTAL
                    ArrayList<Panier> lesnouveauxpaniers = daoPanier.getAll();
                    for (Panier p : lesnouveauxpaniers) {
                        if (p.getIDClient() == client.getIdUser()) {

                            String str = p.getArticles();

                            if (str.equals("")){
                                totalPrice=0.0;
                                daoPanier.supprimer(client.getIdUser());
                            }else{
                                String[] IDArticles = str.split(",");
                                String str2 = p.getQuantite();
                                String[] Quantites = str2.split(",");

                                int[] arts=new int[IDArticles.length];
                                for (int i=0;i<IDArticles.length;i++){
                                    arts[i]=Integer.parseInt(IDArticles[i]);
                                }
                                int[] quants = new int[Quantites.length];
                                for (int i=0;i<Quantites.length;i++){
                                    quants[i]=Integer.parseInt(Quantites[i]);
                                }

                                totalPrice=p.calculMontant(arts,quants);
                            }



                        }
                    }

                    totalLabel.setText(String.format("Total: %.2f €", totalPrice));
                    sousTotalRow.setText(String.format("%.2f €", totalPrice));
                    nbProduits -= Integer.parseInt(quantityLabel.getText());
                    nbProduitsRow.setText(String.valueOf(nbProduits));
                    // Rafraîchir l'affichage
                    productsContainer.revalidate();
                    productsContainer.repaint();


                }
            }
        });
    }


    public void commander (JButton bouton, User client,VuePanier vuePanier) {
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vuePanier.showConfirmationPopup();

                Panier panier = new Panier();
                // Récupérer tous les paniers depuis la base de données
                DAOFactory daoFactory = DAOFactory.getInstance("shoppingbd", "root", "");

                // Utiliser DAOFactory pour obtenir une instance de DAOCommande
                DAOCommande daoCommande = daoFactory.getCommandeDAO();

                // Utiliser DAOFactory pour obtenir une instance de DAOPanier
                DAOPanier daoPanier = daoFactory.getPanierDAO();

                // Utiliser DAOFactory pour obtenir une instance de DAOPanier
                DAOArticle daoArticle = daoFactory.getDAOArticle();

                ArrayList<Panier> lespaniers = daoPanier.getAll();

                for (Panier p : lespaniers) {
                    if (p.getIDClient() == client.getIdUser()) {

                        daoCommande.nouvelleCommande(p, client.getIdUser());

                        daoPanier.supprimer(client.getIdUser());
                    }
                }









            }
        });
    }




}
