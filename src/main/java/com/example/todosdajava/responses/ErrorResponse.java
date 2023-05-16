package com.example.todosdajava.responses;

// TODO_NOT_FOUND: "Todo neexistuje %s"

public record ErrorResponse(String message, String code, Integer status) {
}
