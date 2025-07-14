package com.example.Example.controllers;

import com.example.Example.services.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class JokeController {
    private final APIService apiService;

    @Autowired
    public JokeController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/joke")
    public ResponseEntity<Object> getJoke(){
        System.out.println(apiService.getJoke());
        return apiService.getJoke();
    }

}
