package com.barbearia.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoRequestDTO(
        @NotNull(message = "ID do cliente não pode ser nulo")
        Long clienteId,

        @NotNull(message = "ID do barbeiro não pode ser nulo")
        Long barbeiroId,

        @NotNull
        @Future
        LocalDate data,

        @NotNull
        LocalTime horaInicio,

        @NotNull
        LocalTime horaFim,

        @NotNull
        BigDecimal valorServico
) {}
