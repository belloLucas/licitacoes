package com.bellolucas.backend.repository;

import com.bellolucas.backend.model.Licitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicitacaoRepository extends JpaRepository<Licitacao, Long> {
    List<Licitacao> findByUasgCodigo(String uasgCodigo);

    List<Licitacao> findByNumeroPregao(String numeroPregao);

    boolean existsByUasgCodigoAndNumeroPregao(String uasgCodigo, String numeroPregao);
}
