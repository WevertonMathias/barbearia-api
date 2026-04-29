package com.barbearia.api.controller;

import com.barbearia.api.dto.ServicoResponseDTO;
import com.barbearia.api.model.Servico;
import com.barbearia.api.repository.ServicoRepository;
import com.barbearia.api.service.ServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService service;

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> cadastrar(@RequestBody @Valid ServicoResponseDTO dados, UriComponentsBuilder uriBuilder) {
       var response = service.salvar(dados);
       var uri = uriBuilder.path("/servicos/{id}").buildAndExpand(response.id()).toUri();

       return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ServicoResponseDTO dados) {
        var response = service.atualizar(id, dados);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
