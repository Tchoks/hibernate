package util;

/**
 * @authors Martin Tchokonthe And Mohammed Sylla
 * @date on 17/04/2017.
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

    static {

        try {
            Configuration configuration = new Configuration().configure();

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory();

        } catch (Throwable throwable) {
            System.err.println("Error creating Session: " + throwable);
            throw new ExceptionInInitializerError(throwable);

        }
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    /**
     *
     * @param headersList
     * @param rowsList
     *
     * it will print for us a friendly table
     */
    public static void table(List<String> headersList, List<List<String>> rowsList) {
        Board board = new Board(150);
        String tableString = board.setInitialBlock(new Table(board, 150, headersList, rowsList).tableToBlocks()).build().getPreview();
        System.out.println(tableString);
    }

}
