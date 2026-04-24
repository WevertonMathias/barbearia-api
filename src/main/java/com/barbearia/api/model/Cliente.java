package com.barbearia.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "cliente")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;

    @Column(unique = true)
    private String email;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String observacoes;


    @Column(name = "criado_em", insertable = false, updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    public void prePersist(){
        this.criadoEm = LocalDateTime.now();
    }
}
