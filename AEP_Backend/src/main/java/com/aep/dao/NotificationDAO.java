package com.aep.dao;

import com.aep.model.Notification;
import com.aep.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    public boolean addNotification(Notification notification) {
        String sql = "INSERT INTO Notifications (professional_id, message) VALUES (?, ?)";
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, notification.getProfessionalId());
            stmt.setString(2, notification.getMessage());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Notification> getNotifications(int professionalId) {
        String sql = "SELECT * FROM Notifications WHERE professional_id = ?";
        List<Notification> notifications = new ArrayList<>();
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, professionalId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("notification_id"));
                notification.setProfessionalId(rs.getInt("professional_id"));
                notification.setMessage(rs.getString("message"));
                notification.setIsRead(rs.getBoolean("is_read"));
                notification.setCreatedAt(rs.getString("created_at"));
                notifications.add(notification);
            }
        } catch (Exception e) {}
        return notifications;
    }

    public boolean markAsRead(int notificationId) {
        String sql = "UPDATE Notifications SET is_read = TRUE WHERE notification_id = ?";
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, notificationId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }
}

