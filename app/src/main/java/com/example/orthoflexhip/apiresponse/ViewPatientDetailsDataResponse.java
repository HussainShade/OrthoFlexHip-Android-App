package com.example.orthoflexhip.apiresponse;

public class ViewPatientDetailsDataResponse {
    private boolean success;
    private Data data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        private String id;
        private String username;
        private String password;
        private String name;
        private String age;
        private String gender;
        private String mobile;
        private String hospital_id;
        private String height;
        private String weight;
        private String problem;
        private String admitted_date;
        private String discharge_date;
        private String feedback;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getHospitalID() {
            return hospital_id;
        }

        public void setHospitalID(String hospitalID) {
            this.hospital_id = hospitalID;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getProblem() {
            return problem;
        }

        public void setProblem(String problem) {
            this.problem = problem;
        }

        public String getAdmittedDate() {
            return admitted_date;
        }

        public void setAdmittedDate(String admittedDate) {
            this.admitted_date = admittedDate;
        }

        public String getDischargeDate() {
            return discharge_date;
        }

        public void setDischargeDate(String dischargeDate) {
            this.discharge_date = dischargeDate;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getScoreResult() {
            return score_result;
        }

        public void setScoreResult(String scoreResult) {
            this.score_result = scoreResult;
        }

        private String score;
        private String score_result;
    }
}
