package com.barbearia.api.repository;

import com.barbearia.api.model.Agendamento;
import com.barbearia.api.model.Barbeiro;
import com.barbearia.api.model.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByBarbeiroIdAndDataAndHoraInicio(Long barbeiroId, LocalDate data, LocalTime horaInicio);

    List<Agendamento> findByData(LocalDate date);

    List<Agendamento> findByBarbeiroId(Long barbeiroId);

    List<Agendamento> findByStatusAndData(StatusAgendamento status, LocalDate data);

}