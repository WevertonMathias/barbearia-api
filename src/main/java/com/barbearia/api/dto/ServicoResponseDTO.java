package com.barbearia.api.dto;

import com.barbearia.api.model.Servico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServicoResponseDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Integer tempoEstimado,
        LocalDateTime criadoAt
) {
    public ServicoResponseDTO(Servico servico){
        this(
                servico.getId(),
                servico.getNome(),
                servico.getDescricao(),
                servico.getPreco(),
                servico.getTempoEstimado(),
                servico.getCriadoAt()
        );
    }
}
