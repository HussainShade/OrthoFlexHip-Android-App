package com.example.orthoflexhip.dataClass;

public class VideoListData {
    private boolean status;
    private VideoListRecyclerData[] data;

    public boolean getStatus() { return status; }
    public void setStatus(boolean value) { this.status = value; }

    public VideoListRecyclerData[] getData() { return data; }
    public void setData(VideoListRecyclerData[] value) { this.data = value; }
}

