package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        System.out.println("테스트 호출");
        return new ResponseEntity<>("다른 서버임", HttpStatus.OK);
    }

    @GetMapping("/fail")
    public ResponseEntity<String> fail() {
        System.out.println("실패 호출");
        return new ResponseEntity<>("실패하는 다른 서버임", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sleep")
    public ResponseEntity<String> sleep() {
        System.out.println("지연 호출");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {}

        return new ResponseEntity<>("지연되는 다른 서버임", HttpStatus.OK);
    }
}
