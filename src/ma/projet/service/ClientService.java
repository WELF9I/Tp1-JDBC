package ma.projet.service;

import ma.projet.beans.Client;
import ma.projet.dao.IDao;
import ma.projet.connexion.Connexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService implements IDao<Client> {
    @Override
    public boolean create(Client o) {
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO client (nom, prenom) VALUES (?, ?)")) {
            statement.setString(1, o.getNom());
            statement.setString(2, o.getPrenom());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Client o) {
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM client WHERE id = ?")) {
            statement.setInt(1, o.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client o) {
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE client SET nom = ?, prenom = ? WHERE id = ?")) {
            statement.setString(1, o.getNom());
            statement.setString(2, o.getPrenom());
            statement.setInt(3, o.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Client findById(int id) {
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM client WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Client(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = Connexion.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM client")) {
            while (resultSet.next()) {
                clients.add(new Client(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}

