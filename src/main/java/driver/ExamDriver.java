package driver;

import domain.Utilisateur;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * @authors Martin Tchokonthe And Mohammed Sylla
 * @date on 17/04/2017.
 */

public class ExamDriver {

    private static Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    public static void main(String[] args) {
           session.beginTransaction();

       /* Droit d = new Droit();
        d.setNom("Admin");
        d.setDescription("Droits administrateur");
        session.save(d);



        Utilisateur u1 = new Utilisateur();
        u1.setLogin("CComp");
        u1.setNom("Computing");
        u1.setPrenom("Claude");


        session.save(u1);

        u1.addToDroits(d);
        */

        //Droit d1 = (Droit) session.load(Droit.class, new Long(1));

        //Droit d2 = (Droit) session.load(Droit.class, new Long(1));

        Utilisateur u = (Utilisateur) session.get(Utilisateur.class,new Long(2));

        /*u.addToDroits(d1);
        u.addToDroits(d2);
        */

        List result = session.createQuery("select u from Utilisateur u left join fetch u.droits where u.id = 1").list();
        Utilisateur utilisateur = (Utilisateur) result.get(0);


        session.getTransaction().commit();

        session.close();
    }
}
