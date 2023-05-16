package com.example.todosdajava.services;

import com.example.todosdajava.entities.TodoEntity;
import com.example.todosdajava.exceptions.TodoNotFoundException;
import com.example.todosdajava.repositories.TodoRepository;
import com.example.todosdajava.requests.TodoCreateRequest;
import com.example.todosdajava.requests.TodoUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoEntity> getAll() {
        return this.todoRepository.findAll();
    }

    public TodoEntity get(long id) throws TodoNotFoundException {
        return this.todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found [%s]".formatted(id)));
    }

    public TodoEntity create(TodoCreateRequest todo) {
        var newTodo = new TodoEntity(todo.getLabel());
        return this.todoRepository.save(newTodo);
    }

    public TodoEntity delete(long id) throws TodoNotFoundException {
        var todoEntity = this.get(id);

        this.todoRepository.delete(todoEntity);

        return todoEntity;
    }

    public TodoEntity update(TodoUpdateRequest todo) throws TodoNotFoundException {
        var todoEntity = this.get(todo.getId());
        todoEntity.setLabel(todo.getLabel());

        return this.todoRepository.save(todoEntity);
    }
}
