package Modele;

public class Journalisation {

    private int IDUser;
    //private ??? heure_debut;
    //private ??? heure_fin;
    //private ?? duree;
    private Article[] articles;

    /**
     * Constructeur
     */

    public Journalisation() {
        this.IDUser = 0;
        this.articles = new Article[0];
    }

    /**
     * Constructeur
     * @param IDUser
     * @param articles
     */
    public Journalisation(int IDUser, Article[] articles) {
        this.IDUser = IDUser;
        this.articles = articles;
    }

    //getters
    public int getIDUser() {
        return IDUser;
    }
    public Article[] getArticles() {
        return articles;
    }


}
