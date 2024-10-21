package com.example.studentmanagement;

public class StudentAddedEvent {
    private final Student student;

    public StudentAddedEvent(Student student){
        this.student = student;
    }

    public Student getStudent(){
        return student;
    }
}
