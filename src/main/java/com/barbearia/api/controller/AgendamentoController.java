package com.barbearia.api.controller;

import com.barbearia.api.model.Agendamento;
import com.barbearia.api.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping
    public Agendamento criar(@RequestBody Agendamento agendamento){
        return service.salvar(agendamento);
    }

    public List<Agendamento> Listar(){
        return service.listarTodos();
    }

    @GetMapping("/data/{data}")
    public List<Agendamento> listarPorData(@PathVariable LocalDate data){
        return service.buscarPorData(data);
    }

    @GetMapping("/barabeiro/{id}")
    public List<Agendamento> listarPorBarbeiro(@PathVariable Long id){
        return service.buscarPorBarbeiro(id);
    }

}
