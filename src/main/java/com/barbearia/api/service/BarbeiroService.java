package com.barbearia.api.service;
import com.barbearia.api.model.Barbeiro;
import com.barbearia.api.repository.BarbeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository repository;

    public Barbeiro salvar(Barbeiro barbeiro) {
        return repository.save(barbeiro);
    }

    public List<Barbeiro> listarTodos() {
        return repository.findAll();
    }
}