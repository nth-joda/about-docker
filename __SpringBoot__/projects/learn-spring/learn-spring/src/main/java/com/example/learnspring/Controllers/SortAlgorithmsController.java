package com.example.learnspring.Controllers;

import com.example.learnspring.Enums.AlgorithmName;
import com.example.learnspring.Requests.SortRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sort-algorithms")
public class SortAlgorithmsController {
    @PostMapping("/sort")
    public ResponseEntity sort(@RequestBody SortRequest request){
        return new ResponseEntity<>(
                request,
                HttpStatus.OK);
    }
}
