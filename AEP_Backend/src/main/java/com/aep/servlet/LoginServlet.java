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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;
    private ProfessionalDAO professionalDAO;
    private InstitutionDAO institutionDAO;

    public void init() {
        userDAO = new UserDAO();
        professionalDAO = new ProfessionalDAO();
        institutionDAO = new InstitutionDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.loginUser(email, password);
        if(user != null){
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            if("Professional".equals(user.getUserType())){
                AcademicProfessional professional = professionalDAO.getProfessionalByUserId(user.getUserId());
                session.setAttribute("professional", professional);
                response.sendRedirect("professionalDashboard.jsp");
            } else {
                AcademicInstitution institution = institutionDAO.getInstitutionByUserId(user.getUserId());
                session.setAttribute("institution", institution);
                response.sendRedirect("institutionDashboard.jsp");
            }
        } else {
            response.sendRedirect("login.jsp?error=Invalid Credentials");
        }
    }
}

