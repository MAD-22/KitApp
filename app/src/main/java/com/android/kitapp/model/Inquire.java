package com.android.kitapp.model;

public class Inquire {
    private String key;
    private String name;
    private String type;
    private String user;
    private String date;
    private String about;
    private String reply;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Inquire() {

    }

    public Inquire(String name, String type, String user, String date, String about) {
        this.name = name;
        this.type = type;
        this.user = user;
        this.date = date;
        this.about = about;
    }
}
