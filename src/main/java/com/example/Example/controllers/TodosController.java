package com.example.Example.controllers;

import com.example.Example.services.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TodosController {
    private final APIService apiService;

    @Autowired
    public TodosController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/todos")
    public ResponseEntity<Object> getTodo(){
        return apiService.getTodo();
    }

    @GetMapping("/todosTitle")
    public ResponseEntity<Object> getTodoTitle(){
        return apiService.getTodoTitle();
    }
}
