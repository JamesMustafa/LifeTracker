package com.james.LifeTracker.dto.binding.auth;

import com.james.LifeTracker.validation.PasswordValidator;

import javax.validation.constraints.*;

@PasswordValidator(pass = "password",
        confPass = "confirmPassword",
        message = "The passwords do not match")
public class UserAddBindingModel {

    @NotBlank
    @Size(min = 3,max = 20, message = "Password length must be between 3 and 20 characters")
    private String password;

    @NotBlank
    @Size(min = 3,max = 20, message = "Password length must be between 3 and 20 characters")
    private String confirmPassword;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2,max = 15, message = "Name length must be between 2 and 15 characters")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 3,max = 15, message = "Surname length must be between 3 and 15 characters")
    private String surname;

    @Email(message = "Please enter a valid email address")
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
