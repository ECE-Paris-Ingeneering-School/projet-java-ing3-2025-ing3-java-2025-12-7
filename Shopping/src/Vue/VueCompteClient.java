package Vue;
import Vue.createInfoRow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VueCompteClient extends JPanel{
    public VueCompteClient() {

        setLayout(new BorderLayout());

        // Panel principal avec disposition verticale
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 240));

        // Ajout des différentes sections
        Top topVCC = new Top();
        topVCC.getIconTop(0);
        mainPanel.add(topVCC);
        mainPanel.add(new Nav());



        JPanel content = new JPanel();
        content.setBackground(new Color(220, 223, 197));
        JLabel titre = new JLabel("Votre compte");
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titre, BorderLayout.CENTER);

        // Panel des informations client
        JPanel infoClient = createInfoClientPanel();
        // Panel de l'historique des achats
        JPanel historiqueAchats = createHistoriqueAchatsPanel();

        content.add(infoClient, BorderLayout.CENTER);
        JLabel messageCommande = new JLabel("Vous n'avez pas encore commandé ?");
        JButton commande = new JButton("Commander");
        messageCommande.setFont(new Font("Arial", Font.BOLD, 10));
        content.add(messageCommande, BorderLayout.CENTER);
        content.add(commande, BorderLayout.CENTER);
        content.add(historiqueAchats, BorderLayout.CENTER);

        // contraintes de taille
        content.setMaximumSize(new Dimension(800, 500));
        content.setPreferredSize(new Dimension(780, 450));

        mainPanel.add(content);
        mainPanel.add(new Bottom());

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createInfoClientPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Informations "));
        createInfoRow Nom = new createInfoRow("Nom:", "Dupont");
        createInfoRow Prenom = new createInfoRow("Prénom:", "Jean");
        createInfoRow Email = new createInfoRow("Email:", "jean.dupont@example.com");
        createInfoRow Telephone = new createInfoRow("Téléphone:", "06 12 34 56 78");
        createInfoRow Adresse = new createInfoRow("Adresse:", "123 Rue des Biscuits, 75000 Paris");
        createInfoRow DateNaissance = new createInfoRow("Date de naissance:", "13/04/2000");
        panel.add(Nom);
        panel.add(Prenom);
        panel.add(Email);
        panel.add(Telephone);
        panel.add(Adresse);
        panel.add(DateNaissance);

        return panel;
    }

    private JPanel createHistoriqueAchatsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Vos commandes"));
        String[] columns = {"N° Commande", "Date", "Produits", "Quantité", "Montant", "Statut"};
        Object[][] data = {
                {"#12345", "01/04/2025", "Biscuits Chocolat", "2", "9.98€", "Expédiée"},
                {"#12345", "01/04/2025", "Thé Earl Grey", "1", "5.50€", "Expédiée"},
                {"#12346", "25/03/2025", "Soda Citron", "6", "12.00€", "Livrée"},
                {"#12346", "25/03/2025", "Biscuits Vanille", "3", "12.99€", "Livrée"},
                {"#12347", "10/03/2025", "Café Arabica", "1", "8.50€", "Livrée"},
                {"#12347", "10/03/2025", "Cookies Pépites", "2", "7.80€", "Livrée"},
                {"#12348", "15/02/2025", "Chocolat Noir", "4", "15.96€", "Livrée"},
                {"#12349", "01/02/2025", "Tisane Bio", "2", "9.98€", "Livrée"},
                {"#12346", "25/03/2025", "Soda Citron", "6", "12.00€", "Livrée"},
                {"#12346", "25/03/2025", "Biscuits Vanille", "3", "12.99€", "Livrée"},
                {"#12347", "10/03/2025", "Café Arabica", "1", "8.50€", "Livrée"},
                {"#12347", "10/03/2025", "Cookies Pépites", "2", "7.80€", "Livrée"},
                {"#12348", "15/02/2025", "Chocolat Noir", "4", "15.96€", "Livrée"},
                {"#12349", "01/02/2025", "Tisane Bio", "2", "9.98€", "Livrée"}
        };

        // Création du tableau
        JTable table = new JTable(data, columns);
        table.setFillsViewportHeight(true);
        // Ajout du tableau dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 200));
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }




}
