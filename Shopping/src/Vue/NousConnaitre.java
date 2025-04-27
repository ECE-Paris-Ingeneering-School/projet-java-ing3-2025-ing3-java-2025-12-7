package Vue;

import javax.swing.*;
import java.awt.*;
// classe qui affiche les informations du site
public class NousConnaitre extends JPanel {
    public NousConnaitre() {
        setLayout(new BorderLayout());
        setBackground(new Color(216, 232, 173));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(220, 223, 197));

        // Titre
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(220, 223, 197));
        JLabel titre = new JLabel("Qui sommes-nous ?");
        titre.setFont(new Font("Serif", Font.BOLD, 26));
        titre.setForeground(new Color(3, 64, 38));
        titlePanel.add(titre);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(titlePanel);

        // Panel centré avec effet carte
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBackground(new Color(220, 223, 197));
        JPanel infoPanel = createInfoNousConnaitre();
        centerPanel.add(infoPanel);
        mainPanel.add(centerPanel);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(new Bottom());

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createInfoNousConnaitre() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            }
        };
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(700, 180));
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setOpaque(false);

        // Contenu
        JTextArea textArea = new JTextArea(
                "Chez Biscuits&Drinks.co, nous sommes passionnés par l'art de découvrir et proposer des produits uniques et savoureux. Notre objectif est de vous offrir une expérience gustative originale, avec une sélection artisanale de biscuits,boissons, et autres curiosités venues du monde entier.\n\n" +
                        "Bienvenue dans notre univers ! "
        );
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFocusable(false);

        panel.add(textArea, BorderLayout.CENTER);

        return panel;
    }
}