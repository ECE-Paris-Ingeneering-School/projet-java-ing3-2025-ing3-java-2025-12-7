package DAO;


// import des packages
import Modele.Article;
import java.sql.*;
import java.util.ArrayList;

public class DAOArticleIMPL implements DAOArticle {

    // attribut privé pour l'objet du DAOFactoru
    private DAOFactory daoFactory;

    // constructeur dépendant de la classe DAOFactory
    public DAOArticleIMPL(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    /**
     * Récupérer de la base de données tous les objets des articles dans une liste
     * @return : liste retournée des objets des articles récupérés
     */
    public ArrayList<Article> getAll() {
        ArrayList<Article> listeArticles = new  ArrayList<Article>();

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


                int IDArticle = resultats.getInt(1);
                String nomArticle = resultats.getString(2);
                int quantiteArticle = resultats.getInt(3);
                float prixArticle = resultats.getFloat(4);
                String marqueArticle = resultats.getString(5);
                String typeArticle = resultats.getString(6);
                String categorieArticle = resultats.getString(7);
                float reductionArticle = resultats.getFloat(8);
                java.util.Date dateAjoutArticle = resultats.getDate(9);
                java.util.Date datePeremptionArticle = resultats.getDate(11);
                String imageArticle = resultats.getString(10);





                // instancier un objet de Article avec ces 11 champs en paramètres
                Article article = new Article(IDArticle,nomArticle,quantiteArticle,prixArticle,marqueArticle,typeArticle,categorieArticle,reductionArticle,dateAjoutArticle,datePeremptionArticle,imageArticle);

                // ajouter ce produit à listeArticles
                listeArticles.add(article);
            }
        }
        catch (SQLException e) {
            //traitement de l'exception
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }

        return listeArticles;
    }

    @Override
    /**
     Ajouter un nouvel article en paramètre dans la base de données
     @params : article = objet de l'Article en paramètre à insérer dans la base de données
     */
    public void ajouter(Article article) {
        try {
            // connexion
            Connection connexion = daoFactory.getConnection();

            // récupération des paramètres de l'objet article en paramètre
            String nom = article.getNomArticle();
            float prix = article.getPrixArticle();
            int quantite =article.getQuantiteArticle() ;
            String marque=article.getMarqueArticle();
            String type=article.getTypeArticle();
            String categorie=article.getCategorieArticle();
            float reduction=article.getReductionArticle();
            java.util.Date dateAjout=  article.getDateAjoutArticle();
            java.util.Date datePeremption=article.getDatePeremptionArticle();
            String image=  article.getImageArticle();

            // Exécution de la requête INSERT INTO de l'objet article en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO produit(`nomProduit`, `quantiteProduit`, `prixProduit`, `marqueProduit`, `typeProduit`, `categorieProduit`, `reductionProduit`, `dateAjoutProduit`, `datePeremptionProduit`, `photoProduit`) VALUES ("+nom+","+prix+","+marque+","+type+","+categorie+","+reduction+","+dateAjout+","+datePeremption+","+image+")");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ajout de l'article impossible");
        }
    }

    @Override
/**
 * Permet de chercher et récupérer des objet d'Article dans la base de données via une demande en paramètre
 * @param : demande
 * @return : liste d'objets d'Article cherchée et retournée
 */
    public ArrayList<Article>  rechercher(String demande){
        Article article = null;
        ArrayList<Article> listeArticles = new  ArrayList<Article>();

        try {
            // connexion
            Connection connexion = daoFactory.getConnection();;
            Statement statement = connexion.createStatement();


            // Exécution de la requête SELECT pour récupérer les produit de la demande dans la base de données

            String sql = "SELECT * FROM produit WHERE nomProduit =? OR marqueProduit=? OR typeProduit=? OR categorieProduit=?";
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, demande);
            stmt.setString(2, demande);
            stmt.setString(3, demande);
            stmt.setString(4, demande);

            ResultSet resultats = stmt.executeQuery();

            // 	Se déplacer sur le prochain enregistrement : retourne false si la fin est atteinte
            while (resultats.next()) {
                // récupérer les 11 champs de la table produit dans la base de données
                // récupération des 11 champs due l'article de la base de données
                int IDArticle = resultats.getInt(1);
                String nomArticle = resultats.getString(2);
                int quantiteArticle = resultats.getInt(3);
                float prixArticle = resultats.getFloat(4);
                String marqueArticle = resultats.getString(5);
                String typeArticle = resultats.getString(6);
                String categorieArticle = resultats.getString(7);
                float reductionArticle = resultats.getFloat(8);
                java.util.Date dateAjoutArticle = resultats.getDate(9);
                java.util.Date datePeremptionArticle = resultats.getDate(11);
                String imageArticle = resultats.getString(10);

                // Si un article correspondant à la demande est trouvé, l'instancier et l'ajouter à la liste

                    // instanciation de l'objet d'Article avec Ses 11 champs puis ajout dans la liste
                    article = new Article(IDArticle,nomArticle,quantiteArticle,prixArticle,marqueArticle,typeArticle,categorieArticle,reductionArticle,dateAjoutArticle,datePeremptionArticle,imageArticle);
                    listeArticles.add(article);
                    System.out.println(listeArticles.size());

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Pas d'articles correspondant à votre recherche");
        }

        return listeArticles;
    }

    @Override
    /**
     * Permet de modifier les données du nom de l'objet de la classe Produit en paramètre
     * dans la base de données à partir de l'id de cet objet en paramètre
     * @param : product = objet en paramètre de la classe Produit à mettre à jour
     * @return : objet product en paramètre mis à jour  dans la base de données à retourner
     */
    public Article modifier(Article article) {
        try {
            // connexion
            Connection connexion = daoFactory.getConnection();

            // récupération des paramètres de l'objet article en paramètre
            int IDArticle = article.getIDArticle();
            String nom = article.getNomArticle();
            float prix = article.getPrixArticle();
            int quantite =article.getQuantiteArticle() ;
            String marque=article.getMarqueArticle();
            String type=article.getTypeArticle();
            String categorie=article.getCategorieArticle();
            float reduction=article.getReductionArticle();
            java.util.Date dateAjout=  article.getDateAjoutArticle();
            java.util.Date datePeremption=article.getDatePeremptionArticle();
            String image=  article.getImageArticle();

            // Exécution de la requête INSERT INTO de l'objet article en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE produit set nomProduit=nom, quantiteProduit=quantite, prixProduit=prix, marqueProduit=marque, typeProduit=type, categorieProduit=categorie, reductionProduit=reduction, dateAjoutProduit=ajout, datePeremptionProduit=peremption, photoProduit=image)WHERE idProduit= "+IDArticle);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Modification de l'article impossible");
        }

        return article;
    }

    @Override
    /**
     * Supprimer un objet de la classe Produit en paramètre dans la base de données en respectant la contrainte
     * d'intégrité référentielle : en supprimant un produit, supprimer aussi en cascade toutes les commandes de la
     * table commander qui ont l'id du produit supprimé.
     * @params : product = objet de Produit en paramètre à supprimer de la base de données
     */
    public void retirer (int id) {


        try {
            // connexion
            Connection connexion = daoFactory.getConnection();;
            PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM produit WHERE IDArticle="+id);
            preparedStatement.executeUpdate();
            // 	Se déplacer sur le prochain enregistrement : retourne false si la fin est atteinte

        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Impossible de supprimer l'article");
        }



    }

    @Override
    public Article unarticle(int id){
        Article article = null;

        try {
            // connexion
            Connection connexion = daoFactory.getConnection();;
            Statement statement = connexion.createStatement();

            // Exécution de la requête SELECT pour récupérer l'article de l'id dans la base de données
            ResultSet resultats = statement.executeQuery("select * from produit where idProduit="+id);

            // 	Se déplacer sur le prochain enregistrement : retourne false si la fin est atteinte
            while (resultats.next()) {
                // récupérer les 11 champs de la table produit dans la base de données
                // récupération des 11 champs de l'article de la base de données
                int IDArticle = resultats.getInt(1);
                String nomArticle = resultats.getString(2);
                int quantiteArticle = resultats.getInt(3);
                float prixArticle = resultats.getFloat(4);
                String marqueArticle = resultats.getString(5);
                String typeArticle = resultats.getString(6);
                String categorieArticle = resultats.getString(7);
                float reductionArticle = resultats.getFloat(8);
                java.util.Date dateAjoutArticle = resultats.getDate(9);
                java.util.Date datePeremptionArticle = resultats.getDate(11);
                String imageArticle = resultats.getString(10);

                // Si l'id de l'article est trouvé, l'instancier et sortir de la boucle
                if (id == IDArticle) {
                    // instanciation de l'objet de Article avec ses 11 champs
                    article = new Article(IDArticle,nomArticle,quantiteArticle,prixArticle,marqueArticle,typeArticle,categorieArticle,reductionArticle,dateAjoutArticle,datePeremptionArticle,imageArticle);
                    break;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Article non trouvé dans la base de données");
        }

        return article;
    }

}

