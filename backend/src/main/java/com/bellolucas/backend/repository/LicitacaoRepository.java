package com.bellolucas.backend.repository;

import com.bellolucas.backend.model.Licitacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicitacaoRepository extends JpaRepository<Licitacao, Long> {
    Licitacao listByUasgCodigo(String uasgCodigo);
    Licitacao listByNumeroPregao(String numeroPregao);
}
