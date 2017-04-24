package dao;

import domain.Salle;
import domain.SalleId;
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

public class SalleDao extends GenericDao<Salle, SalleId>  {
    @Override
    public void findAll(SessionFactory sessionFactory, Transaction transaction) {
        List<String> headersList = Arrays.asList("batiment", "numero salle", "capacite");
        List<List<String>> rowsList = null;
        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            List<Salle> salles = session.createQuery("from Salle").getResultList();
            for (Salle salle : salles) {
                rowsList = Arrays.asList(Arrays.asList(salle.getId().getBatiment(), salle.getId().getNumero_salle(), String.valueOf(salle.getCapacite())));
                HibernateUtil.table(headersList, rowsList);
            }
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }

    }

    @Override
    public Salle findById(SessionFactory sessionFactory, Transaction transaction, SalleId salleId) {
        Salle salle =  null;
        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            salle = session.get(Salle.class, salleId);
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
        return salle;
    }

    @Override
    public Salle create(SessionFactory sessionFactory, Transaction transaction, Salle salle) {
        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            session.save(salle);
            transaction.commit();
            return salle;
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
            return null;
        }
    }

    @Override
    public Salle update(SessionFactory sessionFactory, Transaction transaction, Salle salle) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(salle);
            transaction.commit();
            return salle;
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(SessionFactory sessionFactory, Transaction transaction, Salle salle) {
        boolean flag = false;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(salle);
            transaction.commit();
            flag = true;
        }catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
        return flag;
    }
}
