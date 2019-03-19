package pl.pwn.reaktor.dziekanat.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

import static pl.pwn.reaktor.dziekanat.model.RoleEnum.ROLE_STUDENT;

public class SignInService {


    public Boolean isPasswordMatch(String password, String confPassword) {

        if (password.equals(confPassword)) {
            return true;
        }
        return false;
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
    }
}
