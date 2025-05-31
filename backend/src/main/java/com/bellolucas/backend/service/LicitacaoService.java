package com.bellolucas.backend.service;

import com.bellolucas.backend.model.Licitacao;
import com.bellolucas.backend.repository.LicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicitacaoService {
    @Autowired
    private LicitacaoRepository repository;

    public List<Licitacao> list() {
        return repository.findAll();
    }

    public Licitacao listByUasgCodigo(String uasgCodigo) {
        return repository.listByUasgCodigo(uasgCodigo);
    }

    public Licitacao listByNumeroPregao(String numeroPregao) {
        return repository.listByNumeroPregao(numeroPregao);
    }
}
