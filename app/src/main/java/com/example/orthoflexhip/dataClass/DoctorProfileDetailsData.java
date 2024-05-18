package com.example.orthoflexhip.dataClass;

import java.time.LocalDate;
public class DoctorProfileDetailsData {
    private String id;
    private String username;
    private String name;
    private String profile_photo;
    private String age;
    private String gender;
    private String dob;
    private String mobile;

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getUsername() { return username; }
    public void setUsername(String value) { this.username = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getProfilePhoto() { return profile_photo; }
    public void setProfilePhoto(String value) { this.profile_photo = value; }

    public String getAge() { return age; }
    public void setAge(String value) { this.age = value; }

    public String getGender() { return gender; }
    public void setGender(String value) { this.gender = value; }

    public String getDob() { return dob; }
    public void setDob(String value) { this.dob = value; }

    public String getMobile() { return mobile; }
    public void setMobile(String value) { this.mobile = value; }
}
