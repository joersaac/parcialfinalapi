package com.grupo16.src.models.dtos;

public class MessageDto {
    private String message;

    public MessageDto(String message) {
        super();
        this.message = message;
    }

    public MessageDto() {
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
