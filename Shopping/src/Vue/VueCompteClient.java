package Vue;

import Controleur.ControleurClient;
import DAO.DAOFactory;
import Modele.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class VueCompteClient extends JPanel {
    private final Color backgroundColor = new Color(220, 223, 197);
    private final Color headerColor = new Color(200, 203, 177);
    private final Color contentColor = new Color(235, 238, 212);
    private final ControleurClient controleur;
    private Client client;
    private JFrame parentFrame;

    public VueCompteClient(JFrame parentFrame, int idClient, ControleurClient controleur) {
        this.parentFrame = parentFrame;
        this.controleur = controleur;
        this.client = controleur.getClient(idClient);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColor);

        // Titre
        JPanel titrePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titrePanel.setBackground(backgroundColor);
        JLabel titre = new JLabel("Votre compte : Client");
        titre.setFont(new Font("Serif", Font.BOLD, 26));
        titrePanel.add(titre);
        mainPanel.add(titrePanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // Panneau supérieur
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        JPanel infoClientPanel = createInfoClientPanel();
        topPanel.add(infoClientPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.insets = new Insets(0, 0, 0, 0);
        JPanel commanderPanel = createCommanderPanel();
        topPanel.add(commanderPanel, gbc);

        // Ajouter le panneau supérieur au panneau principal
        JPanel topPanelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanelContainer.setBackground(backgroundColor);
        topPanelContainer.add(topPanel);
        mainPanel.add(topPanelContainer);
        mainPanel.add(Box.createVerticalStrut(15));

        // Historique des achats centré
        JPanel historiqueContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        historiqueContainer.setBackground(backgroundColor);
        JPanel historiquePanel = createHistoriqueAchatsPanel();
        historiqueContainer.add(historiquePanel);
        mainPanel.add(historiqueContainer);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(new Bottom());

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createInfoClientPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 15, 10)); // Disposition en grille pour les informations

        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setPreferredSize(new Dimension(420, 350));

        // Titre
        JLabel titreSection = new JLabel("Mes informations", JLabel.CENTER);
        titreSection.setFont(new Font("Arial", Font.BOLD, 16));
        titreSection.setForeground(new Color(51, 85, 140));

        // Ajouter le titre dans un panel séparé qui couvre les deux colonnes
        JPanel titreSectionPanel = new JPanel();
        titreSectionPanel.setBackground(Color.WHITE);
        titreSectionPanel.add(titreSection);

        // Données client (via contrôleur)
        String nom = client.getNomUser();
        String prenom = client.getPrenomUser();
        String email = client.getMailUser();
        String adresse = client.getAdresseClient();
        String dateNaissance = new SimpleDateFormat("dd/MM/yyyy").format(client.getDateNaissanceClient());

        // Champs (interface inchangée)
        JLabel nomLabel = new JLabel("Nom:");
        nomLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel nomValue = new JLabel(nom);

        JLabel prenomLabel = new JLabel("Prénom:");
        prenomLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel prenomValue = new JLabel(prenom);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel emailValue = new JLabel(email);

        JLabel adresseLabel = new JLabel("Adresse:");
        adresseLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel adresseValue = new JLabel(adresse);

        JLabel dateNaissanceLabel = new JLabel("Date de naissance:");
        dateNaissanceLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel dateNaissanceValue = new JLabel(dateNaissance);

        panel.add(titreSectionPanel);
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.WHITE);
        panel.add(emptyPanel); // Pour que le titre occupe deux cellules

        panel.add(nomLabel);
        panel.add(nomValue);
        panel.add(prenomLabel);
        panel.add(prenomValue);
        panel.add(emailLabel);
        panel.add(emailValue);
        panel.add(adresseLabel);
        panel.add(adresseValue);
        panel.add(dateNaissanceLabel);
        panel.add(dateNaissanceValue);

        // Ajouter un bouton de modification en bas
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        JButton modifierButton = new JButton("Modifier mes informations");
        modifierButton.setBackground(new Color(216, 232, 173));
        modifierButton.setFocusPainted(false);
        modifierButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(modifierButton);

        // Ajouter le bouton dans un panel séparé qui couvre les deux colonnes
        panel.add(buttonPanel);
        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel2.setBackground(Color.WHITE);
        panel.add(buttonPanel2); // Cellule vide pour compléter la grille

        return panel;
    }

    private JPanel createCommanderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setPreferredSize(new Dimension(200, 280));

        // Créer un peu d'espace vertical pour centrer le contenu
        panel.add(Box.createVerticalGlue());

        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messagePanel.setBackground(Color.WHITE);
        JTextArea messageLabel = new JTextArea("Vous n'avez\n" + "toujours pas\n" + "commandé ?");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 15));
        messageLabel.setEditable(false);
        messageLabel.setForeground(new Color(51, 85, 140));
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        // Créer un panel pour que le label respecte l'alignement
        JPanel messagePanelContainer = new JPanel();
        messagePanelContainer.setBackground(Color.WHITE);
        messagePanelContainer.setAlignmentX(Component.CENTER_ALIGNMENT);

        messagePanelContainer.add(messagePanel);
        panel.add(messagePanelContainer);

        // Espace entre le message et le bouton
        panel.add(Box.createVerticalStrut(30));

        // Bouton commander avec listener
        JButton commanderButton = new JButton("COMMANDER");
        commanderButton.setBackground(new Color(76, 175, 80));
        commanderButton.setForeground(Color.WHITE);
        commanderButton.setFont(new Font("Arial", Font.BOLD, 14));
        commanderButton.setFocusPainted(false);
        commanderButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        commanderButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        commanderButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ajout du listener pour naviguer vers VueProduits
        commanderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}

        });

        panel.add(commanderButton);

        // Ajouter un espace vertical pour le centrage
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createHistoriqueAchatsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setPreferredSize(new Dimension(700, 200));

        // En-tête
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(headerColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel headerLabel = new JLabel("Historique de vos commandes");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(headerLabel);

        panel.add(headerPanel, BorderLayout.NORTH);

        // Récupération des données via le contrôleur
        List<Map<String, Object>> commandes = controleur.getCommandesClient(client.getIdUser());
        float totalAchats = controleur.calculerTotalAchats(commandes);

        // Construction du tableau (interface inchangée)
        String[] columns = {"N° Commande", "Date", "Produits", "Quantité", "Montant", "Statut"};
        Object[][] data = new Object[commandes.size()][6];

        for (int i = 0; i < commandes.size(); i++) {
            Map<String, Object> cmd = commandes.get(i);
            data[i][0] = "#" + cmd.get("idCommande");
            data[i][1] = new SimpleDateFormat("dd/MM/yyyy").format(cmd.get("date"));
            data[i][2] = cmd.get("produit");
            data[i][3] = cmd.get("quantite");
            data[i][4] = String.format("%.2f€", cmd.get("montant"));
            data[i][5] = cmd.get("statut");
        }

        // Création du tableau
        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.setGridColor(new Color(200, 200, 200));
        table.getTableHeader().setBackground(new Color(240, 240, 240));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        // Ajout du tableau dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(backgroundColor);

        panel.add(scrollPane, BorderLayout.CENTER);


        // Panneau de recap du total d'achats
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(headerColor);
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        JLabel totalLabel = new JLabel(String.format("Total de vos achats: %.2f €", totalAchats));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPanel.add(totalLabel);

        panel.add(totalPanel, BorderLayout.SOUTH);

        return panel;

    }
}
