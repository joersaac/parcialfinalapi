package com.grupo16.src.models.dtos;

public class TokenDTO {

    private String username;

    private String token;

    public TokenDTO() {
        super();
    }

    public TokenDTO(String token, String username) {
        super();
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
