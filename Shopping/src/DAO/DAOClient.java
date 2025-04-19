package DAO;

import Modele.Client;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DAOClient {
    Client getClientById(int id) throws SQLException;
    List<Map<String, Object>> getCommandesByClientId(int idClient) throws SQLException;
}
