package com.example.orthoflexhip.dataClass;

public class PatientListData {
    private boolean status;
    private PatientListRecyclerData[] data;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean value) {
        this.status = value;
    }

    public PatientListRecyclerData[] getData() {
        return data;
    }

    public void setData(PatientListRecyclerData[] value) {
        this.data = value;
    }
}
