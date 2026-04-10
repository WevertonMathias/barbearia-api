package com.barbearia.api.service;

import com.barbearia.api.model.Cliente;
import com.barbearia.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<Cliente> listarTodos(){
        return repository.findAll();
    }

    public Cliente salvarNovo(Cliente cliente){
        return repository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente dadosAtualizados){
        Cliente clienteExistente = repository.findById(id).
                orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        clienteExistente.setNome(dadosAtualizados.getNome());
        clienteExistente.setTelefone(dadosAtualizados.getTelefone());
        clienteExistente.setEmail(dadosAtualizados.getEmail());
        clienteExistente.setObservacoes(dadosAtualizados.getObservacoes());
        return repository.save(clienteExistente);
    }

    public void deletar(Long id){
        Cliente cliente = repository.findById(id).
                orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        repository.delete(cliente);
    }
}
