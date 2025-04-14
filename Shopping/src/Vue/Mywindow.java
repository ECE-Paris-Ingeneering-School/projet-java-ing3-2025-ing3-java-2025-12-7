package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mywindow extends JFrame {
    private JPanel contentPanel;

    public Mywindow() {
        super("www.Biscuits&Drinks.co");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        contentPanel = new JPanel(new CardLayout());
        JPanel mainView = createMainView();
        contentPanel.add(mainView, "main");
        add(contentPanel, BorderLayout.CENTER);
        showMainView();
        setVisible(true);
    }

    private JPanel createMainView() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(220, 223, 197));

        Top top = new Top();

        mainPanel.add(top);
        mainPanel.add(new Nav());
        mainPanel.add(new FiltrerTrier());
        mainPanel.add(new ImageP());
        mainPanel.add(new Bottom());

        top.utilisateur.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                top.getIconTop(0);
                VueCompteClient pageClient = new VueCompteClient();
                contentPanel.add(pageClient, "compteClient");
                showView("compteClient");
            }
        });

        top.panier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                top.getIconTop(1);
                VuePanier pagePanier = new VuePanier();
                contentPanel.add(pagePanier, "panier");
                showView("panier");
            }
        });

        return mainPanel;
    }

    public void showMainView() {
        showView("main");
    }

    private void showView(String viewName) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, viewName);
    }
}
