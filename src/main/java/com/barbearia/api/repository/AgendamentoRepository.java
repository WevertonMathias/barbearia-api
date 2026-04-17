package com.barbearia.api.repository;

import com.barbearia.api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    // Aqui também temos todos os métodos CRUD básicos.
}