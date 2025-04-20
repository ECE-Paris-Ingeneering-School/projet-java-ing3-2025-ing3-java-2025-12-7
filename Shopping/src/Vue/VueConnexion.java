package Vue;

import javax.swing.*;
import java.awt.*;
import DAO.DAOFormulaireIMPL;
import Modele.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueConnexion extends JDialog {

    private JPanel connexionPanel;
    private JTextField tfEmailC;
    private JPasswordField pfMdpC;
    private JButton btnInscrivezVous;
    private JButton btnConnexion;
    private DAOFormulaireIMPL daoFormulaire;

    public VueConnexion(JFrame parent, DAOFormulaireIMPL daoFormulaire) {
        super(parent);
        this.daoFormulaire = daoFormulaire;
        setTitle("Connexion");
        setMinimumSize((new Dimension(1000,600)));
        setModal(true);
        setLocationRelativeTo(parent);


        //**************
        // PAS AU POINT
        //**************
        // Panneau principal avec image de fond
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("/PHOTOS/Teatime.jpeg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        // Panneau du formulaire centré
        connexionPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        connexionPanel.setOpaque(false);
        connexionPanel.setPreferredSize(new Dimension(400, 200));

        //Initialisation des éléments du formulaire de connexion
        tfEmailC = new JTextField();
        pfMdpC = new JPasswordField();
        btnConnexion = new JButton("Se connecter");
        btnInscrivezVous = new JButton("Inscrivez-vous");

        //Ajout des éléments sur le panneau de connexion
        connexionPanel.add(new JLabel("Adresse mail"));
        connexionPanel.add(tfEmailC);
        connexionPanel.add(new JLabel("Mot de passe"));
        connexionPanel.add(pfMdpC);
        connexionPanel.add(btnConnexion);
        connexionPanel.add(btnInscrivezVous);

        // Ajout du formulaire centré
        backgroundPanel.add(connexionPanel, new GridBagConstraints());

        //Quand on clique sur le bouton "Connexion", on récupère les infos rentrées
        // et on appelle le code DAOFormulaire pour connecter l'user
        btnConnexion.addActionListener(e -> {
            String email = getEmail();
            String mdp = getMdp();

            //Appel de la méthode connecterClient() qui va vérifier si les informations de connexion rentrées existent dans la base de données shoppingBD.sql
            User utilisateur = daoFormulaire.connecterClient(email, mdp);
            if (utilisateur != null) {
                showMessage("Connexion réussie !");
                dispose();

                //Vérification du statut de l'user pour le renvoyer vers la bonne page : Admin si statut=0 ou Client
                if (utilisateur.getStatutUser() == 0) {
                    new VueCompteAdmin().setVisible(true);
                } else {
                    JFrame clientFrame = new JFrame("Compte Client");
                    clientFrame.setContentPane(new VueCompteClient(clientFrame, utilisateur.getIdUser()));
                    clientFrame.setLocationRelativeTo(null);
                    clientFrame.setVisible(true);
                }

            } else {
                showError("Email ou mot de passe incorrect !");
            }
        });


        //Si jamais le client ne s'est pas inscrit il peut le faire en cliquant sur le bouton qui va le renvoyer vers la fenetre/formulaire d'inscription
        btnInscrivezVous.addActionListener(e -> {
            dispose(); // Fermer la fenêtre de connexion
            new VueInscription(null, daoFormulaire).setVisible(true);
        });

        setVisible(true);
    }


    //Differents getters pour récupérer les infos de la Vue et les stocker pour les utiliser dans la DAOFormulaire
    public String getEmail() {
        return tfEmailC.getText();
    }

    public String getMdp() {
        return String.valueOf(pfMdpC.getPassword());
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel getConnexionPanel() {
        return connexionPanel;
    }

}
