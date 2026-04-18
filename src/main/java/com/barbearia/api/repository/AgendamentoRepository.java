package com.barbearia.api.repository;

import com.barbearia.api.model.Agendamento;
import com.barbearia.api.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    Optional<Agendamento> findByBarbeiroAndDataAndHoraInicio(Barbeiro barbeiro, LocalDate date, LocalTime horarioInicico);

    List<Agendamento> findByData(LocalDate date);

    List<Agendamento> findByBarbeiroId(Long barbeiroId);
}