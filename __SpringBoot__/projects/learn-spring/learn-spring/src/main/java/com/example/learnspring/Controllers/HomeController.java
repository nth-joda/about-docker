package com.example.learnspring.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/home")
@RestController
public class HomeController {
    @GetMapping("/test")
    public ResponseEntity testApp() {
        return new ResponseEntity<>(
                "BE working",
                HttpStatus.OK);
    }
}
