package ru.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteUserServlet extends ContextHttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("UTF-8");

        getUserDao().delete(Integer.valueOf(req.getParameter("id")));

        resp.sendRedirect(req.getContextPath() + "/");
    }

}
