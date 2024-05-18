package com.example.orthoflexhip.dataClass;

public class PatientDetailData {
    private boolean success;
    private PatientMedicalDetailData data;

    public boolean getSuccess() { return success; }
    public void setSuccess(boolean value) { this.success = value; }

    public PatientMedicalDetailData getData() { return data; }
    public void setData(PatientMedicalDetailData value) { this.data = value; }
}
