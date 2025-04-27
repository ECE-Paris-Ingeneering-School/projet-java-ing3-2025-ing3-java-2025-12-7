package DAO;

// import des packages
import Modele.Article;
import java.util.ArrayList;

/**
 * On utilise une interface DAOArticle pour définir les méthodes d'accès aux données de la table produit,
 * indépendamment de la méthode de stockage. On indique juste des noms de méthodes ici.
 */
public interface DAOArticle {
    /**
     * Récupérer de la base de données tous les objets des articles dans une liste
     * @return : liste retournée des objets des articles récupérés
     */
    public ArrayList<Article> getAll();

    /**
     Ajouter un nouvel article en paramètre dans la base de données
     @params : article = objet de l'Article en paramètre à insérer dans la base de données
     */
    public void ajouter(Article article);

    /**
     * Permet de chercher et récupérer une liste  d'objets d' Article dans la base de données via une recherche en paramètre
     * On regarde sa marque, son nom, son type et sa catégorie
     * @param : demande
     * @return : liste d'objets de l'Article cherchée et retournée
     */
    public ArrayList<Article> rechercher(String demande);



    /**
     * Permet de modifier les données du nom de l'objet de la classe Article en paramètre
     * dans la base de données à partir de l'id de cet objet en paramètre
     * @param : article = objet en paramètre de la classe Article à mettre à jour
     * @return : objet article en paramètre mis à jour  dans la base de données à retourner
     */
    public Article modifier(Article article);

    /**
     * Supprimer un objet de la classe Article en paramètre dans la base de données
     * @params : article = objet de Article en paramètre à supprimer de la base de données
     */
    public void retirer (int id);

    /**
     * Permet de retourner un article en fonction de son id
     * @param id en parametre
     * @return  : objet article cherché
     */
    public Article unarticle (int id);



}

