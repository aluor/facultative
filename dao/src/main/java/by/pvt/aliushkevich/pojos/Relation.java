package by.pvt.aliushkevich.pojos;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Relation implements Serializable {
  private static final long serialVersionUID = 2L;
  private int id;
  private Lecturer lecturer;
  private Student student;
  private int mark;
  private String feedback;

  public Relation() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @ManyToOne
  @JoinColumn (name = "fk_student_id")
  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  @ManyToOne
  @JoinColumn (name = "fk_lecturer_id")
  public Lecturer getLecturer() {
    return lecturer;
  }

  public void setLecturer(Lecturer lecturer) {
    this.lecturer = lecturer;
  }

  @Column
  public int getMark() {
    return mark;
  }

  public void setMark(int mark) {
    this.mark = mark;
  }

  @Column
  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

  @Override
  public String toString() {
    return "Relation{" +
        "id=" + id +
        ", lecturerId=" + lecturer.getId() +
        ", studentId=" + student.getId() +
        ", mark=" + mark +
        ", feedback='" + feedback + '\'' +
        '}';
  }
}
