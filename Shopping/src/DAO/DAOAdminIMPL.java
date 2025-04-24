package DAO;

import Modele.Administrateur;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOAdminIMPL implements DAOAdmin {
    private final Connection connexion;

    public DAOAdminIMPL(Connection connexion) {
        this.connexion = connexion;
    }

    @Override
    public Administrateur getAdminById(int id) throws SQLException {
        String sql = "SELECT u.* FROM User u WHERE u.idUser = ? and u.statutUser = 1";

        try (PreparedStatement stmt = connexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Administrateur(
                        rs.getInt("idUser"),
                        rs.getString("mdpUser"),
                        rs.getString("mailUser"),
                        rs.getInt("statutUser"),
                        rs.getString("nomUser"),
                        rs.getString("prenomUser")
                );
            }else {
                throw new SQLException("Aucun admin trouvé avec l'ID: " + id);
            }
        }
    }

    @Override
    public List<Map<String, Object>> getCommandesAdmin() throws SQLException {
        List<Map<String, Object>> commandes = new ArrayList<>();

        String query = "SELECT c.idCommande, c.dateCommande, p.nomProduit, "
                + "c.quantiteProdCom, c.montantCommande, 'Livrée' as statut "
                + "FROM Commande c "
                + "JOIN HistoriqueCommande hc ON c.idCommande = hc.idCommande "
                + "JOIN Produit p ON hc.idProduit = p.idProduit "
                + "ORDER BY c.dateCommande DESC";

        try (Statement stmt = connexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Map<String, Object> commande = new HashMap<>();
                commande.put("idCommande", rs.getInt("idCommande"));
                commande.put("date", rs.getDate("dateCommande"));
                commande.put("produit", rs.getString("nomProduit"));
                commande.put("quantite", rs.getInt("quantiteProdCom"));
                commande.put("montant", rs.getFloat("montantCommande"));
                commande.put("statut", rs.getString("statut"));
                commandes.add(commande);
            }
        }
        return commandes;
    }

    @Override
    public float getTotalVentes() throws SQLException {
        String query = "SELECT SUM(montantCommande) as total FROM Commande";

        try (Statement stmt = connexion.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                return rs.getFloat("total");
            }
        }
        return 0;
    }
}
