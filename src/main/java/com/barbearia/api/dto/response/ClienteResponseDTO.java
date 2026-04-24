package com.barbearia.api.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String telefone,
        String email,
        LocalDate dataNascimento,
        String observacoes,
        LocalDateTime criadoEm
) {
}
