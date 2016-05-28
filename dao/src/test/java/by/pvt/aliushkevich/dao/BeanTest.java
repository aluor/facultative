package by.pvt.aliushkevich.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Rabotnik on 27.05.2016.
 */
@ContextConfiguration("/testSpringContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BeanTest {
  private static Logger log = Logger.getLogger(LecturerDAOTest.class);
  private ApplicationContext context = new ClassPathXmlApplicationContext("beans-dao.xml");

  /*@Before
  public void setUp() throws Exception {
    log.info("trying setUp...");
    //SomeCodeHere
    log.info("setUp: SUCCESS");
  }*/

  @Test
  public void getSessionFactoryBean() throws Exception {
    log.info("testing getSessionFactoryBean... ");
    SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
    assertNotNull("getSessionFactoryBean failed! ", sessionFactory);
    log.info("getSessionFactoryBean: SUCCESS\n[" + sessionFactory + "]");
  }

}
