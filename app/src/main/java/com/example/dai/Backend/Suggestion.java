package com.example.dai.Backend;

public class Suggestion {
    private int id_suggestion;
    private String comment;

    public Suggestion() {}

    public Suggestion(int id_suggestion, String comment) {
        this.id_suggestion = id_suggestion;
        this.comment = comment;
    }

    public int getId_suggestion() {
        return id_suggestion;
    }

    public void setId_suggestion(int id_suggestion) {
        this.id_suggestion = id_suggestion;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Suggestion: " +
                " id = " + id_suggestion +
                ", comment = " + comment;
    }
}
