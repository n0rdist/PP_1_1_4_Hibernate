package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

public interface UserService {
    void createUsersTable() throws SQLSyntaxErrorException;

    void dropUsersTable() throws SQLSyntaxErrorException;

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
