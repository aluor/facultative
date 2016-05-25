package by.pvt.aliushkevich.pojos;

import by.pvt.aliushkevich.enums.ClientType;

/**
 * Created by Rabotnik on 25.05.2016.
 */
public class Client {
  private String login;
  private String password;
  private int choice;
  private ClientType clientType = ClientType.GUEST;

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

  @Override
  public String toString() {
    return "login="+ login+" password="+password+" type="+clientType+" choice="+ choice;
  }

}