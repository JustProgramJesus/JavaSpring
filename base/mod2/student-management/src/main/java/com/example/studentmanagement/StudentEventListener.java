package com.example.studentmanagement;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEventListener {

    // Обработчик события создания студента
    @EventListener
    public void handleStudentAdded(StudentAddedEvent event){
        System.out.println("Событие: Студент добавлен - " + event.getStudent());
    }

    // Обработчик события удаления студента
    @EventListener
    public void handleStudentRemoved(StudentRemovedEvent event){
        System.out.println("Собтие: Студент с ID " + event.getStudentId() + " удален");
    }
}
