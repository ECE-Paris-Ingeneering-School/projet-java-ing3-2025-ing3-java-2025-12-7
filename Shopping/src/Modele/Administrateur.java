package Modele;

import java.sql.Timestamp;
import java.util.Date;

public class Administrateur extends User{

    //Constructeur
    public Administrateur(int id, String mdp, String mail,
                          int statut, String nom, String prenom) {
        super(id, mdp, mail, statut, nom, prenom);
    }




}
