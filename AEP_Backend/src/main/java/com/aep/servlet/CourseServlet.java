package com.aep.servlet;

import com.aep.dao.CourseDAO;
import com.aep.model.Course;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
    private CourseDAO courseDAO;

    public void init() {
        courseDAO = new CourseDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("create".equals(action)){
            int institutionId = Integer.parseInt(request.getParameter("institutionId"));
            String courseTitle = request.getParameter("courseTitle");
            String courseCode = request.getParameter("courseCode");
            String term = request.getParameter("term");
            String outline = request.getParameter("outline");
            String schedule = request.getParameter("schedule");
            String deliveryMethod = request.getParameter("deliveryMethod");
            double compensation = Double.parseDouble(request.getParameter("compensation"));
            String preferredQualifications = request.getParameter("preferredQualifications");

            Course course = new Course();
            course.setInstitutionId(institutionId);
            course.setCourseTitle(courseTitle);
            course.setCourseCode(courseCode);
            course.setTerm(term);
            course.setOutline(outline);
            course.setSchedule(schedule);
            course.setDeliveryMethod(deliveryMethod);
            course.setCompensation(compensation);
            course.setPreferredQualifications(preferredQualifications);

            boolean created = courseDAO.createCourse(course);
            if(created){
                response.sendRedirect("institutionDashboard.jsp?message=Course Created");
            } else {
                response.sendRedirect("createCourse.jsp?error=Creation Failed");
            }
        } else if("update".equals(action)){
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            String courseTitle = request.getParameter("courseTitle");
            String courseCode = request.getParameter("courseCode");
            String term = request.getParameter("term");
            String outline = request.getParameter("outline");
            String schedule = request.getParameter("schedule");
            String deliveryMethod = request.getParameter("deliveryMethod");
            double compensation = Double.parseDouble(request.getParameter("compensation"));
            String preferredQualifications = request.getParameter("preferredQualifications");

            Course course = new Course();
            course.setCourseId(courseId);
            course.setCourseTitle(courseTitle);
            course.setCourseCode(courseCode);
            course.setTerm(term);
            course.setOutline(outline);
            course.setSchedule(schedule);
            course.setDeliveryMethod(deliveryMethod);
            course.setCompensation(compensation);
            course.setPreferredQualifications(preferredQualifications);

            boolean updated = courseDAO.updateCourse(course);
            if(updated){
                response.sendRedirect("institutionDashboard.jsp?message=Course Updated");
            } else {
                response.sendRedirect("editCourse.jsp?error=Update Failed");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

