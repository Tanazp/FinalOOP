package com.aep.model;

public class AcademicInstitution {
    private int institutionId;
    private int userId;
    private String institutionName;
    private String address;
    private String coursesOffered;

    public int getInstitutionId() { return institutionId; }
    public void setInstitutionId(int institutionId) { this.institutionId = institutionId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getInstitutionName() { return institutionName; }
    public void setInstitutionName(String institutionName) { this.institutionName = institutionName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCoursesOffered() { return coursesOffered; }
    public void setCoursesOffered(String coursesOffered) { this.coursesOffered = coursesOffered; }
}

