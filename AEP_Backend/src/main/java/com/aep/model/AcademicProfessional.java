package com.aep.model;

public class AcademicProfessional {
    private int professionalId;
    private int userId;
    private String currentInstitution;
    private String academicPosition;
    private String educationBackground;
    private String expertiseArea;

    public int getProfessionalId() { return professionalId; }
    public void setProfessionalId(int professionalId) { this.professionalId = professionalId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getCurrentInstitution() { return currentInstitution; }
    public void setCurrentInstitution(String currentInstitution) { this.currentInstitution = currentInstitution; }
    public String getAcademicPosition() { return academicPosition; }
    public void setAcademicPosition(String academicPosition) { this.academicPosition = academicPosition; }
    public String getEducationBackground() { return educationBackground; }
    public void setEducationBackground(String educationBackground) { this.educationBackground = educationBackground; }
    public String getExpertiseArea() { return expertiseArea; }
    public void setExpertiseArea(String expertiseArea) { this.expertiseArea = expertiseArea; }
}

