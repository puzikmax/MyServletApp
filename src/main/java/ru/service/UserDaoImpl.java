package ru.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.model.User;
import ru.util.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(id);
        transaction.commit();
        session.close();
    }

    @Override
    public User getById(int id) {
        return  HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().get(User.class,id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("From User").list();
        return userList;
    }
}
