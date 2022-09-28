package ru.DAO;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import ru.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    private static HikariDataSource dataSource;

    private static void initDataBaseConnectionPoll() {
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://192.168.0.100:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("5544801");
    }

    /*public static void closeDataBaseConnection(){
        dataSource.close();
    }*/

    public static int save(User user) {
        System.out.println("Adding users");
        int status=0;

        try{
            Connection con=dataSource.getConnection();
            PreparedStatement ps=con.prepareStatement("insert into users(unique_id,name,age,add_date) values (?,?,?,?)");
            ps.setInt(1,user.getId());
            ps.setString(2,user.getName());
            ps.setInt(3,user.getAge());
            ps.setString(4, user.getName());
            status=ps.executeUpdate();
            con.close();
            System.out.println("Adding complete");
        }catch(Exception ex){
            ex.printStackTrace();}
        return status;
    }
    public static void update(User user) {
        int status;

        try{
            Connection con=dataSource.getConnection();
            PreparedStatement ps=con.prepareStatement("update users set unique_id=?,name=?,age=?,add_date=? where unique_id=?");
            ps.setInt(1,user.getId());
            ps.setString(2,user.getName());
            ps.setInt(3,user.getAge());
            ps.setString(4, user.getName());
            status=ps.executeUpdate();

            con.close();
        }catch(Exception ex){ex.printStackTrace();}

    }
    public static void delete(int id) {
        int status;

        try{
            Connection con=dataSource.getConnection();
            PreparedStatement ps=con.prepareStatement("delete from users where unique_id=?");
            ps.setInt(1,id);
            status=ps.executeUpdate();

            con.close();
        }catch(Exception e){e.printStackTrace();}

    }
    public static User getUserById(int id) {
        User user=new User();
        try{
            Connection con=dataSource.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from users where unique_id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setAge(rs.getInt(3));
                user.setName(rs.getString(4));
            }
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();}
        return user;
    }
    public static List<User> getAllUsers() {
        System.out.println("Reading users");
        List<User> list=new ArrayList<User>();
        try{
            Connection con=dataSource.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from users");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                User user=new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setAge(rs.getInt(3));
                user.setName(rs.getString(4));
                list.add(user);
            }
            con.close();
        }catch(Exception e){

            e.printStackTrace();
        }
        return list;
    }
}

