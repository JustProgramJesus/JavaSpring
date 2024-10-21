package com.example.studentmanagement;

public class Student {
    private long id;
    private String firstName;
    private String lastName;
    private int age;

    public Student(){

    }

    public Student(long id, String firstName, String lastName, int age){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    @Override
    public String toString(){
        return String.format("Student[id=%d, firstName='%s', lastName='%s', age=%d]", id, firstName, lastName, age);
    }
}
