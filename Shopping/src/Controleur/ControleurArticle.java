package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import Modele.Article;

import Vue.*;

public class ControleurArticle {

    public void boutonAjouter(JButton bouton) {

        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VueFicheAjouter vueAjoutP = new VueFicheAjouter();
                vueAjoutP.setVisible(true);
            }

        });
    }

    public void ValiderAjouter(JButton bouton,VueFicheAjouter vueAjoutP ) {

        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        vueAjoutP,"Produit ajoute avec succès!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }

        });
    }

    public void AnnulerAjouter(JButton bouton,VueFicheAjouter vueAjoutP ) {


        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vueAjoutP.dispose();
            }

        });
    }

    public void boutonModifier(JButton bouton) {

        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VueFicheModifier vueModifP = new VueFicheModifier();
                vueModifP.setVisible(true);
            }

        });
    }

    public void ValiderModifier(JButton bouton,VueFicheModifier vueModifP ) {

        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        vueModifP,"Produit modifié avec succès!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }

        });
    }

    public void AnnulerModifier(JButton bouton,VueFicheModifier vueModifP ) {


        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vueModifP.dispose();
            }

        });
    }

    public void ValiderSupprimer(JButton bouton,VueFicheSupprimer vueSupP ) {

        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        vueSupP,"Produit supprimé avec succès!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }

        });
    }

    public void boutonSupprimer(JButton bouton) {

        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VueFicheSupprimer vueSupP = new VueFicheSupprimer();
                vueSupP.setVisible(true);
            }

        });
    }

    public void AnnulerSupprimer(JButton bouton,VueFicheSupprimer vueSupP ) {


        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vueSupP.dispose();
            }

        });
    }



}
