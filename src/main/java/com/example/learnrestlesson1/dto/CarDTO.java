package com.example.learnrestlesson1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CarDTO {

    private Long id;
    @NotEmpty(message = "Название автомобиля не должено быть пустым")
    @Size(min = 3, message = "Название автомобиля должно быть минимум 3 симола")
    private String model;

    @NotEmpty(message = "Серийный номер автомобиля не должен быть пустым")
    @Size(min = 6, message = "Серийный номер автомобиля должен быть минимум 6 символов")
    private String series;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
}
