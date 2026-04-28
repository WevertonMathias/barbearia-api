package com.barbearia.api.controller;

import com.barbearia.api.dto.AgendamentoResponseDTO;
import com.barbearia.api.dto.RelatorioComissaoDTO;
import com.barbearia.api.model.Agendamento;
import com.barbearia.api.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> listar() {
        List<AgendamentoResponseDTO> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
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

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> criarAgendamento(@RequestBody Agendamento agendamento){
        AgendamentoResponseDTO response = service.agendar(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
