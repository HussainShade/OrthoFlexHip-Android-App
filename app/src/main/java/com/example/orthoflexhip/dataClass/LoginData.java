package com.example.orthoflexhip.dataClass;

public class LoginData {
    private boolean success;
    private String message;
    private long id;

    public boolean getSuccess() { return success; }
    public void setSuccess(boolean value) { this.success = value; }

    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }
}
