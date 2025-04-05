package Vue;
import javax.swing.*;
import java.awt.*;

public class VuePanier extends JFrame{
    public VuePanier() {
        super("Panier - Biscuits&Drinks.co");
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
        topVCC.getIconTop(1);
        mainPanel.add(topVCC);
        mainPanel.add(new Nav());

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(220, 223, 197));
        JLabel titre = new JLabel("Votre Panier");
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        content.add(titre);
        JPanel infoPanier = createInfoPanierPanel();
        content.add(infoPanier);
        JPanel infosPanier = createInfoPanier();
        content.add(infosPanier);
        mainPanel.add(content);
        add(mainPanel);
        setVisible(true);

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
    private JPanel createInfoPanierPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Ma commande"));
        panel.add(createInfoRow("Sous total:", "XXX"));
        panel.add(createInfoRow("Nombre de produits:", "12"));

        JLabel messageConnexion = new JLabel("Veuillez vous connecter pour commander");
        panel.add(messageConnexion);
        JButton connectButton = new JButton("Se Connecter");
        panel.add(connectButton);

        JLabel messageInscription = new JLabel("Vous n'êtes pas encore inscrit ? ");
        panel.add(messageInscription);
        JButton inscriptionButton = new JButton("S inscrire");
        panel.add(inscriptionButton);
        inscriptionButton.setSize(15,20);

        JButton validerButton = new JButton("VALIDER MA COMMANDE");
        validerButton.setSize(15,20);
        panel.add(validerButton);

        return panel;
    }
    private JPanel createInfoPanier() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(220, 223, 1));
        String[] Infos = {"NomProduit", "Quantite", "Prix", "Description"};
        for (int i = 0; i < Infos.length; i++) {
            JLabel label = new JLabel(Infos[i], SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            panel.add(label);
        }

        return panel;

    }
}