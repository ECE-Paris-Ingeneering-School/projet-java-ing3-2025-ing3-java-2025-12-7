package Vue;

import javax.swing.*;
import java.awt.*;
//classe pour creer un panel d informations
public class createInfoRow extends JPanel {
    private JLabel valueComp;

    public createInfoRow(String label, String value) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
        setBackground(Color.WHITE);

        JLabel labelComp = new JLabel(label);
        labelComp.setPreferredSize(new Dimension(140, 20));
        labelComp.setFont(new Font("Arial", Font.PLAIN, 12));
        add(labelComp);

        valueComp = new JLabel(value != null ? value : "");
        valueComp.setFont(new Font("Arial", Font.BOLD, 12));
        add(valueComp);
    }


    public createInfoRow() {
        this("Label", "");
    }

    public void setText(String value) {
        if (valueComp != null) {
            valueComp.setText(value);
        }
    }

}
