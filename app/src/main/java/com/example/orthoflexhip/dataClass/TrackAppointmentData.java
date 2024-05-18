package com.example.orthoflexhip.dataClass;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TrackAppointmentData {

    @SerializedName("success")
    boolean success;

    @SerializedName("data")
    ArrayList<TrackAppointmentRecyclerData> data;


    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean getSuccess() {
        return success;
    }

    public void setData(ArrayList<TrackAppointmentRecyclerData> data) {
        this.data = data;
    }
    public List<TrackAppointmentRecyclerData> getData() {
        return data;
    }
}
