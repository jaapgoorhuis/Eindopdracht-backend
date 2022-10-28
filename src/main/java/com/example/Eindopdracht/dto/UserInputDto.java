package com.example.Eindopdracht.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserInputDto {
    @NotEmpty
    public String firstname;

    @NotEmpty
    public String lastname;

    @NotEmpty
    @Email
    public String email;

    @NotEmpty
    public String username;

    @NotEmpty
    @Size(min=6, max=20)
    public String password;

    @NotEmpty
    public static Long[] roles;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long[] getRoles() {
        return roles;
    }

    public void setRoles(Long[] roles) {
        this.roles = roles;
    }

}
