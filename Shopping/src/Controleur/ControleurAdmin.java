package Controleur;

import DAO.DAOAdmin;
import DAO.DAOAdminIMPL;
import DAO.DAOFactory;
import Modele.Administrateur;
import java.util.List;
import java.util.Map;

public class ControleurAdmin {
    private final DAOAdmin daoAdmin;
    private final DAOFactory daoFactory;

    public ControleurAdmin(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        try {
            this.daoAdmin = new DAOAdminIMPL(daoFactory.getConnection());
        } catch (Exception e) {
            throw new RuntimeException("Erreur d'accès aux données admin", e);
        }
    }

    public Administrateur getAdmin(int idAdmin) {
        try {
            Administrateur admin = daoAdmin.getAdminById(idAdmin);
            if (admin == null) {
                throw new RuntimeException("Admin introuvable pour l'ID: " + idAdmin);
            }
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération de l'admin", e);
        }
    }

    public List<Map<String, Object>> getCommandesAdmin() {
        try {
            return daoAdmin.getCommandesAdmin();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public float getTotalVentes() {
        try {
            return daoAdmin.getTotalVentes();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
