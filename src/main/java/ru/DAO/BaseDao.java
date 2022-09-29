package ru.DAO;

import com.zaxxer.hikari.HikariDataSource;
import ru.model.BaseModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T extends BaseModel> {

    private static final HikariDataSource dataSource;

    static {

        dataSource = new HikariDataSource();
        dataSource.setDriverClassName(org.postgresql.Driver.class.getName());
        dataSource.setJdbcUrl("jdbc:postgresql://192.168.0.100:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("5544801");
        dataSource.setMaximumPoolSize(10);
    }

    BaseDao() {
        try {
            Connection con = dataSource.getConnection();
            initTable(con);
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    abstract protected void initTable(Connection con) throws SQLException;

    public int save(T obj) {
        int status = 0;
        try {
            Connection con = dataSource.getConnection();
            status = save(con, obj);
            con.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            dataSource.close();
        }
        return status;
    }

    abstract protected int save(Connection con, T obj) throws SQLException;


    public int update(T obj) {
        int status = -1;
        try {
            Connection con = dataSource.getConnection();
            status = update(con, obj);
            con.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            dataSource.close();
        }
        return status;
    }

    abstract protected int update(Connection con, T obj) throws SQLException;

    public int delete(int id) {
        int status = -1;
        try {
            Connection con = dataSource.getConnection();
            status = delete(con,id);
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            dataSource.close();
        }
        return status;
    }

    abstract protected int delete(Connection con, int id) throws SQLException;

    public T getById(int id) {
        T result = null;
        try {
            Connection con = dataSource.getConnection();
            result = getById(con, id);
            con.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            dataSource.close();
        }
        return result;
    }

    abstract protected T getById(Connection con, int id) throws SQLException;


    public List<T> getAllUsers() {
        List<T> list = new ArrayList<>();
        try {
            Connection con = dataSource.getConnection();
            list = getAll(con);
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            dataSource.close();
        }
        return list;
    }

    abstract protected List<T> getAll(Connection con) throws SQLException;

}

