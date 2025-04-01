package Vue;
import javax.swing.*;
import java.awt.*;

public class ImageP extends JPanel {
    public ImageP() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.WHITE);
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\kawid\\OneDrive\\Desktop\\ING3\\POO Java\\Projet\\welcomeSSSite.jpg");
        // Redimensionner l’image
        Image image = originalIcon.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);

        JLabel imageLb = new JLabel(resizedIcon);
        add(imageLb);
    }
}

