package Vue;

import javax.swing.*;
import java.awt.*;

public class Bottom extends JPanel {
    public Bottom() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setMaximumSize(new Dimension(800, 100));
        setBackground(new Color(51, 85, 140));
        JLabel titre = new JLabel("Nous contacter ");
        JLabel copyR = new JLabel("Copyright © 2025 ");
        titre.setForeground(new Color(255, 255, 255));
        copyR.setForeground(new Color(255, 255, 255));
        add(titre, BorderLayout.EAST);
        add(copyR, BorderLayout.WEST);

    }
}
