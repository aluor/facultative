package by.pvt.aliushkevich.pojos;

public class LecturerValueStudent {
  private int id;
  private String firstName;
  private String lastName;
  private String login;
  private String password;
  private int mark;
  private String feedback;

  public LecturerValueStudent() {
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

  @Override
  public String toString() {
    return "Student ID " + id + ": " + firstName + " " + lastName + " (login: " + login + ")\n" + "mark: " +mark + "feedback: " +feedback;
  }
}


