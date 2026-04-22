package com.barbearia.api.dto;

import java.math.BigDecimal;
import java.util.List;

public record RelatorioComissaoDTO(
        BigDecimal totalFaturado,
        BigDecimal totalComissao,
        Integer quantidadeServicos
) {
}
