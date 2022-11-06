package com.android.kitapp.model;

public class Book {
    private String key;
    private String name;
    private String publisher;
    private String author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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



    public Book() {

    }

    public Book(String name, String publisher, String author, String date, String type, String content){
        this.name = name;
        this.publisher = publisher;
        this.author = author;
        this.date = date;
        this.type = type;
        this.content = content;
    }
}
