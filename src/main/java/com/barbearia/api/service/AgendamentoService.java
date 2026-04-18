package com.barbearia.api.service;

import com.barbearia.api.model.Agendamento;
import com.barbearia.api.model.Barbeiro;
import com.barbearia.api.model.Cliente;
import com.barbearia.api.repository.AgendamentoRepository;
import com.barbearia.api.repository.BarbeiroRepository;
import com.barbearia.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

        // 1. Validção de Data (Não permite agendar no passado.)
        if(agendamento.getData().isBefore(LocalDate.now())){
            throw new RuntimeException("Não é possivel agendar para uma data passada.");
        }

        // 2. Busca e validação do Barbeiro e Cliente
        Cliente cliente = clienteRepository.findById(agendamento.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Barbeiro barbeiro = barbeiroRepository.findById(agendamento.getBarbeiro().getId())
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado."));

        // 3. Validação de Conflito: O barbeiro já está ocupado?
        Optional<Agendamento> conflito = repository.findByBarbeiroAndDataAndHoraInicio(
                barbeiro, agendamento.getData(), agendamento.getHoraInicio()
        );

        if(conflito.isPresent()){
            throw new RuntimeException("Este barbeiro já possui um agendamento neste horario!.");
        }

        // 4. Preenche os objetos e salva
        agendamento.setCliente(cliente);
        agendamento.setBarbeiro(barbeiro);

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

     public List<Agendamento> buscarPorData(LocalDate data){
        return repository.findByData(data);
    }

    public List<Agendamento> buscarPorBarbeiro(Long barbeiroId){
        return repository.findByBarbeiroId(barbeiroId);
    }
}