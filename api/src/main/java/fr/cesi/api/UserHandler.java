package fr.cesi.api;

import fr.cesi.api.dao.UserDao;
import fr.cesi.api.data.RegistrationUser;
import fr.cesi.api.data.SimpleHttpResult;
import fr.cesi.api.data.User;
import fr.cesi.api.data.UserImpl;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UserHandler {
  public static void getUsers(@NotNull RoutingContext routingContext) {
    try {
      ArrayList<User> users = (ArrayList<User>) UserDao.searchUser("");
      routingContext.response()
        .setStatusCode(200)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(users));
    } catch (Exception e) {
      System.err.println(e);

      routingContext.response()
        .setStatusCode(500)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(new SimpleHttpResult(500, "Internal server error.")));
    }
  }

  public static void getUser(@NotNull RoutingContext routingContext) {
    try {
      User user = UserDao.getUser(Integer.parseInt(routingContext.pathParam("id")));
      routingContext.response()
        .setStatusCode(200)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(user));
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

  public static void deleteUser(@NotNull RoutingContext routingContext) {
    try {
      UserDao.deleteUser(Integer.parseInt(routingContext.pathParam("id")));

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

  public static void searchUser(@NotNull RoutingContext routingContext) {
    try {
      ArrayList<User> users = (ArrayList<User>) UserDao.searchUser(routingContext.pathParam("search"));
      routingContext.response()
        .setStatusCode(200)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(users));
    } catch (Exception e) {
      System.err.println(e);

      routingContext.response()
        .setStatusCode(500)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(new SimpleHttpResult(500, "Internal server error.")));
    }
  }

  public static void createUser(@NotNull RoutingContext routingContext) {
    try {
      RegistrationUser registrationUser = Json.decodeValue(routingContext.getBodyAsString(), RegistrationUser.class);

      @NotNull User user = UserDao.addUser(new UserImpl(registrationUser));

      routingContext.response()
        .setStatusCode(201)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(user));
    } catch (Exception e) {
      System.err.println(e);

      routingContext.response()
        .setStatusCode(500)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(new SimpleHttpResult(500, "Internal server error.")));
    }
  }

  public static void updateUser(@NotNull RoutingContext routingContext) {
    try {
      UserImpl updateUser = Json.decodeValue(routingContext.getBodyAsString(), UserImpl.class);

      int userId = UserDao.updateUser(updateUser);
      updateUser.setId(userId);

      routingContext.response()
        .setStatusCode(201)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(updateUser));
    } catch (Exception e) {
      System.err.println(e);

      routingContext.response()
        .setStatusCode(500)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(new SimpleHttpResult(500, "Internal server error.")));
    }
  }
}
