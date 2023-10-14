package com.example.learnrestlesson1.dto;

import com.example.learnrestlesson1.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class PersonDTO {

    private Long id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 25, message = "Длина имени должна составлять от 2 до 25 символов")
    private String firstName;

    @NotEmpty(message = "Фамилия не должна быть пустой")
    @Size(min = 5, max = 25, message = "Длина фамилии должна составлять от 5 до 25 символов")
    private String lastName;

    @Email(message = "Адрес электронной почты нужно ввести корректно")
    @NotEmpty(message = "Адрес электронной почты не должен быть пустым")
    private String username;

    @Size(min = 4, message = "Длина пароля должна составлять от 5 символов")
    @NotEmpty(message = "Пароль не должен быть пустым")
    private String password;

    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
