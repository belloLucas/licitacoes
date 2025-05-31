package com.bellolucas.backend.service;

import com.bellolucas.backend.model.Licitacao;
import com.bellolucas.backend.repository.LicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicitacaoService {
    @Autowired
    private LicitacaoRepository licitacaoRepository;

    public List<Licitacao> list() {
        return licitacaoRepository.findAll();
    }

    public Licitacao listByUasgCodigo(String uasgCodigo) {
        return licitacaoRepository.findByUasgCodigo(uasgCodigo);
    }

    public Licitacao listByNumeroPregao(String numeroPregao) {
        return licitacaoRepository.findByNumeroPregao(numeroPregao);
    }
}
