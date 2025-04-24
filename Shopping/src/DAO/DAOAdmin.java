package DAO;

import Modele.Administrateur;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DAOAdmin {
    Administrateur getAdminById(int id) throws SQLException;
    List<Map<String, Object>> getCommandesAdmin() throws SQLException;
    float getTotalVentes() throws SQLException;
}

