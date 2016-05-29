package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.configuration.HibernateConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;


/**
 * Created by Rabotnik on 27.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class BaseDAOTest {
  private static Logger log = Logger.getLogger(BaseDAO.class);
  @Autowired
  protected SessionFactory sessionFactory;
  @Autowired
  protected IBaseDAO baseDAO;

  @Test
  public void getSession() throws Exception {
    Session testSession = sessionFactory.getCurrentSession();
    log.info("testSession=" + testSession);
    Session expectedSession = baseDAO.getSession();
    log.info("expectedSession=" + expectedSession);
    assertEquals("Sessions mismatch!", testSession, expectedSession);
  }

}