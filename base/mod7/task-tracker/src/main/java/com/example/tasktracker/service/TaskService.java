package com.example.tasktracker.service;

import com.example.tasktracker.model.Task;
import com.example.tasktracker.model.User;
import com.example.tasktracker.repository.TaskRepository;
import com.example.tasktracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Flux<Task> findAllTasks() {
        return taskRepository.findAll()
                .flatMap(this::fillNestedEntities);
    }

    public Mono<Task> findTaskById(String id) {
        return taskRepository.findById(id)
                .flatMap(this::fillNestedEntities);
    }

    public Mono<Task> createTask(Task task) {
        task.setCreatedAt(java.time.Instant.now());
        task.setUpdatedAt(java.time.Instant.now());
        return taskRepository.save(task);
    }

    public Mono<Task> updateTask(String id, Task updatedTask) {
        return taskRepository.findById(id)
                .flatMap(existingTask -> {
                    if (updatedTask.getName() != null) {
                        existingTask.setName(updatedTask.getName());
                    }
                    if (updatedTask.getDescription() != null) {
                        existingTask.setDescription(updatedTask.getDescription());
                    }
                    if (updatedTask.getStatus() != null) {
                        existingTask.setStatus(updatedTask.getStatus());
                    }
                    if (updatedTask.getAuthorId() != null) {
                        existingTask.setAuthorId(updatedTask.getAuthorId());
                    }
                    if (updatedTask.getAssigneeId() != null) {
                        existingTask.setAssigneeId(updatedTask.getAssigneeId());
                    }
                    if (updatedTask.getObserverIds() != null) {
                        existingTask.setObserverIds(updatedTask.getObserverIds());
                    }
                    existingTask.setUpdatedAt(java.time.Instant.now()); // Обновляем дату изменения
                    return taskRepository.save(existingTask);
                });
    }

    public Mono<Void> deleteTask(String id) {
        return taskRepository.deleteById(id);
    }

    public Mono<Task> addObserverToTask(String taskId, String observerId) {
        return taskRepository.findById(taskId)
                .flatMap(task -> {
                    task.getObserverIds().add(observerId);
                    return taskRepository.save(task);
                });
    }

    private Mono<Task> fillNestedEntities(Task task) {
        Mono<User> authorMono = userRepository.findById(task.getAuthorId());
        Mono<User> assigneeMono = userRepository.findById(task.getAssigneeId());
        Flux<User> observersFlux = Flux.fromIterable(task.getObserverIds())
                .flatMap(userRepository::findById);

        return Mono.zip(authorMono, assigneeMono, observersFlux.collectList())
                .map(tuple -> {
                    task.setAuthor(tuple.getT1());
                    task.setAssignee(tuple.getT2());
                    task.setObservers(Set.copyOf(tuple.getT3()));
                    return task;
                });
    }
}