package com.example.studentmanagement;

public class StudentRemovedEvent {
    private final long studentId;

    public StudentRemovedEvent(long studentId){
        this.studentId = studentId;
    }

    public long getStudentId(){
        return studentId;
    }
}
