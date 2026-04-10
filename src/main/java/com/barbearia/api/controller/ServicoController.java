package com.barbearia.api.controller;

import com.barbearia.api.model.Servico;
import com.barbearia.api.repository.ServicoRepository;
import com.barbearia.api.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @GetMapping
    public List<Servico> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Servico criar(@RequestBody Servico servico) {
        return service.salvar(servico);
    }

    @PutMapping("/{id}")
    public Servico atualizar(@PathVariable Long id, @RequestBody Servico servico) {
        return service.atualizar(id, servico);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.deletar(id);
    }

}
