package com.aep.servlet;

import com.aep.dao.CourseDAO;
import com.aep.model.Course;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private CourseDAO courseDAO;

    public void init() {
        courseDAO = new CourseDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseCode = request.getParameter("courseCode");
        String courseTitle = request.getParameter("courseTitle");
        String institutionName = request.getParameter("institutionName");
        String term = request.getParameter("term");
        String schedule = request.getParameter("schedule");
        String deliveryMethod = request.getParameter("deliveryMethod");

        List<Course> courses = courseDAO.searchCourses(courseCode, courseTitle, institutionName, term, schedule, deliveryMethod);
        String json = new Gson().toJson(courses);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}

