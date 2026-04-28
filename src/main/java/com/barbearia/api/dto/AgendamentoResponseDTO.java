package com.barbearia.api.dto;

import com.barbearia.api.model.StatusAgendamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoResponseDTO(
        Long id,
        String clienteNome,
        String barbeiroNome,
        LocalDate data,
        LocalTime horaInicio,
        BigDecimal valorServico,
        StatusAgendamento status
) {}
