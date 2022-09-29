package ru.servlet;

import ru.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddUserServlet extends ContextHttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        final String name = req.getParameter("name");
        final String age = req.getParameter("age");

        final User user = new User();
        user.setName(name);
        user.setAge(Integer.parseInt(age));


        getUserDao().save(user);

        resp.sendRedirect(req.getContextPath() + "/");
    }

}
