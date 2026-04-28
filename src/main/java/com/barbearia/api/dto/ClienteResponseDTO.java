package com.barbearia.api.dto;

import java.time.LocalDateTime;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String email,
        LocalDateTime criadoEm
) {}
