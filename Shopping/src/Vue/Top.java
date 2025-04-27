package Vue;
import Controleur.ControleurAdmin;
import Controleur.ControleurClient;
import Controleur.ControleurPanier;
import Controleur.ControleurTop;
import DAO.DAOFactory;
import Modele.User;
import Vue.VueCompteAdmin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//
//classe qui gere la barre de recherche et les icones
public class Top extends JPanel {
    public JButton utilisateur;
    public JButton panier;
    public JButton deconnexion;
    private ControleurAdmin controleurAdmin;
    private ControleurClient controleurClient;
   // private ControleurTop controleurTop;
    private Mywindow parent;

    public Top(Mywindow parent) {
        this.parent = parent;
        DAOFactory daoFactory = DAOFactory.getInstance("shoppingBD", "root", "root");
        this.controleurAdmin = new ControleurAdmin(daoFactory);
        this.controleurClient = new ControleurClient(daoFactory);

        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(800, 50));
        setBackground(new Color(3, 64, 38));
        JLabel nomSite = new JLabel("Biscuits&Drinks.co", JLabel.LEFT);
        nomSite.setForeground(Color.BLACK);

        JTextField BarreRecherche = new JTextField("Rechercher");
        BarreRecherche.setEditable(true);
        ControleurTop controleur = new ControleurTop();
        controleur.actionRecherche(BarreRecherche,parent);


        JPanel icons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        icons.setBackground(new Color(245, 225, 207));
        this.utilisateur = new JButton("👤");
        this.utilisateur.setBackground(new Color(255, 255, 255));
        this.panier = new JButton("🛒");
        this.panier.setBackground(new Color(255, 255, 255));
        this.deconnexion = new JButton("❌");
        this.deconnexion.setBackground(new Color(170, 54, 54));

        utilisateur.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getIconTop(0);
                User currentUser = parent.getCurrentUser(); // Utiliser le getter

                if (currentUser == null) {
                    JOptionPane.showMessageDialog(parent,
                            "Veuillez vous connecter",
                            "Erreur",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                //Vérification du statut de l'user pour le renvoyer vers la bonne page : Admin si statut=1 ou Client si =0
                if (currentUser != null) {
                    if (currentUser.getStatutUser() == 1) { // Admin
                        VueCompteAdmin pageAdmin = new VueCompteAdmin(parent, currentUser.getIdUser(), controleurAdmin);
                        parent.addAndShowPanel(pageAdmin, "compteAdmin");
                    } else { // Client
                        VueCompteClient pageClient = new VueCompteClient(parent, currentUser.getIdUser(), controleurClient);
                        parent.addAndShowPanel(pageClient, "compteClient");
                    }
                }
            }
        });

        panier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getIconTop(1);
                VuePanier pagePanier = new VuePanier(parent);
                parent.addAndShowPanel(pagePanier, "panier");


            }
        });
        deconnexion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getIconTop(-1);
                int reponse = JOptionPane.showConfirmDialog(
                        parent,
                        "Voulez-vous vraiment vous déconnecter ?",
                        "Confirmation de déconnexion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (reponse == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        // evenement pour revenir à l'accueil
        nomSite.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Réinitialiser tous les boutons
                getIconTop(-1);
                parent.addAndShowPanel(new JPanel(), "home");
            }
        });

        icons.add(utilisateur);
        icons.add(panier);
        icons.add(deconnexion);

        add(nomSite, BorderLayout.WEST);
        add(BarreRecherche, BorderLayout.CENTER);
        add(icons, BorderLayout.EAST);
    }

    public JButton getIconTop(int x) {
        // Réinitialiser tous les boutons
        utilisateur.setBackground(new Color(255, 255, 255));
        panier.setBackground(new Color(255, 255, 255));
        deconnexion.setBackground(Color.WHITE);

        switch(x) {
            case 0:
                utilisateur.setBackground(new Color(115, 162, 191));
                return utilisateur;
            case 1:
                panier.setBackground(new Color(115, 162, 191));
                return panier;
            default:
                return null;
        }
    }
}
