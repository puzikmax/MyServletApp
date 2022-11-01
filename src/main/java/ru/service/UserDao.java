package ru.service;

import ru.model.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    void update(User user);

    void delete(int id);

    User getById(int id);

    List<User> getAllUsers();

}
