package Modele;

public class User {
    protected int idUser;
    protected String mdpUser;
    protected String mailUser;
    protected int statutUser;
    protected String nomUser;
    protected String prenomUser;

    public User() {
        idUser = 0;
        mdpUser = "";
        mailUser = "";
        statutUser = 0;
        nomUser = "";
        prenomUser = "";
    }

    /**
     * Constructeur
     * @param id
     * @param nom
     * @param mail
     * @param mdp
     * @param statut
     * @param prenom
     */


    public User(int id, String mdp, String mail,
                int statut, String nom, String prenom) {
        this.idUser = id;
        this.mdpUser = mdp;
        this.mailUser = mail;
        this.statutUser = statut;
        this.nomUser = nom;
        this.prenomUser = prenom;
    }

    // getters
    public int getIdUser() { return idUser; }
    public String getMdpUser() { return mdpUser; }
    public String getMailUser() { return mailUser; }
    public int getStatutUser() { return statutUser; }
    public String getNomUser() { return nomUser; }
    public String getPrenomUser() { return prenomUser; }
}

