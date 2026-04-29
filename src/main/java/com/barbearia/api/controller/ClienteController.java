package com.barbearia.api.controller;

import com.barbearia.api.dto.ClienteRequestDTO;
import com.barbearia.api.dto.ClienteResponseDTO;
import com.barbearia.api.dto.ClienteUpdateDTO;
import com.barbearia.api.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor // Injeção de dependência moderna pelo construtor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody @Valid ClienteRequestDTO dto) {
        // Recebe os dados de entrada e chama o serviço
        ClienteResponseDTO response = service.salvarNovo(dto);

        // Retorna 201 Created com o objeto de resposta
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar(){
        List<ClienteResponseDTO> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscar(@PathVariable Long id){
        ClienteResponseDTO response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteUpdateDTO dto){
        ClienteResponseDTO response = service.ataualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
