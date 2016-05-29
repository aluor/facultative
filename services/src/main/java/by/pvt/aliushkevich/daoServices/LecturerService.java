package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.dao.ILecturerDAO;
import by.pvt.aliushkevich.dao.IRelationDAO;
import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.pojos.Relation;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Rabotnik on 10.04.2016.
 */
@Service("lecturerService")
@Transactional
public class LecturerService extends BaseService<Lecturer> implements ILecturerService {

  private static Logger log = Logger.getLogger(LecturerService.class);
  @Autowired
  private ILecturerDAO lecturerDAO;
  @Autowired
  private IRelationDAO relationDAO;

  public void addMarkFeedback(int mark, String feedback, int studentId, int courseId) throws DaoException {
    log.info("Trying addMarkFeedback to student...");
    try {
      Lecturer lecturer = lecturerDAO.getLecturerByCourseId(courseId);
      Integer relationId = relationDAO.getRelationId(studentId, lecturer.getId());
      Relation relation = relationDAO.get(relationId);
      relation.setMark(mark);
      relation.setFeedback(feedback);
      relationDAO.saveOrUpdate(relation);
      log.info("addMarkFeedback(commit): SUCCESS");
    } catch (NullPointerException | HibernateException e) {
      log.error("Error addMarkFeedback to student" + e);
      throw new DaoException(e);
    }
  }

  public List<Lecturer> getLecturers() {
    log.info("Trying getLecturers...");
    return lecturerDAO.getLecturers();
  }

  public int getCourseIdByLogin(String login) throws DaoException {
    log.info("Trying getCourseIdByLogin...");
    return lecturerDAO.getCourseIdByLogin(login);
  }

}
