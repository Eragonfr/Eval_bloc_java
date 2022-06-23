package fr.cesi.api;

import fr.cesi.api.authentication.AuthenticationManager;
import fr.cesi.api.authentication.AuthenticationManagerImpl;
import fr.cesi.api.data.SimpleHttpResult;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class AuthenticationHandler {
  /**
   * Check if the user request is allowed in this context
   */
  public static void checkToken(@NotNull RoutingContext routingContext) {
    final String authorizationHeader = routingContext.request().getHeader("Authorization");
    final String token = authorizationHeader.split(" ")[1];

    AuthenticationManager authenticationManager = AuthenticationManagerImpl.getInstance();

    if (!authenticationManager.checkToken(token)) {
      routingContext.response()
        .setStatusCode(401)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(new SimpleHttpResult(401, "You should login before accessing this resource")));
    }
    routingContext.next();
  }

  private static boolean emailValidation(String email) {
    String regex = "^(.+)@(.+)$";
    Pattern pattern = Pattern.compile(regex);
    if (email == null) {
      return false;
    }
    return pattern.matcher(email).matches();
  }
}
