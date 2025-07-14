package com.example.Example.services;

import com.example.Example.dtos.Comment;
import com.example.Example.dtos.JokeDto;
import com.example.Example.dtos.TodosDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.Random;

@Slf4j
@Service
public class APIService {
    private final RestTemplate restTemplate;

    @Autowired
    public APIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Object> getJoke() {
        String url = "https://official-joke-api.appspot.com/random_joke";
        try {
            return ResponseEntity.ok(restTemplate.getForObject(url, JokeDto.class));
        } catch (RestClientException e) {
            log.error("Error calling external API at URL: {}", url);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<Object> getTodo() {
        String url = "https://jsonplaceholder.typicode.com/todos/1";
        try {
            return ResponseEntity.ok(restTemplate.getForObject(url, TodosDto.class));
        } catch (RestClientException e) {
            log.error("Error calling external API at URL: {}", url);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<Object> getTodoTitle(){
        String url = "https://jsonplaceholder.typicode.com/todos/2";
        try {
            TodosDto title = restTemplate.getForObject(url, TodosDto.class);
            if (title == null){
                throw new RuntimeException("Title not found");
            }
            return ResponseEntity.ok(title.getTitle());
        }
        catch (RestClientException e){
            log.error("Error calling external API at URL: {}", url);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    public ResponseEntity<Object> getComment(){
        Random random = new Random();
        String url = "https://jsonplaceholder.typicode.com/comments/" + random.nextInt(1, 10);
        try {
            return ResponseEntity.ok(restTemplate.getForObject(url, Comment.class));
        }
        catch (RestClientException e){
            log.error("Error calling external API at URL: {}", url);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<Object> getCommentEmails() {
        String url = "https://jsonplaceholder.typicode.com/comments";
        try {
            Comment[] commentList = restTemplate.getForObject(url, Comment[].class);
            if (commentList == null) {
                throw new RuntimeException("Comment list is empty!");
            }
            String[] emailList = new String[commentList.length];
            for (int i = 0; i < commentList.length; i++) {
                emailList[i] = commentList[i].getEmail();
            }
            return ResponseEntity.ok(emailList);
        }
        catch (RestClientException e){
            log.error("Error calling external API at URL: {}", url);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

