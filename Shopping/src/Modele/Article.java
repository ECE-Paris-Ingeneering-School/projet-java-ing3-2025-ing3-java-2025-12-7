package Modele;
import java.util.*;
import java.awt.*;

public class Article {

    private int IDArticle;
    private String nomArticle;
    private int quantiteArticle;
    private float prixArticle;
    private String marqueArticle;
    private int categorieArticle; // 1-> Boisson 2-> Biscuit
    //private File imageArticle;
    private float reductionArticle;
    private Date datePeremptionArticle;
    private Date aujourdhui;

    /**
     * Constructeur par défaut
     */
    public Article() {
        this.IDArticle = 0;
        this.nomArticle = "";
        this.quantiteArticle = 0;
        this.prixArticle = 0;
        this.marqueArticle = "";
        this.categorieArticle = 0;
        //this.imageArticle = "";
        this.reductionArticle = 0;
        this.datePeremptionArticle = null;
        this.aujourdhui = null;
    }

    /**
     * Constructeur
     * @param ID
     * @param nom
     * @param quantite
     * @param prix
     * @param categorie
     * @param marque
     * @param reduction
     * @param peremption
     */
    public Article (int ID, String nom, int quantite, float prix, int categorie, String marque,float reduction,Date peremption){
        this.IDArticle = ID;
        this.nomArticle = nom;
        this.quantiteArticle = quantite;
        this.prixArticle = prix;
        this.marqueArticle = marque;
        this.categorieArticle = categorie;
        //this.imageArticle = "";
        this.reductionArticle = reduction;
        this.datePeremptionArticle = peremption;

    }

    //getters
    public int getIDArticle() {
        return IDArticle;
    }
    public String getNomArticle() {
        return nomArticle;
    }
    public int getQuantiteArticle() {
        return quantiteArticle;
    }
    public float getPrixArticle() {
        return prixArticle;
    }
    public String getMarqueArticle() {
        return marqueArticle;
    }
    public int getCategorieArticle() {
        return categorieArticle;
    }
    public float getReductionArticle() {
        return reductionArticle;
    }
    public Date getDatePeremptionArticle() {
        return datePeremptionArticle;
    }
    public Date getAujourdhui() {
        return aujourdhui;
    }

}
