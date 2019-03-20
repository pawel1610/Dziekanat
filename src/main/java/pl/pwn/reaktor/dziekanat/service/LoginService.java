package pl.pwn.reaktor.dziekanat.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;



public class LoginService {

    public User login(String login, String password){

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query =session.createQuery("select u from User u where u.login=:login and u.password=:password and u.active=1");

        query.setParameter("login", login);
        query.setParameter("password", password);
        query.setMaxResults(1);
        User logedUser = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return logedUser;
    }
}

