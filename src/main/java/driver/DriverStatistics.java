package driver;

import dao.*;
import domain.*;
import exceptions.BusinessException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.Statistics;

import java.util.Map;
import java.util.SortedSet;

/**
 * @authors Martin Tchokonthe And Mohammed Sylla
 * @date on 17/04/2017.
 */
public class DriverStatistics {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Transaction transaction = null;
    public static void main(String[] args) throws BusinessException {

        // Instantiating daos
        GenericDao<Formation, Integer> formationDao = new FormationDao();
        GenericDao<Cours, CoursId> coursDao = new CoursDao();
        GenericDao<Salle, SalleId> salleDao = new SalleDao();
        GenericDao<Seance, Integer> seanceDao = new SeanceDao();



        // Create Rooms
        // create some transient rooms
        SalleId A = new SalleId("batiment A", "B128");
        SalleId B = new SalleId("Batiment B", "A127");

        Salle salle1 = new Salle(A, 30);
        Salle salle2 = new Salle(B, 40);

        // load rooms to database
        Salle roomA =  salleDao.create(sessionFactory, transaction, salle1);
        Salle roomB =  salleDao.create(sessionFactory, transaction, salle2);


        // Create formations
        // some transient formation object
        Formation finance = new Formation("Master 2 Finance");
        Formation sitn = new Formation("Master 2 SITN");
        Formation id = new Formation("Master 2 ID");


        // load some formation to database
        Formation formation_finance = formationDao.create(sessionFactory, transaction, finance);
        Formation formation_sitn = formationDao.create(sessionFactory, transaction, sitn);
        Formation formation_id = formationDao.create(sessionFactory, transaction, id);

        // some course id
        CoursId coursId_optimization = new CoursId(1, formation_finance);
        CoursId coursId_hibernate = new CoursId(2,formation_finance);
        CoursId coursId_J2EE = new CoursId(3, formation_id);
        CoursId coursId_bigdata = new CoursId(4, formation_sitn);


        // some transient course object
        Cours optimization = new Cours(coursId_optimization, "Optimization Finance");
        Cours J2EE = new Cours(coursId_J2EE, "J2EE");
        Cours bigdata = new Cours(coursId_bigdata, "bigdata");
        Cours hibernate = new Cours(coursId_hibernate, "hibernate");

        // persistent courses
        Cours cours_optimization =  coursDao.create(sessionFactory, transaction, optimization);
        Cours cours_J2EE =  coursDao.create(sessionFactory, transaction, J2EE);
        Cours cours_bigdata = coursDao.create(sessionFactory, transaction, bigdata);
        Cours cours_hibernate = coursDao.create(sessionFactory, transaction, hibernate);

        // Create seance
        Seance seance = new Seance(cours_bigdata, roomB);
        Seance seance1 = new Seance(cours_J2EE, roomA);
        Seance seance2 = new Seance(cours_J2EE, roomB);
        Seance seance3 = new Seance(cours_optimization, roomA);

        seanceDao.create(sessionFactory, transaction, seance);
        seanceDao.create(sessionFactory, transaction, seance1);
        seanceDao.create(sessionFactory, transaction, seance2);
        seanceDao.create(sessionFactory, transaction, seance3);

        // add courses to formation, here all our objects are persistent
        //formation_finance.addCours(cours_optimization);
        //formation_id.addCours(cours_J2EE);
        //formation_sitn.addCours(cours_bigdata);

        // add seances to courses
        //cours_optimization.addSeance(seance3);
        //cours_bigdata.addSeance(seance);
        //cours_J2EE.addSeance(seance1);
        //cours_J2EE.addSeance(seance2);

        SortedSet<Map.Entry<String, Integer>> coursesForAFormationWithJava = Statistics.CoursesForAFormationWithJava(sessionFactory, transaction, 1);
        System.out.println("nombre de cours de la  formation: " + formation_finance.getId() + " avec JAVA");
        Statistics.print(coursesForAFormationWithJava);

        SortedSet<Map.Entry<String, Integer>> coursesForAFormationWithHql = Statistics.CoursesForAFormationWithHql(sessionFactory, transaction, 1);
        System.out.println("nombre de cours de la  formation: " + formation_finance.getId() + " avec HQL");
        Statistics.print(coursesForAFormationWithHql);


        SortedSet<Map.Entry<String, Integer>> coursesForEachFormation = Statistics.CoursesForEachFormation(sessionFactory, transaction);
        System.out.println("nombre de cours par formations: ");
        Statistics.print(coursesForEachFormation);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        SortedSet<Map.Entry<String, Integer>> seancesForEachCourse = Statistics.SeancesForEachCourse(sessionFactory, transaction);
        System.out.println("nombre de seances par cours: ");
        Statistics.print(seancesForEachCourse);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        SortedSet<Map.Entry<String, Integer>> seancesForEachRoom  = Statistics.SeancesForEachRoom(sessionFactory, transaction);
        System.out.println("nombre de seances par salle: ");
        Statistics.print(seancesForEachRoom);

        sessionFactory.close();


    }

}
