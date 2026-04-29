package com.barbearia.api.dto;

import com.barbearia.api.model.Barbeiro;

import java.time.LocalDateTime;

public record BarbeiroResponseDTO(
        Long id,
        String nome,
        Double percentualComissao,
        Boolean ativo,
        LocalDateTime criadoEm

) {
    public BarbeiroResponseDTO(Barbeiro barbeiro){
        this(
                barbeiro.getId(),
                barbeiro.getNome(),
                barbeiro.getPercentualComissao(),
                barbeiro.getAtivo(),
                barbeiro.getCriadoEm()
        );
    }
}
