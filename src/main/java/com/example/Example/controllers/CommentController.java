package com.example.Example.controllers;

import com.example.Example.services.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private final APIService apiService;

    @Autowired
    public CommentController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/comment")
    public ResponseEntity<Object> getComment(){
        return apiService.getComment();
    }

    @GetMapping("/emails")
    public ResponseEntity<Object> getEmails(){
        return apiService.getCommentEmails();
    }
}
