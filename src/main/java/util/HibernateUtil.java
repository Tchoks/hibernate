package util;

/**
 * Created by marti on 15/04/2017.
 */

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *  We want to use this class to create a single instance of JDBC connection.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
   // private static ThreadLocal<Session> threadlocal;

    static {

        try {
            Configuration configuration = new Configuration().configure();

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory();
            //threadlocal = new ThreadLocal<Session>();

        } catch (Throwable throwable) {
            System.err.println("Error creating Session: " + throwable);
            throw new ExceptionInInitializerError(throwable);

        }
    }


   /*private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
*/

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /*public static Session getSession() {
        Session session = threadlocal.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadlocal.set(session);
        }
        return session;
    }

    public static void closeSession() {
        Session session = threadlocal.get();
        if (session != null) {
            session.close();
            threadlocal.set(null);
        }
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
    */

}
