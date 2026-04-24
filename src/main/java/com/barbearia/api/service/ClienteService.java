package com.barbearia.api.service;

import com.barbearia.api.dto.request.ClienteRequestDTO;
import com.barbearia.api.dto.response.ClienteResponseDTO;
import com.barbearia.api.infra.VerificarSeEmailExisteException;
import com.barbearia.api.model.Cliente;
import com.barbearia.api.repository.ClienteRepository;
import com.barbearia.api.service.validador.ClienteServiceValidador;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteServiceValidador validador;

    public List<Cliente> listarTodos(){
        return repository.findAll();
    }

    @Transactional
    public ClienteResponseDTO salvarNovo(ClienteRequestDTO clienteRequestDto){

        validador.verificarSeEmailExiste(clienteRequestDto.email());

        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequestDto.nome());
        cliente.setTelefone(clienteRequestDto.telefone());
        cliente.setEmail(clienteRequestDto.email());
        cliente.setDataNascimento(clienteRequestDto.dataNascimento());
        cliente.setObservacoes(clienteRequestDto.observacoes());

        try{
            repository.save(cliente);
            return new ClienteResponseDTO(
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getTelefone(),
                    cliente.getEmail(),
                    cliente.getDataNascimento(),
                    cliente.getObservacoes(),
                    cliente.getCriadoEm()
            );

        }catch (DataIntegrityViolationException e){
            throw new VerificarSeEmailExisteException();
        }
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
