package com.gingermadfire.persistense;

public class Student {

    private final long id;

    private String name;

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Студент по имени: " + name + " с id: " + id;
    }
}
