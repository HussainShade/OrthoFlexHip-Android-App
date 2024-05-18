package com.example.orthoflexhip.dataClass;

import com.google.gson.annotations.SerializedName;

public class TrackAppointmentRecyclerData {
    @SerializedName("id")
    String id;

    @SerializedName("patient_id")
    String patientId;

    @SerializedName("doctor_id")
    String doctorId;

    @SerializedName("schedule_date")
    String scheduleDate;

    @SerializedName("schedule_time")
    String scheduleTime;

    @SerializedName("reason")
    String reason;

    @SerializedName("request_date")
    String requestDate;

    @SerializedName("status")
    String status;


    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public String getPatientId() {
        return patientId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    public String getDoctorId() {
        return doctorId;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
    public String getRequestDate() {
        return requestDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
