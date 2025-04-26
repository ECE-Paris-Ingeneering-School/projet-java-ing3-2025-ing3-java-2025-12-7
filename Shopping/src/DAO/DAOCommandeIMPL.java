package DAO;


import Modele.Commande;
import Modele.Panier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class DAOCommandeIMPL implements DAOCommande{

    private DAOFactory daoFactory;

    // constructeur dépendant de la classe DAOFactory
    public DAOCommandeIMPL(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Créer une nouvelle commande dans la base de données et retourne la commande pour le controleur Commande
     * @param panier
     * @param IDClient
     * @return
     */
    @Override
    public Commande nouvelleCommande(Panier panier, int IDClient) {
        //1. recup les infos du panier 2; insert dans commande avec l'id du client les param du paniers
        Commande commande = new Commande();
        String listeProduits = panier.getArticles();
        String lesquantite = panier.getQuantite();
        float montant = panier.getMontant();
        java.util.Date date = null;//HELP SALIM

        String[] resultat = listeProduits.split(",");
        int nombre=0;
        for (int i = 0; i < resultat.length; i++) {
            nombre += Integer.parseInt(resultat[i]);
        }

        try{

            // connexion
            Connection connexion = daoFactory.getConnection();



            // Exécution de la requête INSERT INTO de l'objet article en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "INSERT INTO commande(idUser, montantCommande, produitsCommandes, quantiteProdCom, dateCommande) VALUES (?, ?, ?, ?, ?)"
            );

            preparedStatement.setInt(1, IDClient);
            preparedStatement.setFloat(2, montant);
            preparedStatement.setString(3, listeProduits);
            preparedStatement.setString(4, lesquantite);
            preparedStatement.setDate(5, new java.sql.Date(date.getTime()));

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ajout de la commande impossible");

        }

        return null;
    }

}
