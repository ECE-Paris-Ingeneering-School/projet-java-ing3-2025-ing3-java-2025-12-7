package DAO;

import Modele.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOClientIMPL implements DAOClient {
    private final Connection connection;

    public DAOClientIMPL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Client getClientById(int id) throws SQLException {
        String sql = "SELECT u.*, c.dateNaissanceClient, c.dateAjoutClient " +
                "FROM User u JOIN Client c ON u.idUser = c.idUser " +
                "WHERE u.idUser = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Client(
                        rs.getInt("idUser"),
                        rs.getString("loginUser"),
                        rs.getString("mdpUser"),
                        rs.getString("mailUser"),
                        rs.getInt("statutUser"),
                        rs.getString("nomUser"),
                        rs.getString("prenomUser"),
                        rs.getString("telephoneUser"),
                        rs.getString("adresseUser"),
                        rs.getDate("dateNaissanceClient"),
                        rs.getTimestamp("dateAjoutClient")
                );
            }
        }
        return null;
    }

    public List<Map<String, Object>> getCommandesByClientId(int idClient) throws SQLException {
        List<Map<String, Object>> commandes = new ArrayList<>();

        String query = "SELECT c.idCommande, c.dateCommande, p.nomProduit, " +
                "hc.quantiteProdCom, c.montantCommande, 'Livrée' as statut " +
                "FROM Commande c " +
                "JOIN HistoriqueCommande hc ON c.idCommande = hc.idCommande " +
                "JOIN Produit p ON hc.idProduit = p.idProduit " +
                "WHERE c.idUser = ? " +
                "ORDER BY c.dateCommande DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idClient);
            ResultSet rs = stmt.executeQuery();

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
}
