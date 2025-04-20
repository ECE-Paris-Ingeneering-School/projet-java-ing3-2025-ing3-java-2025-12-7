package Modele;

import java.sql.Timestamp;
import java.util.Date;

public class Client extends User{

    private Date dateNaissanceClient;
    private Timestamp dateAjoutClient;
    private Article[] articlesClient;

    /**
     * Constructeur par défaut
     */
    public Client() {

        this.dateNaissanceClient = null;
        this.dateAjoutClient = null;
        this.articlesClient = new Article[0];
    }
    /**
     * Constructeur
     * @param dateNaissance
     * @param dateAjout
     * @param id
     * @param login
     * @param mdp
     * @param mail
     * @param statut
     * @param nom
     * @param prenom
     * @param telephone
     * @param adresse
     */
    public Client(int id, String login, String mdp, String mail,
                  int statut, String nom, String prenom,
                  String telephone, String adresse,
                  Date dateNaissance, Timestamp dateAjout) {
        super(id, login, mdp, mail, statut, nom, prenom, telephone, adresse);
        this.dateNaissanceClient = dateNaissance;
        this.dateAjoutClient = dateAjout;
        this.articlesClient = new Article[0];
    }


    //getters

    public Date getDateNaissanceClient() { return dateNaissanceClient; }
    public Timestamp getDateAjoutClient() { return dateAjoutClient; }
    public Article[] getArticlesClient() {
        return articlesClient;
    }




}
