package com.example.FirebaseUi.Model;

public class User {
    private String name;
    private String fName;
    private int age;
    public User() {
        //empty constructor needed
    }

    public User(String name, String fName, int age) {
        this.name = name;
        this.fName = fName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getfName() {
        return fName;
    }

    public int getAge() {
        return age;
    }
}
