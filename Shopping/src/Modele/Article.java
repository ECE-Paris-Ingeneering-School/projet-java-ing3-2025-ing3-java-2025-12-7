package Modele;
import java.util.*;

public class Article {

    private int IDArticle;
    private String nomArticle;
    private int quantiteArticle;
    private float prixArticle;
    private String marqueArticle;
    private String typeArticle; // 1-> Boisson 2-> Biscuit
    private String categorieArticle;
    private float reductionArticle;
    private Date dateAjoutArticle;
    private Date datePeremptionArticle;
    private String imageArticle;

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
        this.typeArticle = "";
        this.categorieArticle = "";
        this.reductionArticle = 0;
        this.dateAjoutArticle = null;
        this.datePeremptionArticle = null;
        this.imageArticle = "";
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
     * @param type
     * @param ajout
     */
    public Article (int ID, String nom, int quantite, float prix, String marque, String type, String categorie,float reduction,Date ajout,Date peremption, String image){
        this.IDArticle = ID;
        this.nomArticle = nom;
        this.quantiteArticle = quantite;
        this.prixArticle = prix;
        this.marqueArticle = marque;
        this.typeArticle = type;
        this.categorieArticle = categorie;
        this.imageArticle = image;
        this.reductionArticle = reduction;
        this.dateAjoutArticle = ajout;
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
    public String getTypeArticle() {return typeArticle;}
    public String getCategorieArticle() {return categorieArticle;}
    public String getImageArticle() {return imageArticle;}
    public float getReductionArticle() {
        return reductionArticle;
    }
    public Date getDatePeremptionArticle() {
        return datePeremptionArticle;
    }
    public Date getDateAjoutArticle() {return dateAjoutArticle;}
    public Date getAujourdhui() {
        return aujourdhui;
    }


}
