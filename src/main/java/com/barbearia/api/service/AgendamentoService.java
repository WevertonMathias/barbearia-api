package com.barbearia.api.service;

import com.barbearia.api.model.Agendamento;
import com.barbearia.api.model.Barbeiro;
import com.barbearia.api.model.Cliente;
import com.barbearia.api.repository.AgendamentoRepository;
import com.barbearia.api.repository.BarbeiroRepository;
import com.barbearia.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    // Método para salvar (Criação e atualização)
    public Agendamento salvar(Agendamento agendamento) {
        // 1. Valida se o cliente existe no banco
        Cliente cliente = clienteRepository.findById(agendamento.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + agendamento.getCliente().getId()));

        // 2. Valida se o barbeiro existe no banco
        Barbeiro barbeiro = barbeiroRepository.findById(agendamento.getBarbeiro().getId())
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado com o ID: " + agendamento.getBarbeiro().getId()));

        // 3. Atribui os objetos recuperados do banco ao agendamento
        agendamento.setCliente(cliente);
        agendamento.setBarbeiro(barbeiro);

        // 4. Salva no banco de dados
        return repository.save(agendamento);
    }

    // Método para listar todos os agendamentos
    public List<Agendamento> listarTodos() {
        return repository.findAll();
    }

    // Método para buscar por ID
    public Agendamento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    // Método para deletar
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}