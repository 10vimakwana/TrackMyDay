package com.project.trackmydayapp.model;

public class FeedbackModel {
    int userId,feedbackId;
    String title,feedback,reply;
    int isadmin,issend;

    public FeedbackModel(int userId, int feedbackId, String title, String feedback, String reply, int isadmin, int issend) {
        this.userId = userId;
        this.feedbackId = feedbackId;
        this.title = title;
        this.feedback = feedback;
        this.reply = reply;
        this.isadmin = isadmin;
        this.issend = issend;
    }

    public int getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(int isadmin) {
        this.isadmin = isadmin;
    }

    public int getIssend() {
        return issend;
    }

    public void setIssend(int issend) {
        this.issend = issend;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
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
