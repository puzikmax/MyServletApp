package ru.servlet;

import ru.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GetIndexPageServlet extends ContextHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("users", getUserDao().getAllUsers());
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }
}
