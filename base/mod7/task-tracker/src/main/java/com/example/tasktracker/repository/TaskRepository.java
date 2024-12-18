package com.example.tasktracker.repository;

import com.example.tasktracker.model.Task;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TaskRepository extends ReactiveMongoRepository<Task, String> {
    Flux<Task> findAllByAuthorId(String authorId);
}