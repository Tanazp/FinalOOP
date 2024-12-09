package com.aep.dao;

import com.aep.model.Request;
import com.aep.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    public boolean submitRequest(Request request) {
        String sql = "INSERT INTO Requests (course_id, professional_id, status) VALUES (?, ?, 'Pending')";
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, request.getCourseId());
            stmt.setInt(2, request.getProfessionalId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Request> getRequestsByInstitution(int institutionId) {
        String sql = "SELECT r.*, c.course_title FROM Requests r JOIN Courses c ON r.course_id = c.course_id WHERE c.institution_id = ?";
        List<Request> requests = new ArrayList<>();
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, institutionId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setRequestId(rs.getInt("request_id"));
                request.setCourseId(rs.getInt("course_id"));
                request.setProfessionalId(rs.getInt("professional_id"));
                request.setStatus(rs.getString("status"));
                request.setCreatedAt(rs.getString("created_at"));
                requests.add(request);
            }
        } catch (Exception e) {}
        return requests;
    }

    public boolean updateRequestStatus(int requestId, String status) {
        String sql = "UPDATE Requests SET status = ? WHERE request_id = ?";
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, requestId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }
}

