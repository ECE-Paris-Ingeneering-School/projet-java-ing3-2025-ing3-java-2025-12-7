package Modele;

import java.sql.Timestamp;
import java.util.Date;

public class Client extends User{

    private Date dateNaissanceClient;
    private Timestamp dateAjoutClient;
    private Article[] articlesClient;
    private String adresseClient;

    /**
     * Constructeur par défaut
     */
    public Client() {

        this.dateNaissanceClient = null;
        this.dateAjoutClient = null;
        this.articlesClient = new Article[0];
        this.dateAjoutClient = null;
    }
    /**
     * Constructeur
     * @param dateNaissance
     * @param dateAjout
     * @param id
     * @param mdp
     * @param mail
     * @param statut
     * @param nom
     * @param prenom
     * @param adresse
     */
    public Client(int id, String mdp, String mail,
                  int statut, String nom, String prenom,
                  Date dateNaissance, Timestamp dateAjout, String adresse) {
        super(id, mdp, mail, statut, nom, prenom);
        this.dateNaissanceClient = dateNaissance;
        this.dateAjoutClient = dateAjout;
        this.articlesClient = new Article[0];
        this.adresseClient = adresse;
    }


    //getters

    public Date getDateNaissanceClient() { return dateNaissanceClient; }
    public Timestamp getDateAjoutClient() { return dateAjoutClient; }
    public Article[] getArticlesClient() {
        return articlesClient;
    }
    public String getAdresseClient() { return adresseClient; }

}
