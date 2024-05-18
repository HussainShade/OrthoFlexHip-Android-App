package com.example.orthoflexhip.dataClass;

public class DoctorProfileData {
    private boolean success;
    private DoctorProfileDetailsData data;

    public boolean getSuccess() { return success; }
    public void setSuccess(boolean value) { this.success = value; }

    public DoctorProfileDetailsData getData() { return data; }
    public void setData(DoctorProfileDetailsData value) { this.data = value; }
}

