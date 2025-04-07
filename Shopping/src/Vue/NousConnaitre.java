package Vue;

import javax.swing.*;
import java.awt.*;

public class NousConnaitre extends JFrame {
    public NousConnaitre() {
        super("Nous connaitre - Biscuits&Drinks.co");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal avec disposition verticale
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(220, 223, 197));


        Nav navNC = new Nav();
        navNC.getButtonNav(5);
        mainPanel.add(new Top());
        mainPanel.add(navNC);


        // Titre centré
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(220, 223, 197));
        JLabel titre = new JLabel("Qui sommes-nous ?");
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        titre.setForeground(new Color(51, 85, 140));
        titlePanel.add(titre);
        mainPanel.add(titlePanel);

        JPanel NousC = createInfoNousConnaitre();
        NousC.setSize(300,200);
        mainPanel.add(NousC);
        mainPanel.add(new Bottom());

        add(mainPanel);

        setVisible(true);
    }
    private JPanel createInfoNousConnaitre() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Qui sommes nous ? "));

        JTextArea textArea = new JTextArea("Qui sommes-nous ? \n\n" +
                "Chez Biscuits&Drinks.co, nous avons à cœur de vous offrir une expérience gustative unique, où la qualité rencontre la convivialité. Notre mission est simple : vous apporter les meilleurs biscuits et boissons, soigneusement sélectionnés pour ravir vos papilles et égayer vos moments de détente.\n\n" +
                "Nos biscuits sont confectionnés à partir des meilleurs ingrédients, avec une attention particulière portée à leur goût, leur texture et leur fraîcheur. Qu'ils soient croquants, moelleux ou fondants, nos biscuits sont conçus pour satisfaire toutes vos envies gourmandes.\n\n" +
                "Nous proposons également une sélection de boissons pour accompagner vos petites pauses sucrées. Du thé parfumé au café riche, en passant par des jus de fruits rafraîchissants et des boissons plus exotiques, chaque boisson est choisie pour se marier parfaitement avec nos délicieux biscuits.\n\n" +
                "Notre engagement ? Vous offrir des produits de qualité, tout en vous faisant découvrir de nouvelles saveurs à chaque bouchée et gorgée. Nous croyons que la simplicité et la qualité font toute la différence, et c’est pourquoi nous travaillons avec des producteurs et artisans locaux pour garantir la meilleure expérience possible.\n\n" +
                "Alors, que vous soyez en quête de douceur ou à la recherche de nouvelles saveurs, nous avons quelque chose pour vous. Laissez-vous tenter par nos produits et partagez un moment de gourmandise avec Biscuits&Drinks.co !");
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(textArea);
        panel.setSize(100, 200);

        return panel;
    }
}
