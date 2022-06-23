package fr.cesi.api.authentication;

public interface AuthenticationManager {
  /**
   * Verify the provided token
   */
  boolean checkToken(String token);
}
