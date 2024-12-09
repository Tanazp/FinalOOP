package com.aep.servlet;

import com.aep.dao.RequestDAO;
import com.aep.dao.NotificationDAO;
import com.aep.model.Request;
import com.aep.model.Notification;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {
    private RequestDAO requestDAO;
    private NotificationDAO notificationDAO;

    public void init() {
        requestDAO = new RequestDAO();
        notificationDAO = new NotificationDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("submit".equals(action)){
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            int professionalId = Integer.parseInt(request.getParameter("professionalId"));

            Request req = new Request();
            req.setCourseId(courseId);
            req.setProfessionalId(professionalId);

            boolean submitted = requestDAO.submitRequest(req);
            if(submitted){
                response.sendRedirect("searchCourses.jsp?message=Request Submitted");
            } else {
                response.sendRedirect("searchCourses.jsp?error=Submission Failed");
            }
        } else if("updateStatus".equals(action)){
            int requestId = Integer.parseInt(request.getParameter("requestId"));
            String status = request.getParameter("status");
            int professionalId = Integer.parseInt(request.getParameter("professionalId"));
            String message = "Your request to teach course ID " + requestId + " has been " + status;
            boolean updated = requestDAO.updateRequestStatus(requestId, status);
            if(updated){
                Notification notification = new Notification();
                notification.setProfessionalId(professionalId);
                notification.setMessage(message);
                notificationDAO.addNotification(notification);
                response.sendRedirect("institutionDashboard.jsp?message=Status Updated");
            } else {
                response.sendRedirect("institutionDashboard.jsp?error=Update Failed");
            }
        }
    }
}

