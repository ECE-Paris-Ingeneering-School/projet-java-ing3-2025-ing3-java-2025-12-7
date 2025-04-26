package DAO;

import Modele.Article;
import Modele.Panier;

import java.sql.*;
import java.util.ArrayList;
import java.util.StringJoiner;

//DANS CHAQUE CODE DU DAO POUR LA CONNEXION A LA BASE DE DONNEES; IL FAUT ADAPTER LE MESSAGE ET LE STATEMENT DU CATCH A CHAQUE CODE

//        try {
//            // connexion
//            Connection connexion = daoFactory.getConnection();;
//            PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM panier WHERE IdUser="+idClient);
//            preparedStatement.executeUpdate();
//            // 	Se déplacer sur le prochain enregistrement : retourne false si la fin est atteinte
//
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("");
//        }


public class DAOPanierIMPL implements DAOPanier{

    private DAOFactory daoFactory;

    // constructeur dépendant de la classe DAOFactory
    public DAOPanierIMPL(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     *Retourne tous les paniers de la base de données
     *@return la liste des paniers
     */
    @Override
    public ArrayList<Panier> getAll() {
        ArrayList<Panier> listePanier = new  ArrayList<Panier>();

        /*
            Récupérer la liste des paniers de la base de données dans listePanier
        */
        try {
            // connexion
            Connection connexion = daoFactory.getConnection();;
            Statement statement = connexion.createStatement();

            // récupération des paniers de la base de données avec la requete SELECT
            ResultSet resultats = statement.executeQuery("select * from panier");

            // 	Se déplacer sur le prochain enregistrement : retourne false si la fin est atteinte
            while (resultats.next()) {
                // récupérer les 5 champs de la table panier dans la base de données

                int IDPanier = resultats.getInt(1);
                int IDUser = resultats.getInt(2);
                String ListeIDProduit = resultats.getString(3);
                String ListeIDQuantite = resultats.getString(4);
                float montant = resultats.getFloat(5);

                // instancier un objet de Panier avec ses 5 champs en paramètres
                Panier panier = new Panier(IDPanier,IDUser,ListeIDProduit,ListeIDQuantite,montant);

                // ajouter ce produit à listePaniers
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
     * Récupérer dans la base de données le panier d'un client spécifique
     * @return : le panier d'un client spécifique
     */
    public Panier UnPanier(int IDC){
        Panier panier = null;

        try {
            // connexion
            Connection connexion = daoFactory.getConnection();;
            Statement statement = connexion.createStatement();

            // Exécution de la requête SELECT pour récupérer l'article de l'id dans la base de données
            ResultSet resultats = statement.executeQuery("select * from panier where idUser="+IDC);

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
                if (IDC == IDClient) {
                    // instanciation de l'objet de Article avec ses 11 champs
                    panier = new Panier(IDPanier,IDClient,listeArticles,listeQuantites,prix);
                    break;
                }
            }
            System.out.println("Panier trouvé dans la base de données");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Panier non trouvé dans la base de données");
        }

        return panier;
    }

    @Override
    /**
     Créer un nouveau panier pour un client spécifique (lors de sa première connexion, ou après une commande)
     @params : IDClient = Id du client qui n'a pas encore de panier
     */
    public void nouveauPanier(int IDClient){
        try {
            // connexion
            Connection connexion = daoFactory.getConnection();

            Panier panier = new Panier();
            // initialisation des paramètres

            float montant = panier.getMontant();
            String articles = panier.getArticles();
            String quantites = panier.getQuantite();


            // Exécution de la requête INSERT INTO de l'objet panier
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "INSERT INTO panier(idUser, listeIDProduits, listeQuantite, montantPanier) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setInt(1, IDClient);
            preparedStatement.setString(2, articles);
            preparedStatement.setString(3, quantites);
            preparedStatement.setFloat(4, montant);

            //Execution
            preparedStatement.executeUpdate();


            System.out.println("Ajout du panier ok");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ajout du panier impossible");
        }

    }

    @Override
    /**
     * Permet d'ajouter un article dans le panier déjà existant d'un client
     * @param article : l'article que l'on souhaite ajouter
     * @param IDClient : le client dont on doit changer le panier
     */
    public void ajouter(Article article, int IDClient){

        //1. Recupere le panier du client avec IDClient
        Panier panier = new Panier();

        try{

            //Connexion à la base de données
            Connection connexion = daoFactory.getConnection();
            Statement statement = connexion.createStatement();

            DAOPanier daoPanier = daoFactory.getPanierDAO();

            //Récupère le panier du client
            panier=daoPanier.UnPanier(IDClient);
            if (panier == null) {
                System.out.println("Erreur : aucun panier trouvé pour l'utilisateur " + IDClient);
                return; // ou lancer une exception personnalisée
            }

            //Récupère les deux string des informations : ID des articles et quantités
            String lesArticles= panier.getArticles();
            String lesQuantites= panier.getQuantite();

            //Ajoute l'id du nouvel article à la liste
            if (lesArticles == null || lesArticles.isEmpty()) {
                lesArticles = String.valueOf(article.getIDArticle());
            } else {
                lesArticles = lesArticles + "," + article.getIDArticle();
            }

            //Ajoute une quantité 1 à la liste des quantités
            if (lesQuantites == null || lesQuantites.isEmpty()) {
                lesQuantites = "1";
            } else {
                lesQuantites = lesQuantites + ",1";
            }

            String[] articles = lesArticles.split(",");
            String[] quantites = lesQuantites.split(",");

            int[] arts=new int[articles.length];
            for (int i=0;i<articles.length;i++){
                arts[i]=Integer.parseInt(articles[i]);
            }

            int[] quants = new int[quantites.length];
            for (int i=0;i<quantites.length;i++){
                quants[i]=Integer.parseInt(quantites[i]);
            }


            float montant=panier.calculMontant(arts,quants);
            // Exécution de la requête UPDATE pour les nouvelles listes
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "UPDATE panier SET listeIDProduits = ?, listeQuantite = ?,montantPanier=? WHERE idUser = ?"
            );
            preparedStatement.setString(1, lesArticles);
            preparedStatement.setString(2, lesQuantites);
            preparedStatement.setFloat(3, montant);
            preparedStatement.setInt(4,IDClient);

            //Execution
            preparedStatement.executeUpdate();

            System.out.println("Article ajouté au panier");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Impossible de d'ajouter l'article au panier");
        }

    }


    @Override
    /**
     * Permet de retirer un article du panier d'un client spécifique
     * @param idClient: le client dont on doit modifier le panier
     * @param article : l'article que l'on doit retirer du panier
     */
    public void retirer (int idClient, Article article){

        Panier panier = new Panier();
        try{
            Connection connexion = daoFactory.getConnection();
            Statement statement = connexion.createStatement();

            DAOPanier daoPanier = daoFactory.getPanierDAO();

            panier=daoPanier.UnPanier(idClient);

            //Recupère les listes des id des produits et des quantites
            String lesArticles= panier.getArticles();
            String lesQuantites= panier.getQuantite();
            int IDPanier = panier.getIDPanier();

            //Trouve la position de l'article à supprimer
            int position = lesArticles.indexOf(article.getIDArticle());

            //Supprime l'article
            String[] articles = lesArticles.split(",");
            articles[position]="";

            //Retransforme en string la nouvelle liste des ID
            StringJoiner stringJoiner =  new StringJoiner(",",  "",  "") ;
            for (int i=0;i<articles.length;i++){
                stringJoiner.add(articles[i]);
            }
            String NlesArticles = stringJoiner.toString() ;


            //Supprime la quantité
            String[] quantites = lesQuantites.split(",");
            quantites[position]="";

            //Retransforme en string la nouvelle liste des quantités
            StringJoiner stringJoiner2 =  new StringJoiner(",",  "",  "") ;
            for (int i=0;i<quantites.length;i++){
                stringJoiner2.add(quantites[i]);
            }
            String NlesQuantites = stringJoiner2.toString() ;

            int[] arts=new int[articles.length];
            for (int i=0;i<articles.length;i++){
                arts[i]=Integer.parseInt(articles[i]);
            }

            int[] quants = new int[quantites.length];
            for (int i=0;i<quantites.length;i++){
                quants[i]=Integer.parseInt(quantites[i]);
            }


            float montant=panier.calculMontant(arts,quants);


            // Exécution de la requête UPDATE avec les nouvelles listes
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "UPDATE panier SET listeIDProduits = ?, listeQuantite = ? WHERE idPanier = ?"
            );
            preparedStatement.setString(1, NlesArticles);
            preparedStatement.setString(2, NlesQuantites);
            preparedStatement.setInt(3, IDPanier);

            //Execution
            preparedStatement.executeUpdate();

            System.out.println("Article retiré du panier");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Impossible de retirer l'article du panier");
        }

    }

    @Override
    /**
     * Permet de commander un panier
     * @param idClient en parametre
     * @return  : objet panier commandé
     */
    public Panier commander (int idClient){
        //1. recupere le panier avec l'id 2. appelle le Dao de créer une commande 3; juste utile parce qu'il faut vider le panier -> update retirer
        return null;
    }

    @Override
    /**
     * Permet d'augmenter la quantité de 1 d'un article spécifique dans un panier
     * @param article : l'article dont on souhaite augmenter la quantité
     * @param IDClient : le client dont on souhaite modifier le panier
     */
    public void PLUS1 (Article article,int idClient){

        Panier panier = new Panier();
        try{
            Connection connexion = daoFactory.getConnection();
            Statement statement = connexion.createStatement();

            DAOPanier daoPanier = daoFactory.getPanierDAO();

            panier=daoPanier.UnPanier(idClient);

            //Recupère les listes des id des produits et des quantites
            String lesArticles= panier.getArticles();
            String lesQuantites= panier.getQuantite();
            int IDPanier = panier.getIDPanier();

            //Trouve la position de l'article à augmenter
            String[] articles = lesArticles.split(",");
            String[] quantites = lesQuantites.split(",");

            int position = -1;
            for (int i = 0; i < articles.length; i++) {
                if (Integer.parseInt(articles[i]) == article.getIDArticle()) {
                    position = i;
                    break;
                }
            }

            if (position == -1) {
                System.out.println("Article non trouvé dans le panier !");
                return; // ou gérer autrement l'erreur
            }

            //Augmente la quantité
            int quantiteA = Integer.parseInt(quantites[position]);
            quantiteA++;
            quantites[position] = String.valueOf(quantiteA);

            //Retransforme en string la nouvelle liste des quantités
            StringJoiner stringJoiner2 =  new StringJoiner(",",  "",  "") ;
            for (int i=0;i<quantites.length;i++){
                stringJoiner2.add(quantites[i]);
            }
            String NlesQuantites = stringJoiner2.toString() ;


            int[] arts=new int[articles.length];
            for (int i=0;i<articles.length;i++){
                arts[i]=Integer.parseInt(articles[i]);
            }

            int[] quants = new int[quantites.length];
            for (int i=0;i<quantites.length;i++){
                quants[i]=Integer.parseInt(quantites[i]);
            }


            float montant=panier.calculMontant(arts,quants);

            // Exécution de la requête UPDATE avec la nouvelle liste
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "UPDATE panier SET listeQuantite = ?, montantPanier= ? WHERE idPanier = ?"
            );
            preparedStatement.setString(1, NlesQuantites);
            preparedStatement.setFloat(2, montant);
            preparedStatement.setInt(3, IDPanier);

            //Execution
            preparedStatement.executeUpdate();

            System.out.println("Quantité de l'article augmentée");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Impossible de d'augmenter la quantité de l'article");
        }
    }
    @Override
    /**
     * Permet de diminuer la quantité de 1 d'un article spécifique dans un panier
     * @param article : l'article dont on souhaite diminuer la quantité
     * @param IDClient : le client dont on souhaite modifier le panier
     */
    public void MOINS1 (Article article, int idClient){

        try{
            Connection connexion = daoFactory.getConnection();
            Statement statement = connexion.createStatement();

            DAOPanier daoPanier = daoFactory.getPanierDAO();

            Panier panier=daoPanier.UnPanier(idClient);

            //Recupère les listes des id des produits et des quantites
            String lesArticles= panier.getArticles();
            String lesQuantites= panier.getQuantite();
            int IDPanier = panier.getIDPanier();

            //Trouve la position de l'article à diminuer
            String[] articles = lesArticles.split(",");
            String[] quantites = lesQuantites.split(",");

            int position = -1;
            for (int i = 0; i < articles.length; i++) {
                if (Integer.parseInt(articles[i]) == article.getIDArticle()) {
                    position = i;
                    break;
                }
            }

            if (position == -1) {
                System.out.println("Article non trouvé dans le panier !");
                return; // ou gérer autrement l'erreur
            }

            //Diminue la quantité
            int quantiteA = Integer.parseInt(quantites[position]);
            quantiteA--;
            quantites[position] = String.valueOf(quantiteA);

            //Retransforme en string la nouvelle liste des quantités
            StringJoiner stringJoiner2 =  new StringJoiner(",",  "",  "") ;
            for (int i=0;i<quantites.length;i++){
                stringJoiner2.add(quantites[i]);
            }
            String NlesQuantites = stringJoiner2.toString() ;

            int[] arts=new int[articles.length];
            for (int i=0;i<articles.length;i++){
                arts[i]=Integer.parseInt(articles[i]);
            }

            int[] quants = new int[quantites.length];
            for (int i=0;i<quantites.length;i++){
                quants[i]=Integer.parseInt(quantites[i]);
            }


            float montant=panier.calculMontant(arts,quants);

            // Exécution de la requête UPDATE avec la nouvelle liste
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "UPDATE panier SET listeQuantite = ?, montantPanier=? WHERE idPanier = ?"
            );
            preparedStatement.setString(1, NlesQuantites);
            preparedStatement.setFloat(2, montant);
            preparedStatement.setInt(3,IDPanier);

            //Execution
            preparedStatement.executeUpdate();

            System.out.println("Quantité de l'article diminuée");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Impossible de de diminuer la quantité de l'article");
        }

    }
}
