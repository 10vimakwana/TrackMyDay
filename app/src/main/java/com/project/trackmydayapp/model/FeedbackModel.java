package com.project.trackmydayapp.model;

public class FeedbackModel {
    int userId,feedbackId;
    String title,feedback;

    public FeedbackModel(int userId, int feedbackId, String title, String feedback) {
        this.userId = userId;
        this.feedbackId = feedbackId;
        this.title = title;
        this.feedback = feedback;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
