package org.example.Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DTO.DtoGrade;
import org.example.DTO.DtoGroup;
import org.example.Service.BaseStudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/group/*"})
public class GroupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        var mapper = new ObjectMapper();
        BaseStudentService studentService = new BaseStudentService();

        String groupNumber = "";
        String page = "";
        String pathInfo = req.getPathInfo();
        String[] parts = pathInfo.split("/");
        if (parts.length >= 3) {
            groupNumber = parts[1];
            page = parts[2];
            DtoGroup group = studentService.getStudentsByGroup(Integer.parseInt(groupNumber), Integer.parseInt(page));

            try (var output = resp.getWriter()) {
                resp.setContentType("application/json");
                output.write(mapper.writeValueAsString(group));
                output.flush();
            }
            catch (Exception e){
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                try (var output = resp.getWriter()) {
                    output.write("Group not found");
                    output.flush();
                }
            }
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (var output = resp.getWriter()) {
                output.write("Invalid data supplied");
                output.flush();
            }
        }
    }

}
