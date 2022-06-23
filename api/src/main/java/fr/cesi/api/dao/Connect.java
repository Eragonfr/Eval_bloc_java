package fr.cesi.api.dao;

import java.sql.Connection;

public interface Connect {

  /**
   * Récupère la connection a la BDD
   *
   * @return la connection
   */
  Connection getConnection();

}
