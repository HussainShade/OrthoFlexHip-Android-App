package com.example.orthoflexhip.dataClass;

public class PatientProfileDetailsData {
    private String id;
    private String username;
    private String name;
    private String profile_photo;
    private String age;
    private String gender;
    private String mobile;
    private String hospital_id;

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

    public String getMobile() { return mobile; }
    public void setMobile(String value) { this.mobile = value; }

    public String getHospitalID() { return hospital_id; }
    public void setHospitalID(String value) { this.hospital_id = value; }
}
