package ru.servlet;

import ru.model.User;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeleteUserServlet extends HttpServlet {

    private Map<Integer, User> users;

    @Override
    public void init() {

        final Object users = getServletContext().getAttribute("users");

        if (users == null || !(users instanceof ConcurrentHashMap)) {

            throw new IllegalStateException("You're repo does not initialize!");
        } else {

            this.users = (ConcurrentHashMap<Integer, User>) users;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        users.remove(Integer.valueOf(req.getParameter("id")));

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
