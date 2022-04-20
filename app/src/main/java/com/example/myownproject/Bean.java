package com.example.myownproject;

public class Bean {

    String name;
    String text;
    int id;

    public Bean() {
        name="";
        text="";
        id=-5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
