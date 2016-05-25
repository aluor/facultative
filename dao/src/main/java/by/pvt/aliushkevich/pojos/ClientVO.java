package by.pvt.aliushkevich.pojos;

import by.pvt.aliushkevich.enums.ClientType;

public class ClientVO {
  private int id;
  private String firstName;
  private String lastName;
  private String login;
  private String password;
  private int choice;
  private int mark;
  private String feedback;
  private ClientType clientType = ClientType.GUEST;

  public ClientVO() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getChoice() {
    return choice;
  }

  public void setChoice(int choice) {
    this.choice = choice;
  }

  public int getMark() {
    return mark;
  }

  public void setMark(int mark) {
    this.mark = mark;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

  public ClientType getClientType() {
    return clientType;
  }

  public void setClientType(ClientType clientType) {
    this.clientType = clientType;
  }

  @Override
  public String toString() {
    return "{id="+id+" firstName="+firstName+" lastName="+lastName+
        " login="+login+" password="+password+" choice="+choice+
        " mark="+mark+" feedback="+feedback+" clientType="+clientType+"}";
  }
}


