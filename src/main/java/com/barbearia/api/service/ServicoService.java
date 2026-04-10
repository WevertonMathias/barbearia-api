package com.barbearia.api.service;

import com.barbearia.api.model.Servico;
import com.barbearia.api.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public List<Servico> listarTodos() {
        return repository.findAll();
    }

    public Servico salvar(Servico servico) {
        return repository.save(servico);
    }

    public Servico atualizar(Long id, Servico dadosNovos) {
        Servico servicoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));

        servicoExistente.setNome(dadosNovos.getNome());
        servicoExistente.setDescricao(dadosNovos.getDescricao());
        servicoExistente.setPreco(dadosNovos.getPreco());
        servicoExistente.setTempoEstimado(dadosNovos.getTempoEstimado());

        return repository.save(servicoExistente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
