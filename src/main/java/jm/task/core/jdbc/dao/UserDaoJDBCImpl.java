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

    public void createUsersTable() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                stmt = conn.createStatement();
                stmt.executeUpdate(CREATE);
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void dropUsersTable() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = Util.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(DROP);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Util.getConnection();
            stmt = conn.prepareStatement(SAVE_USER);

            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);

            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void removeUserById(long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                stmt = conn.prepareStatement(REMOVE);
                stmt.setLong(1, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Util.getConnection();

            if (conn != null) {
                stmt = conn.prepareStatement(GET_ALL);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    User user = new User(rs.getString("name"),
                            rs.getString("lastName"),
                            rs.getByte("age"));
                    list.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                stmt = conn.prepareStatement(CLEAN);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
