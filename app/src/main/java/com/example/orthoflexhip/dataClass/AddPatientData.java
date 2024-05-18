package com.example.orthoflexhip.dataClass;

public class AddPatientData {
    private boolean status;
    private String data;
    private long patientID;

    public boolean getStatus() { return status; }
    public void setStatus(boolean value) { this.status = value; }

    public String getData() { return data; }
    public void setData(String value) { this.data = value; }

    public long getPatientID() { return patientID; }
    public void setPatientID(long value) { this.patientID = value; }
}
