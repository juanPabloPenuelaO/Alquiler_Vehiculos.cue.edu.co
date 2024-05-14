package repository.userImpl;

import config.DatabaseConnection;
import mapping.dtos.userDTO;
import model.reservations;
import model.user;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static config.DatabaseConnection.getConnection;

public class userRepositoryJDBC implements Repository<user> {

    private Connection conn;

    public userRepositoryJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void addUser(userDTO userDTO) {

    }

    @Override
    public List<user> list() {
        List<user> users = new ArrayList<>();

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                user user = createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar clientes", e);
        }
        return users;
    }

    @Override
    public user byId(int id) {
        user User = null;
        try {
            String sql = "SELECT * FROM cliente WHERE id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User = createUser(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cliente por ID", e);
        }
        return User;
    }

    @Override
    public void save(user User) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                "INSERT INTO users(name, lastName, phoneNumber, password) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, User.getName());
            preparedStatement.setString(2, User.getLastName());
            preparedStatement.setInt(3, User.getPhoneNumber());
            preparedStatement.setString(4, User.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar cliente", e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                "DELETE FROM cliente WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar cliente", e);
        }
    }

    @Override
    public void update(reservations reservation) {

    }


    private user createUser(ResultSet resultSet) throws SQLException {
        user User = new user();
        User.setId(resultSet.getInt("id"));
        User.setName(resultSet.getString("name"));
        User.setLastName(resultSet.getString("lastName"));
        User.setPhoneNumber(resultSet.getInt("phoneNumber"));
        User.setPassword(resultSet.getString("password"));
        return User;
    }
}