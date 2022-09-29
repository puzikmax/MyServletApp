package ru.servlet;

import ru.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class UpdateUserServlet extends ContextHttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        final int id = Integer.parseInt(req.getParameter("id"));
        final String name = req.getParameter("name");

        User user = new User(id);
        user.setName(name);
        getUserDao().update(user);

        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final String id = req.getParameter("id");

        final User user = getUserDao().getById(Integer.parseInt(id));

        if (Objects.isNull(user)) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(req, resp);
    }

}