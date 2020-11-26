package com.programato.models;

import javax.validation.constraints.NotBlank;


public class LoginModel {
    @NotBlank(message = "Email must not blank")
    private String email;
    @NotBlank(message = "Password must not blank")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
