package dao;

import domain.Cours;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by marti on 17/04/2017.
 */
public class CoursDao extends GenericDao<Cours,Integer> {


    @Override
    public void findAll(SessionFactory sessionFactory, Transaction transaction) {
        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            List<Cours> cours = session.createQuery("from Cours ").getResultList();
            for (Cours c : cours) {
                System.out.println("\tId cours: " + c.getId());
                System.out.println("\tformation: " + c.getFormation());
                System.out.println("\tnom cours: " + c.getNom_cours());
            }
            transaction.commit();

        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
    }

    @Override
    public Cours findById(SessionFactory sessionFactory, Transaction transaction, Integer integer) {
        return null;
    }

    @Override
    public Cours create(SessionFactory sessionFactory, Transaction transaction, Cours cours) {
        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            session.save(cours);
            transaction.commit();
            return cours;
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
            return null;
        }
    }

    @Override
    public Cours update(SessionFactory sessionFactory, Transaction transaction, Cours object) {
        return null;
    }

    @Override
    public boolean delete(SessionFactory sessionFactory, Transaction transaction, Cours object) {
        return false;
    }
}
