package fr.cesi.api.data;

/**
 * User class, this give some basic definitions of a user
 */
public class UserImpl implements User {
  private int id;
  private String lastname;
  private String firstname;
  private String cellphone;
  private String deskphone;
  private String mail;
  private Workplace service;
  private Workplace place;

  public UserImpl() {}

  public UserImpl(RegistrationUser registrationUser) {
    this.firstname = registrationUser.firstname;
    this.lastname = registrationUser.lastname;
    this.cellphone = registrationUser.cellphone;
    this.deskphone = registrationUser.deskphone;
    this.mail = registrationUser.mail;
    this.place = registrationUser.place;
    this.service = registrationUser.service;
  }

  // -- Getters --
  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getFirstname() {
    return firstname;
  }

  @Override
  public String getLastname() {
    return lastname;
  }

  @Override
  public String getCellphone() {
    return cellphone;
  }

  @Override
  public String getDeskphone() {
    return deskphone;
  }

  @Override
  public String getMail() {
    return mail;
  }

  @Override
  public Workplace getService() {
    return service;
  }

  @Override
  public Workplace getPlace() {
    return place;
  }

  // -- Setters --
  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  @Override
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  @Override
  public void setCellphone(String cellphone) {
    this.cellphone = cellphone;
  }

  @Override
  public void setDeskphone(String deskphone) {
    this.deskphone = deskphone;
  }

  @Override
  public void setMail(String mail) {
    this.mail = mail;
  }

  @Override
  public void setService(Workplace service) {
    this.service = service;
  }

  @Override
  public void setPlace(Workplace place) {
    this.place = place;
  }
}
