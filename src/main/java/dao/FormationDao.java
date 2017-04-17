package dao;


import domain.Cours;
import domain.Formation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

/**
 * Created by marti on 17/04/2017.
 */
public class FormationDao extends GenericDao<Formation, Integer> {


    @Override
    public void findAll(SessionFactory sessionFactory, Transaction transaction) {
        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            List<Formation> formations = session.createQuery("from Formation ").list();
            for (Formation formation : formations) {
                System.out.println("\tId formation: " + formation.getId());
                System.out.println("\tformation name : " + formation.getNom_formation());
                Set<Cours> cours = formation.getList_cours();
                StringBuffer buffer = new StringBuffer();
                for (Cours c : cours) {
                    buffer.append(c.getNom_cours()).append("; ");
                }
                System.out.println("Cours List: " + buffer);
            }
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
    }

    @Override
    public Formation findById(SessionFactory sessionFactory, Transaction transaction, Integer id) {
        Formation formation = null;
        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            formation = session.get(Formation.class, id);
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
        return formation;
    }

    @Override
    public Formation create(SessionFactory sessionFactory, Transaction transaction, Formation formation) {
        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            session.save(formation);
            transaction.commit();
            return formation;
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
            return null;
        }
    }

    @Override
    public Formation update(SessionFactory sessionFactory, Transaction transaction, Formation formation) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(formation);
            transaction.commit();
            return formation;
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(SessionFactory sessionFactory, Transaction transaction, Formation formation) {
        boolean flag = false;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(formation);
            transaction.commit();
            flag = true;
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
        return flag;
    }
}
