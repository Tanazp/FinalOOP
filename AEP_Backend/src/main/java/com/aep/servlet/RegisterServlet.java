package com.aep.servlet;

import com.aep.dao.UserDAO;
import com.aep.dao.ProfessionalDAO;
import com.aep.dao.InstitutionDAO;
import com.aep.model.User;
import com.aep.model.AcademicProfessional;
import com.aep.model.AcademicInstitution;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;
    private ProfessionalDAO professionalDAO;
    private InstitutionDAO institutionDAO;

    public void init() {
        userDAO = new UserDAO();
        professionalDAO = new ProfessionalDAO();
        institutionDAO = new InstitutionDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType = request.getParameter("userType");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setUserType(userType);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        boolean userRegistered = userDAO.registerUser(user);
        if(userRegistered){
            User registeredUser = userDAO.loginUser(email, password);
            if("Professional".equals(userType)){
                String currentInstitution = request.getParameter("currentInstitution");
                String academicPosition = request.getParameter("academicPosition");
                String educationBackground = request.getParameter("educationBackground");
                String expertiseArea = request.getParameter("expertiseArea");
                AcademicProfessional professional = new AcademicProfessional();
                professional.setUserId(registeredUser.getUserId());
                professional.setCurrentInstitution(currentInstitution);
                professional.setAcademicPosition(academicPosition);
                professional.setEducationBackground(educationBackground);
                professional.setExpertiseArea(expertiseArea);
                professionalDAO.createProfessional(professional);
            } else {
                String institutionName = request.getParameter("institutionName");
                String address = request.getParameter("address");
                String coursesOffered = request.getParameter("coursesOffered");
                AcademicInstitution institution = new AcademicInstitution();
                institution.setUserId(registeredUser.getUserId());
                institution.setInstitutionName(institutionName);
                institution.setAddress(address);
                institution.setCoursesOffered(coursesOffered);
                institutionDAO.createInstitution(institution);
            }
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("register.jsp?error=Registration Failed");
        }
    }
}

