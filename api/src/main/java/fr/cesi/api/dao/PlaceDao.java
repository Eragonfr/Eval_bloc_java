package fr.cesi.api.dao;

import fr.cesi.api.data.Workplace;
import fr.cesi.api.data.WorkplaceImpl;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceDao {
  public static @NotNull Workplace addPlace(@NotNull Workplace workplace) throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    try (PreparedStatement insertWorkplace = connect.getConnection().prepareStatement("INSERT INTO places (name) VALUES (?) RETURNING id")) {
      insertWorkplace.setString(1, workplace.getName());

      ResultSet insertedPlaceId = insertWorkplace.executeQuery();
      insertedPlaceId.next();
      workplace.setId(insertedPlaceId.getInt("id"));
    }

    return workplace;
  }

  public static void deletePlace(int workplaceId) throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    try (PreparedStatement deleteWorkplace = connect.getConnection().prepareStatement("DELETE FROM places WHERE id = (?)")) {
      deleteWorkplace.setInt(1, workplaceId);

      deleteWorkplace.execute();
    }
  }

  public static @NotNull Workplace getPlace(int workplaceId) throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    try (PreparedStatement getWorkspace = connect.getConnection().prepareStatement("SELECT * FROM places WHERE id = (?)")) {
      getWorkspace.setInt(1, workplaceId);

      ResultSet workspaceResultSet = getWorkspace.executeQuery();

      Workplace place = new WorkplaceImpl();
      if (workspaceResultSet.next()) {
        place.setId(workspaceResultSet.getInt("id"));
        place.setName(workspaceResultSet.getString("name"));
      }
      return place;
    }
  }

  public static @NotNull ArrayList<Workplace> getPlaces() throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    try (PreparedStatement getWorkspace = connect.getConnection().prepareStatement("SELECT * FROM places;")) {

      ResultSet workspaceResultSet = getWorkspace.executeQuery();

      ArrayList<Workplace> places = new ArrayList<>();
      while (workspaceResultSet.next()) {
        Workplace place = new WorkplaceImpl();
        place.setId(workspaceResultSet.getInt("id"));
        place.setName(workspaceResultSet.getString("name"));
        places.add(place);
      }
      return places;
    }
  }

  public static int updatePlace(@NotNull Workplace workplace) throws SQLException {
    Connect connect = ConnectImpl.getInstance();
    try (PreparedStatement updateWorkspace = connect.getConnection().prepareStatement("UPDATE places SET (name) = (?) WHERE id = (?)")) {
      updateWorkspace.setString(1, workplace.getName());
      updateWorkspace.setInt(2, workplace.getId());

      return updateWorkspace.executeUpdate();
    }
  }
}
