package com.example.orthoflexhip.dataClass;

public class PendingAppoinmentRecyclerData {
    private String id;
    private String patientID;
    private String reason;
    private String request_date;
    private String schedule_date;
    private String schedule_time;
    private String status;
    private String name;
    private String patient_photo;

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getPatientID() { return patientID; }
    public void setPatientID(String value) { this.patientID = value; }

    public String getReason() { return reason; }
    public void setReason(String value) { this.reason = value; }

    public String getRequestDate() { return request_date; }
    public void setRequestDate(String value) { this.request_date = value; }

    public String getScheduleDate() { return schedule_date; }
    public void setScheduleDate(String value) { this.schedule_date = value; }

    public String getScheduleTime() { return schedule_time; }
    public void setScheduleTime(String value) { this.schedule_time = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getPatientPhoto() { return patient_photo; }
    public void setPatientPhoto(String value) { this.patient_photo = value; }
}
