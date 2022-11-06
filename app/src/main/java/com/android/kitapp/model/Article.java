package com.android.kitapp.model;

public class Article {
    private String key;
    private String name;
    private String publisher;
    private String date;
    private String type;
    private String content;

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article() {

    }

    public Article(String name, String publisher, String date, String type, String content){
        this.name = name;
        this.publisher = publisher;
        this.date = date;
        this.type = type;
        this.content = content;
    }
}
