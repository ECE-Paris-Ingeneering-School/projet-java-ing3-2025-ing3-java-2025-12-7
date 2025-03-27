package Modele;

public class User {

    private int IDUser;
    private String nomUser;
    private String mailUser;
    private String motdepasseUser;
    private int statutUSer;

    /**
     * Constructeur par défaut
     */
    public User (){
        this.IDUser = 0;
        this.nomUser = "";
        this.mailUser = "";
        this.motdepasseUser = "";
        this.statutUSer = 0;
    }

    /**
     * Constructeur
     * @param ID
     * @param nom
     * @param mail
     * @param mdp
     * @param statut
     */

    public User(int ID, String nom, String mail, String mdp, int statut) {
        this.IDUser = ID;
        this.nomUser = nom;
        this.mailUser = mail;
        this.motdepasseUser = mdp;
        this.statutUSer = statut;
    }

    // getters
    public int getIDUser() {
        return IDUser;
    }
    public String getNomUser() {
        return nomUser;
    }
    public String getMailUser() {
        return mailUser;
    }
    public String getMotdepasseUser() {
        return motdepasseUser;
    }
    public int getStatutUSer() {
        return statutUSer;
    }


}
