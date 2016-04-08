package by.pvt.aliushkevich.entity;

import by.pvt.aliushkevich.enums.Courses;

public class Lecturer {

  private int id;
  private String firstName;
  private String lastName;
  private String login;
  private String password;
  private Courses teachesCourse;

  public Lecturer() {
  }

  public Lecturer(String firstName, String lastName, String login, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.login = login;
    this.password = password;
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

  public Courses getTeachesCourses() {
    return teachesCourse;
  }

  public void setTeachesCourse(Courses course) {
    this.teachesCourse = course;
  }

  public void setMark(Student student, int mark) {
    student.setMark(mark);
  }

  public void setFeedback(Student student, String feedback) {
    student.setFeedback(feedback);
  }

  @Override
  public String toString() {
    return "Lecturer №" + id + ": " + firstName + " " + lastName + " (login: " + login + ")\n"
        + "Teaches courses:" + teachesCourse;
  }
}
