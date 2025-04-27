package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

import DAO.DAOArticle;
import DAO.DAOFactory;
import DAO.DAOPanier;
import DAO.DAOArticle;
import DAO.DAOCommande;

import Modele.Panier;
import Modele.Client;
import Modele.Article;
import Modele.User;

import Vue.*;

public class ControleurTop {

    public void actionRecherche(JTextField barre, Mywindow parent){


        ArrayList<Article> articles = new ArrayList<>();
        barre.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(barre.getText());

                VueRecherche recherche = new VueRecherche(parent,barre.getText());
                parent.addAndShowPanel(recherche, "recherche");

            }
        });

        // Auto complétion de la recherche pour indiquer a l'utilisateur les bonnes recherches
        // NE FONCTIONNE PAS

        DAOFactory daoFactory = DAOFactory.getInstance("shoppingbd", "root", "root");
        DAOArticle daoArticle = daoFactory.getDAOArticle();
        ArrayList<Article> tousLesArticles = daoArticle.getAll();

        // Set = pas de doublons
        Set<String> suggestionsSet = new HashSet<>();

        for (Article article : tousLesArticles) {
            suggestionsSet.add(article.getNomArticle());
            suggestionsSet.add(article.getMarqueArticle());
            suggestionsSet.add(article.getCategorieArticle());
            suggestionsSet.add(article.getTypeArticle());
        }

        // Tu convertis en liste (si tu en as besoin pour la JPopupMenu)
        List<String> suggestions = new ArrayList<>(suggestionsSet);


        JPopupMenu suggestionMenu = new JPopupMenu();

        barre.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { showSuggestions(); }
            public void removeUpdate(DocumentEvent e) { showSuggestions(); }
            public void insertUpdate(DocumentEvent e) { showSuggestions(); }

            public void showSuggestions() {
                suggestionMenu.setVisible(false);
                suggestionMenu.removeAll();
                String input = barre.getText().toLowerCase();

                if (input.isEmpty()) return;

                for (String suggestion : suggestions) {
                    if (suggestion.startsWith(input)) {
                        JMenuItem item = new JMenuItem(suggestion);
                        item.addActionListener(e -> {
                            barre.setText(suggestion);
                            suggestionMenu.setVisible(true);
                        });
                        suggestionMenu.add(item);
                    }
                }

                if (suggestionMenu.getComponentCount() > 0) {
                    suggestionMenu.show(barre, 0, barre.getHeight());
                }
            }
        });

    }
}

