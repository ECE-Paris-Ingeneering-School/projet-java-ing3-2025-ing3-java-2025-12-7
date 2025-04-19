package Modele;

import java.sql.Timestamp;
import java.util.Date;

public class Administrateur extends User{

    //Constructeur
    public Administrateur(int id, String login, String mdp, String mail,
                          int statut, String nom, String prenom,
                          String telephone, String adresse) {
        super(id, login, mdp, mail, statut, nom, prenom, telephone, adresse);
    }




}
