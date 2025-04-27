/* Source : https://sql.sh/cours/create-table/auto_increment
Série de vidéo : https://www.youtube.com/watch?v=n2_1tYv5oY8
Vidéo : https://www.youtube.com/watch?v=nIQatIpL_GE

ALLER VOIR L INTERFACE DAOFormulaire.java POUR LES COMMENTAIRES JAVADOC DE CE FICHIER
 */

package DAO;

import Modele.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Gestion de l'inscription et de la connexion des utilisateurs
public class DAOFormulaireIMPL implements DAOFormulaire {
    private DAOFactory daoFactory;

    //Constructeur de la classe DAOFormulaireIMPL
    public DAOFormulaireIMPL(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    //Méthode inscrireClient(), vérifie le remplissage des champs, enregistrement du client dans la table mère User et fille Client
    public boolean inscrireClient(String nom, String prenom, String dateNaissance, String email, String adresse, String mdp, String confirmerMdp) {

        //Vérification du remplissage des champs d'inscription
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || adresse.isEmpty()|| mdp.isEmpty()) {
            return false;
        }

        //Vérification que le mdp et la confirmation sont identiques
        if (!mdp.equals(confirmerMdp)) {
            return false;
        }

        try (Connection connexion = daoFactory.getConnection()){

            int newIdUser= -1;

            // 1. Requete SQL d'insertion d'un nouvel utilisateur dans la table User
            String insertUserSQL= "INSERT INTO user (nomUser, prenomUser, mailUser, mdpUser, statutUser) VALUES (?,?,?,?,?)";
            try(PreparedStatement preparedStatement = connexion.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS)) {

                //Remplissage des paramètres de la requête
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, prenom);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, mdp);
                preparedStatement.setInt(5, 0); //  0 = client, 1 = admin

                //Execution
                preparedStatement.executeUpdate();

                // Récupération de la clé primaire générée (idUser)
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        newIdUser = generatedKeys.getInt(1); // Lire l'ID généré automatiquement dans la BD et l'enregistré dans la table Client
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
            String insertClientSQL = "INSERT INTO Client (idUser, dateNaissanceClient, dateAjoutClient, adresseClient) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connexion.prepareStatement(insertClientSQL)) {
                preparedStatement.setInt(1, newIdUser);
                preparedStatement.setString(2, dateNaissance); // ATTENTION : Date au format "aaaa-mm-jj"
                preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis())); //Date du jour
                preparedStatement.setString(4, adresse);

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
            String query = "SELECT * FROM User WHERE mailUser = ? AND mdpUser = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, mdp);

            //Exécution
            ResultSet resultats = preparedStatement.executeQuery();

            //Correspondance dans la base
            if (resultats.next()) {
                int id = resultats.getInt("IDUser");
                String mail = resultats.getString("mailUser");
                String motDePasse = resultats.getString("mdpUser");
                int statut = resultats.getInt("statutUSer");

                // Création d'un objet User à partir des infos de l'utilisateur
                return new User(id, motDePasse, mail, statut, "", "");
            } else {
                return null; //Pas d'utilisateur corespondant trouvé
            }
        } catch (SQLException e) {
            e.printStackTrace(); //Erreur SQL
            return null;
        }

    }


    public DAOFactory getDaoFactory() {
        return this.daoFactory;
    }

}

