package com.bellolucas.backend.dto;

import com.bellolucas.backend.model.Licitacao;

import java.time.format.DateTimeFormatter;

public record LicitacaoDTO(
        Long id,
        String codigoUasg,
        String numeroPregao,
        String descricao,
        String edital,
        String endereco,
        String telefone,
        String fax,
        String dataEntregaProposta,
        String itemsUrl
) {
    public LicitacaoDTO(Licitacao licitacao) {
        this(
                licitacao.getId(),
                licitacao.getUasgCodigo(),
                licitacao.getNumeroPregao(),
                licitacao.getDescricao(),
                licitacao.getEdital(),
                licitacao.getEndereco(),
                licitacao.getTelefone(),
                licitacao.getFax(),
                licitacao.getDataAbertura() != null ? licitacao.getDataAbertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null,
                licitacao.getItensUrl()
        );
    }
}
