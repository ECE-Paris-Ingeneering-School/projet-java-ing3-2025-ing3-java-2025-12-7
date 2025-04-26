package DAO;

import Modele.Commande;
import Modele.Panier;

public interface DAOCommande {

    public Commande nouvelleCommande(Panier panier, int IDClient);



}
