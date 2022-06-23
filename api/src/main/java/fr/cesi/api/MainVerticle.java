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
    // List of all users
    router.get("/").handler(UserHandler::getUsers);

    // Search
    router.get("/user/search/:search").handler(UserHandler::searchUser);

    // Get user
    router.get("/user/:id").handler(UserHandler::getUser);

    // Create user
    router.post("/user").handler(AuthenticationHandler::checkToken);
    router.post("/user").handler(UserHandler::createUser);

    // Delete user
    router.delete("/user/:id").handler(AuthenticationHandler::checkToken);
    router.delete("/user/:id").handler(UserHandler::deleteUser);

    // Update user
    router.put("/user/:id").handler(AuthenticationHandler::checkToken);
    router.put("/user/:id").handler(UserHandler::updateUser);

    // Get place list
    router.get("/place").handler(PlaceHandler::getPlaces);

    // Get one place
    router.get("/place/:id").handler(PlaceHandler::getPlace);

    // Add a place
    router.post("/place").handler(AuthenticationHandler::checkToken);
    router.post("/place").handler(PlaceHandler::createPlace);

    // Delete one place
    router.delete("/place/:id").handler(AuthenticationHandler::checkToken);
    router.delete("/place/:id").handler(PlaceHandler::deletePlace);

    // Update place
    router.put("/place/:id").handler(AuthenticationHandler::checkToken);
    router.put("/place/:id").handler(PlaceHandler::updatePlace);

    // Get service list
    router.get("/service").handler(ServiceHandler::getServices);

    // Get one service
    router.get("/service/:id").handler(ServiceHandler::getService);

    // Add a service
    router.post("/service").handler(AuthenticationHandler::checkToken);
    router.post("/service").handler(ServiceHandler::createService);

    // Delete one service
    router.delete("/service/:id").handler(AuthenticationHandler::checkToken);
    router.delete("/service/:id").handler(ServiceHandler::deleteService);

    // Update service
    router.put("/service/:id").handler(AuthenticationHandler::checkToken);
    router.put("/service/:id").handler(ServiceHandler::updateService);

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
