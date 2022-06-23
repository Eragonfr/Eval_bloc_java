package fr.cesi.api.data;

/**
 * A user is a person, it can be a client or an employee
 * A user have a way to login in the app.
 */
public interface User {
  int getId();

  String getFirstname();

  String getLastname();

  String getCellphone();

  String getDeskphone();

  Workplace getService();

  Workplace getPlace();

  String getMail();

  void setId(int id);

  void setFirstname(String firstname);

  void setLastname(String lastname);

  void setCellphone(String phone);

  void setDeskphone(String phone);

  void setMail(String mail);

  void setService(Workplace service);

  void setPlace(Workplace place);
}
