package by.pvt.aliushkevich.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lecturer implements Serializable {
  private static final long serialVersionUID = 1L;
  private int id;
  private String firstName;
  private String lastName;
  private String login;
  private String password;
  private int courseId;
  private Set<Relation> relations = new HashSet<>();

  public Lecturer() {
  }

  public Lecturer(String firstName, String lastName, String login, String password, int courseId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.login = login;
    this.password = password;
    this.courseId = courseId;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @Column
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column
  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL, orphanRemoval=true)
  public Set<Relation> getRelations() {
    return relations;
  }

  public void setRelations(Set<Relation> relations) {
    this.relations = relations;
  }

  @Override
  public String toString() {
    return "Lecturer №" + id + ": " + firstName + " " + lastName + " (login: " + login + ")\n"
        + "Teaches course:" + courseId + " relations: " + relations;
  }
}


//import by.pvt.aliushkevich.enums.Courses;
//
//public class Lecturer {
//
//  private int id;
//  private String firstName;
//  private String lastName;
//  private String login;
//  private String password;
//  private Courses teachesCourse;
//
//  public Lecturer() {
//  }
//
//  public Lecturer(String firstName, String lastName, String login, String password) {
//    this.firstName = firstName;
//    this.lastName = lastName;
//    this.login = login;
//    this.password = password;
//  }
//
//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }
//
//  public String getFirstName() {
//    return firstName;
//  }
//
//  public void setFirstName(String firstName) {
//    this.firstName = firstName;
//  }
//
//  public String getLastName() {
//    return lastName;
//  }
//
//  public void setLastName(String lastName) {
//    this.lastName = lastName;
//  }
//
//  public String getLogin() {
//    return login;
//  }
//
//  public void setLogin(String login) {
//    this.login = login;
//  }
//
//  public String getPassword() {
//    return password;
//  }
//
//  public void setPassword(String password) {
//    this.password = password;
//  }
//
//  public Courses getTeachesCourses() {
//    return teachesCourse;
//  }
//
//  public void setTeachesCourse(Courses course) {
//    this.teachesCourse = course;
//  }
//
//  public void setMark(Student student, int mark) {
//    student.setMark(mark);
//  }
//
//  public void setFeedback(Student student, String feedback) {
//    student.setFeedback(feedback);
//  }
//
//  @Override
//  public String toString() {
//    return "Lecturer №" + id + ": " + firstName + " " + lastName + " (login: " + login + ")\n"
//        + "Teaches courses:" + teachesCourse;
//  }
//}
