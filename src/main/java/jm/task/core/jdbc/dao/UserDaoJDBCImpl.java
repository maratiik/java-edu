package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String CREATE = "create table if not exists users (" +
                                         "id bigint primary key auto_increment," +
                                         "name varchar(50) not null," +
                                         "lastName varchar(50) not null," +
                                         "age tinyint not null)";
    private static final String DROP = "drop table if exists users";
    private static final String SAVE_USER = "insert into users(name, lastName, age) values(?, ?, ?)";
    private static final String REMOVE = "delete from users where id=?";
    private static final String GET_ALL = "select * from users order by id";
    private static final String CLEAN = "truncate table users";

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Statement stmt = Util.getConnection().createStatement()) {
            stmt.executeUpdate(CREATE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement stmt = Util.getConnection().createStatement()) {
            stmt.executeUpdate(DROP);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement stmt = Util.getConnection().prepareStatement(SAVE_USER)) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement stmt = Util.getConnection().prepareStatement(REMOVE)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement stmt = Util.getConnection().prepareStatement(GET_ALL)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getByte("age")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement stmt = Util.getConnection().createStatement()) {
            stmt.executeUpdate(CLEAN);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

