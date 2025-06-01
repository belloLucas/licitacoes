package com.bellolucas.backend.dto;

public record LicitacaoDTO(
        String codigoUasg,
        String numeroPregao,
        String descricao,
        String edital,
        String endereco,
        String telefone,
        String fax,
        String dataEntregaProposta
) {
}
