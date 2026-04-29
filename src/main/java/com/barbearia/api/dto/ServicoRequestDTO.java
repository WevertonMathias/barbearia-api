package com.barbearia.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServicoRequestDTO(
        @NotBlank String nome,
        String descricao,
        @NotNull @Positive BigDecimal preco,
        @NotNull @Positive LocalDateTime tempoEstimado
        ) {}
