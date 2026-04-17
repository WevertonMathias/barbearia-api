package com.barbearia.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "barbeiro")
@Data
public class Barbeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "percentual_comissao")
    private Double percentualComissao;

    private Boolean ativo = true;

    @Column(name = "criado_em", insertable = false, updatable = false)
    private java.time.LocalDateTime criadoEm;
}
