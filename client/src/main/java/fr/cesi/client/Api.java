package fr.cesi.client;

import fr.cesi.api.data.User;

public interface Api {
  @GET("")
  Call<List<User>> getUsers();
}
