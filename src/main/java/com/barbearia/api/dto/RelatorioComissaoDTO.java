package com.barbearia.api.dto;

import java.util.List;

public record RelatorioComissaoDTO(
        Double totalFaturado,
        Double totalComissao,
        Integer quantidadeServicos
) {
}
