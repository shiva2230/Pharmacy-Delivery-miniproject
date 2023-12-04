package com.tkmce.pharmacyDelivery.controller;

import com.tkmce.pharmacyDelivery.dto.LoginDto;
import com.tkmce.pharmacyDelivery.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacy")
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService=loginService;
    }

    @PostMapping
    public ResponseEntity<LoginDto> createLogin(@RequestBody LoginDto loginDto){
        LoginDto savedLogin =loginService.createLogin(loginDto);
        return new ResponseEntity<>(savedLogin, HttpStatus.CREATED);
    }

    @GetMapping("hello")
    public String Hello(){
        return "Hello";
    }

    @GetMapping("getAll")
    public ResponseEntity<List<LoginDto>> getAllLogins(){
        List<LoginDto> loginDtos=loginService.getAllLogins();
        return ResponseEntity.ok(loginDtos);
    }
}
