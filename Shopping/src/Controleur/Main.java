package Controleur;
import DAO.DAOFactory;
import DAO.DAOFormulaireIMPL;
import Vue.Mywindow;
import Vue.VueConnexion;
import Vue.VueInscription;

import javax.swing.*;




public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        SwingUtilities.invokeLater(Mywindow::new); //fenetre de l'accueil à décommenter si besoin
        /*DAOFactory dao = DAOFactory.getInstance("shopping", "root", "root");
        DAOFormulaireIMPL daoFormulaire = new DAOFormulaireIMPL(dao);

        JFrame parent = new JFrame();
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parent.setVisible(false);

        //Appel de la fenetre de connexion
        new VueConnexion(parent, daoFormulaire);*/
    }
}