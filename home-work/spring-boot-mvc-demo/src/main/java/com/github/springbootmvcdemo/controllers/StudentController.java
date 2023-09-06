package com.github.springbootmvcdemo.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Validation section
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/v1/students")
@Validated
public class StudentController {
    @GetMapping("/{id}")
    // Validation of @PathVariable => url: /v1/students/1
    // public String get(@PathVariable("id") @Positive Integer id){
    public String get(@PathVariable("id") @Positive(message = "Student ID must be greater than 0.") Integer id) {
        return "Method Get on Student Controller";
    }

    @GetMapping()
    // Validation of @PathVariable => url: /v1/students?id=1
    public String getRequestParam(
            @RequestParam("id") @Min(value = 1, message = "Student ID must be greater than 0.") Integer id) {
        return "Method Get on Student Controller" + id;
    }
}
