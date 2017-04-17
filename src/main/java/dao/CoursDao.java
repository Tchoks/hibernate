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
                System.out.println("\tformation name: " + c.getFormation().getNom_formation());
                System.out.println("\tnom cours: " + c.getNom_cours());
            }
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
    }

    @Override
    public Cours findById(SessionFactory sessionFactory, Transaction transaction, Integer id) {
        Cours cours = null;
        try(Session session = sessionFactory.openSession() ) {
          transaction = session.beginTransaction();
          cours = session.get(Cours.class, id);
          transaction.commit();
        }catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
        return cours;
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
    public boolean delete(SessionFactory sessionFactory, Transaction transaction, Cours cours) {
        boolean flag = false;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            //Formation formation = cours.getFormation();
            //formation.getList_cours().remove(cours);
            session.delete(cours);
            transaction.commit();
            flag = true;
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
        return flag;
    }
}
