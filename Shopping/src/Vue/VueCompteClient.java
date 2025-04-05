package Vue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VueCompteClient extends JFrame{
    public VueCompteClient() {
        super("Compte Client - Biscuits&Drinks.co");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        content.add(titre, BorderLayout.CENTER);

        // Panel des informations client
        JPanel infoClient = createInfoClientPanel();
        // Panel de l'historique des achats
        JPanel historiqueAchats = createHistoriqueAchatsPanel();

        content.add(infoClient, BorderLayout.CENTER);
        content.add(historiqueAchats, BorderLayout.CENTER);

        // contraintes de taille
        content.setMaximumSize(new Dimension(800, 500));
        content.setPreferredSize(new Dimension(780, 450));
        mainPanel.add(content);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createInfoClientPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Informations "));
        panel.add(createInfoRow("Nom:", "Dupont"));
        panel.add(createInfoRow("Prénom:", "Jean"));
        panel.add(createInfoRow("Email:", "jean.dupont@example.com"));
        panel.add(createInfoRow("Téléphone:", "06 12 34 56 78"));
        panel.add(createInfoRow("Adresse:", "123 Rue des Biscuits, 75000 Paris"));
        panel.add(createInfoRow("Date de naissance:", "13/04/2000"));
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
    // pour les informations du client
    private JPanel createInfoRow(String label, String value) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.setBackground(Color.WHITE);
        JLabel labelComp = new JLabel(label);
        labelComp.setPreferredSize(new Dimension(150, 25));
        labelComp.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel valueComp = new JLabel(value);
        valueComp.setFont(new Font("Arial", Font.PLAIN, 12));
        row.add(labelComp);
        row.add(valueComp);

        return row;
    }



}
