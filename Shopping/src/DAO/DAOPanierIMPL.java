package DAO;

import Modele.Article;
import Modele.Panier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DAOPanierIMPL implements DAOPanier{

    private DAOFactory daoFactory;

    // constructeur dépendant de la classe DAOFactory
    public DAOPanierIMPL(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public ArrayList<Panier> getAll() {
        ArrayList<Panier> listePanier = new  ArrayList<Panier>();

        /*
            Récupérer la liste des articles de la base de données dans listeArticles
        */
        try {
            // connexion
            Connection connexion = daoFactory.getConnection();;
            Statement statement = connexion.createStatement();

            // récupération des articles de la base de données avec la requete SELECT
            ResultSet resultats = statement.executeQuery("select * from produit");

            // 	Se déplacer sur le prochain enregistrement : retourne false si la fin est atteinte
            while (resultats.next()) {
                // récupérer les 11 champs de la table produit dans la base de données


                int IDPanier = resultats.getInt(1);
                int IDUser = resultats.getInt(2);
                String ListeIDProduit = resultats.getString(3);
                String ListeIDQuantite = resultats.getString(4);
                float montant = resultats.getFloat(5);





                // instancier un objet de Article avec ces 11 champs en paramètres
                Panier panier = new Panier(IDPanier,IDUser,ListeIDProduit,ListeIDQuantite,montant);

                // ajouter ce produit à listeArticles
                listePanier.add(panier);
            }
        }
        catch (SQLException e) {
            //traitement de l'exception
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }

        return listePanier;
    }

    @Override
    /**
     * Récupérer de la base de données tous les articles d'un client dans une liste
     * @return : liste retournée des articles dans un panier d'un client spécifique
     */
    public Panier lesArticles(int IDC){
        Panier panier = null;

        try {
            // connexion
            Connection connexion = daoFactory.getConnection();;
            Statement statement = connexion.createStatement();

            // Exécution de la requête SELECT pour récupérer l'article de l'id dans la base de données
            ResultSet resultats = statement.executeQuery("select * from panier where IDClient="+IDC);

            // 	Se déplacer sur le prochain enregistrement : retourne false si la fin est atteinte
            while (resultats.next()) {
                // récupérer les 11 champs de la table panier dans la base de données
                // récupération des 11 champs de l'article de la base de données
                int IDPanier = resultats.getInt(1);
                int IDClient = resultats.getInt(2);
                String listeArticles =resultats.getString(3);
                String listeQuantites = resultats.getString(4);
                float prix = resultats.getFloat(5);
                //java.util.Date date = new java.util.Date(resultats.getLong(6));

                // Si l'id de l'article est trouvé, l'instancier et sortir de la boucle
                if (IDC == IDPanier) {
                    // instanciation de l'objet de Article avec ses 11 champs
                    panier = new Panier(IDPanier,IDClient,listeArticles,listeQuantites,prix);
                    break;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Panier non trouvé dans la base de données");
        }

        return panier;
    }

    @Override
    /**
     Ajouter un nouvel article en paramètre dans un panier dans la base de données
     @params : article = objet de l'Article en paramètre à insérer dans le panier d'un client spécifique  dans la base de données
     */
    public void nouveauPanier(int IDClient){


    }

    @Override
    /**
     Ajouter un nouvel article en paramètre dans un panier dans la base de données
     @params : article = objet de l'Article en paramètre à insérer dans le panier d'un client spécifique  dans la base de données
     */
    public void ajouter(Article article, int IDClient){


    }


    @Override
    /**
     * Supprimer un objet de la classe Article en paramètre dans la base de données
     * @params : article = objet de Article en paramètre à supprimer dans le panier d'un client spécifique de la base de données
     */
    public void retirer (int idClient){

    }

    @Override
    /**
     * Permet de commander un panier
     * @param idClient en parametre
     * @return  : objet panier commandé
     */
    public Panier commander (int idClient){
        return null;
    }

    @Override
    /**
     * Supprimer un objet de la classe Article en paramètre dans la base de données
     * @params : article = objet de Article en paramètre à supprimer dans le panier d'un client spécifique de la base de données
     */
    public void PLUS1 (Article article,int idClient){

    }
    @Override
    /**
     * Supprimer un objet de la classe Article en paramètre dans la base de données
     * @params : article = objet de Article en paramètre à supprimer dans le panier d'un client spécifique de la base de données
     */
    public void MOINS1 (Article article, int idClient){

    }
}
