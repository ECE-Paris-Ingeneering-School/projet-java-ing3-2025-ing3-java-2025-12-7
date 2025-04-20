package Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import DAO.DAOArticle;
import DAO.DAOFactory;
import DAO.DAOPanier;
import DAO.DAOArticle;
import Modele.Panier;
import Modele.Client;
import javax.swing.*;
import java.awt.*;
import Modele.Article;
import Vue.ProductPanelFactory;
import Vue.createInfoRow;


public class ControleurPanier {

    private double totalPrice;
    private int nbProduits;

    public void attacherBouton(JButton bouton, Article article, Client client) {
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Panier panier = new Panier();
                // Récupérer tous les paniers depuis la base de données
                DAOFactory daoFactory = DAOFactory.getInstance("shoppingbd","root","root");

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

    public void boutonPLUS(JButton bouton, JLabel quantityLabel, JLabel priceLabel, JLabel totalLabel, createInfoRow sousTotalRow, createInfoRow nbProduitsRow, double price, double[] productTotalPrice ) {
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(quantityLabel.getText());
                value++;
                quantityLabel.setText(String.valueOf(value));

                totalPrice += price;
                productTotalPrice[0] = price * value;

                priceLabel.setText(String.format("%.2f €", price * value));
                totalLabel.setText(String.format("Total: %.2f €", totalPrice));
                sousTotalRow.setText(String.format("%.2f €", totalPrice));
                nbProduits++;
                nbProduitsRow.setText(String.valueOf(nbProduits));
            }
        });
    }

    public void boutonMOINS(JButton bouton, JLabel quantityLabel, JLabel priceLabel, JLabel totalLabel, createInfoRow sousTotalRow, createInfoRow nbProduitsRow, double price, double[] productTotalPrice ) {
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(quantityLabel.getText());
                if (value > 1) {
                    value--;
                    quantityLabel.setText(String.valueOf(value));
                    totalPrice -= price;
                    productTotalPrice[0] = price * value;

                    priceLabel.setText(String.format("%.2f €", price * value));
                    totalLabel.setText(String.format("Total: %.2f €", totalPrice));
                    sousTotalRow.setText(String.format("%.2f €", totalPrice));
                    nbProduits--;
                    nbProduitsRow.setText(String.valueOf(nbProduits));
                }
            }
        });
    }

    public void boutonCROIX(JButton bouton, JPanel productPanel, JPanel productsContainer, JLabel quantityLabel, JLabel priceLabel, createInfoRow sousTotalRow, JLabel totalLabel, createInfoRow nbProduitsRow, double[] productTotalPrice ) {
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

                    totalPrice -= productTotalPrice[0];
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


}
