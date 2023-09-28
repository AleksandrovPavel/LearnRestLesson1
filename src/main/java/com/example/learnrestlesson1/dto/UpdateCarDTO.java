package com.example.learnrestlesson1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UpdateCarDTO {

    @NotEmpty(message = "Название автомобиля не должено быть пустым")
    @Size(min = 4, message = "Название автомобиля должно быть минимум 4 симола")
    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
