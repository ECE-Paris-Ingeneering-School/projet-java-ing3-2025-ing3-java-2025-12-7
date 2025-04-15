package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//cette classe est faite pour creer le panel individuelle de chaque produit.
public class ProductPanelFactory {

    public static JPanel createProductPanel(String name, String description, double price, String imageKey) {

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        productPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        productPanel.setPreferredSize(new Dimension(250, 300));
        productPanel.setBackground(new Color(220, 223, 197));

        // Image du produit
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));

        // Panel de l'image
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        imagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePanel.setMaximumSize(new Dimension(220, 150));

        productPanel.add(imagePanel);

        // Panel pour le nom et le prix
        JPanel infoPanel = new JPanel(new BorderLayout(10, 0));
        infoPanel.setBackground(new Color(220, 223, 197));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        infoPanel.setMaximumSize(new Dimension(230, 30));

        // Prix (gauche)
        JLabel priceLabel = new JLabel(String.format("%.2f €", price));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setForeground(new Color(102, 51, 0));

        // Nom (droite)
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        infoPanel.add(priceLabel, BorderLayout.WEST);
        infoPanel.add(nameLabel, BorderLayout.EAST);
        productPanel.add(infoPanel);

        // Séparateur
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(230, 2));
        productPanel.add(separator);

        // Description
        JTextArea descLabel = new JTextArea(description);
        descLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        descLabel.setLineWrap(true);
        descLabel.setWrapStyleWord(true);
        descLabel.setBackground(new Color(220, 223, 197));
        descLabel.setEditable(false);
        descLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descLabel.setMaximumSize(new Dimension(230, 60));

        productPanel.add(descLabel);

        // Espace avant le bouton
        productPanel.add(Box.createVerticalStrut(15));

        // Bouton Ajouter au panier
        JButton addToCartButton = new JButton("Ajouter au panier");
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.setBackground(new Color(76, 175, 80));
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.setFocusPainted(false);
        addToCartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        null,
                        name + " ajouté à votre panier !",
                        "Produit ajouté",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        productPanel.add(addToCartButton);
        return productPanel;
    }
}
