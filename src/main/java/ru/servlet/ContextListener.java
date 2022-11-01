package ru.servlet;

import ru.model.User;
import ru.service.UserDaoImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        final ServletContext servletContext = servletContextEvent.getServletContext();

        servletContext.setAttribute("userDaoImpl", new UserDaoImpl());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}