package com.bellolucas.backend.repository;

import com.bellolucas.backend.model.Licitacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicitacaoRepository extends JpaRepository<Licitacao, Long> {
    Page<Licitacao> findByUasgCodigo(String uasgCodigo, Pageable pageable);

    Page<Licitacao> findByNumeroPregao(String numeroPregao, Pageable pageable);

    boolean existsByUasgCodigoAndNumeroPregao(String uasgCodigo, String numeroPregao);
}
