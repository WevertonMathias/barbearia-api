package com.barbearia.api.service;

import com.barbearia.api.dto.ClienteRequestDTO;
import com.barbearia.api.dto.ClienteResponseDTO;
import com.barbearia.api.dto.ClienteUpdateDTO;
import com.barbearia.api.model.Cliente;
import com.barbearia.api.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

   private final ClienteRepository repository;

   @Transactional
   public ClienteResponseDTO salvarNovo(ClienteRequestDTO dto){

       // 1. Lógica de Validação: Evita duplicidade antes de bater no erro do banco
       if(repository.existsByEmail(dto.email())){
           throw new RuntimeException("Este e-mal já está cadastrado no sistema!");
       }

       // 2. Conversão: Transforma o Record (DTO) na Entidade (Domain)
       Cliente cliente = new Cliente();
       cliente.setNome(dto.nome());
       cliente.setEmail(dto.email());
       cliente.setTelefone(dto.telefone());
       cliente.setDataNascimento(dto.dataNascimento());
       cliente.setObservacoes(dto.observacoes());

       // 3. Persistência: O @PrePersist na classe Cliente cuidará do 'criadoEm'
       Cliente salvo = repository.save(cliente);

       // 4. Retorno: Devolve o ResponseDTO com o ID e a Data gerados
       return new ClienteResponseDTO(
               salvo.getId(),
               salvo.getNome(),
               salvo.getEmail(),
               salvo.getCriadoEm()
       );
    }

    public List<ClienteResponseDTO> listarTodos(){
       return repository.findAll().stream().map(cliente -> new ClienteResponseDTO(
               cliente.getId(),
               cliente.getNome(),
               cliente.getEmail(),
               cliente.getCriadoEm()
       ))
               .collect(Collectors.toList());

    }

    public ClienteResponseDTO buscarPorId(Long id){
       Cliente cliente = repository.findById(id)
               .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID " + id));

       return new ClienteResponseDTO(
               cliente.getId(),
               cliente.getNome(),
               cliente.getEmail(),
               cliente.getCriadoEm()
       );
    }

    @Transactional
    public ClienteResponseDTO ataualizar(Long id, ClienteUpdateDTO dto){
       // 1. Busca o cliente ou lança erro se não existir
       Cliente cliente = repository.findById(id)
               .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        // 2. Atualiza apenas os campos permitidos
        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        cliente.setDataNascimento(dto.dataNascimento());
        cliente.setObservacoes(dto.observacoes());

        // 3. Salva (O Hibernate entende que é um UPDATE porque o objeto já tem ID)
        Cliente atualizado = repository.save(cliente);

        // 4. Retorna o ResponseDTO (padronização)
        return new ClienteResponseDTO(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getEmail(),
                atualizado.getCriadoEm()
        );
    }

    @Transactional
    public void excluir(Long id){
       if (!repository.existsById(id)){
           throw new RuntimeException("Não é possível excluir: Cliente não encontrado com o ID: " + id);
       }
       repository.deleteById(id);
    }
}
