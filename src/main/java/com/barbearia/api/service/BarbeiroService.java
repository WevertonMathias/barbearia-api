package com.barbearia.api.service;
import com.barbearia.api.dto.BarbeiroResponseDTO;
import com.barbearia.api.dto.BarbeiroResquestDTO;
import com.barbearia.api.model.Barbeiro;
import com.barbearia.api.repository.BarbeiroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BarbeiroService {


    private final BarbeiroRepository repository;

    @Transactional
    public BarbeiroResponseDTO cadastrar (BarbeiroResquestDTO dados) {
        var barbeiro = new Barbeiro();
        barbeiro.setNome(dados.nome());
        barbeiro.setPercentualComissao(dados.percentualComissao());

         repository.save(barbeiro);
         return new BarbeiroResponseDTO(barbeiro);
    }

    public List<BarbeiroResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(BarbeiroResponseDTO::new).toList();
    }

    public BarbeiroResponseDTO buscarPorId(Long id){
        var barbeiro = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return new BarbeiroResponseDTO(barbeiro);
    }

    @Transactional
    public void excluir(Long id){
        if (!repository.existsById(id)){
            throw new EntityNotFoundException();
        }
        repository.deleteById(id);
    }
}