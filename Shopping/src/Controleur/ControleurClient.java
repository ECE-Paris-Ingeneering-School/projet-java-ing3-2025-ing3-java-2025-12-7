package Controleur;

import DAO.DAOClient;
import DAO.DAOClientIMPL;
import DAO.DAOFactory;
import Modele.Client;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ControleurClient {
    private final DAOClient daoClient;
    private final DAOFactory daoFactory;

    public ControleurClient(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        try {
            this.daoClient = new DAOClientIMPL(daoFactory.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }

    }

    public Client getClient(int idClient) {
        try {
            return daoClient.getClientById(idClient);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Map<String, Object>> getCommandesClient(int idClient) {
        try {
            return daoClient.getCommandesByClientId(idClient);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public float calculerTotalAchats(List<Map<String, Object>> commandes) {
        if (commandes == null) return 0;

        float total = 0;
        for (Map<String, Object> cmd : commandes) {
            total += (float) cmd.get("montant");
        }
        return total;
    }

    public DAOFactory getDaoFactory() {
        return daoFactory;
    }
}
