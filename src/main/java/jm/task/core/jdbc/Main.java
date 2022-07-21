package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.SQLSyntaxErrorException;

public class Main {
    public static void main(String[] args) throws SQLSyntaxErrorException {
        Session session = Util.getSessionFactory().openSession();

        // реализуйте алгоритм здесь
//        Util.getConnection();
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.dropUsersTable();
        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);

        userDao.removeUserById(1);

        for (User user: userDao.getAllUsers()
             ) {
            System.out.println(user.getName());
        }
//        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
