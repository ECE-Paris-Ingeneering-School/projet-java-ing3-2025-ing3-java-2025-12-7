package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DAO.DAOFormulaireIMPL;

public class VueInscription extends JDialog {
    private JPanel inscriptionPanel;
    private JTextField tfNom;
    private JButton btnInscription;
    private JTextField tfEmail;
    private JPasswordField pfMdp;
    private JPasswordField pfConfirmerMdp;
    private JButton btnConnectezVous;
    private JTextField tfDateNaissance;
    private DAOFormulaireIMPL daoFormulaire;

    public VueInscription(JFrame parent, DAOFormulaireIMPL daoFormulaire) {
        super(parent);
        this.daoFormulaire = daoFormulaire;
        setTitle("Inscription");
        setMinimumSize((new Dimension(1000,600)));
        setModal(true);
        setLocationRelativeTo(parent);

        // Panneau principal avec image de fond
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Teatime.jpeg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout()); // Pour centrer le formulaire
        setContentPane(backgroundPanel);

        // Panneau du formulaire centré
        inscriptionPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inscriptionPanel.setOpaque(false); // Rendre le panneau transparent pour voir l'image de fond
        inscriptionPanel.setPreferredSize(new Dimension(400, 250));

        //Initialisation des éléments du formulaire d'inscription
        tfNom = new JTextField();
        tfEmail = new JTextField();
        pfMdp = new JPasswordField();
        tfDateNaissance = new JFormattedTextField();
        pfConfirmerMdp = new JPasswordField();
        btnInscription = new JButton("S'inscrire");
        btnConnectezVous = new JButton("Connectez-vous");

        //Ajout des éléments sur le panneau d'inscription
        inscriptionPanel.add(new JLabel("Nom"));
        inscriptionPanel.add(tfNom);
        inscriptionPanel.add(new JLabel("Date de naissance"));
        inscriptionPanel.add(tfDateNaissance);
        inscriptionPanel.add(new JLabel("Adresse mail"));
        inscriptionPanel.add(tfEmail);
        inscriptionPanel.add(new JLabel("Mot de passe"));
        inscriptionPanel.add(pfMdp);
        inscriptionPanel.add(new JLabel("Confirmation du mot de passe"));
        inscriptionPanel.add(pfConfirmerMdp);
        inscriptionPanel.add(btnInscription);
        inscriptionPanel.add(btnConnectezVous);

        // Centrer le panneau du formulaire dans la fenêtre
        backgroundPanel.add(inscriptionPanel, new GridBagConstraints());

        //Quand on clique sur le bouton "Inscription", on récupère les infos rentrées
        // et on appelle le code DAOFormulaire pour inscrire le client
        btnInscription.addActionListener(e-> {
            String nom = getNom();
            String dateNaissance = getDateNaissance();
            String email = getEmail();
            String mdp = getMdp();
            String confirmerMdp = getConfirmerMdp();

            //Appel de la méthode inscrireClient() qui va enregistrer les informations d'inscription dans la base de données shoppingBD.sql
            boolean success = daoFormulaire.inscrireClient(nom, dateNaissance, email, mdp, confirmerMdp);
            if (success) {
                JOptionPane.showMessageDialog(this, "Inscription réussie !");
                dispose(); // Fermer la fenêtre
                new VueConnexion(null, daoFormulaire).setVisible(true); // Suite à l'inscription, on retourne à la vue connexion pour se connecter
            } else {
                showError("Échec de l'inscription !");
            }
        });

        //Si jamais le client a déjà un compte, il peut se connecter en cliquant sur le bouton Connectez-vous
        // qui va le renvoyer vers la fenetre/formulaire d'inscription
        btnConnectezVous.addActionListener(e -> {
            dispose(); // Fermer la fenêtre de connexion
            new VueConnexion(null, daoFormulaire).setVisible(true);
        });

        setVisible(true);
    }

    //Différents getters pour récupérer les infos de la Vue et les stocker pour les utiliser dans la DAOFormulaire
    public String getNom() {
        return tfNom.getText();
    }

    public String getDateNaissance() {
        return tfDateNaissance.getText();
    }

    public String getEmail() {
        return tfEmail.getText();
    }

    public String getMdp() {
        return String.valueOf(pfMdp.getPassword());
    }

    public String getConfirmerMdp() {
        return String.valueOf(pfConfirmerMdp.getPassword());
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel getInscriptionPanel() {
        return inscriptionPanel;
    }
}
