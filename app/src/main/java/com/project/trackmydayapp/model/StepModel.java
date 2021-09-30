package com.project.trackmydayapp.model;

    public class StepModel {
    Integer stepId,userId,steps;
    String date;

    public StepModel(Integer stepId, Integer userId, Integer steps, String date) {
        this.stepId = stepId;
        this.userId = userId;
        this.steps = steps;
        this.date = date;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
