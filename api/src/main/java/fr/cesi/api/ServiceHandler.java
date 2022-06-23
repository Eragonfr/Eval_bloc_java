package fr.cesi.api;

import fr.cesi.api.dao.PlaceDao;
import fr.cesi.api.dao.ServicesDao;
import fr.cesi.api.dao.UserDao;
import fr.cesi.api.data.*;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceHandler {
  public static void getServices(@NotNull RoutingContext routingContext) {
    try {
      ArrayList<Workplace> services = ServicesDao.getServices();

      routingContext.response()
        .setStatusCode(200)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(services));
    } catch (Exception e) {
      System.err.println(e);

      routingContext.response()
        .setStatusCode(500)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(new SimpleHttpResult(500, "Internal server error.")));
    }
  }

  public static void getService(@NotNull RoutingContext routingContext) {
    try {
      Workplace service = ServicesDao.getService(Integer.parseInt(routingContext.pathParam("id")));

      routingContext.response()
        .setStatusCode(200)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(service));
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

  public static void deleteService(@NotNull RoutingContext routingContext) {
    try {
      ServicesDao.deleteService(Integer.parseInt(routingContext.pathParam("id")));

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

  public static void updateService(@NotNull RoutingContext routingContext) {
    try {
      Workplace workplace = Json.decodeValue(routingContext.getBodyAsString(), WorkplaceImpl.class);
      int workplaceId = ServicesDao.updateService(workplace);
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

  public static void createService(@NotNull RoutingContext routingContext) {
    try {
      RegistrationWorkplace registrationWorkplace = Json.decodeValue(routingContext.getBodyAsString(), RegistrationWorkplace.class);
      Workplace workplace = ServicesDao.addService(new WorkplaceImpl(registrationWorkplace));

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
