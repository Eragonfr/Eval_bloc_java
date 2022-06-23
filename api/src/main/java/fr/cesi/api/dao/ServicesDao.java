package fr.cesi.api.dao;

import fr.cesi.api.data.Workplace;
import fr.cesi.api.data.WorkplaceImpl;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServicesDao {
  public static @NotNull Workplace addService(@NotNull Workplace workplace) throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    try (PreparedStatement insertWorkplace = connect.getConnection().prepareStatement("INSERT INTO services (name) VALUES (?) RETURNING id")) {
      insertWorkplace.setString(1, workplace.getName());

      ResultSet insertedServiceId = insertWorkplace.executeQuery();
      insertedServiceId.next();
      workplace.setId(insertedServiceId.getInt("id"));
    }

    return workplace;
  }

  public static void deleteService(int workplaceId) throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    try (PreparedStatement deleteWorkplace = connect.getConnection().prepareStatement("DELETE FROM services WHERE id = (?)")) {
      deleteWorkplace.setInt(1, workplaceId);

      deleteWorkplace.execute();
    }
  }

  public static @NotNull Workplace getService(int workplaceId) throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    try (PreparedStatement getWorkspace = connect.getConnection().prepareStatement("SELECT * FROM services WHERE id = (?)")) {
      getWorkspace.setInt(1, workplaceId);

      ResultSet workspaceResultSet = getWorkspace.executeQuery();

      Workplace service = new WorkplaceImpl();
      if (workspaceResultSet.next()) {
        service.setId(workspaceResultSet.getInt("id"));
        service.setName(workspaceResultSet.getString("name"));
      }
      return service;
    }
  }

  public static @NotNull ArrayList<Workplace> getServices() throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    try (PreparedStatement getWorkspace = connect.getConnection().prepareStatement("SELECT * FROM services;")) {

      ResultSet workspaceResultSet = getWorkspace.executeQuery();

      ArrayList<Workplace> services = new ArrayList<>();
      while (workspaceResultSet.next()) {
        Workplace service = new WorkplaceImpl();
        service.setId(workspaceResultSet.getInt("id"));
        service.setName(workspaceResultSet.getString("name"));
        services.add(service);
      }
      return services;
    }
  }

  public static int updateService(@NotNull Workplace workplace) throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    try (PreparedStatement updateWorkspace = connect.getConnection().prepareStatement("UPDATE services SET (name) = (?) WHERE id = (?)")) {
      updateWorkspace.setString(1, workplace.getName());
      updateWorkspace.setInt(2, workplace.getId());

      return updateWorkspace.executeUpdate();
    }
  }
}
