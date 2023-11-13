package org.example.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListResponse<T> {
    private int totalCount;
    private List<T> list;
    private boolean error;
    private String message;

    public ListResponse(List<T> list, int totalCount){
        this.list = list;
        this.totalCount = totalCount;
        this.error = false;
        this.message = null;
    }

    // Конструктор для ответа с ошибкой
    public ListResponse(boolean error, String message) {
        this.list = null;
        this.totalCount = 0;
        this.error = error;
        this.message = message;
    }
}
