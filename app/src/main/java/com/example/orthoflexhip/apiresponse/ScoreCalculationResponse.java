package com.example.orthoflexhip.apiresponse;

public class ScoreCalculationResponse {
    private boolean success;
    private String message;
    private String  score_result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getScore_result() {
        return score_result;
    }

    public void setScore_result(String score_result) {
        this.score_result = score_result;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score;
}
