package fr.cesi.api;

import fr.cesi.api.dao.UserDao;
import fr.cesi.api.data.SimpleHttpResult;
import fr.cesi.api.data.User;
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
}
