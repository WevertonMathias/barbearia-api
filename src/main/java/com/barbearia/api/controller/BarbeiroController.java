package com.barbearia.api.controller;
import com.barbearia.api.model.Barbeiro;
import com.barbearia.api.service.BarbeiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiro")
public class BarbeiroController {

    @Autowired
    private BarbeiroService service;

    @PostMapping
    public Barbeiro criar(@RequestBody Barbeiro barbeiro) {
        return service.salvar(barbeiro);
    }

    @GetMapping
    public List<Barbeiro> listar() {
        return service.listarTodos();
    }
}
