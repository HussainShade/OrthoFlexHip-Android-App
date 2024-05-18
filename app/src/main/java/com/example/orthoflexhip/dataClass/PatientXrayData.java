package com.example.orthoflexhip.dataClass;

public class PatientXrayData {
    private boolean success;
    private PatientXrayImagesData data;

    public boolean getSuccess() { return success; }
    public void setSuccess(boolean value) { this.success = value; }

    public PatientXrayImagesData getData() { return data; }
    public void setData(PatientXrayImagesData value) { this.data = value; }
}
