package by.pvt.aliushkevich.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Rabotnik on 27.05.2016.
 */
//TODO: Как задавать конфиг контекста для тестов при анотациях?

@ContextConfiguration("/testSpringContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
public class BaseDAOTest {
  private static Logger log = Logger.getLogger(BaseDAO.class);
  private ApplicationContext context = new ClassPathXmlApplicationContext("beans-dao.xml");

//  @Autowired
//  protected SessionFactory sessionFactory;

  @Test
  public void getManualSessionTest(){
    log.info("testing getManualSessionTest...");
    SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
    Session session = sessionFactory.getCurrentSession();
    log.info("session="+session);
  }

  @Test
  public void getSession() throws Exception {

  }

  @Test
  public void saveOrUpdate() throws Exception {

  }

  @Test
  public void delete() throws Exception {

  }

  @Test
  public void get() throws Exception {

  }

  @Test
  public void getPersistentClass() throws Exception {

  }
}