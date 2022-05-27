package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            statement = Util.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            preparedStatement = Util.getConnection().prepareStatement("SHOW TABLES FROM preproject1 LIKE 'usersTable'");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                try {
                    String SQL = "CREATE TABLE usersTable ( id BIGINT NOT NULL AUTO_INCREMENT, name TINYTEXT, lastName TINYTEXT, age TINYINT, PRIMARY KEY (id) )";
                    statement.executeUpdate(SQL);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            statement = Util.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            preparedStatement = Util.getConnection().prepareStatement("SHOW TABLES FROM preproject1 LIKE 'usersTable'");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String SQL = "DROP TABLE usersTable";
                try {
                    statement.executeUpdate(SQL);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Util.getConnection().prepareStatement("INSERT INTO usersTable(name, lastName, age) VALUES(?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            if (preparedStatement.executeUpdate() == 1) {
                System.out.println("User с именем – " + name + " добавлен в базу данных");
            }
            ;

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Util.getConnection().prepareStatement("DELETE FROM usersTable WHERE (id = ?)");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        PreparedStatement preparedStatement = null;
        List<User> list = new ArrayList<User>();
        try {
            preparedStatement = Util.getConnection().prepareStatement("SELECT * FROM usersTable");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String n = resultSet.getString("name");
                String ln = resultSet.getString("lastName");
                Byte a = resultSet.getByte("age");
                User user = new User(n, ln, a);
                list.add(user);
            }
            System.out.println(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        try {
            statement = Util.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String SQL = "TRUNCATE TABLE usersTable";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
