package com.example.learnrestlesson1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/car")
public class CarController {

    @GetMapping
    public String getAllCars() {
        return "car/all_cars";
    }

    @GetMapping("/{car-id}")
    public String getCarById(@PathVariable("car-id") Long carId, Model model) {
        model.addAttribute("carId", carId);
        return "/car/car_by_id";
    }

    @GetMapping("/{car-id}/update_car")
    public String updateCar(@PathVariable("car-id") Long carId,
                            Model model){
        model.addAttribute("carId", carId);
        return "/car/update_car";
    }
}













