package com.example.studentmanagement;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    // Получить список всех студентов
    public List<Student> getAllStudents(){
        return students;
    }


    // Добавить нового студента
    public Student addStudent(String firstName, String lastName, int age){
        Student student = new Student(counter.incrementAndGet(), firstName, lastName, age);
        students.add(student);
        return student;
    }

    // Найти студента по id
    public Optional<Student> getStudentById(long id){
        return students.stream().filter(student -> student.getId() == id).findFirst();
    }

    // Удалить студента по id
    public boolean removeStudent(long id){
        return students.removeIf(student -> student.getId() == id);
    }

    // Очистить список студентов
    public void clearStudents(){
        students.clear();
    }
}
