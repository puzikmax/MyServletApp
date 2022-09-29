package ru.DAO;

import ru.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao<User> {

    public UserDao() {
        super();
    }

    @Override
    protected void initTable(Connection con) throws SQLException {
        System.out.println("Create Table USERS if exist");
        String sqlCreateTable = con.nativeSQL("CREATE TABLE IF NOT EXISTS USERS ( "
                + "id INT GENERATED ALWAYS AS IDENTITY, "
                + "name VARCHAR(255) NOT NULL, "
                + "age INT, "
                + "PRIMARY KEY(id) ) ");
        System.out.println(sqlCreateTable);
        con.prepareStatement(sqlCreateTable).execute();
    }

    @Override
    public int save(Connection con, User obj) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "insert into users(name,age) values (?,?)");
        ps.setString(1, obj.getName());
        ps.setInt(2, obj.getAge());
        return ps.executeUpdate();

    }

    @Override
    public int update(Connection con, User obj) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "update users set name=?,age=? where id=?");
        ps.setString(1, obj.getName());
        ps.setInt(2, obj.getAge());
        ps.setInt(3, obj.getId());
        return ps.executeUpdate();
    }

    @Override
    protected int delete(Connection con, int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("delete from users where id=?");
        ps.setInt(1, id);
        return ps.executeUpdate();
    }

    @Override
    protected User getById(Connection con, int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("select * from users where id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User user = new User(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setAge(rs.getInt(3));
            return user;
        }
        return null;
    }

    @Override
    protected List<User> getAll(Connection con) throws SQLException {
        List<User> userList = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("select * from users");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User user = new User(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setAge(rs.getInt(3));
            userList.add(user);
        }

        PreparedStatement ps2 = con.prepareStatement("select * from user_cur");
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            User user = new User(rs2.getInt(1));
            user.setCurrency(Integer.parseInt(rs2.getString(2)));
            userList.add(user);
        }
        return userList;
    }
}
