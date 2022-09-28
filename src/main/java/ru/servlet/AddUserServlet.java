package ru.servlet;

import ru.DAO.Dao;
import ru.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class AddUserServlet extends HttpServlet {

    private Map<Integer, User> users;

    private AtomicInteger id;

    @Override
    public void init() {

        final Object users = getServletContext().getAttribute("users");

        if (users == null || !(users instanceof ConcurrentHashMap)) {

            throw new IllegalStateException("You're repo does not initialize!");
        } else {

            this.users = (ConcurrentHashMap<Integer, User>) users;
        }

        id = new AtomicInteger(2);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("UTF-8");

            final String name = req.getParameter("name");

            final User user = new User();
            final int id = this.id.getAndIncrement();


            user.setId(id);
            user.setName(name);

        int status = Dao.save(user);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
