package driver;

import dao.*;
import domain.*;
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


        // list all rooms in the database
        System.out.println("Rooms in the database: ");
        salleDao.findAll(sessionFactory, transaction);


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
        CoursId coursId_J2EE = new CoursId(2, formation_id);
        CoursId coursId_bigdata = new CoursId(3, formation_sitn);


        // some transient course object
        Cours optimization = new Cours(coursId_optimization, "Optimization Finance");
        Cours J2EE = new Cours(coursId_J2EE, "J2EE");
        Cours bigdata = new Cours(coursId_bigdata, "bigdata");

        // persistent courses
        Cours cours_optimization =  coursDao.create(sessionFactory, transaction, optimization);
        Cours cours_J2EE =  coursDao.create(sessionFactory, transaction, J2EE);
        Cours cours_bigdata = coursDao.create(sessionFactory, transaction, bigdata);

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
        /*formation_finance.addCours(cours_optimization);
        formation_id.addCours(cours_J2EE);
        formation_sitn.addCours(cours_bigdata);
        */

        // add seances to courses
        /*cours_optimization.addSeance(seance3);
        cours_bigdata.addSeance(seance);
        cours_J2EE.addSeance(seance1);
        cours_J2EE.addSeance(seance2);
        */

        // new list of courses
        System.out.println("List of courses : ");
        coursDao.findAll(sessionFactory, transaction);


        // list all formations from database
        System.out.println("List of formations: ");
        formationDao.findAll(sessionFactory, transaction);

        // list of seance
        System.out.println("Seance in the database: ");
        seanceDao.findAll(sessionFactory, transaction);

        // update some formation
        finance.setNom_formation("Master 2 Finance des Marchés");
        formationDao.update(sessionFactory, transaction, finance);

        // list all formations from database after update
        System.out.println("List of formations after updating formation: " + finance.getNom_formation());
        formationDao.findAll(sessionFactory, transaction);


        // delete formation
        Formation updated = formationDao.findById(sessionFactory, transaction, finance.getId());
        formationDao.delete(sessionFactory, transaction, updated);

        // new list
        System.out.println("New list of formations after deleting formation: " + updated.getNom_formation());
        formationDao.findAll(sessionFactory, transaction);


        // new list of courses after delete
        System.out.println("New list of courses after deleting formation: " + updated.getNom_formation());
        coursDao.findAll(sessionFactory, transaction);

        // list of seance after delete
        System.out.println("Seance in the database after deleting formation: " + updated.getNom_formation());
        seanceDao.findAll(sessionFactory, transaction);


        // delete a course
        Cours delete_course = coursDao.findById(sessionFactory, transaction, bigdata.getCoursId());
        coursDao.delete(sessionFactory, transaction, delete_course);

        // new list
        System.out.println("New list of formations after deleting course: " + delete_course.getNom_cours());
        formationDao.findAll(sessionFactory, transaction);


        // new list of courses after delete
        System.out.println("New list of courses after deleting course: " + delete_course.getNom_cours());
        coursDao.findAll(sessionFactory, transaction);

        // list of seance after delete
        System.out.println("Seance in the database after deleting course: " + delete_course.getNom_cours());
        seanceDao.findAll(sessionFactory, transaction);




        /*



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
        CoursId coursId_J2EE = new CoursId(2, formation_finance);
        CoursId coursId_bigdata = new CoursId(3, formation_id);


        // some transient course object
        Cours optimization = new Cours(coursId_optimization, "Optimization Finance");
        Cours J2EE = new Cours(coursId_J2EE, "J2EE");
        Cours bigdata = new Cours(coursId_bigdata, "bigdata");

        // persistent courses
        Cours cours_optimization =  coursDao.create(sessionFactory, transaction, optimization);
        Cours cours_J2EE =  coursDao.create(sessionFactory, transaction, J2EE);
        Cours cours_bigdata = coursDao.create(sessionFactory, transaction, bigdata);

        // add courses to formation, here all our objects are persistent
        formation_finance.addCours(cours_optimization);
        formation_finance.addCours(cours_J2EE);
        formation_sitn.addCours(cours_bigdata);

        // create some transient rooms
        SalleId A = new SalleId("batiment A", "B128");
        SalleId B = new SalleId("Batiment B", "A127");

        Salle salle1 = new Salle(A, 30);
        Salle salle2 = new Salle(B, 40);

        // load rooms to database
        Salle roomA =  salleDao.create(sessionFactory, transaction, salle1);
        Salle roomB =  salleDao.create(sessionFactory, transaction, salle2);

        // Create seance
        Seance seance = new Seance(cours_bigdata, roomB);
        Seance seance1 = new Seance(cours_J2EE, roomA);
        seanceDao.create(sessionFactory, transaction, seance);
        seanceDao.create(sessionFactory, transaction, seance1);

        cours_bigdata.addSeance(seance);
        cours_J2EE.addSeance(seance1);

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
        Cours delete_course = coursDao.findById(sessionFactory, transaction, bigdata.getCoursId());
        //coursDao.delete(sessionFactory, transaction, delete_course);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // new list
        System.out.println("New list of formations after delete: ");
        formationDao.findAll(sessionFactory, transaction);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // new list of courses
        System.out.println("New list of courses after delete: ");
        coursDao.findAll(sessionFactory, transaction);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");


        // list all rooms in the database
        System.out.println("Rooms in the database: ");
        salleDao.findAll(sessionFactory, transaction);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // update room capacity
        roomA.setCapacite(35);
        salleDao.update(sessionFactory, transaction, roomA);
        System.out.println("Rooms in the database: ");
        salleDao.findAll(sessionFactory, transaction);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // delete a room
      //  salleDao.delete(sessionFactory, transaction, roomA);
        System.out.println("Rooms in the database: ");
        salleDao.findAll(sessionFactory, transaction);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // list of seance
        System.out.println("Seance in the database: ");
        seanceDao.findAll(sessionFactory, transaction);
        */

        sessionFactory.close();
    }


}
