package com.aep.servlet;

import com.aep.dao.NotificationDAO;
import com.aep.model.Notification;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;

@WebServlet("/notifications")
public class NotificationServlet extends HttpServlet {
    private NotificationDAO notificationDAO;

    public void init() {
        notificationDAO = new NotificationDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int professionalId = Integer.parseInt(request.getParameter("professionalId"));
        List<Notification> notifications = notificationDAO.getNotifications(professionalId);
        String json = new Gson().toJson(notifications);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}

