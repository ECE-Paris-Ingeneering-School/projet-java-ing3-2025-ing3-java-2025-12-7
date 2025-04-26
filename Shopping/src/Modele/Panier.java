package Modele;

import java.util.*;
import Modele.Article;
import DAO.*;
import Modele.Article;
import Modele.Panier;

import java.sql.*;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Panier {

    private int IDPanier;
    private String articles;
    private String quantite;
    private float montant;
    private  int IDClient;
    private DAOFactory daoFactory;
    //private Date dateJour;

    /**
     * Constructeur par défaut
     */

    public Panier() {
        this.IDPanier = 0;
        this.quantite = "";
        this.montant = 0;
        this.IDClient = 0;
      // this.dateJour = new Date(0);
        this.articles = "";
    }

    /**
     * Constructeur
     * @param articles
     * @param quantite
     * @param montant
     * @param IDP
     * @param IDC
     */
    public Panier(int IDP, int IDC ,String articles, String quantite, float montant) {
        this.IDPanier = IDP;
        this.IDClient = IDC;
        this.articles = articles;
        this.quantite = quantite;
        this.montant = montant;
    //  this.dateJour = dateJour;

    }

    //getters
    public int getIDPanier() {return IDPanier;}
    public String getArticles() {
        return articles;
    }
    public String getQuantite() {
        return quantite;
    }
    public float getMontant() {
        return montant;
    }
    public int getIDClient() {
        return IDClient;
    }
//    public Date getDateJour() {
//        return dateJour;
//    }

    public float calculMontant(int[] idArticles, int[] quantite) {
        float montant=0;

        // Récupérer tous les paniers depuis la base de données
        DAOFactory daoFactory = DAOFactory.getInstance("shoppingbd","root","root");

        // Utiliser DAOFactory pour obtenir une instance de DAOPanier
        DAOArticle daoArticle = daoFactory.getDAOArticle();

        for (int i = 0; i < idArticles.length; i++) {
            Article article=daoArticle.unarticle(idArticles[i]);
            float prix=article.getPrixArticle();

            int qteAvecReduction = (quantite[i] / 6) * 6;
            int qteSansReduction = quantite[i] % 6;

            float prixAvecReduction = qteAvecReduction * prix * (1 - article.getReductionArticle());
            float prixSansReduction = qteSansReduction * prix;

            float prixTotal = prixAvecReduction + prixSansReduction;
            montant += prixTotal;
        }

        return montant;
    }





}
