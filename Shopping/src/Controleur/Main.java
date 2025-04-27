package Controleur;
import DAO.DAOFactory;
import DAO.DAOFormulaireIMPL;
import Vue.Mywindow;
import Vue.VueConnexion;
import Vue.VueInscription;
import javax.swing.*;



public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        SwingUtilities.invokeLater(() -> {

            // Initialiser la DAOFactory
            DAOFactory dao = DAOFactory.getInstance("shoppingBD", "root", "root");


            // Créer et afficher la fenêtre de connexion
            new VueConnexion(new DAOFormulaireIMPL(dao));
        });
    }
}