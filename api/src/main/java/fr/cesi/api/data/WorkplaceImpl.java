package fr.cesi.api.data;

public class WorkplaceImpl implements Workplace {
  private int id;
  private String name;

  public WorkplaceImpl() {}

  public WorkplaceImpl(RegistrationWorkplace registrationWorkplace) {
    this.name = registrationWorkplace.name;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }
}
