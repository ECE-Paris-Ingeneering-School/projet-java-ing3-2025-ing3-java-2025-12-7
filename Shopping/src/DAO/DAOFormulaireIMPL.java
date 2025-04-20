package DAO;

import Modele.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOFormulaireIMPL implements DAOFormulaire {
    private DAOFactory daoFactory;

    // constructeur dépendant de la classe DAOFactory
    public DAOFormulaireIMPL(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    //Méthode inscrireClient(), vérifie le remplissage des champs, enregistrement du client dans la table mère User et fille Client
    public boolean inscrireClient(String nom, String dateNaissance, String email, String mdp, String confirmerMdp) {

        // Vérification du remplissage des champs
        if (nom.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
            return false;
        }

        //Vérification que le mdp et la confirmation sont identiques
        if (!mdp.equals(confirmerMdp)) {
            return false;
        }

        try (Connection connexion = daoFactory.getConnection()){

            int newIdUser= -1;

            // 1. Requete SQL d'insertion d'un nouvel utilisateur dans la table User
            String insertUserSQL= "INSERT INTO user (loginUser, mailUser, mdpUser, statutUser) VALUES (?, ?, ?, ?)";
            try(PreparedStatement preparedStatement = connexion.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS)) {

                //Remplissage des paramètres de la requête
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, mdp);
                preparedStatement.setInt(4, 1); // 1 = client, 0 = admin

                //Execution
                preparedStatement.executeUpdate();

                // Récupération de la clé primaire générée (idUser)
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        newIdUser = generatedKeys.getInt(1); // ID généré automatiquement et enregistré dans la table Client
                    } else {
                        return false; //Erreur : pas de clé générée
                    }
                }
            }

            //vérification de la création d'un nouvel user
            if (newIdUser == -1) {
                return false; //Erreur : utilisateur pas inséré
            }

            // 2. Requete SQL d'insertion dans la table Client
            String insertClientSQL = "INSERT INTO Client (idUser, dateNaissanceClient, dateAjoutClient) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connexion.prepareStatement(insertClientSQL)) {
                preparedStatement.setInt(1, newIdUser);
                preparedStatement.setString(2, dateNaissance); // ATTENTION : Date au format "aaaa-mm-jj"
                preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis())); //Date du jour

                //Exécution de l'insertion dans Client
                preparedStatement.executeUpdate();
            }

            return true; // Inscription réussie
        } catch(SQLException e) {
            e.printStackTrace();
            return false; // Erreur SQL
        }
    }

    //Méthode connecterClient(), récupère l'email et mdp d'un user et va le chercher dans la table mère User
    public User connecterClient(String email, String mdp) {

        // Vérification du remplissage des champs
        if (email.isEmpty() || mdp.isEmpty()) {
            return null;
        }

        //Requete SQL de vérification des infos rentrées par l'utilisateur dans la base de données
        try (Connection connexion = daoFactory.getConnection()){
            String query = "SELECT * FROM user WHERE mailUser = ? AND mdpUser = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, mdp);
            ResultSet resultats = preparedStatement.executeQuery();

            //Correspondance dans la base : création d'un objet User
            if (resultats.next()) {
                int id = resultats.getInt("IDUser");
                String login = resultats.getString("loginUser");
                String mail = resultats.getString("mailUser");
                String motDePasse = resultats.getString("mdpUser");
                int statut = resultats.getInt("statutUSer");

                // Crée l'utilisateur sans les infos inutiles (nom, prenom, etc.)
                return new User(id, login, motDePasse, mail, statut, "", "", "", "");
            } else {
                return null; //Pas d'utilisateur corespondant trouvé
            }
        } catch (SQLException e) {
            e.printStackTrace(); //Erreur SQL
            return null;
        }

    }
}

