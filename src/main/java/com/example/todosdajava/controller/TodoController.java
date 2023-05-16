package com.example.todosdajava.controllers;

import com.example.todosdajava.exceptions.TodoNotFoundException;
import com.example.todosdajava.requests.TodoCreateRequest;
import com.example.todosdajava.requests.TodoUpdateRequest;
import com.example.todosdajava.responses.ErrorResponse;
import com.example.todosdajava.services.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public ResponseEntity<Object> todos() {
        var todos = this.todoService.getAll();
        return ResponseEntity.ok().body(todos);
    }

    @PatchMapping("/todos")
    public ResponseEntity<Object> update(@RequestBody @Valid TodoUpdateRequest todo) throws TodoNotFoundException {
        var toUpdate = this.todoService.update(todo);
        return ResponseEntity.ok().body(toUpdate);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) throws TodoNotFoundException {
        this.todoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/todos/@")
    public ResponseEntity<Object> todo(@PathVariable long id) throws Exception {
        var todo = this.todoService.get(id);
        return ResponseEntity.ok().body(todo);
    }

    // parameter pollution

    @PostMapping("/todos")
    public ResponseEntity<Object> create(@RequestBody @Valid TodoCreateRequest todoCreateRequest) {
        var newTodo = this.todoService.create(todoCreateRequest);

        return ResponseEntity.ok().body(newTodo);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        "Something went wrong",
                        "INTERNAL_SERVER_ERROR",
                        HttpStatus.INTERNAL_SERVER_ERROR.value()
                ));
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTodoNotException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        e.getMessage(),
                        "TODO_NOT_FOUND",
                        HttpStatus.NOT_FOUND.value()
                ));
    }
}

// CRUD
// CREATE, READ, DELETE, UPDATE

// GET, POST, DELETE, PUT, PATCH

// GET - ziskavani dat do tabulky, detaily todo, faktura atd.
// POST - prihlasovani, registrace, vytvareni novych dat
// DELETE - mazani
// PUT a PATCH - uprava dat

// GET nema body
// POST body ma

// kosik.cz?firstName=tomas&lastName=Svojanovsky
// query params

// kosik.cz/api/v1/maso/1
// kosik.cz/api/v1/maso/2
// path params

// my.page/api/v1/users/1/todos

// [GET] my.page/api/v1/users - ziskavani vsechn uzivatelu
// [POST] my.page/api/v1/users - zakladani uzivatele
// [DELETE] my.page/api/v1/users/1 - smazani uzivatele s id 1
// [PATCH] my.page/api/v1/users - uprava uzivatele - to zalezi na body

// [POST] my.page/api/v1/users

// kosik.cz/api/v1/login  - PRihlaseni
// kosik.cz/api/v1/ovoce  - Sekce ovoce
// kosik.cz/api/v2/ovoce  - Sekce ovoce
// kosik.cz/api/v1/maso   - Sekce maso

// api/v1/ovoce endpoint, resource
// unikatni