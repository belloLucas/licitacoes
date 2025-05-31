package com.bellolucas.backend.dto;

public record LicitacaoDTO(
        String codigoUasg,
        String numeroPregao,
        String objeto,
        String dataEntregaProposta
) {
}
