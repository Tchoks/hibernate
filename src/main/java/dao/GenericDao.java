package dao;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;

/**
 * Created by marti on 17/04/2017.
 */
public abstract class GenericDao<T, ID extends Serializable> {

   public abstract void findAll(SessionFactory sessionFactory, Transaction transaction);

   public abstract T findById(SessionFactory sessionFactory, Transaction transaction, ID id);

   public abstract T create(SessionFactory sessionFactory, Transaction transaction, T object);

   public  abstract T update(SessionFactory sessionFactory, Transaction transaction, T object);

   public abstract boolean delete(SessionFactory sessionFactory, Transaction transaction, T object);
}
