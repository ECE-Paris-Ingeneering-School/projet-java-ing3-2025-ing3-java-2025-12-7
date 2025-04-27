package DAO;

import Modele.Client;
import Modele.User;

public interface DAOFormulaire {

    /**
     * @author Laure Petit
     * Permet d'inscrire un nouveau client dans la base de données.
     * Ajoute ses informations dans les tables User et Client.
     *
     * @param nom Nom de l'utilisateur
     * @param prenom Prénom de l'utilisateur
     * @param dateNaissance Date de naissance de l'utilisateur
     * @param email Adresse email de l'utilisateur
     * @param adresse Adresse postale de l'utilisateur
     * @param mdp Mot de passe de l'utilisateur
     * @param confirmerMdp Confirmation du mot de passe
     * @return true si l'inscription a réussi, false sinon
     */
    boolean inscrireClient(String nom, String prenom, String dateNaissance, String email, String adresse, String mdp, String confirmerMdp);

    /**
     * @author Laure Petit
     * Permet de connecter un client en vérifiant son email et son mot de passe.
     * @param email Email saisi par l'utilisateur
     * @param mdp Mot de passe saisi par l'utilisateur
     * @return Un objet de USER si les informations rentrées sont correctes, null sinon
     */
    User connecterClient(String email, String mdp);

    DAOFactory getDaoFactory();
}
