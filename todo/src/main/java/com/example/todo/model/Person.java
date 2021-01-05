package com.example.todo.model;


public class Person{
    private Integer id;
    private String name;

    public Person(Integer id, String name){
        this(name);
        this.id = id;
    }

    public Person(String name){
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}