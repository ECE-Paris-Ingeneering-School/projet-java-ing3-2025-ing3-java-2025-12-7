package DAO;
// import des packages
import Modele.Panier;
import java.util.ArrayList;
import Modele.Article;

public interface DAOPanier {


    public ArrayList<Panier> getAll();


    /**
     * Récupérer de la base de données tous les articles d'un client dans une liste
     * @return : liste retournée des articles dans un panier d'un client spécifique
     */
    public Panier lesArticles(int IDClient);


    /**
     Ajouter un nouvel article en paramètre dans un panier dans la base de données
     @params : article = objet de l'Article en paramètre à insérer dans le panier d'un client spécifique  dans la base de données
     */
    public void nouveauPanier(int IDClient);


    /**
     Ajouter un nouvel article en paramètre dans un panier dans la base de données
     @params : article = objet de l'Article en paramètre à insérer dans le panier d'un client spécifique  dans la base de données
     */
    public void ajouter(Article article, int IDClient);


    /**
     * Supprimer un objet de la classe Article en paramètre dans la base de données
     * @params : article = objet de Article en paramètre à supprimer dans le panier d'un client spécifique de la base de données
     */
    public void retirer (int idClient);


    /**
     * Permet de commander un panier
     * @param idClient en parametre
     * @return  : objet panier commandé
     */
    public Panier commander (int idClient);

    /**
     Ajouter un nouvel article en paramètre dans un panier dans la base de données
     @params : article = objet de l'Article en paramètre à insérer dans le panier d'un client spécifique  dans la base de données
     */
    public void PLUS1(Article article, int IDClient);

    /**
     Ajouter un nouvel article en paramètre dans un panier dans la base de données
     @params : article = objet de l'Article en paramètre à insérer dans le panier d'un client spécifique  dans la base de données
     */
    public void MOINS1(Article article, int IDClient);

}
