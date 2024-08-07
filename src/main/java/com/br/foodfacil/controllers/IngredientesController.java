package com.br.foodfacil.controllers;

import com.br.foodfacil.dtos.SaborRequestDto;
import com.br.foodfacil.services.impl.IngredienteServiceImpl;
import com.br.foodfacil.utils.AppUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = {"http://localhost:5173",
        "https://food-facil-painel-admin-8mcqkbvbs-eliezerbrasilians-projects.vercel.app",
        "https://food-facil-painel-admin.vercel.app," +
                "https://foodfacil-website.vercel.app/"})
@RestController
@RequestMapping(AppUtils.baseUrl + "/ingrediente")

public class IngredientesController {
    @Autowired
    IngredienteServiceImpl ingredienteServiceImpl;

    @PostMapping
    ResponseEntity<Object> cadastraIngrediente(@RequestBody SaborRequestDto saborRequestDto){

        System.out.println(saborRequestDto);
        return ingredienteServiceImpl.registra(saborRequestDto);
    }

    @GetMapping
    ResponseEntity<Object> getAllIngredientes(){
        return ingredienteServiceImpl.getAll();
    }
}
