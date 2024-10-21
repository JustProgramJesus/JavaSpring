package com.example.studentmanagement;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StudentInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final StudentService studentService;

    public StudentInitializer(StudentService studentService){
        this.studentService = studentService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event){
        studentService.addStudent("Сергей", "Мамаев", 20);
        studentService.addStudent("Алексей", "Синицин", 31);
        studentService.addStudent("Виктор", "Андреев", 23);
        studentService.addStudent("Дмитрий", "Воробьев", 21);
        System.out.println("Инициализация студентов завершена.");
    }
}
