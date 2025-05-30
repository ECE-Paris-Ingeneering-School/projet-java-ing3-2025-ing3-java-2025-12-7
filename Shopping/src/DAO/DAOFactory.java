package DAO;

import Modele.Article;


import java.sql.*;
import java.util.ArrayList;

/**
 * La DAO Factory (DaoFactory.java) permet d'initialiser le DAO en chargeant notamment les drivers nécessaires
 * (ici un driver JDBC MySQL) et se connecte à la base de données. La Factory peut fournir plusieurs DAO (ici,
 * il n'y en a qu'un seul, UtilisateurDao, qui correspond à une table de la base).
 */

public class DAOFactory {

    /**
     * Attributs private pour la connexion JDBC
     */
    private static String url;
    private String username;
    private String password;

    // constructeur
    public DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * Méthode qui retourne 1 objet de DaoFactory
     * @param : url, username et password de la base de données
     * @return : objet de la classe DaoFactoru
     */
    public static DAOFactory getInstance(String database, String username, String password) {
        try {
            // chargement driver "com.mysql.cj.jdbc.Driver"
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {

            System.out.println("Erreur de connexion à la base de données");
            throw new RuntimeException("Driver MySQL introuvalble", e);
        }

        url = "jdbc:mysql://localhost:3306/" + database; //REGARDER S'IL FAUT AJOUTER 8888 PARCE QUE POUR CERTAIN ORDI CE N'EST PAS LE MÊME LIEN

        // Instancier une instance l'objet de DaoFactory
        DAOFactory instance = new DAOFactory(url, username,password );

        // Retourner cette instance
        return instance;
    }

    /**
     * Méthode qui retourne le driver de base de données approprié
     * @return : le driver approprié
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        // Retourner la connection du driver de la base de données
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Récupération du Dao pour le produit
     * @return : objet de la classe DAOArticleIMPL
     */
    public DAOArticle getDAOArticle() {
        // Retourner un objet de DAOArticleIMPL qui implémente DAOArticle
        return new DAOArticleIMPL(this);
    }

    /**
     * Récupération du Dao pour les paniers
     * @return : objet de la classe DAOPanierIMPL
     */
    public DAOPanier getPanierDAO() {
        // Retourner un objet de DAOPanierIMPL qui implémente DAOPanier
        return new DAOPanierIMPL(this);
    }

    /**
     * Récupération du Dao pour les commandes
     * @return : objet de la classe DAOCommandeIMPL
     */
    public DAOCommande getCommandeDAO() {
        // Retourner un objet de DAOCommandeIMPL qui implémente DAOCommande
        return new DAOCommandeIMPL(this);
    }

    /**
     * Récupération du Dao pour les administrateurs
     * @return : objet de la classe DAOAdminIMPL
     */
    public DAOAdmin getAdminDAO() {
        // Retourner un objet de DAOCommandeIMPL qui implémente DAOCommande
        return new DAOAdminIMPL(this);
    }

    /**
     * Récupération du Dao pour les clients
     * @return : objet de la classe DAOClientIMPL
     */
    public DAOClient getClientDAO() {
        // Retourner un objet de DAOCommandeIMPL qui implémente DAOCommande
        return new DAOClientIMPL(this);
    }


    /**
     *     Fermer la connexion à la base de données
     */
    public void disconnect() {
        Connection connexion = null;

        try {
            // création d'un ordre SQL (statement)
            connexion = this.getConnection();
            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur de déconnexion à la base de données");
        }
    }
}
