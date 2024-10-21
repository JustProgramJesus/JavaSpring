package com.example.studentmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class StudentCommands {
    private final StudentService studentService;

    @Autowired
    public StudentCommands(StudentService studentService){
        this.studentService = studentService;
    }

    // Команда для добавления нового студента
    @ShellMethod("Добавить нового студента: add-student <firstName> <lastName> <age>")
    public String addStudent(String firstName, String lastName, int age){
        Student student = studentService.addStudent(firstName, lastName, age);
        return "Студент " + student + " добавлен.";
    }

    // Команда для удаления студента по ID
    @ShellMethod("Удалить студента по ID: remove-student <id>")
    public String removeStudent(long id){
        if (studentService.removeStudent(id)){
            return "Студент с ID: " + id + " - удален!";
        } else {
            return "Студент с ID: " + id + " не был найден";
        }
    }

    // Команда для вывода списка студентов
    @ShellMethod("Показать всех студентов: list-students")
    public List<Student> listStudents(){
        return studentService.getAllStudents();
    }

    // Команда для очистки списка студентов
    @ShellMethod("Очистить список студентов: clear-students")
    public String clearStudents(){
        studentService.clearStudents();
        return "Список студентов был очищен";
    }
}