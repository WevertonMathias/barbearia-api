package com.barbearia.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "clientes")
@Data // O Lombok já cria Getter, Setter, toString, etc.
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String telefone;

    @Column(unique = true, nullable = false) // Melhoria: E-mail único e obrigatório
    private String email;

    @Column(name = "data_nascimento")
    private java.time.LocalDate dataNascimento;

    private String observacoes;

    @Column(name = "criado_em", updatable = false) // Removi o insertable=false para o @PrePersist funcionar
    private java.time.LocalDateTime criadoEm;

    // Melhoria: O banco preenche a data de criação sozinho
    @PrePersist
    protected void onCreate() {
        this.criadoEm = java.time.LocalDateTime.now();
    }
}
