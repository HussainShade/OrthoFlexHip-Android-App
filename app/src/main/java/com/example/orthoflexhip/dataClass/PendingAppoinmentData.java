package com.example.orthoflexhip.dataClass;

public class PendingAppoinmentData {
    private boolean success;
    private PendingAppoinmentRecyclerData[] data;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean getSuccess() { return success; }
    public void setSuccess(boolean value) { this.success = value; }

    public PendingAppoinmentRecyclerData[] getData() { return data; }
    public void setData(PendingAppoinmentRecyclerData[] value) { this.data = value; }
}
