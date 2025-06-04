package com.bellolucas.backend.service;

import com.bellolucas.backend.model.Licitacao;
import com.bellolucas.backend.repository.LicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicitacaoService {
    @Autowired
    private LicitacaoRepository licitacaoRepository;

    public Page<Licitacao> list(Pageable pageable) {
        return licitacaoRepository.findAll(pageable);
    }

    public Page<Licitacao> listByUasgCodigo(String uasgCodigo, Pageable pageable) {
        return licitacaoRepository.findByUasgCodigo(uasgCodigo, pageable);
    }

    public Page<Licitacao> listByNumeroPregao(String numeroPregao, Pageable pageable) {
        return licitacaoRepository.findByNumeroPregao(numeroPregao, pageable);
    }
}
