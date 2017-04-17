package driver;

import dao.CoursDao;
import dao.FormationDao;
import dao.GenericDao;
import dao.SalleDao;
import domain.Cours;
import domain.Formation;
import domain.Salle;
import domain.SalleId;
import exceptions.BusinessException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Created by marti on 16/04/2017.
 */


public class Driver {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Transaction transaction = null;
    public static void main(String[] args) throws BusinessException {

        GenericDao<Formation, Integer> formationDao = new FormationDao();
        GenericDao<Cours, Integer> coursDao = new CoursDao();
        GenericDao<Salle, SalleId> salleDao = new SalleDao();

        // some transient formation object
        Formation finance = new Formation("Master 2 Finance");
        Formation sitn = new Formation("Master 2 SITN");
        Formation id = new Formation("Master 2 ID");


        // load some formation to database
        Formation formation_finance = formationDao.create(sessionFactory, transaction, finance);
        Formation formation_sitn = formationDao.create(sessionFactory, transaction, sitn);
        Formation formation_id = formationDao.create(sessionFactory, transaction, id);

        // some transient course object
        Cours optimization = new Cours("Optimization Finance", formation_finance);
        Cours J2EE = new Cours("J2EE", formation_finance);
        Cours bigdata = new Cours("bigdata", formation_id);

        // persistent courses
        Cours cours_optimization =  coursDao.create(sessionFactory, transaction, optimization);
        Cours cours_J2EE =  coursDao.create(sessionFactory, transaction, J2EE);

        // add courses to formation, here all our objects are persistent
        formation_finance.addCours(cours_optimization);
        formation_finance.addCours(cours_J2EE);
        formation_sitn.addCours(coursDao.create(sessionFactory, transaction, bigdata));

        // list all formations from database
        System.out.println("List of formations: ");
        formationDao.findAll(sessionFactory, transaction);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("List of courses: ");
        // list all courses from database
        coursDao.findAll(sessionFactory, transaction);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // update some formation
        finance.setNom_formation("Master 2 Finance des Marchés");
        formationDao.update(sessionFactory, transaction, finance);

        // value of the new update formation
        Formation updated = formationDao.findById(sessionFactory, transaction, finance.getId());
        System.out.println("List of updated formations: ");
        formationDao.findAll(sessionFactory, transaction);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // delete formation
        formationDao.delete(sessionFactory, transaction, updated);

        // new list
        System.out.println("New list of formations after delete: ");
        formationDao.findAll(sessionFactory, transaction);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // new list of courses
        System.out.println("New list of courses after delete: ");
        coursDao.findAll(sessionFactory, transaction);

        // delete a course
        Cours delete_course = coursDao.findById(sessionFactory, transaction, bigdata.getId());
        coursDao.delete(sessionFactory, transaction, delete_course);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // new list
        System.out.println("New list of formations after delete: ");
        formationDao.findAll(sessionFactory, transaction);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // new list of courses
        System.out.println("New list of courses after delete: ");
        coursDao.findAll(sessionFactory, transaction);

        // create some transient rooms
        SalleId A = new SalleId("batiment A", "B128");
        SalleId B = new SalleId("Batiment B", "A127");

        Salle salle1 = new Salle(A, 30);
        Salle salle2 = new Salle(B, 40);

        // load rooms to database
       Salle roomA =  salleDao.create(sessionFactory, transaction, salle1);
       Salle roomB =  salleDao.create(sessionFactory, transaction, salle2);

        // list all rooms in the database
        System.out.println("Rooms in the database: ");
        salleDao.findAll(sessionFactory, transaction);

        // update room capacity
        roomA.setCapacite(35);
        salleDao.update(sessionFactory, transaction, roomA);
        System.out.println("Rooms in the database: ");
        salleDao.findAll(sessionFactory, transaction);

        // delete a room
        salleDao.delete(sessionFactory, transaction, roomA);
        System.out.println("Rooms in the database: ");
        salleDao.findAll(sessionFactory, transaction);


        sessionFactory.close();
    }


}
