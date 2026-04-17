package com.barbearia.api.repository;

import com.barbearia.api.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
    // O JpaRepository já nos dá: save(), findById(), findAll(), deleteById(), etc.
}