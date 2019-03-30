package pl.pwn.reaktor.dziekanat.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.pwn.reaktor.dziekanat.model.DTO.StudentDTO;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

import java.util.List;

import static pl.pwn.reaktor.dziekanat.model.RoleEnum.ROLE_STUDENT;

public class UserService {


    public Boolean isPasswordMatch(String password, String confPassword) {

        if (password.equals(confPassword)) {
            return true;
        }
        return false;
    }

    public User isLoginExist(String login) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select u from User u where u.login=:login ");
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
//        newUser.setStudent(null);
        session.save(newUser);
        transaction.commit();
        session.close();


    }

    public void update(User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }


    public List<User> getAllAdmin() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("Select u from User u where u.role = 'ROLE_ADMIN'");
        List<User> admins = query.list();
        session.close();
        return admins;
    }

    public List<StudentDTO> getAllStudents() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "Select new pl.pwn.reaktor.dziekanat.model.DTO.StudentDTO (s.id, u.login, u.active, s.firstName, s.lastName," +
                " s.address.street, s.address.city) from User u INNER JOIN u.student s where u.role = 'ROLE_STUDENT'";  // w tym zapytaniu poza select tworzymy od razu obiekt StudentDTO - pozwala na to Hibernate oraz SpringData

        Query query = session.createQuery(hql);
        List<StudentDTO> studentDTOList = query.list();
        session.close();
        return studentDTOList;
    }


}
