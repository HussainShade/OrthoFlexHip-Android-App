package com.example.orthoflexhip.dataClass;

public class PatientMedicationListData {
    private long id;
    private long patientID;
    private String antibiotics;
    private String analgesics;
    private String antacids;
    private String anti_edema_drugs;
    private String tromboprophylaxis;
    private String supportive_drugs;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public long getPatientID() { return patientID; }
    public void setPatientID(long value) { this.patientID = value; }

    public String getAntibiotics() { return antibiotics; }
    public void setAntibiotics(String value) { this.antibiotics = value; }

    public String getAnalgesics() { return analgesics; }
    public void setAnalgesics(String value) { this.analgesics = value; }

    public String getAntacids() { return antacids; }
    public void setAntacids(String value) { this.antacids = value; }

    public String getAntiEdemaDrugs() { return anti_edema_drugs; }
    public void setAntiEdemaDrugs(String value) { this.anti_edema_drugs = value; }

    public String getTromboprophylaxis() { return tromboprophylaxis; }
    public void setTromboprophylaxis(String value) { this.tromboprophylaxis = value; }

    public String getSupportiveDrugs() { return supportive_drugs; }
    public void setSupportiveDrugs(String value) { this.supportive_drugs = value; }
}
