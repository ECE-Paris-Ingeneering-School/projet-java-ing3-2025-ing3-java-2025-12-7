/* Sources : TP8
Pour le traitement de la date de naissance : https://www.jmdoudoux.fr/java/dej/chap-utilisation_dates.htm
 Codes de Kawthar qui s'est occupée de la Vue, notamment pour :
 -l'image en background,
 -le centrage des éléments
 -les boutons
 -l'esthetique en général

Premier essai du code codé grâce à l'interface GUI (voir dossier archives)
Série de vidéo : https://www.youtube.com/watch?v=n2_1tYv5oY8
Vidéo : https://www.youtube.com/watch?v=nIQatIpL_GE
*/


package Vue;

import javax.swing.*;
import java.awt.*;

//Pour gérer la date de naissance
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

import DAO.DAOFormulaireIMPL;

/**
 * @author Laure Petit
 * Cette classe affiche la fenêtre d'inscription à l'utilisateur.
 * Permet à un utilisateur de s'inscrire ou de se connecter s'il a un compte.
 */
public class VueInscription extends JFrame {
    private JTextField tfNom;
    private JButton btnInscription;
    private JTextField tfEmail;
    private JPasswordField pfMdp;
    private JPasswordField pfConfirmerMdp;
    private JButton btnConnectezVous;
    private JFormattedTextField ftfDateNaissance;
    private JTextField tfPrenom;
    private JTextField tfAdresse;
    private DAOFormulaireIMPL daoFormulaire;

    /**
     * @author Laure Petit
     * Constructeur VueInscription
     * Permet de réaliser l'affichage graphique et les actions des boutons
     * @param daoFormulaire DAO pour gérer les opérations de connexion et inscription
     */
    public VueInscription(DAOFormulaireIMPL daoFormulaire) {
        this.daoFormulaire = daoFormulaire;

        //Configuration de base de la fenêtre
        setTitle("Inscription");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setResizable(false);

        // On crée un panneau principal avec une image de fond
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Shopping/PHOTOS/gouter5.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setOpaque((false));
        setContentPane(backgroundPanel);

        JPanel inscriptionPanel = new JPanel();
        inscriptionPanel.setLayout(new BoxLayout(inscriptionPanel, BoxLayout.Y_AXIS));
        inscriptionPanel.setBackground(new Color(255, 255, 255, 230)); // Rendre le panneau semi transparent pour voir l'image de fond
        inscriptionPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        /* *********************************************************
         * Initialisation des éléments du formulaire d'inscription *
         ********************************************************* */

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
        JLabel lblDateNaissance = new JLabel("Date de naissance (au format aaaa-mm-jj)");
        lblDateNaissance.setAlignmentX(Component.CENTER_ALIGNMENT);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Refuse les dates invalides

        DateFormatter dateFormatter = new DateFormatter(dateFormat);

        // Création du champ de texte formaté
        ftfDateNaissance = new JFormattedTextField(dateFormatter);
        ftfDateNaissance.setMaximumSize(new Dimension(300, 30));
        ftfDateNaissance.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adresse
        JLabel lblAdresse = new JLabel("Adresse postale");
        lblAdresse.setAlignmentX(Component.CENTER_ALIGNMENT);
        tfAdresse = new JTextField(40);
        tfAdresse.setMaximumSize(new Dimension(300, 30));
        tfAdresse.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Mot de passe
        JLabel lblMdp = new JLabel("Mot de passe (au moins 6 caractères)");
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

        // Bouton inscription
        btnInscription = new JButton("S'inscrire");
        btnInscription.setBackground(new Color(76, 175, 80));
        btnInscription.setForeground(Color.WHITE);
        btnInscription.setFocusPainted(false);
        btnInscription.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnInscription.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Texte et bouton connexion
        JLabel lblAccount = new JLabel("Vous avez déjà un compte ?");
        lblAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConnectezVous= new JButton("Connectez-vous");
        btnConnectezVous.setBackground(new Color(76, 172, 175));
        btnConnectezVous.setForeground(Color.WHITE);
        btnConnectezVous.setFocusPainted(false);
        btnConnectezVous.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConnectezVous.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Ajout des éléments sur le panneau d'inscription (avec espacements)
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
        inscriptionPanel.add(ftfDateNaissance);
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

        // On ajoute le panneau d'inscription au centre de l'écran
        backgroundPanel.add(inscriptionPanel, new GridBagConstraints());

        /* *********************************
         * Gestion des actions des boutons *
         ********************************* */

        /**
         * @author Laure Petit
         * Quand on clique sur le bouton "Inscription", on récupère les infos rentrées
         * et on appelle le code DAOFormulaire pour inscrire le client.
         */
        btnInscription.addActionListener(e-> {
            String nom = getNom();
            String prenom = getPrenom();
            String dateNaissance = getDateNaissance();
            String email = getEmail();
            String adresse = getAdresse();
            String mdp = getMdp();
            String confirmerMdp = getConfirmerMdp();

            //Vérification du remplissage des champs d'inscription
            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || adresse.isEmpty()|| mdp.isEmpty()) {
                showError("Veuillez remplir tous les champs !");
            }

            //Vérification de la longueur du mot de passe
            if (mdp.length() < 6) {
                showError("Le mot de passe doit contenir au moins 6 caractères.");
            }

            //Vérification que le mdp et la confirmation sont identiques
            if (!mdp.equals(confirmerMdp)) {
                showError("Les mots de passe ne sont pas identiques");
            }

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

        /**
         * @author Laure Petit
         * Si jamais le client a déjà un compte, il peut se connecter en cliquant sur le bouton Connectez-vous
         * qui va le renvoyer vers la fenetre/formulaire d'inscription
         */
        btnConnectezVous.addActionListener(e -> {
            dispose(); // Fermer la fenêtre de connexion
            new VueConnexion(daoFormulaire).setVisible(true);
        });

    }

    //Différents getters pour récupérer les infos de la Vue et les stocker pour les utiliser dans la DAOFormulaire

    /**
     * @author Laure Petit
     * Getter pour récupérer le nom saisi.
     * @return Nom sous forme de String
     */
    public String getNom() {
        return tfNom.getText();
    }

    /**
     * @author Laure Petit
     * Getter pour récupérer le prénom saisi.
     * @return Prénom sous forme de String
     */
    public String getPrenom() {
        return tfPrenom.getText();
    }

    /**
     * @author Laure Petit
     * Getter pour récupérer le nom saisi.
     * @return DateNaissance sous forme de String
     */
    public String getDateNaissance() {
        return ftfDateNaissance.getText();
    }

    /**
     * @author Laure Petit
     * Getter pour récupérer l'email saisi.
     * @return Email sous forme de String
     */
    public String getEmail() {
        return tfEmail.getText();
    }

    /**
     * @author Laure Petit
     * Getter pour récupérer l'adresse saisie.
     * @return Adresse sous forme de String
     */
    public String getAdresse() {
        return tfAdresse.getText();
    }

    /**
     * @author Laure Petit
     * Getter pour récupérer le mot de passe saisi.
     * @return Mot de passe sous forme de String
     */
    public String getMdp() {
        return String.valueOf(pfMdp.getPassword());
    }

    /**
     * @author Laure Petit
     * Getter pour récupérer le 2eme mot de passe saisi.
     * @return Confirmation de mot de passe sous forme de String
     */
    public String getConfirmerMdp() {
        return String.valueOf(pfConfirmerMdp.getPassword());
    }

    /**
     * @author Laure Petit
     * Permet d'afficher un message d'erreur sous forme de pop up
     * @param message Le message d'erreur à afficher
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * @author Laure Petit
     * Permet d'afficher un message sous forme de pop up
     * Ex : Inscription réussie
     * @param message Le message à afficher
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
