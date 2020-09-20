package com.james.LifeTracker.dto.binding.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserServiceModel {

    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2,max = 15, message = "Name length must be between 2 and 15 characters")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 3,max = 15, message = "Surname length must be between 3 and 15 characters")
    private String surname;

    @Email(message = "Please enter a valid email address")
    private String email;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
