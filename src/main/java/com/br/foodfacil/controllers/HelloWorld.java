package com.br.foodfacil.controllers;

import com.br.foodfacil.utils.AppUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppUtils.baseUrl + "/hello")
public class HelloWorld {

    @GetMapping
    ResponseEntity<Object> hello(){
        return ResponseEntity.ok().body("hello world");
    }
}
