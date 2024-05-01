package com.br.foodfacil.controllers;

import com.br.foodfacil.dtos.PedidoRequestEditDto;
import com.br.foodfacil.services.PedidosService;
import com.br.foodfacil.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(AppUtils.baseUrl + "/pedido")
public class PedidosController {
    @Autowired
    PedidosService pedidosService;

    @GetMapping
    ResponseEntity<Object> getAll(){
        return pedidosService.getAll();
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> edita(
            @PathVariable String id,
            @RequestBody PedidoRequestEditDto pedidoRequestEditDto){
        return  pedidosService.edita(pedidoRequestEditDto, id);
    }

}