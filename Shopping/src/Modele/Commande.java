package Modele;

public class Commande {
    private int idCommande;
    private int idClient;
    private float montantCommande;
    private String produitsCommande;
    private int quantiteProduits;
    private java.sql.Date dateCommande;

    /**
     * Constructeur par défaut
     */
    public Commande() {
        idCommande = 0;
        idClient = 0;
        montantCommande = 0;
        produitsCommande = "";
        quantiteProduits = 0;
        dateCommande = null;
    }

    /**
     * Constructeur avec paramètres
     *
     * @param id
     * @param idClient
     * @param montant
     * @param produits
     * @param quantite
     * @param date
     */
    public Commande (int id, int idClient,float montant, String produits, int quantite, java.sql.Date date) {
        this.idCommande = id;
        this.idClient = idClient;
        this.montantCommande = montant;
        this.produitsCommande = produits;
        this.quantiteProduits = quantite;
        this.dateCommande = date;

    }

    public int getIdCommande() {return idCommande;}
    public int getIdClient() {return idClient;}
    public float getMontantCommande() {return montantCommande;}
    public String getProduitsCommande() {return produitsCommande;}
    public int getQuantiteProduits() {return quantiteProduits;}
    public java.sql.Date getDateCommande() {return dateCommande;}




}
