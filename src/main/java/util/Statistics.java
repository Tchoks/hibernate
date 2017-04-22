package util;

import domain.Cours;
import domain.Formation;
import domain.Salle;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.*;

/**
 * Created by marti on 22/04/2017.
 */
public class Statistics {


    public static SortedSet<Map.Entry<String, Integer>> CoursesForEachFormation(SessionFactory sessionFactory, Transaction transaction) {

        Map<String, Integer> result = new TreeMap<>();

        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            List<Formation> formations = session.createQuery("from Formation ").list();
            for (Formation formation : formations) {
                result.put(formation.getNom_formation(), formation.getList_cours().size());
            }
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
        return entriesSortedByValues(result);
    }


    public static SortedSet<Map.Entry<String, Integer>> SeancesForEachCourse(SessionFactory sessionFactory, Transaction transaction) {

        Map<String, Integer> result = new TreeMap<>();

        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            List<Cours> cours = session.createQuery("from Cours").getResultList();
            for (Cours c : cours) {
                result.put(c.getNom_cours(), c.getSeances().size());
            }
        }
        return entriesSortedByValues(result);
    }

    public static SortedSet<Map.Entry<String, Integer>> SeancesForEachRoom(SessionFactory sessionFactory, Transaction transaction) {

        Map<String, Integer> result = new TreeMap<>();

        try(Session session = sessionFactory.openSession() ) {
            transaction = session.beginTransaction();
            List<Salle> salles = session.createQuery("from Salle").getResultList();
            for (Salle salle : salles) {
                result.put(salle.getId().getNumero_salle(), salle.getSeances().size());
            }
        }
        return entriesSortedByValues(result);
    }






    //===============================> private methods <=================================
    static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<>(
                (e1, e2) -> {
                    int res = e1.getValue().compareTo(e2.getValue());
                    if (e1.getKey().equals(e2.getKey())) {
                        return res; // Code will now handle equality properly
                    } else {
                        return res != 0 ? res : 1; // While still adding all entries
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    public static <K,V extends Comparable<? super V>>
    void print(SortedSet<Map.Entry<K, V>> entries) {
        for (Map.Entry<K, V> entry : entries) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

}
