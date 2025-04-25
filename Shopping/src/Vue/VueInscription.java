package Vue;

import javax.swing.*;
import java.awt.*;

import DAO.DAOFormulaireIMPL;

public class VueInscription extends JFrame {
    private JTextField tfNom;
    private JButton btnInscription;
    private JTextField tfEmail;
    private JPasswordField pfMdp;
    private JPasswordField pfConfirmerMdp;
    private JButton btnConnectezVous;
    private JTextField tfDateNaissance;
    private JTextField tfPrenom;
    private JTextField tfAdresse;
    private DAOFormulaireIMPL daoFormulaire;

    public VueInscription(DAOFormulaireIMPL daoFormulaire) {
        this.daoFormulaire = daoFormulaire;
        setTitle("Inscription");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panneau principal avec image de fond
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Shopping/PHOTOS/Teatime.jpeg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setOpaque((false)); // Pour centrer le formulaire
        setContentPane(backgroundPanel);

        JPanel inscriptionPanel = new JPanel();
        inscriptionPanel.setLayout(new BoxLayout(inscriptionPanel, BoxLayout.Y_AXIS));
        inscriptionPanel.setBackground(new Color(255, 255, 255, 230)); // Rendre le panneau semi transparent pour voir l'image de fond
        inscriptionPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        //Initialisation des éléments du formulaire d'inscription

        // Titre
        JLabel lblInscrire = new JLabel("S'inscrire");
        lblInscrire.setFont(new Font("Arial", Font.BOLD, 18));
        lblInscrire.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nom
        JLabel lblNom = new JLabel("Nom");
        lblNom.setAlignmentX(Component.CENTER_ALIGNMENT);
        tfNom = new JTextField(20);
        tfNom.setMaximumSize(new Dimension(300, 30));
        tfNom.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Prénom
        JLabel lblPrenom = new JLabel("Prénom");
        lblPrenom.setAlignmentX(Component.CENTER_ALIGNMENT);
        tfPrenom = new JTextField(20);
        tfPrenom.setMaximumSize(new Dimension(300, 30));
        tfPrenom.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adresse mail
        JLabel lblEmail = new JLabel("Adresse mail");
        lblEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        tfEmail = new JTextField(20);
        tfEmail.setMaximumSize(new Dimension(300, 30));
        tfEmail.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Date de naissance
        JLabel lblDateNaissance = new JLabel("Date de naissance");
        lblDateNaissance.setAlignmentX(Component.CENTER_ALIGNMENT);
        tfDateNaissance = new JFormattedTextField();
        tfDateNaissance.setMaximumSize(new Dimension(300, 30));
        tfDateNaissance.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adresse
        JLabel lblAdresse = new JLabel("Adresse");
        lblAdresse.setAlignmentX(Component.CENTER_ALIGNMENT);
        tfAdresse = new JTextField(40);
        tfAdresse.setMaximumSize(new Dimension(300, 30));
        tfAdresse.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Mot de passe
        JLabel lblMdp = new JLabel("Mot de passe");
        lblMdp.setAlignmentX(Component.CENTER_ALIGNMENT);
        pfMdp = new JPasswordField(20);
        pfMdp.setMaximumSize(new Dimension(300, 30));
        pfMdp.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Confirmer mot de passe
        JLabel lblConfirmerMdp = new JLabel("Confirmation du mot de passe");
        lblConfirmerMdp.setAlignmentX(Component.CENTER_ALIGNMENT);
        pfConfirmerMdp = new JPasswordField(20);
        pfConfirmerMdp.setMaximumSize(new Dimension(300, 30));
        pfConfirmerMdp.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Bouton connexion
        btnInscription = new JButton("Se connecter");
        btnInscription.setBackground(new Color(76, 175, 80));
        btnInscription.setForeground(Color.WHITE);
        btnInscription.setFocusPainted(false);
        btnInscription.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnInscription.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Texte et bouton inscription
        JLabel lblAccount = new JLabel("Vous avez déjà un compte ?");
        lblAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConnectezVous= new JButton("Connectez-vous");
        btnConnectezVous.setBackground(new Color(76, 172, 175));
        btnConnectezVous.setForeground(Color.WHITE);
        btnConnectezVous.setFocusPainted(false);
        btnConnectezVous.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConnectezVous.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Ajout des éléments sur le panneau d'inscription
        inscriptionPanel.add(lblInscrire);
        inscriptionPanel.add(Box.createVerticalStrut(10));
        inscriptionPanel.add(lblNom);
        inscriptionPanel.add(Box.createVerticalStrut(5));
        inscriptionPanel.add(tfNom);
        inscriptionPanel.add(Box.createVerticalStrut(10));
        inscriptionPanel.add(lblPrenom);
        inscriptionPanel.add(Box.createVerticalStrut(5));
        inscriptionPanel.add(tfPrenom);
        inscriptionPanel.add(Box.createVerticalStrut(10));
        inscriptionPanel.add(lblDateNaissance);
        inscriptionPanel.add(Box.createVerticalStrut(5));
        inscriptionPanel.add(tfDateNaissance);
        inscriptionPanel.add(Box.createVerticalStrut(10));
        inscriptionPanel.add(lblEmail);
        inscriptionPanel.add(Box.createVerticalStrut(5));
        inscriptionPanel.add(tfEmail);
        inscriptionPanel.add(Box.createVerticalStrut(10));
        inscriptionPanel.add(lblAdresse);
        inscriptionPanel.add(Box.createVerticalStrut(5));
        inscriptionPanel.add(tfAdresse);
        inscriptionPanel.add(Box.createVerticalStrut(10));
        inscriptionPanel.add(lblMdp);
        inscriptionPanel.add(Box.createVerticalStrut(5));
        inscriptionPanel.add(pfMdp);
        inscriptionPanel.add(Box.createVerticalStrut(10));
        inscriptionPanel.add(lblConfirmerMdp);
        inscriptionPanel.add(Box.createVerticalStrut(5));
        inscriptionPanel.add(pfConfirmerMdp);
        inscriptionPanel.add(Box.createVerticalStrut(15));
        inscriptionPanel.add(btnInscription);
        inscriptionPanel.add(Box.createVerticalStrut(30));
        inscriptionPanel.add(lblAccount);
        inscriptionPanel.add(Box.createVerticalStrut(10));
        inscriptionPanel.add(btnConnectezVous);

        // Centrer le panneau du formulaire dans la fenêtre
        backgroundPanel.add(inscriptionPanel, new GridBagConstraints());

        //Quand on clique sur le bouton "Inscription", on récupère les infos rentrées
        // et on appelle le code DAOFormulaire pour inscrire le client
        btnInscription.addActionListener(e-> {
            String nom = getNom();
            String prenom = getPrenom();
            String dateNaissance = getDateNaissance();
            String email = getEmail();
            String adresse = getAdresse();
            String mdp = getMdp();
            String confirmerMdp = getConfirmerMdp();

            //Appel de la méthode inscrireClient() qui va enregistrer les informations d'inscription dans la base de données shoppingBD.sql
            boolean success = daoFormulaire.inscrireClient(nom, prenom, dateNaissance, email, adresse, mdp, confirmerMdp);
            if (success) {
                JOptionPane.showMessageDialog(this, "Inscription réussie !");
                dispose(); // Fermer la fenêtre
                new VueConnexion(daoFormulaire).setVisible(true); // Suite à l'inscription, on retourne à la vue connexion pour se connecter
            } else {
                showError("Échec de l'inscription !");
            }
        });

        //Si jamais le client a déjà un compte, il peut se connecter en cliquant sur le bouton Connectez-vous
        // qui va le renvoyer vers la fenetre/formulaire d'inscription
        btnConnectezVous.addActionListener(e -> {
            dispose(); // Fermer la fenêtre de connexion
            new VueConnexion(daoFormulaire).setVisible(true);
        });

        //setVisible(true);
    }

    //Différents getters pour récupérer les infos de la Vue et les stocker pour les utiliser dans la DAOFormulaire
    public String getNom() {
        return tfNom.getText();
    }

    public String getPrenom() {
        return tfPrenom.getText();
    }

    public String getDateNaissance() {
        return tfDateNaissance.getText();
    }

    public String getEmail() {
        return tfEmail.getText();
    }

    public String getAdresse() {
        return tfAdresse.getText();
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
}
