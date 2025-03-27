package Modele;

import java.util.*;

public class Panier {

    private Article[] articles;
    private int[] quantite;
    private float montant;
    private Client client;
    private Date dateJour;

    /**
     * Constructeur par défaut
     */

    public Panier() {
        this.quantite = new int[0];
        this.montant = 0;
        this.client = new Client();
        this.dateJour = new Date(0);
        this.articles = new Article[0];
    }

    /**
     * Constructeur
     * @param articles
     * @param quantite
     * @param montant
     * @param client
     * @param dateJour
     */
    public Panier(Article[] articles, int[] quantite, float montant, Client client, Date dateJour) {
        this.articles = articles;
        this.quantite = quantite;
        this.montant = montant;
        this.client = client;
        this.dateJour = dateJour;

    }

    //getters
    public Article[] getArticles() {
        return articles;
    }
    public int[] getQuantite() {
        return quantite;
    }
    public float getMontant() {
        return montant;
    }
    public Client getClient() {
        return client;
    }
    public Date getDateJour() {
        return dateJour;
    }





}
