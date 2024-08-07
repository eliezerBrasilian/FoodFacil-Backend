package com.br.foodfacil.controllers;

import com.br.foodfacil.dtos.CupomDto;
import com.br.foodfacil.services.impl.CupomServiceImpl;
import com.br.foodfacil.utils.AppUtils;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:5173",
        "https://food-facil-painel-admin-8mcqkbvbs-eliezerbrasilians-projects.vercel.app",
        "https://food-facil-painel-admin.vercel.app" +
                "https://foodfacil-website.vercel.app/"})
@RestController
@RequestMapping(AppUtils.baseUrl + "/cupom")

public class CupomController {

    @Autowired
    CupomServiceImpl cupomServiceImpl;

    @PostMapping
    ResponseEntity<Object> registerCupom(@RequestBody CupomDto cupomDto){
        return cupomServiceImpl.registra(cupomDto);
    }

    @GetMapping("{cupomId}")
    ResponseEntity<Object> findCupom(@PathVariable String cupomId){
        return cupomServiceImpl.buscaCupom(cupomId);
    }

    @GetMapping()
    ResponseEntity<Object> getAll(){
        return cupomServiceImpl.getAll();
    }
}
