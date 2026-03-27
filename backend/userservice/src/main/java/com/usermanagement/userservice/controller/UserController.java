package com.usermanagement.userservice.controller;

import com.usermanagement.userservice.entity.User;
import com.usermanagement.userservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController @RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(
            @PathVariable int id
    ){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> save(User user){
        return ResponseEntity.ok(userService.save(user));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(
            Exception exception,
            HttpServletRequest request
    ){
        log.debug("Exception occurred with message:{}, for uri:{}", exception.getMessage(), request.getRequestURL());
        return ResponseEntity.internalServerError().build();
    }
}
