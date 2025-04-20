package Modele;

import java.util.*;

public class Panier {

    private int IDPanier;
    private String articles;
    private String quantite;
    private float montant;
    private  int IDClient;
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





}
