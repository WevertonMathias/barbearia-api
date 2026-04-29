package com.barbearia.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BarbeiroResquestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "O percentual de comissão é obrigatório")
        Double percentualComissao
) {
}
