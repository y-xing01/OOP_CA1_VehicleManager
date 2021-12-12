package org.example;

import java.time.LocalDateTime;

public class Email {

    private int id;
    private String to;
    private String subject;
    private String text;
    private LocalDateTime date;

    public Email(int id,String to, String subject, String text, LocalDateTime date) {
        this.id = id;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
