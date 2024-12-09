package com.aep.dao;

import com.aep.model.User;
import com.aep.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public boolean registerUser(User user) {
        String sql = "INSERT INTO Users (user_type, name, email, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserType());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public User loginUser(String email, String password) {
        String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserType(rs.getString("user_type"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        } catch (Exception e) {}
        return null;
    }
}

