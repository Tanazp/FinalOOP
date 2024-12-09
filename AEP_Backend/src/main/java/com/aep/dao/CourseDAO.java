package com.aep.dao;

import com.aep.model.Course;
import com.aep.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public boolean createCourse(Course course) {
        String sql = "INSERT INTO Courses (institution_id, course_title, course_code, term, outline, schedule, delivery_method, compensation, preferred_qualifications) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, course.getInstitutionId());
            stmt.setString(2, course.getCourseTitle());
            stmt.setString(3, course.getCourseCode());
            stmt.setString(4, course.getTerm());
            stmt.setString(5, course.getOutline());
            stmt.setString(6, course.getSchedule());
            stmt.setString(7, course.getDeliveryMethod());
            stmt.setDouble(8, course.getCompensation());
            stmt.setString(9, course.getPreferredQualifications());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Course> searchCourses(String courseCode, String courseTitle, String institutionName, String term, String schedule, String deliveryMethod) {
        String sql = "SELECT c.*, ai.institution_name FROM Courses c JOIN AcademicInstitutions ai ON c.institution_id = ai.institution_id WHERE 1=1";
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            int index = 1;
            if (courseCode != null && !courseCode.isEmpty()) {
                sql += " AND c.course_code = ?";
                stmt.setString(index++, courseCode);
            }
            if (courseTitle != null && !courseTitle.isEmpty()) {
                sql += " AND c.course_title LIKE ?";
                stmt.setString(index++, "%" + courseTitle + "%");
            }
            if (institutionName != null && !institutionName.isEmpty()) {
                sql += " AND ai.institution_name = ?";
                stmt.setString(index++, institutionName);
            }
            if (term != null && !term.isEmpty()) {
                sql += " AND c.term = ?";
                stmt.setString(index++, term);
            }
            if (schedule != null && !schedule.isEmpty()) {
                sql += " AND c.schedule = ?";
                stmt.setString(index++, schedule);
            }
            if (deliveryMethod != null && !deliveryMethod.isEmpty()) {
                sql += " AND c.delivery_method = ?";
                stmt.setString(index++, deliveryMethod);
            }
            stmt.execute();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("course_id"));
                course.setInstitutionId(rs.getInt("institution_id"));
                course.setCourseTitle(rs.getString("course_title"));
                course.setCourseCode(rs.getString("course_code"));
                course.setTerm(rs.getString("term"));
                course.setOutline(rs.getString("outline"));
                course.setSchedule(rs.getString("schedule"));
                course.setDeliveryMethod(rs.getString("delivery_method"));
                course.setCompensation(rs.getDouble("compensation"));
                course.setPreferredQualifications(rs.getString("preferred_qualifications"));
                courses.add(course);
            }
        } catch (Exception e) {}
        return courses;
    }

    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM Courses WHERE course_id = ?";
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("course_id"));
                course.setInstitutionId(rs.getInt("institution_id"));
                course.setCourseTitle(rs.getString("course_title"));
                course.setCourseCode(rs.getString("course_code"));
                course.setTerm(rs.getString("term"));
                course.setOutline(rs.getString("outline"));
                course.setSchedule(rs.getString("schedule"));
                course.setDeliveryMethod(rs.getString("delivery_method"));
                course.setCompensation(rs.getDouble("compensation"));
                course.setPreferredQualifications(rs.getString("preferred_qualifications"));
                return course;
            }
        } catch (Exception e) {}
        return null;
    }

    public boolean updateCourse(Course course) {
        String sql = "UPDATE Courses SET course_title = ?, course_code = ?, term = ?, outline = ?, schedule = ?, delivery_method = ?, compensation = ?, preferred_qualifications = ? WHERE course_id = ?";
        try (Connection conn = DBConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseTitle());
            stmt.setString(2, course.getCourseCode());
            stmt.setString(3, course.getTerm());
            stmt.setString(4, course.getOutline());
            stmt.setString(5, course.getSchedule());
            stmt.setString(6, course.getDeliveryMethod());
            stmt.setDouble(7, course.getCompensation());
            stmt.setString(8, course.getPreferredQualifications());
            stmt.setInt(9, course.getCourseId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }
}

