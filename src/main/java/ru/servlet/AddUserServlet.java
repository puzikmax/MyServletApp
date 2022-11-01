package ru.servlet;

import ru.model.User;
import ru.service.UserDaoImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UserDaoImpl userDao = new UserDaoImpl();
        request.setCharacterEncoding("UTF-8");

        final String name = request.getParameter("name");
        final String age = request.getParameter("age");

        final User user = new User();
        user.setName(name);
        user.setAge(Integer.parseInt(age));

        userDao.save(user);

        response.sendRedirect(request.getContextPath() + "/");
    }
}
