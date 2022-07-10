package com.grupo16.src.models.dtos;

import javax.validation.constraints.*;

public class BookingDto {

    @NotBlank
    @Size(min = 4, max = 32)
    private String username;

    @Min(value = 1)
    private Long code;

    public BookingDto() {
        super();
    }

    public BookingDto(String username) {
        super();
        this.username = username;
    }

    public BookingDto(String username, Long code) {
        super();
        this.username = username;
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
