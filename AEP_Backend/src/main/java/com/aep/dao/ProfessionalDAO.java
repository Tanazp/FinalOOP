package com.aep.dao;

import com.aep.model.AcademicProfessional;
import com.aep.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfessionalDAO {
    public boolean createProfessional(AcademicProfessional professional) {
        String sql = "INSERT INTO AcademicProfessionals (user_id, current_institution, academic_position, education_background, expertise_area) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, professional.getUserId());
            stmt.setString(2, professional.getCurrentInstitution());
            stmt.setString(3, professional.getAcademicPosition());
            stmt.setString(4, professional.getEducationBackground());
            stmt.setString(5, professional.getExpertiseArea());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public AcademicProfessional getProfessionalByUserId(int userId) {
        String sql = "SELECT * FROM AcademicProfessionals WHERE user_id = ?";
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                AcademicProfessional professional = new AcademicProfessional();
                professional.setProfessionalId(rs.getInt("professional_id"));
                professional.setUserId(rs.getInt("user_id"));
                professional.setCurrentInstitution(rs.getString("current_institution"));
                professional.setAcademicPosition(rs.getString("academic_position"));
                professional.setEducationBackground(rs.getString("education_background"));
                professional.setExpertiseArea(rs.getString("expertise_area"));
                return professional;
            }
        } catch (Exception e) {}
        return null;
    }
}

