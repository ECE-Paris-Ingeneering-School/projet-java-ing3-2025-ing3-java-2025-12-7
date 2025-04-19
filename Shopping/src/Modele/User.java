package Modele;

public class User {
    protected int idUser;
    protected String loginUser;
    protected String mdpUser;
    protected String mailUser;
    protected int statutUser;
    protected String nomUser;
    protected String prenomUser;
    protected String telephoneUser;
    protected String adresseUser;

    /**
     * Constructeur
     * @param id
     * @param nom
     * @param mail
     * @param mdp
     * @param statut
     * @param login
     * @param prenom
     * @param telephone
     * @param adresse
     */

    public User(int id, String login, String mdp, String mail,
                int statut, String nom, String prenom,
                String telephone, String adresse) {
        this.idUser = id;
        this.loginUser = login;
        this.mdpUser = mdp;
        this.mailUser = mail;
        this.statutUser = statut;
        this.nomUser = nom;
        this.prenomUser = prenom;
        this.telephoneUser = telephone;
        this.adresseUser = adresse;
    }

    // getters
    public int getIdUser() { return idUser; }
    public String getLoginUser() { return loginUser; }
    public String getMdpUser() { return mdpUser; }
    public String getMailUser() { return mailUser; }
    public int getStatutUser() { return statutUser; }
    public String getNomUser() { return nomUser; }
    public String getPrenomUser() { return prenomUser; }
    public String getTelephoneUser() { return telephoneUser; }
    public String getAdresseUser() { return adresseUser; }
}

