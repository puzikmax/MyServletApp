package ru.servlet;

import ru.model.User;
import ru.service.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UpdateUserServlet extends HttpServlet {

    UserDaoImpl userDao = new UserDaoImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");

        final int id = Integer.parseInt(request.getParameter("id"));
        final String name = request.getParameter("name");
        final int age = Integer.parseInt(request.getParameter("age"));

        User user = new User();
        user.setName(name);
        user.setAge(age);
        userDao.update(user);

        response.sendRedirect(request.getContextPath() + "/");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final int id = Integer.parseInt(request.getParameter("id"));
        final User user = userDao.getById(id);

        if (Objects.isNull(user)) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(request, response);
    }
}
