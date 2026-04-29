package com.barbearia.api.dto;

import com.barbearia.api.model.Agendamento;
import com.barbearia.api.model.StatusAgendamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AgendamentoResponseDTO(
        Long id,
        String nomeCliente,
        String nomeBarbeiro,
        LocalDate data,
        LocalTime horaInicio,
        LocalTime horaFim,
        BigDecimal valorServico,
        StatusAgendamento status,
        LocalDateTime criadoAt
) {
    public AgendamentoResponseDTO(Agendamento agendamento){
        this(
                agendamento.getId(),
                agendamento.getCliente().getNome(),
                agendamento.getBarbeiro().getNome(),
                agendamento.getData(),
                agendamento.getHoraInicio(),
                agendamento.getHoraFim(),
                agendamento.getValorServico(),
                agendamento.getStatus(),
                agendamento.getCriadoAt()
        );
    }
}
