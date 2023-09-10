package com.example.learnrestlesson1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PersonDTO {

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 25, message = "Длина имени должна составлять от 2 до 25 символов")
    private String firstName;

    @NotEmpty(message = "Фамилия не должна быть пустой")
    @Size(min = 5, max = 25, message = "Длина фамилии должна составлять от 5 до максимального количества символов")
    private String lastName;

    @Email(message = "Адрес электронной почты нужно ввести корректно")
    @NotEmpty(message = "Адрес электронной почты не должен быть пустым")
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
