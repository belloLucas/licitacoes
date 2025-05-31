package com.bellolucas.backend.repository;

import com.bellolucas.backend.model.Licitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicitacaoRepository extends JpaRepository<Licitacao, Long> {
    Licitacao findByUasgCodigo(String uasgCodigo);
    Licitacao findByNumeroPregao(String numeroPregao);
}
