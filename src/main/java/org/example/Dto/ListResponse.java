package org.example.Dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ListResponse<T> {
    private final int totalCount;
    private final List<T> list;

    public ListResponse(List<T> list){
        this.list = list;
        this.totalCount = list.size();
    }
}
