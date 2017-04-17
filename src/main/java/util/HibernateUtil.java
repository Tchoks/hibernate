package util;

/**
 * Created by marti on 15/04/2017.
 */

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import wagu.Board;
import wagu.Table;

import java.util.List;

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


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public static void table(List<String> headersList, List<List<String>> rowsList) {
        Board board = new Board(100);
        String tableString = board.setInitialBlock(new Table(board, 100, headersList, rowsList).tableToBlocks()).build().getPreview();
        System.out.println(tableString);
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
