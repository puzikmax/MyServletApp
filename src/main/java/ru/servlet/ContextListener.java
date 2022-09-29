package ru.servlet;

import ru.DAO.UserDao;
import ru.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;

@WebListener
public class ContextListener implements ServletContextListener {

    private Map<Integer, User> users;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        servletContext.setAttribute("userDao", new UserDao());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        users = null;
    }
}