package pl.pwn.reaktor.dziekanat.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

import java.lang.reflect.InvocationTargetException;

import static pl.pwn.reaktor.dziekanat.model.RoleEnum.ROLE_STUDENT;

public class SignInService {


    public Boolean isPasswordMatch(String password, String confPassword) {

        if (password.equals(confPassword)) {
            return true;
        }
        return false;
    }

    public User isLoginExist (String login){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query =session.createQuery("select u from User u where u.login=:login ");
        query.setParameter("login", login);
        query.setMaxResults(1);
        User IsUserExist = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return IsUserExist;
    }

    public void signIn(String login, String password) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setRole(ROLE_STUDENT);
        newUser.setActive(true);

        session.save(newUser);
        transaction.commit();
        session.close();
    }
}
