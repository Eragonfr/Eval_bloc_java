package fr.cesi.api;

import fr.cesi.api.dao.PlaceDao;
import fr.cesi.api.dao.UserDao;
import fr.cesi.api.data.*;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceHandler {
  public static void getPlaces(@NotNull RoutingContext routingContext) {
    try {
      ArrayList<Workplace> places = PlaceDao.getPlaces();

      routingContext.response()
        .setStatusCode(200)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(places));
    } catch (Exception e) {
      System.err.println(e);

      routingContext.response()
        .setStatusCode(500)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(new SimpleHttpResult(500, "Internal server error.")));
    }
  }

  public static void getPlace(@NotNull RoutingContext routingContext) {
    try {
      Workplace place = PlaceDao.getPlace(Integer.parseInt(routingContext.pathParam("id")));

      routingContext.response()
        .setStatusCode(200)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(place));
    }catch (NumberFormatException e) {
      routingContext.response()
        .setStatusCode(400)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(new SimpleHttpResult(400, "Param must be a number.")));
    } catch (Exception e) {
      System.err.println(e);

      routingContext.response()
        .setStatusCode(500)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(new SimpleHttpResult(500, "Internal server error.")));
    }
  }

  public static void deletePlace(@NotNull RoutingContext routingContext) {
    try {
      PlaceDao.deletePlace(Integer.parseInt(routingContext.pathParam("id")));

      routingContext.response()
        .setStatusCode(204)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end();
    } catch (NumberFormatException e) {
      routingContext.response()
        .setStatusCode(400)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(new SimpleHttpResult(400, "Param must be a number.")));
    } catch (Exception e) {
      System.err.println(e);

      routingContext.response()
        .setStatusCode(500)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(new SimpleHttpResult(500, "Internal server error.")));
    }
  }

  public static void updatePlace(@NotNull RoutingContext routingContext) {
    try {
      Workplace workplace = Json.decodeValue(routingContext.getBodyAsString(), WorkplaceImpl.class);
      int workplaceId = PlaceDao.updatePlace(workplace);
      workplace.setId(workplaceId);

      routingContext.response()
        .setStatusCode(204)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(workplace));
    } catch (Exception e) {
      System.err.println(e);

      routingContext.response()
        .setStatusCode(500)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(new SimpleHttpResult(500, "Internal server error.")));
    }
  }

  public static void createPlace(@NotNull RoutingContext routingContext) {
    try {
      RegistrationWorkplace registrationWorkplace = Json.decodeValue(routingContext.getBodyAsString(), RegistrationWorkplace.class);
      Workplace workplace = PlaceDao.addPlace(new WorkplaceImpl(registrationWorkplace));

      routingContext.response()
        .setStatusCode(204)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(workplace));
    } catch (Exception e) {
      System.err.println(e);

      routingContext.response()
        .setStatusCode(500)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(new SimpleHttpResult(500, "Internal server error.")));
    }
  }
}
