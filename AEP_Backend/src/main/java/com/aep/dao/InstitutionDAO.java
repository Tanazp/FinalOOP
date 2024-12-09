package com.aep.dao;

import com.aep.model.AcademicInstitution;
import com.aep.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InstitutionDAO {
    public boolean createInstitution(AcademicInstitution institution) {
        String sql = "INSERT INTO AcademicInstitutions (user_id, institution_name, address, courses_offered) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, institution.getUserId());
            stmt.setString(2, institution.getInstitutionName());
            stmt.setString(3, institution.getAddress());
            stmt.setString(4, institution.getCoursesOffered());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public AcademicInstitution getInstitutionByUserId(int userId) {
        String sql = "SELECT * FROM AcademicInstitutions WHERE user_id = ?";
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                AcademicInstitution institution = new AcademicInstitution();
                institution.setInstitutionId(rs.getInt("institution_id"));
                institution.setUserId(rs.getInt("user_id"));
                institution.setInstitutionName(rs.getString("institution_name"));
                institution.setAddress(rs.getString("address"));
                institution.setCoursesOffered(rs.getString("courses_offered"));
                return institution;
            }
        } catch (Exception e) {}
        return null;
    }
}

