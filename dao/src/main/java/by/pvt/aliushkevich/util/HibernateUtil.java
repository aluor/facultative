package by.pvt.aliushkevich.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
  private static HibernateUtil util = null;
  private static Logger log = Logger.getLogger(HibernateUtil.class);
  private SessionFactory sessionFactory = null;
  private final ThreadLocal sessions = new ThreadLocal();

  private HibernateUtil() {
    try {
      sessionFactory = new Configuration().
          configure().buildSessionFactory();
    } catch (Throwable ex) {
      log.error("Initial SessionFactory creation failed." + ex);
      System.exit(0);
    }
  }

  public static synchronized HibernateUtil getHibernateUtil() {
    if (util == null) {
      util = new HibernateUtil();
    }
    return util;
  }

  public Session getSession() {
    Session session = (Session) sessions.get();
    if (session == null) {
      session = sessionFactory.openSession();
      sessions.set(session);
    }
    return session;
  }

  public void closeSession() {
    getSession().close();
    sessions.set(null);
  }

}
