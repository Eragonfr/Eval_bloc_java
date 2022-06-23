package fr.cesi.api;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    // Create a Router
    Router router = Router.router(vertx);

    // Allow POST requests on all urls
    router.route().handler(BodyHandler.create());

    // -- Set the routes in the router --
    // Main page
    router.get("/").handler(UserHandler::getUsers);

    // Demo validation token
    // On vérifie le token
    router.get("/validate-token").handler(AuthenticationHandler::checkToken);
    // Puis si le token est bon cette fonction là est appelé
    //router.get("/validate-token").handler();

    // Serve static resources from the /assets directory
    router.route("/assets/*").handler(StaticHandler.create("assets"));

    // Create the HTTP server and show it port to the console
    vertx.createHttpServer()
      .requestHandler(router)
      .listen(config().getInteger("http.port", 8888))
      .onSuccess(server ->
        System.out.println(
          "BHS API listening on http://localhost:" + server.actualPort() + "/"
        )
      );
  }
}
