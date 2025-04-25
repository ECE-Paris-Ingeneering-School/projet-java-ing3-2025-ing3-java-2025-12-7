package Vue;

import javax.swing.*;
import java.awt.*;
import Controleur.ControleurAdmin;
import Controleur.ControleurClient;
import Controleur.Session;
import DAO.DAOFormulaireIMPL;
import Modele.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueConnexion extends JFrame {

    private JTextField tfEmailC;
    private JPasswordField pfMdpC;
    private JButton btnInscrivezVous;
    private JButton btnConnexion;
    private DAOFormulaireIMPL daoFormulaire;

    public VueConnexion(DAOFormulaireIMPL daoFormulaire) {
        this.daoFormulaire = daoFormulaire;
        setTitle("Connexion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setResizable(false);


        //**************
        // PAS AU POINT
        //**************
        // Panneau principal avec image de fond
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Shopping/PHOTOS/Teatime.jpeg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setOpaque(false);
        setContentPane(backgroundPanel);

        JPanel connexionPanel = new JPanel();
        connexionPanel.setLayout(new BoxLayout(connexionPanel, BoxLayout.Y_AXIS));
        connexionPanel.setBackground(new Color(255, 255, 255, 230)); // blanc semi-transparent
        connexionPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        /*connexionPanel.setOpaque(false);
        connexionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        connexionPanel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));*/


        //Initialisation des éléments du formulaire de connexion

        // Titre
        JLabel lblCo = new JLabel("Se connecter");
        lblCo.setFont(new Font("Arial", Font.BOLD, 18));
        lblCo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adresse mail
        JLabel lblEmail = new JLabel("Adresse mail");
        lblEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        tfEmailC = new JTextField(20);
        tfEmailC.setMaximumSize(new Dimension(300, 30));
        tfEmailC.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Mot de passe
        JLabel lblMdp = new JLabel("Mot de passe");
        lblMdp.setAlignmentX(Component.CENTER_ALIGNMENT);
        pfMdpC = new JPasswordField(20);
        pfMdpC.setMaximumSize(new Dimension(300, 30));
        pfMdpC.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Bouton connexion
        btnConnexion = new JButton("Se connecter");
        btnConnexion.setBackground(new Color(76, 175, 80));
        btnConnexion.setForeground(Color.WHITE);
        btnConnexion.setFocusPainted(false);
        btnConnexion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConnexion.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Texte et bouton inscription
        JLabel lblNoAccount = new JLabel("Vous n'avez pas de compte ?");
        lblNoAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnInscrivezVous = new JButton("Inscrivez-vous");
        btnInscrivezVous.setBackground(new Color(76, 160, 175));
        btnInscrivezVous.setForeground(Color.WHITE);
        btnInscrivezVous.setFocusPainted(false);
        btnInscrivezVous.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnInscrivezVous.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Ajout des éléments sur le panneau de connexion
        connexionPanel.add(lblCo);
        connexionPanel.add(Box.createVerticalStrut(10));
        connexionPanel.add(lblEmail);
        connexionPanel.add(Box.createVerticalStrut(5));
        connexionPanel.add(tfEmailC);
        connexionPanel.add(Box.createVerticalStrut(15));
        connexionPanel.add(lblMdp);
        connexionPanel.add(Box.createVerticalStrut(5));
        connexionPanel.add(pfMdpC);
        connexionPanel.add(Box.createVerticalStrut(20));
        connexionPanel.add(btnConnexion);
        connexionPanel.add(Box.createVerticalStrut(30));
        connexionPanel.add(lblNoAccount);
        connexionPanel.add(Box.createVerticalStrut(10));
        connexionPanel.add(btnInscrivezVous);

        // Centrage du panneau blanc
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

                Session.setCurrentUser(utilisateur); // Stocker l'utilisateur connecté
                SwingUtilities.invokeLater(() -> new Mywindow()); // Ouvrir la fenetre principale (accueil)

            } else {
                showError("Email ou mot de passe incorrect !");
            }
        });


        //Si jamais le client ne s'est pas inscrit il peut le faire en cliquant sur le bouton qui va le renvoyer vers la fenetre/formulaire d'inscription
        btnInscrivezVous.addActionListener(e -> {
            dispose(); // Fermer la fenêtre de connexion
            new VueInscription(daoFormulaire).setVisible(true);
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

}
