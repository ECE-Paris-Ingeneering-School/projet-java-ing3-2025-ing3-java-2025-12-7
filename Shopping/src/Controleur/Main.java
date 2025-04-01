package Controleur;
import Vue.Mywindow;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        SwingUtilities.invokeLater(Mywindow::new);

    }
}