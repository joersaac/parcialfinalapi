package com.grupo16.src.models.dtos;

public class TokenDTO {
    private String token;

    public TokenDTO() {
        super();
    }

    public TokenDTO(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
