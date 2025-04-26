package DAO;
// import des packages
import Modele.Panier;
import java.util.ArrayList;
import Modele.Article;

public interface DAOPanier {

    /**
     * Retourne tous les paniers de la base de données
     * @return la liste des paniers
     */
    public ArrayList<Panier> getAll();


    /**
     * Récupérer dans la base de données le panier d'un client spécifique
     * @return : le panier d'un client spécifique
     */
    public Panier UnPanier(int IDClient);


    /**
     Créer un nouveau panier pour un client spécifique (lors de sa première connexion, ou après une commande)
     @params : IDClient = Id du client qui n'a pas encore de panier
     */
    public void nouveauPanier(int IDClient);


    /**
     * Permet d'ajouter un article dans le panier déjà existant d'un client
     * @param article : l'article que l'on souhaite ajouter
     * @param IDClient : le client dont on doit changer le panier
     */
    public void ajouter(Article article, int IDClient);


    /**
     * Permet de retirer un article du panier d'un client spécifique
     * @param idClient: le client dont on doit modifier le panier
     * @param article : l'article que l'on doit retirer du panier
     */
    public void retirer (int idClient, Article article);


    /**
     * PAS SURE QUE CE SOIT UTILE
     * Permet de commander un panier
     * @param idClient en parametre
     * @return  : objet panier commandé
     */
    public Panier commander (int idClient);

    /**
     * Permet d'augmenter la quantité de 1 d'un article spécifique dans un panier
     * @param article : l'article dont on souhaite augmenter la quantité
     * @param IDClient : le client dont on souhaite modifier le panier
     */
    public void PLUS1(Article article, int IDClient);

    /**
     * Permet de diminuer la quantité de 1 d'un article spécifique dans un panier
     * @param article : l'article dont on souhaite diminuer la quantité
     * @param IDClient : le client dont on souhaite modifier le panier
     */
    public void MOINS1(Article article, int IDClient);

}
