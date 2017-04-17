package driver;

import dao.CoursDao;
import dao.FormationDao;
import dao.GenericDao;
import dao.SalleDao;
import domain.*;
import exceptions.BusinessException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

/**
 * Created by marti on 16/04/2017.
 */


public class Driver {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Transaction transaction = null;
    public static void main(String[] args) throws BusinessException {


       //  Driver driver = new Driver();

        /* Add few address records in database*/
      //  Address address1 = driver.addAddress("13 Avenue Jean Jaurés", "Bagneux", "Paris", 92220);
      //  Address address2 = driver.addAddress("Corso Francia", "Torino", "Piemonte", 74440);

        /* Add few employee records in database */
      //  Integer empID1 = driver.addEmployee("Zara", "Ali", 1000, address1); // address1 will be add because of cascade type = ALL
      //  Integer empID2 = driver.addEmployee("Daisy", "Das", 5000, address2);
      //  Integer empID3 = driver.addEmployee("John", "Paul", 10000, address1);

        /* List down all the employees */
      //  driver.listEmployees();

        /* Update employee's records */
      //  driver.updateEmployee(empID1, 5000);

        /* Delete an employee from database */
      //  driver.deleteEmployee(empID3);

        /* List dow new list of the employees */
      //  driver.listEmployees();
      //  Transaction transaction = null;
        GenericDao<Formation, Integer> formationDao = new FormationDao();
        GenericDao<Cours, Integer> coursDao = new CoursDao();
        GenericDao<Salle, SalleId> salleDao = new SalleDao();

        // some transient formation object
        Formation finance = new Formation("Master 2 Finance");
        Formation sitn = new Formation("Master 2 SITN");
        Formation id = new Formation("Master 2 ID");

        // some transient courses object

        /*Cours J2EE = new Cours("J2EE");
        Cours bigData = new Cours("BigData");

        coursDao.create(sessionFactory, transaction, optimization);
        coursDao.create(sessionFactory, transaction, J2EE);
        coursDao.create(sessionFactory, transaction, bigData);
        */

        // assign a cours to a formation
        /*finance.addCours(optimization);
        finance.addCours(J2EE);
        finance.addCours(bigData);

        sitn.addCours(J2EE);
        id.addCours(bigData);
        */



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
        //formationDao.update(sessionFactory, transaction, formation_finance);

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


    public Address addAddress(String street, String city, String state, Integer zipcode) {
        Transaction transaction = null;
        Integer addressID = null;
        Address address = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            address = new Address(street, city, state, zipcode);
            session.save(address);
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
        return address;
    }

    public Integer addEmployee(String firstname, String lastname, int salary, Address address) {
        Transaction transaction = null;
        Integer employeeID = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Employee employee = new Employee(firstname, lastname, salary, address);
            employeeID = (Integer) session.save(employee);
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
        return employeeID;
    }


    public void listEmployees() {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext(); ) {
                Employee employee = (Employee) iterator.next();
                System.out.println("First Name: " + employee.getFirstname());
                System.out.println("Last Name: " + employee.getLastname());
                System.out.println("Salary: " + employee.getSalary());
                Address address = employee.getAddress();
                System.out.println("Address ");
                System.out.println("\tStreet: " + address.getStreet_name());
                System.out.println("\tCity: " + address.getCity_name());
                System.out.println("\tState: " + address.getState_name());
                System.out.println("\tZipcode: " + address.getZipcode());
            }
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }
    }


    public void updateEmployee(Integer employeeID, int salary) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeID);
            employee.setSalary(salary);
            session.update(employee);
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }

    }


    public void deleteEmployee(Integer employeeID) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeID);
            session.delete(employee);
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) transaction.rollback();
            he.printStackTrace();
        }

    }









}
