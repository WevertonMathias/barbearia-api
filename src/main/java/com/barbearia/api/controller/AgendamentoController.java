package com.barbearia.api.controller;

import com.barbearia.api.dto.RelatorioComissaoDTO;
import com.barbearia.api.model.Agendamento;
import com.barbearia.api.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    @PutMapping("/{id}/concluir")
    public Agendamento cocluir(@PathVariable Long id){
        return service.concluiragendamento(id);
    }

    @GetMapping("/concluidos")
    public List<Agendamento> listarConcluidos(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data){
        return service.buscarConcluidosPorData(data);
    }

    @GetMapping("/relatorio-financeiro")
    public RelatorioComissaoDTO obterRelatorio(@RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate data){
        return service.gerarRelatorioPorData(data);
    }

}
