package com.example.orthoflexhip.dataClass;

public class PatientXrayImagesData {
    private String id;
    private String pre_xray_image;
    private String post_xray_image;

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getPreXrayImage() { return pre_xray_image; }
    public void setPreXrayImage(String value) { this.pre_xray_image = value; }

    public String getPostXrayImage() { return post_xray_image; }
    public void setPostXrayImage(String value) { this.post_xray_image = value; }
}
