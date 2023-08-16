package com.example.finalproject.model;

public class FeedbackModel {

    private int id;

    private int userId;

    private String subject;

    private String content;

    public FeedbackModel(int id, int userId, String subject, String content) {
        this.id = id;
        this.userId = userId;
        this.subject = subject;
        this.content = content;
    }

    public FeedbackModel(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FeedbackModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
