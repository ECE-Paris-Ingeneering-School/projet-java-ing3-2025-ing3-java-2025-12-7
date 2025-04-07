package Vue;
import javax.swing.*;
import java.awt.*;


public class Mywindow extends JFrame {


    public Mywindow() {
        super("www.Biscuits&Drinks.co");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        // Panel principal avec disposition verticale
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(220, 223, 197));
        // Ajout des différentes sections
        mainPanel.add(new Top());
        mainPanel.add(new Nav());
        mainPanel.add(new FiltrerTrier());
        mainPanel.add(new ImageP());
        mainPanel.add(new Bottom());


        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

}

