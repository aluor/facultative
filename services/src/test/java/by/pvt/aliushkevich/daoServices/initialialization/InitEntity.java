package by.pvt.aliushkevich.daoServices.initialialization;

import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.pojos.Relation;
import by.pvt.aliushkevich.pojos.Student;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rabotnik on 29.05.2016.
 */
public class InitEntity {
  private static Logger log = Logger.getLogger(InitEntity.class);

  public static void initLecturer(Lecturer lecturer) {
    log.info("trying initLecturer...");
    lecturer.setFirstName("testLecturerFN");
    lecturer.setLastName("testLecturerLN");
    lecturer.setLogin("testLecturerLn");
    lecturer.setPassword("testLecturerPd");
    lecturer.setCourseId(-1);
    log.info("initLecturer: SUCCESS");
  }

  public static void initStudent(Student student) {
    log.info("trying initStudent...");
    student.setFirstName("testStudentFN");
    student.setLastName("testStudentLN");
    student.setLogin("testStudentLn");
    student.setPassword("testStudentPd");
    log.info("initStudent: SUCCESS");
  }

  public static Set<Relation> initRelations(Lecturer lecturer, Student student) {
    log.info("trying initRelations...");
    Set<Relation> relations = new HashSet<>();
    Relation relation = new Relation();
    relation.setStudent(student);
    relation.setLecturer(lecturer);
    relation.setMark(-1);
    relation.setFeedback("testFeedback");
    relations.add(relation);
    lecturer.setRelations(relations);
    student.setRelations(relations);
    log.info("initRelations: SUCCESS");
    return relations;
  }

}