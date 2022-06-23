package fr.cesi.api.authentication;

public class AuthenticationManagerImpl implements AuthenticationManager {
  // The admin token/password
  static private final String token = "MySuperStrongToken";
  // Service instance
  private static AuthenticationManager instance;

  private AuthenticationManagerImpl() {
  }

  public static AuthenticationManager getInstance() {
    if (AuthenticationManagerImpl.instance == null) {
      instance = new AuthenticationManagerImpl();
    }
    return instance;
  }

  @Override
  public boolean checkToken(String token) {
    if (token == this.token) {
      return true;
    }
    return false;
  }
}
