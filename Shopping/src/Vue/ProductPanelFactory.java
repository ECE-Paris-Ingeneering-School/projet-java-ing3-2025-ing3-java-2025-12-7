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

        productPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        productPanel.setPreferredSize(new Dimension(250, 300));
        productPanel.setBackground(new Color(220, 223, 197));

        // Image du produit
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        ImageIcon icon = new ImageIcon(imageKey);
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        imageLabel.setIcon(icon);

        // Panel de l'image
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        imagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePanel.setMaximumSize(new Dimension(220, 150));
        imagePanel.setBackground(new Color(220, 223, 197)); // pour avoir un fond d image de la meme couleur

        productPanel.add(imagePanel);

        // Nom du produit
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productPanel.add(nameLabel);

        // Séparateur
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(230, 2));
        productPanel.add(separator);

        // Panel horizontal pour description et prix
        JPanel descPricePanel = new JPanel();
        descPricePanel.setLayout(new BorderLayout());
        descPricePanel.setBackground(new Color(220, 223, 197));
        descPricePanel.setMaximumSize(new Dimension(230, 60));

        // Description
        JTextArea descLabel = new JTextArea(description);
        descLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        descLabel.setLineWrap(true);
        descLabel.setWrapStyleWord(true);
        descLabel.setBackground(new Color(220, 223, 197));
        descLabel.setEditable(false);
        descLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        descLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        // Prix
        JLabel priceLabel = new JLabel(String.format("%.2f €", price));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setForeground(new Color(102, 51, 0));
        priceLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
        priceLabel.setHorizontalAlignment(SwingConstants.LEFT);

        descPricePanel.add(descLabel, BorderLayout.CENTER);
        descPricePanel.add(priceLabel, BorderLayout.EAST);

        productPanel.add(descPricePanel);

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
    public static JPanel createMarquePanel(String name, String imageKey) {
        JPanel marquePanel = new JPanel();
        marquePanel.setLayout(new BoxLayout(marquePanel, BoxLayout.Y_AXIS));
        marquePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        marquePanel.setPreferredSize(new Dimension(250, 270));
        marquePanel.setBackground(new Color(220, 223, 197));

        // Image
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        ImageIcon icon = new ImageIcon(imageKey);
        Image img = icon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        imageLabel.setIcon(icon);

        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);
        imagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePanel.setMaximumSize(new Dimension(220, 180));
        imagePanel.setBackground(new Color(220, 223, 197));

        marquePanel.add(imagePanel);
        // Espace pour abaisser le nom de la marque
        marquePanel.add(Box.createVerticalStrut(10));

        // Nom de la marque
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        marquePanel.add(nameLabel);

        return marquePanel;
    }


}
