package org.example.Servlet;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home"})
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var output = resp.getWriter()) {
            var userName = req.getParameter("username");
            output.write("Hellooooo " + userName + "!");
            output.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var mapper = new ObjectMapper();

        Order order = null;
        try (var input = req.getInputStream()) {
            order = mapper.readValue(new String(input.readAllBytes()), Order.class);
        }

        var user = new User();
        user.name = "myname";
        user.email = "myemail@test.ru";
        user.orderInfo = order.position +" " + order.count;
        try (var output = resp.getWriter()) {
            resp.setContentType("application/json");
            output.write(mapper.writeValueAsString(user));
            output.flush();
        }
    }

    public static class Order {
        public String position;
        private int count;
    }
    public static class User {
        public String name;
        public String email;

        public String orderInfo;
    }
}
