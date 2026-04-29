package com.barbearia.api.service;

import com.barbearia.api.dto.ServicoResponseDTO;
import com.barbearia.api.model.Servico;
import com.barbearia.api.repository.ServicoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository repository;

    @Transactional
    public ServicoResponseDTO salvar(ServicoResponseDTO dados) {
        var servico = new Servico();
        servico.setNome(dados.nome());
        servico.setDescricao(dados.descricao());
        servico.setPreco(dados.preco());
        servico.setTempoEstimado(dados.tempoEstimado());

        repository.save(servico);

        return new ServicoResponseDTO(servico);
    }

    public List<ServicoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(ServicoResponseDTO::new).toList();
    }

    @Transactional
    public ServicoResponseDTO atualizar(Long id, ServicoResponseDTO dados) {
         var servico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));

        servico.setNome(dados.nome());
        servico.setDescricao(dados.descricao());
        servico.setPreco(dados.preco());
        servico.setTempoEstimado(dados.tempoEstimado());

        return new ServicoResponseDTO(servico);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)){
            throw new RuntimeException("Serviço não encontrado!");
        }
        repository.deleteById(id);
    }
}
