package com.example.orthoflexhip.dataClass;

import java.time.LocalDate;

public class RecentPatientRecyclerData {
    private String id;
    private String name;
    private LocalDate admittedDate;
    private String admitted_date;

    public String getAdmitted_date() {
        return admitted_date;
    }

    public void setAdmitted_date(String admitted_date) {
        this.admitted_date = admitted_date;
    }

    private LocalDate dischargeDate;
    private String profilePhoto;

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    private String profile_photo;

    private String status;

    public String getID() {
        return id;
    }

    public void setID(String value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public LocalDate getAdmittedDate() {
        return admittedDate;
    }

    public void setAdmittedDate(LocalDate value) {
        this.admittedDate = value;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate value) {
        this.dischargeDate = value;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String value) {
        this.profilePhoto = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }
}
