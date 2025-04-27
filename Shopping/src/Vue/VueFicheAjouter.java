package Vue;

import Controleur.ControleurArticle;

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
    private JComboBox<String> type;
    private JTextField datePeremp;
    private JButton AjouterArticle;
    private JButton Annuler;
    private final Color backgroundColor = new Color(220, 223, 197);

    public VueFicheAjouter() {
        super("Ajouter un Produit");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 450);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel titrePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titrePanel.setBackground(backgroundColor);
        JLabel titre = new JLabel("Ajouter un Produit");
        titre.setFont(new Font("Serif", Font.BOLD, 18));
        titre.setForeground(new Color(102, 51, 0));
        titrePanel.add(titre);
        mainPanel.add(titrePanel, BorderLayout.NORTH);


        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        //ID = createStyledTextField();
        NOM = createStyledTextField();
        QUANTITE = createStyledTextField();
        MARQUE = createStyledTextField();
        PRIX = createStyledTextField();
        String[] categories = {"Limonade", "Jus de fruit","Sirop","Thé glacé","Boisson énergisante","Cookies","Biscuits","Madeleines","Sablés","Gaufrettes"};
        categorie = new JComboBox<>(categories);
        categorie.setBackground(Color.WHITE);
        categorie.setPreferredSize(new Dimension(150, 25));
        String[] types = {"Biscuit", "Boisson"};
        type = new JComboBox<>(types);
        type.setBackground(Color.WHITE);
        type.setPreferredSize(new Dimension(150, 25));
        datePeremp = new JTextField(10);
        datePeremp.setPreferredSize(new Dimension(150, 25));
        datePeremp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)), BorderFactory.createEmptyBorder(3, 5, 3, 5)));
        datePeremp.setText("");

        int row = 0;
        //addFormField(formPanel, gbc, "ID:", ID, row++);
        addFormField(formPanel, gbc, "Nom:", NOM, row++);
        addFormField(formPanel, gbc, "Quantité:", QUANTITE, row++);
        addFormField(formPanel, gbc, "Prix (€):", PRIX, row++);
        addFormField(formPanel, gbc, "Marque:", MARQUE, row++);
        addFormField(formPanel, gbc, "Catégorie:", categorie, row++);
        addFormField(formPanel, gbc, "Type:", type, row++);
        addFormField(formPanel, gbc, "Date de péremption:", datePeremp, row++);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(null);
        scrollPane.setBackground(backgroundColor);
        scrollPane.setPreferredSize(new Dimension(400, 280));

        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        formContainer.setBackground(backgroundColor);
        formContainer.add(scrollPane);
        mainPanel.add(formContainer, BorderLayout.CENTER);

        // Panneau des boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)); // Espacement réduit
        buttonPanel.setBackground(backgroundColor);
        AjouterArticle = createStyledButton("AJOUTER", new Color(51, 85, 140));
        Annuler = createStyledButton("ANNULER", new Color(0, 0, 0));


        buttonPanel.add(AjouterArticle);
        buttonPanel.add(Annuler);


        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        ControleurArticle controleurArticle = new ControleurArticle();
        controleurArticle.ValiderAjouter(AjouterArticle,this);
        controleurArticle.AnnulerAjouter(Annuler,this);

//        // Listeners pour les boutons ajouter et annuler
//        AjouterArticle.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(
//                        VueFicheAjouter.this,"Produit ajoute avec succès!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
//            }
//        });

//        Annuler.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//            }
//        });

        setContentPane(mainPanel);
        setVisible(true);


    }
    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(150, 25));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(3, 5, 3, 5)
        ));
        return field;
    }

    // Méthode pour ajouter un champ au formulaire avec son label
    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent field, int gridy) {
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.weightx = 0.0;

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
    }


    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12)); // Police plus petite
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        button.setPreferredSize(new Dimension(100, 30));
        return button;
    }

}
