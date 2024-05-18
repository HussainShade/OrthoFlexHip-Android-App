package com.example.orthoflexhip.dataClass;

public class PatientListRecyclerData {
    private String id;
    private String name;
    private String problem;
    private String admitted_date;
    private String profile_photo;

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getProblem() { return problem; }
    public void setProblem(String value) { this.problem = value; }

    public String getAdmittedDate() { return admitted_date; }
    public void setAdmittedDate(String value) { this.admitted_date = value; }

    public String getProfilePhoto() { return profile_photo; }
    public void setProfilePhoto(String value) { this.profile_photo = value; }
}
