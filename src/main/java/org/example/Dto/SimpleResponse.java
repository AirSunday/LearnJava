package org.example.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SimpleResponse<T> {
    private T result;
    private boolean error;
    private String message;

    // Конструктор для успешного ответа
    public SimpleResponse(T result) {
        this.result = result;
        this.error = false;
        this.message = null;
    }

    // Конструктор для ответа с ошибкой
    public SimpleResponse(boolean error, String message) {
        this.result = null;
        this.error = error;
        this.message = message;
    }
}
