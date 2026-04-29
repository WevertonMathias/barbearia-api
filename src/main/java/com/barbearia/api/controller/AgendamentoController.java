package com.barbearia.api.controller;

import com.barbearia.api.dto.AgendamentoRequestDTO;
import com.barbearia.api.dto.AgendamentoResponseDTO;
import com.barbearia.api.dto.RelatorioComissaoDTO;
import com.barbearia.api.service.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService service;

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> agendar(@RequestBody @Valid AgendamentoRequestDTO dados) {
        return ResponseEntity.ok(service.agendar(dados));
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<AgendamentoResponseDTO> concluir(@PathVariable Long id) {
        return ResponseEntity.ok(service.concluirAgendamento(id));
    }

    @GetMapping("/relatorio")
    public ResponseEntity<RelatorioComissaoDTO> relatorio(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(service.gerarRelatorioDeComissaoPorData(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
