package org.example.Servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DTO.DtoGrade;
import org.example.Service.BaseStudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/grade"})
public class GradeServlet extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        var mapper = new ObjectMapper();

        DtoGrade dtoGrade = null;
        try (var input = req.getReader()) {
            dtoGrade = mapper.readValue(input, DtoGrade.class);
        }
        catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (var output = resp.getWriter()) {
                output.write("Invalid data supplied");
                output.flush();
            }
        }

        BaseStudentService studentService = new BaseStudentService();


            boolean ifSuccessful = studentService.updateGradeByStudent(dtoGrade);

            if(ifSuccessful) {
                resp.setStatus(HttpServletResponse.SC_OK);
                try (var output = resp.getWriter()) {
                    output.write("Successful update");
                    output.flush();
                }
            }
            else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                try (var output = resp.getWriter()) {
                    output.write("Student not found");
                    output.flush();
                }
            }

    }
}
