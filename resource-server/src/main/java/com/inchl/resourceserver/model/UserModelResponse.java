package com.inchl.resourceserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModelResponse<T> {
    private int code;
    private String msg;
    private T data;
    private List<T> dataList;
}
