package com.barbearia.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "servicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(name = "tempo_estimado")
    private Integer tempoEstimado;

    @Column(name = "criado_at")
    private LocalDateTime criadoAt;

    @PrePersist
    public void prePersist(){
        this.criadoAt = LocalDateTime.now();
    }

}
