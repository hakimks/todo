package com.hakim.todo.models;

public class Todo {
    private int id;
    private String title;
    private String description;
    private boolean completed;

    public Todo(int id, String title, String description, boolean completed ){
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }
}
