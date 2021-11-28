package com.challenge.getir.model;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int code;
    private String url;
}
