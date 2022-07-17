package com.example.todoapp.Model;

public class Detail {
    String id;
    String name;
    String task;
    public long createdDate;

    public Detail() {
        //public no-arg constructor needed
    }

    public Detail(String id, String name, String task, long createdDate) {
        this.id = id;
        this.name = name;
        this.task = task;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTask() {
        return task;
    }

    public long getCreatedDate() {
        return createdDate;
    }
}
