package com.example.learnrestlesson1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CarDTO {

    @NotEmpty(message = "Название автомобиля не должено быть пустым")
    @Size(min = 4, message = "Название автомобиля должно быть минимум 4 симола")
    private String model;

    @NotEmpty(message = "Серийный номер автомобиля не должен быть пустым")
    @Size(min = 7, message = "Серийный номер автомобиля должен быть минимум 7 символов")
    private String series;
}
