package com.barbearia.api.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "cliente") // Ajustado para o singular igual ao seu SQL
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String email;

    @Column(name = "data_nascimento")
    private java.time.LocalDate dataNascimento;

    private String observacoes;

    @Column(name = "criado_em", insertable = false, updatable = false)
    private java.time.LocalDateTime criadoEm;
}
