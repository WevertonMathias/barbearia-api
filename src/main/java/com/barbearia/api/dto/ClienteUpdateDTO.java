package com.barbearia.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ClienteUpdateDTO(
        @NotBlank(message = "O nome não pode estar vazio")
        String nome,

        String telefone,

        LocalDate dataNascimento,

        String observacoes
) {
}
