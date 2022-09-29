package ru.servlet;

import ru.DAO.UserDao;

import javax.servlet.http.HttpServlet;

public class ContextHttpServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() {
        final Object userDao = getServletContext().getAttribute("userDao");
        if (userDao == null || !(userDao instanceof UserDao)) {
            throw new IllegalStateException("You're repo userDao does not initialize!");
        }
        else {
            this.userDao = (UserDao) userDao;
        }
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
