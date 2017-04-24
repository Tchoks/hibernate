package dao;

import domain.Seance;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @authors Martin Tchokonthe And Mohammed Sylla
 * @date on 17/04/2017.
 */

public class SeanceDao extends GenericDao<Seance, Integer> {
    @Override
    public void findAll(SessionFactory sessionFactory, Transaction transaction) {
        List<String> headersList = Arrays.asList("Id Seance", "Id Formation", "Id Cours", "batiment", "salle");
        List<List<String>> rowsList = null;
        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            List<Seance> seances = session.createQuery("from Seance").getResultList();
            for (Seance seance : seances) {
                rowsList = Arrays.asList( Arrays.asList(String.valueOf(seance.getId()), String.valueOf(seance.getCours().getCoursId().getFormation().getId()), String.valueOf(seance.getCours().getCoursId().getId()), seance.getSalle().getId().getBatiment(), seance.getSalle().getId().getNumero_salle()));
                HibernateUtil.table(headersList, rowsList);
            }
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }

    }

    @Override
    public Seance findById(SessionFactory sessionFactory, Transaction transaction, Integer id) {
        Seance seance = null;
        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            seance = session.get(Seance.class, id);
            transaction.commit();
        }catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
        return seance;
    }

    @Override
    public Seance create(SessionFactory sessionFactory, Transaction transaction, Seance seance) {
        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            session.save(seance);
            transaction.commit();
            return seance;
        }catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
            return null;
        }
    }

    @Override
    public Seance update(SessionFactory sessionFactory, Transaction transaction, Seance seance) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(seance);
            transaction.commit();
            return seance;
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(SessionFactory sessionFactory, Transaction transaction, Seance seance) {
        boolean flag = false;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(seance);
            transaction.commit();
            flag = true;
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
        return flag;
    }
}
