package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class VueFicheAjouter extends JFrame {
    private JTextField ID;
    private JTextField NOM;
    private JTextField QUANTITE;
    private JTextField MARQUE;
    private JTextField PRIX;

    private JComboBox<String> categorie;
    private JTextField datePeremp;
    private JButton AjouterArticle;
    private JButton Annuler;

    public VueFicheAjouter() {
        super("Ajouter un Produit");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        ID= new JTextField(10);
        NOM = new JTextField(10);
        QUANTITE = new JTextField(10);
        MARQUE = new JTextField(10);
        PRIX = new JTextField(10);
        String[] categories = {"Biscuit","Boisson"};
        categorie = new JComboBox<>(categories);
        datePeremp = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
        // Ajout des labels et champs de texte
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(ID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Nom:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(NOM, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Quantité:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(QUANTITE, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Prix:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(PRIX, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Marque:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(MARQUE, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Catégorie:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(categorie, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Date de péremption:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(datePeremp, gbc);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        AjouterArticle = new JButton("Ajouter");
        Annuler = new JButton("Annuler");

        buttonPanel.add(AjouterArticle);
        buttonPanel.add(Annuler);

        // Listeners pour les boutons ajouter et annuler
        AjouterArticle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}
        });

        Annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);
    }

}
