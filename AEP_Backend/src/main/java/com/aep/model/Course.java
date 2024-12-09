package com.aep.model;

public class Course {
    private int courseId;
    private int institutionId;
    private String courseTitle;
    private String courseCode;
    private String term;
    private String outline;
    private String schedule;
    private String deliveryMethod;
    private double compensation;
    private String preferredQualifications;

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public int getInstitutionId() { return institutionId; }
    public void setInstitutionId(int institutionId) { this.institutionId = institutionId; }
    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }
    public String getOutline() { return outline; }
    public void setOutline(String outline) { this.outline = outline; }
    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    public String getDeliveryMethod() { return deliveryMethod; }
    public void setDeliveryMethod(String deliveryMethod) { this.deliveryMethod = deliveryMethod; }
    public double getCompensation() { return compensation; }
    public void setCompensation(double compensation) { this.compensation = compensation; }
    public String getPreferredQualifications() { return preferredQualifications; }
    public void setPreferredQualifications(String preferredQualifications) { this.preferredQualifications = preferredQualifications; }
}

