package fr.cesi.api.dao;

import fr.cesi.api.data.*;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
  public static @NotNull User addUser(@NotNull User user) throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    try (PreparedStatement insertUser = connect.getConnection().prepareStatement("INSERT INTO users (lastname, firstname, cellphone, deskphone, email, service, place) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id")) {
      insertUser.setString(1, user.getFirstname());
      insertUser.setString(2, user.getLastname());
      insertUser.setString(3, user.getCellphone());
      insertUser.setString(4, user.getDeskphone());
      insertUser.setString(5, user.getMail());
      insertUser.setInt(6, user.getService().getId());
      insertUser.setInt(7, user.getPlace().getId());

      ResultSet insertedUserID = insertUser.executeQuery();
      insertedUserID.next();
      user.setId(insertedUserID.getInt("id"));
    }

    return user;
  }

  public static int updateUser(@NotNull User user) throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    try (PreparedStatement updateUser = connect.getConnection().prepareStatement("UPDATE users SET (firstname, lastname, cellphone, deskphone, email, service, place) = (?, ?, ?, ?, ?, ?, ?) WHERE id = (?)")) {
      updateUser.setString(1, user.getFirstname());
      updateUser.setString(2, user.getLastname());
      updateUser.setString(3, user.getCellphone());
      updateUser.setString(4, user.getDeskphone());
      updateUser.setString(5, user.getMail());
      updateUser.setInt(6, user.getService().getId());
      updateUser.setInt(7, user.getPlace().getId());
      updateUser.setInt(8, user.getId());

      return updateUser.executeUpdate();
    }
  }

  public static void deleteUser(int userId) throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    try (PreparedStatement updateUser = connect.getConnection().prepareStatement("DELETE FROM users WHERE id = (?)")) {
      updateUser.setInt(1, userId);

      updateUser.execute();
    }
  }

  public static @NotNull List<User> searchUser(String search) throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    ArrayList<User> users = new ArrayList<>();
    try (PreparedStatement preparedStatement = connect.getConnection().prepareStatement("SELECT users.id, users.firstname, users.lastname, users.cellphone, users.deskphone, users.email, places.id as place_id, places.name as place_name, services.id as service_id, services.name as service_name FROM users LEFT JOIN places ON users.place=places.id LEFT JOIN services ON users.service=services.id WHERE users.firstname ILIKE (?) OR users.lastname ILIKE (?)")) {
      preparedStatement.setString(1, "%" + search + "%");
      preparedStatement.setString(2, "%" + search + "%");
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        User user = new UserImpl();

        user.setId(resultSet.getInt("id"));
        user.setFirstname(resultSet.getString("firstname"));
        user.setLastname(resultSet.getString("lastname"));
        user.setCellphone(resultSet.getString("cellphone"));
        user.setDeskphone(resultSet.getString("deskphone"));
        user.setMail(resultSet.getString("email"));

        Workplace service = new WorkplaceImpl();
        service.setId(resultSet.getInt("service_id"));
        service.setName(resultSet.getString("service_name"));

        user.setService(service);

        Workplace place = new WorkplaceImpl();
        place.setId(resultSet.getInt("place_id"));
        place.setName(resultSet.getString("place_name"));
        user.setPlace(place);

        users.add(user);
      }
    }
    return users;
  }

  public static @NotNull User getUser(int userId) throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    User user = new UserImpl();
    try (PreparedStatement preparedStatement = connect.getConnection().prepareStatement("SELECT users.id, users.firstname, users.lastname, users.cellphone, users.deskphone, users.email, places.id as place_id, places.name as place_name, services.id as service_id, services.name as service_name FROM users LEFT JOIN places ON users.place=places.id LEFT JOIN services ON users.service=services.id WHERE (users.id = ?);")) {
      preparedStatement.setInt(1, userId);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        user.setId(resultSet.getInt("id"));
        user.setFirstname(resultSet.getString("firstname"));
        user.setLastname(resultSet.getString("lastname"));
        user.setCellphone(resultSet.getString("cellphone"));
        user.setDeskphone(resultSet.getString("deskphone"));
        user.setMail(resultSet.getString("email"));

        Workplace service = new WorkplaceImpl();
        service.setId(resultSet.getInt("service_id"));
        service.setName(resultSet.getString("service_name"));

        user.setService(service);

        Workplace place = new WorkplaceImpl();
        place.setId(resultSet.getInt("place_id"));
        place.setName(resultSet.getString("place_name"));
        user.setPlace(place);
      }
    }
    return user;
  }
}
