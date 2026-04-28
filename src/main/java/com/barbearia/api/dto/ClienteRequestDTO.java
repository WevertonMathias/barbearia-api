package com.barbearia.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record ClienteRequestDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "O telefone é obrigatório")
        String telefone,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Por favor, insira um e-mail válido")
        String email,

        @Past(message = "A data de nascimento deve ser uma data passada")
        LocalDate dataNascimento, // Mudamos para LocalDate para facilitar o uso no banco

        String observacoes
) {}