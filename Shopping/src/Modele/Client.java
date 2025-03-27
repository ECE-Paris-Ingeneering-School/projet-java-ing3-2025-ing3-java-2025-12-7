package Modele;

import java.util.*;
import java.text.DateFormat;

public class Client extends User{

    private Date naissanceClient;
    private Date ajoutCompteClient;
    private Article[] articlesClient;
    private Date aujourdhui;

    /**
     * Constructeur par défaut
     */

    public Client (){
        this.naissanceClient = new Date(0);
        this.ajoutCompteClient = new Date(0);
        this.articlesClient = new Article[0];
        this.aujourdhui = new Date(0);
    }

    /**
     * Constructeur
     * @param naissanceClient
     * @param ajoutCompteClient
     * @param ID
     * @param nom
     * @param mail
     * @param mdp
     * @param statut
     */
    public Client (Date naissanceClient, Date ajoutCompteClient, int ID, String nom, String mail, String mdp, int statut){
        super(ID, nom, mail, mdp, statut);
        this.naissanceClient = naissanceClient;
        this.ajoutCompteClient = ajoutCompteClient;
        this.articlesClient = new Article[0];
    }

    //getters

    public Date getNaissanceClient() {
        return naissanceClient;
    }
    public Date getAjoutCompteClient() {
        return ajoutCompteClient;
    }
    public Article[] getArticlesClient() {
        return articlesClient;
    }




}
