package com.grupo16.src.models.dtos;

import javax.validation.constraints.NotBlank;

public class LoginDTO {
    @NotBlank
    private String identifier;

    @NotBlank
    private String password;

    public LoginDTO() {
        super();
    }

    public LoginDTO(String identifier, String password) {
        super();
        this.identifier = identifier;
        this.password = password;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
