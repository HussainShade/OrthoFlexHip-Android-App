package com.example.orthoflexhip.dataClass;

public class PatientMedicationData {
    private boolean success;
    private PatientMedicationListData data;

    public boolean getSuccess() { return success; }
    public void setSuccess(boolean value) { this.success = value; }

    public PatientMedicationListData getData() { return data; }
    public void setData(PatientMedicationListData value) { this.data = value; }
}
