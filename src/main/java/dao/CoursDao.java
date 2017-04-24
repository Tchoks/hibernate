package dao;

import domain.Cours;
import domain.CoursId;
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

public class CoursDao extends GenericDao<Cours, CoursId> {


    @Override
    public void findAll(SessionFactory sessionFactory, Transaction transaction) {
        try(Session session = sessionFactory.openSession() ) {
            List<String> headersList = Arrays.asList("Id cours", "formation", "cours", "List seances", "List batiment", "List salle");
            List<List<String>> rowsList = null;
            transaction = session.beginTransaction();
            List<Cours> cours = session.createQuery("from Cours ").getResultList();
            for (Cours c : cours) {
                StringBuffer buffer = new StringBuffer();
                StringBuffer batiment = new StringBuffer();
                StringBuffer salle = new StringBuffer();
                for (Seance seance : c.getSeances()) {
                    buffer.append(seance.getId()).append("; ");
                    batiment.append(seance.getSalle().getId().getBatiment()).append("; ");
                    salle.append(seance.getSalle().getId().getNumero_salle()).append("; ");
                }
                rowsList = Arrays.asList(Arrays.asList(String.valueOf(c.getCoursId().getId()), String.valueOf(c.getCoursId().getFormation().getNom_formation()), c.getNom_cours(), buffer.toString(), batiment.toString(), salle.toString()));
                HibernateUtil.table(headersList, rowsList);
            }
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
    }


    @Override
    public Cours findById(SessionFactory sessionFactory, Transaction transaction, CoursId coursId) {
        Cours cours = null;
        try(Session session = sessionFactory.openSession() ) {
          transaction = session.beginTransaction();
          cours = session.get(Cours.class, coursId);
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
    public Cours update(SessionFactory sessionFactory, Transaction transaction, Cours cours) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(cours);
            transaction.commit();
            return cours;
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(SessionFactory sessionFactory, Transaction transaction, Cours cours) {
        boolean flag = false;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
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
