package com.barbearia.api.controller;
import com.barbearia.api.dto.BarbeiroResponseDTO;
import com.barbearia.api.dto.BarbeiroResquestDTO;
import com.barbearia.api.model.Barbeiro;
import com.barbearia.api.service.BarbeiroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiros")
@RequiredArgsConstructor
public class BarbeiroController {

    private final BarbeiroService service;

    @PostMapping
    public ResponseEntity<BarbeiroResponseDTO> cadastrar(@RequestBody @Valid BarbeiroResquestDTO dados) {
        var response = service.cadastrar(dados);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BarbeiroResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarbeiroResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
